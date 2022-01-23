/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package io.github.prolobjectlink.prolog;

import static io.github.prolobjectlink.prolog.PrologTermType.ATOM_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.DOUBLE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FALSE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FLOAT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.INTEGER_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LIST_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LONG_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.NIL_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.TRUE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.VARIABLE_TYPE;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Partial implementation of {@link PrologJavaConverter} interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractJavaConverter 
		implements PrologJavaConverter {

	private final PrologProvider provider;

	protected AbstractJavaConverter(PrologProvider provider) {
		this.provider = provider;
	}

	public final boolean containQuotes(String functor) {
		if (functor != null && !functor.isEmpty()) {
			return functor.startsWith("\'") && functor.endsWith("\'");
		}
		return false;
	}

	public final String removeQuotes(String functor) {
		if (containQuotes(functor)) {
			return functor.substring(1, functor.length() - 1);
		}
		return functor;
	}

	public final Object toObject(PrologTerm prologTerm) {
		if (prologTerm == null) {
			return null;
		}

		switch (prologTerm.getType()) {
		case NIL_TYPE:
			return null;
		case TRUE_TYPE:
			return true;
		case FALSE_TYPE:
			return false;
		case ATOM_TYPE:
			return removeQuotes(prologTerm.getFunctor());
		case INTEGER_TYPE:
			return ((PrologInteger) prologTerm).getIntegerValue();
		case FLOAT_TYPE:
			return ((PrologFloat) prologTerm).getFloatValue();
		case LONG_TYPE:
			return ((PrologLong) prologTerm).getLongValue();
		case DOUBLE_TYPE:
			return ((PrologDouble) prologTerm).getDoubleValue();
		case VARIABLE_TYPE:
			return prologTerm;
		case LIST_TYPE:
			return toObjectsArray(prologTerm.getArguments());
		case STRUCTURE_TYPE:
			return prologTerm;
		case OBJECT_TYPE:
			return prologTerm.getObject();
		default:
			throw new UnknownTermError(prologTerm);
		}
	}

	public final Object[] toObjectsArray(PrologTerm[] terms) {
		Object array = Array.newInstance(Object.class, terms.length);
		for (int i = 0; i < terms.length; i++) {
			Array.set(array, i, toObject(terms[i]));
		}
		return (Object[]) array;
	}

	public final PrologTerm toTerm(Object object) {
		// null pointer
		if (object == null) {
			return provider.prologNil();
		}

		// string data type
		else if (object instanceof String) {
			return provider.newAtom("" + (String) object + "");
		}

		// primitives and wrappers data types
		else if (object.getClass() == char.class || object instanceof Character) {
			return provider.newAtom("" + (String) object + "");
		} else if (object.getClass() == byte.class || object instanceof Byte) {
			return provider.newInteger((Integer) object);
		} else if (object.getClass() == short.class || object instanceof Short) {
			return provider.newInteger((Integer) object);
		} else if (object.getClass() == boolean.class || object instanceof Boolean) {
			return (Boolean) object ? provider.prologTrue() : provider.prologFalse();
		} else if (object.getClass() == int.class || object instanceof Integer) {
			return provider.newInteger((Integer) object);
		} else if (object.getClass() == float.class || object instanceof Float) {
			return provider.newFloat((Float) object);
		} else if (object.getClass() == long.class || object instanceof Long) {
			return provider.newLong((Long) object);
		} else if (object.getClass() == double.class || object instanceof Double) {
			return provider.newDouble((Double) object);
		}

		// object array
		else if (object instanceof Object[]) {
			return provider.newList(toTermsArray((Object[]) object));
		}

		throw new UnknownTermError(object);

	}

	public final PrologTerm[] toTermsArray(Object[] objects) {
		PrologTerm[] terms = new PrologTerm[objects.length];
		for (int i = 0; i < objects.length; i++) {
			terms[i] = toTerm(objects[i]);
		}
		return terms;
	}

	public final List<Map<String, Object>> toObjectMaps(Map<String, PrologTerm>[] maps) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(maps.length);
		for (Map<String, PrologTerm> map : maps) {
			list.add(toObjectMap(map));
		}
		return list;
	}

	public final Map<String, Object> toObjectMap(Map<String, PrologTerm> map) {
		Map<String, Object> objects = new HashMap<String, Object>(map.size());
		for (Entry<String, PrologTerm> entry : map.entrySet()) {
			objects.put(entry.getKey(), toObject(entry.getValue()));
		}
		return objects;
	}

	public final List<Object> toObjectList(PrologTerm[] terms) {
		List<Object> list = new ArrayList<Object>(terms.length);
		for (PrologTerm prologTerm : terms) {
			list.add(toObject(prologTerm));
		}
		return list;
	}

	public final List<List<Object>> toObjectLists(PrologTerm[][] terms) {
		List<List<Object>> list = new ArrayList<List<Object>>(terms.length);
		for (PrologTerm[] prologTerms : terms) {
			list.add(toObjectList(prologTerms));
		}
		return list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractJavaConverter other = (AbstractJavaConverter) obj;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider)) {
			return false;
		}
		return true;
	}

}
