/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.prolobjectlink.prolog;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.prolobjectlink.AbstractWrapper;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractConverter<T> extends AbstractWrapper implements PrologConverter<T> {

	public static final String SIMPLE_ATOM_REGEX = ".|[a-z][A-Za-z0-9_]*";
	private static final String IMPOSIBLE_CONVERT = "Impossible convert '";
	private static final String FROM = "' from '";
	private static final String TO = "' to '";

	protected final HashMap<String, PrologVariable> sharedVariables;
	protected final HashMap<String, T> sharedPrologVariables;
	protected final PrologProvider provider;

	protected AbstractConverter() {
		sharedVariables = new HashMap<String, PrologVariable>();
		sharedPrologVariables = new HashMap<String, T>();
		provider = createProvider();
	}

	public final boolean isQuoted(String functor) {
		if (!functor.isEmpty()) {
			char beginChar = functor.charAt(0);
			char endChar = functor.charAt(functor.length() - 1);
			return beginChar == '\'' && endChar == '\'';
		}
		return false;
	}

	public final String requireQuoted(String functor) {
		if (!functor.matches(SIMPLE_ATOM_REGEX) && !isQuoted(functor)) {
			return "'" + functor + "'";
		}
		return functor;
	}

	public final String removeQuoted(String functor) {
		if (isQuoted(functor)) {
			String newFunctor = "";
			newFunctor += functor.substring(1, functor.length() - 1);
			return newFunctor;
		}
		return functor;
	}

	public final PrologTerm[] toTermArray(T[] terms) {
		PrologTerm[] iTerms = new PrologTerm[terms.length];
		for (int i = 0; i < terms.length; i++) {
			iTerms[i] = toTerm(terms[i]);
		}
		return iTerms;
	}

	public final PrologTerm[][] toTermMatrix(T[][] terms) {
		int n = terms.length;
		int m = terms[0].length;
		PrologTerm[][] iTerms = new PrologTerm[n][m];
		for (int i = 0; i < n; i++) {
			iTerms[i] = toTermArray(terms[i]);
		}
		return iTerms;
	}

	public final Map<String, PrologTerm> toTermMap(Map<String, T> map) {
		Map<String, PrologTerm> solutionMap = new HashMap<String, PrologTerm>(map.size());
		Set<String> keys = map.keySet();
		for (String key : keys) {
			solutionMap.put(key, toTerm(map.get(key)));
		}
		return solutionMap;
	}

	public final Map<String, PrologTerm>[] toTermMapArray(Map<String, T>[] map) {
		Map<String, PrologTerm>[] solutions = new Map[map.length];
		for (int i = 0; i < map.length; i++) {
			solutions[i] = toTermMap(map[i]);
		}
		return solutions;
	}

	public final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		Class<T> clazz = getGenericClass();
		if (clazz != null && clazz.isAssignableFrom(o.getClass())) {
			PrologTerm term = toTerm((T) o);
			if (from.isAssignableFrom(term.getClass())) {
				return from.cast(term);
			}
		}
		throw new RuntimeError(

				IMPOSIBLE_CONVERT + o + FROM + from + "'"

		);
	}

	public final <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from) {
		Class<T> clazz = getGenericClass();
		Class<?> cType = os.getClass().getComponentType();
		if (clazz != null && clazz.isAssignableFrom(cType)) {
			PrologTerm[] terms = toTermArray((T[]) os);
			if (from.isAssignableFrom(terms.getClass())) {
				return from.cast(terms);
			}
		}
		throw new RuntimeError(

				IMPOSIBLE_CONVERT +

						Arrays.toString(os) +

						FROM + from + "'"

		);
	}

	public final <K extends PrologTerm> K[][] toTermMatrix(Object[][] oss, Class<K[][]> from) {
		Class<T> clazz = getGenericClass();
		Class<?> cType = oss.getClass().getComponentType();
		Class<?> c = Array.newInstance(clazz, 0).getClass();
		if (c.isAssignableFrom(cType)) {
			PrologTerm[][] terms = toTermMatrix((T[][]) oss);
			if (from.isAssignableFrom(terms.getClass())) {
				return from.cast(terms);
			}
		}
		throw new RuntimeError(

				IMPOSIBLE_CONVERT +

						Arrays.toString(oss) +

						FROM + from + "'"

		);
	}

	public final <K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map,
			Class<K> from) {
		Map<String, PrologTerm> solutionMap = new HashMap<String, PrologTerm>(map.size());
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Object o = map.get(key);
			PrologTerm term = toTerm(o, from);
			solutionMap.put(key, term);
		}
		return solutionMap;
	}

	public final <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(Map<String, V>[] map,
			Class<K> from) {
		Map<String, PrologTerm>[] solutions = new Map[map.length];
		for (int i = 0; i < map.length; i++) {
			solutions[i] = toTermMap(map[i], from);
		}
		return solutions;
	}

	public final <K> K fromTerm(PrologTerm term, Class<K> to) {
		T t = fromTerm(term);
		if (to.isAssignableFrom(t.getClass())) {
			return to.cast(t);
		}
		throw new RuntimeError(

				IMPOSIBLE_CONVERT + term + TO + to + "'"

		);
	}

	public final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		T[] ts = fromTermArray(terms);
		if (to.isAssignableFrom(ts.getClass())) {
			return to.cast(ts);
		}
		throw new RuntimeError(

				IMPOSIBLE_CONVERT + terms + TO + to + "'"

		);
	}

	public final <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to) {
		T t = fromTerm(head, body);
		if (to.isAssignableFrom(t.getClass())) {
			return to.cast(t);
		}
		throw new RuntimeError(

				IMPOSIBLE_CONVERT +

						head + " and " + Arrays.toString(body) +

						TO + to + "'"

		);
	}

	public final Class<T> getGenericClass() {
		Class<T> templateClass = null;
		Type[] generics = getClass().getGenericInterfaces();
		if (generics.length == 1 && generics[0] instanceof ParameterizedType) {
			ParameterizedType parameterized = (ParameterizedType) generics[0];
			Type type = parameterized.getActualTypeArguments()[0];
			if (type instanceof Class<?>) {
				templateClass = (Class<T>) type;
			}
		}
		return templateClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sharedPrologVariables == null) ? 0 : sharedPrologVariables.hashCode());
		result = prime * result + ((sharedVariables == null) ? 0 : sharedVariables.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractConverter<?> other = (AbstractConverter<?>) obj;
		if (sharedPrologVariables == null) {
			if (other.sharedPrologVariables != null)
				return false;
		} else if (!sharedPrologVariables.equals(other.sharedPrologVariables)) {
			return false;
		}
		if (sharedVariables == null) {
			if (other.sharedVariables != null)
				return false;
		} else if (!sharedVariables.equals(other.sharedVariables)) {
			return false;
		}
		return true;
	}

}
