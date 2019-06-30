/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Partial implementation of Prolog converter interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractConverter<T> implements PrologConverter<T> {

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

	protected final PrologLogger getLogger() {
		return provider.getLogger();
	}

	private final boolean isQuoted(String functor) {
		if (!functor.isEmpty()) {
			char beginChar = functor.charAt(0);
			char endChar = functor.charAt(functor.length() - 1);
			return beginChar == '\'' && endChar == '\'';
		}
		return false;
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
		throw new PrologError(

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
		throw new PrologError(

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
		throw new PrologError(

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
		throw new PrologError(

				IMPOSIBLE_CONVERT + term + TO + to + "'"

		);
	}

	public final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		T[] ts = fromTermArray(terms);
		if (to.isAssignableFrom(ts.getClass())) {
			return to.cast(ts);
		}
		throw new PrologError(

				IMPOSIBLE_CONVERT + Arrays.toString(terms) + TO + to + "'"

		);
	}

	public final <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to) {
		T t = fromTerm(head, body);
		if (to.isAssignableFrom(t.getClass())) {
			return to.cast(t);
		}
		throw new PrologError(

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
