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
package io.github.prolobjectlink.prolog;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Partial implementation of {@link PrologProvider}
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractProvider implements PrologProvider {

	protected final PrologConverter<?> converter;

	public AbstractProvider(PrologConverter<?> converter) {
		this.converter = converter;
	}

	public final PrologList parseList(String stringList) {
		PrologTerm term = parseTerm(stringList);
		checkListType(term);
		return (PrologList) term;
	}

	public final PrologClause parseClause(String stringClause) {
		PrologEngine engine = newEngine();
		engine.asserta(stringClause);
		return engine.iterator().next();
	}

	public final PrologStructure parseStructure(String stringStructure) {
		PrologTerm term = parseTerm(stringStructure);
		checkStructureType(term);
		return (PrologStructure) term;
	}

	public final Set<PrologClause> parseProgram(String file) {
		return newEngine(file).getProgramClauses();
	}

	public final Set<PrologClause> parseProgram(File in) {
		return parseProgram(in.getAbsolutePath());
	}

	public final PrologFloat newFloat() {
		return newFloat(0F);
	}

	public final PrologDouble newDouble() {
		return newDouble(0D);
	}

	public final PrologInteger newInteger() {
		return newInteger(0);
	}

	public final PrologLong newLong() {
		return newLong(0L);
	}

	public final PrologList newList(PrologTerm head) {
		return newList(new PrologTerm[] { head });
	}

	public final PrologList newList(Object head) {
		return newList(getJavaConverter().toTerm(head));
	}

	public final PrologList newList(Object[] arguments) {
		return newList(getJavaConverter().toTermsArray(arguments));
	}

	public final PrologList newList(Object head, Object tail) {
		PrologJavaConverter transformer = getJavaConverter();
		PrologTerm headTerm = transformer.toTerm(head);
		PrologTerm tailTerm = transformer.toTerm(tail);
		return newList(headTerm, tailTerm);
	}

	public final PrologList newList(Object[] arguments, Object tail) {
		PrologJavaConverter transformer = getJavaConverter();
		PrologTerm[] array = transformer.toTermsArray(arguments);
		PrologTerm tailTerm = transformer.toTerm(tail);
		return newList(array, tailTerm);
	}

	public final PrologTerm newStructure(String functor, Object... arguments) {
		PrologJavaConverter transformer = getJavaConverter();
		PrologTerm[] parameters = transformer.toTermsArray(arguments);
		return newStructure(functor, parameters);
	}

	public final PrologTerm newStructure(Object left, String operator, Object right) {
		PrologJavaConverter transformer = getJavaConverter();
		PrologTerm leftTerm = transformer.toTerm(left);
		PrologTerm rightTerm = transformer.toTerm(right);
		return newStructure(leftTerm, operator, rightTerm);
	}

	public final PrologTerm newEntry(PrologTerm key, PrologTerm value) {
		return new PrologEntry(this, key, value);
	}

	public final PrologTerm newEntry(Object key, Object value) {
		PrologJavaConverter transformer = getJavaConverter();
		PrologTerm keyTerm = transformer.toTerm(key);
		PrologTerm valueTerm = transformer.toTerm(value);
		return new PrologEntry(this, keyTerm, valueTerm);
	}

	public final PrologTerm newMap(Map<PrologTerm, PrologTerm> map) {
		return new PrologMap(this, map);
	}

	public final PrologTerm newMap(int initialCapacity) {
		return new PrologMap(this, initialCapacity);
	}

	public final PrologTerm newMap() {
		return new PrologMap(this);
	}

	public final <T extends PrologTerm> T cast(PrologTerm term, Class<T> type) {
		return type.cast(term);
	}

	public final <T extends PrologTerm> T cast(PrologTerm term) {
		return (T) term;
	}

	public final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		return converter.toTerm(o, from);
	}

	public final <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from) {
		return converter.toTermArray(os, from);
	}

	public final <K extends PrologTerm> K[][] toTermMatrix(Object[][] oss, Class<K[][]> from) {
		return converter.toTermMatrix(oss, from);
	}

	public final <K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map,
			Class<K> from) {
		return converter.toTermMap(map, from);
	}

	public final <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(Map<String, V>[] map,
			Class<K> from) {
		return converter.toTermMapArray(map, from);
	}

	public final PrologConverter<?> getConverter() {
		return converter;
	}

	public final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return converter.fromTerm(term, to);
	}

	public final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		return converter.fromTermArray(terms, to);
	}

	public final <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to) {
		return converter.fromTerm(head, body, to);
	}

	public final PrologParser getParser() {
		return this;
	}

	public final String getVersion() {
		return newEngine().getVersion();
	}

	public final String getName() {
		return newEngine().getName();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((converter == null) ? 0 : converter.hashCode());
		return result;
	}

	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		AbstractProvider other = (AbstractProvider) object;
		if (converter == null) {
			if (other.converter != null)
				return false;
		} else if (!converter.equals(other.converter)) {
			return false;
		}
		return true;
	}

	public abstract String toString();

	private final void checkListType(PrologTerm term) {
		if (!term.isList()) {
			throw new ListExpectedError(term);
		}
	}

	private final void checkStructureType(PrologTerm term) {
		if (!term.isStructure()) {
			throw new StructureExpectedError(term);
		}
	}

	public final int size() {
		return getJavaConverter().size();
	}

	public final boolean isEmpty() {
		return getJavaConverter().isEmpty();
	}

	public final boolean containsKey(Object key) {
		return getJavaConverter().containsKey(key);
	}

	public final boolean containsValue(Object value) {
		return getJavaConverter().containsValue(value);
	}

	public final PrologMapping<?> get(Object key) {
		return getJavaConverter().get(key);
	}

	public final PrologMapping<?> put(Class<?> key, PrologMapping<?> value) {
		return getJavaConverter().put(key, value);
	}

	public final PrologMapping<?> remove(Object key) {
		return getJavaConverter().remove(key);
	}

	public final void putAll(Map<? extends Class<?>, ? extends PrologMapping<?>> m) {
		getJavaConverter().putAll(m);
	}

	public final void clear() {
		getJavaConverter().clear();
	}

	public final Set<Class<?>> keySet() {
		return getJavaConverter().keySet();
	}

	public final Collection<PrologMapping<?>> values() {
		return getJavaConverter().values();
	}

	public final Set<Entry<Class<?>, PrologMapping<?>>> entrySet() {
		return getJavaConverter().entrySet();
	}

	public final void register(PrologMapping<?> mapping) {
		put(mapping.getType(), mapping);
	}

	public final PrologTerm getTerm(PrologMapping<?> mapping) {
		return mapping.toTerm(this);
	}

	public final <O> PrologTerm getTerm(PrologMapping<?> mapping, O o) {
		return mapping.toTerm(this, o);
	}

	public void unregister(PrologMapping<?> mapping) {
		remove(mapping.getType());
	}

}
