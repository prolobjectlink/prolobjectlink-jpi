/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */
package org.prolobjectlink.prolog;

import static org.prolobjectlink.prolog.PrologTermType.ATOM_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.DOUBLE_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.EMPTY_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.FALSE_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.FLOAT_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.INTEGER_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.LIST_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.LONG_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.NIL_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.TRUE_TYPE;
import static org.prolobjectlink.prolog.PrologTermType.VARIABLE_TYPE;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractJavaConverter implements PrologJavaConverter {

	protected final PrologProvider provider;

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

	public Object toObject(PrologTerm prologTerm) {
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
		case EMPTY_TYPE:
			return new Object[0];
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

	public PrologTerm toTerm(Object object) {
		// null pointer
		if (object == null) {
			return provider.prologNil();
		}

		// string data type
		else if (object instanceof String) {
			return provider.newAtom("" + (String) object + "");
		}

		// primitives and wrappers data types
		else if (object.getClass() == boolean.class || object instanceof Boolean) {
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

	public PrologTerm[] toTermsArray(Object[] objects) {
		PrologTerm[] terms = new PrologTerm[objects.length];
		for (int i = 0; i < objects.length; i++) {
			terms[i] = toTerm(objects[i]);
		}
		return terms;
	}

	public List<Map<String, Object>> toObjectMaps(Map<String, PrologTerm>[] maps) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(maps.length);
		for (Map<String, PrologTerm> map : maps) {
			list.add(toObjectMap(map));
		}
		return list;
	}

	public Map<String, Object> toObjectMap(Map<String, PrologTerm> map) {
		Map<String, Object> objects = new HashMap<String, Object>(map.size());
		for (Entry<String, PrologTerm> entry : map.entrySet()) {
			objects.put(entry.getKey(), toObject(entry.getValue()));
		}
		return objects;
	}

}
