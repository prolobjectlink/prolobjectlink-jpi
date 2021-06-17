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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
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
	private static final Set<String> ISO_IEC_BUILT_INS;

	static {

		ISO_IEC_BUILT_INS = new HashSet<String>();

		// 7.4 directives
		ISO_IEC_BUILT_INS.add("dynamic");
		ISO_IEC_BUILT_INS.add("include");
		ISO_IEC_BUILT_INS.add("multifile");
		ISO_IEC_BUILT_INS.add("set_prolog_flag");
		ISO_IEC_BUILT_INS.add("ensure_loaded");
		ISO_IEC_BUILT_INS.add("discontiguous");
		ISO_IEC_BUILT_INS.add("current_prolog_flag");
		ISO_IEC_BUILT_INS.add("initialization");

		// built-ins predicates
		ISO_IEC_BUILT_INS.add("nil");
		ISO_IEC_BUILT_INS.add("fail");
		ISO_IEC_BUILT_INS.add("true");
		ISO_IEC_BUILT_INS.add("false");
		ISO_IEC_BUILT_INS.add(PrologBuiltin.THROW);

		// 8.2 term unification
		ISO_IEC_BUILT_INS.add(PrologBuiltin.UNIFY_WITH_OCCURS_CHECK);

		// 8.3 type testing
		ISO_IEC_BUILT_INS.add(PrologBuiltin.VAR);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ATOM);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.FLOAT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.NUMBER);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.NONVAR);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.OBJECT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.GROUND);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ATOMIC);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.INTEGER);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.COMPOUND);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CALLABLE);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CYCLIC_TERM);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ACYCLIC_TERM);

		// 8.4 term comparison
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SORT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.COMPARE);

		// 8.5 term creation and decomposition
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ARG);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.FUNCTOR);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.COPY_TERM);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.TERM_VARIABLES);

		// 8.6 arithmetics evaluation (operator)
		// 8.7 arithmetic comparison (operator)

		// 8.8 clause retrieval and information
		ISO_IEC_BUILT_INS.add("clause");
		ISO_IEC_BUILT_INS.add("current_predicate");

		// 8.9 clause creation and destruction
		ISO_IEC_BUILT_INS.add("abolish");
		ISO_IEC_BUILT_INS.add("asserta");
		ISO_IEC_BUILT_INS.add("assertz");
		ISO_IEC_BUILT_INS.add("retract");

		// 8.10 All solutions
		ISO_IEC_BUILT_INS.add(PrologBuiltin.BAGOF);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SETOF);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.FINDALL);

		// 8.11 Stream Selection and Control
		ISO_IEC_BUILT_INS.add(PrologBuiltin.OPEN);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CLOSE);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SET_INPUT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SET_OUTPUT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CURRENT_INPUT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CURRENT_OUTPUT);

		// 8.12 character input/output
		// 8.13 byte input/output

		// 8.14 Term input/output
		ISO_IEC_BUILT_INS.add(PrologBuiltin.NL);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.READ);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.WRITE);

		// 8.15 logic and control
		ISO_IEC_BUILT_INS.add("call");
		ISO_IEC_BUILT_INS.add("once");
		ISO_IEC_BUILT_INS.add("repeat");

		// 8.16 atomic term processing
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SUB_ATOM);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CHAR_CODE);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ATOM_CHARS);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ATOM_CODES);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ATOM_LENGTH);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ATOM_CONCAT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.NUMBER_CHARS);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.NUMBER_CODES);

		// 8.17 Implementation defined hooks
		ISO_IEC_BUILT_INS.add(PrologBuiltin.OP);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.HALT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CURRENT_OP);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CHAR_CONVERSION);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CURRENT_CHAR_CONVERSION);

		// 9.X Valuable functors
		ISO_IEC_BUILT_INS.add(PrologBuiltin.E);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.PI);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ABS);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.EXP);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.LOG);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SIN);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.COS);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.MAX);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.MIN);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.GCD);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.LCM);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.TAN);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ASIN);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ACOS);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ATAN);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SIGN);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SQRT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CBRT);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.FLOOR);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.ROUND);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.EPSILON);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CEILING);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.TRUNCATE);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.FLOAT_INTEGER_PART);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.FLOAT_FRACTIONAL_PART);

		// non ISO

		// java foreign language
		ISO_IEC_BUILT_INS.add(PrologBuiltin.GET);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SET);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CAST);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.INVOKE);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.INSTANCE_OF);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.NEW_INSTANCE);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.LOAD_LIBRARY);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.OBJECT_CONVERSION);

		// java runtime reflection
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CLASS_OF);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.FIELDS_OF);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.METHODS_OF);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.SUPER_CLASS_OF);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CONSTRUCTORS_OF);

		// runtime statistics
		ISO_IEC_BUILT_INS.add(PrologBuiltin.STATISTICS);
		ISO_IEC_BUILT_INS.add(PrologBuiltin.CURRENT_TIME);

	}

	public AbstractProvider(PrologConverter<?> converter) {
		this.converter = converter;
	}

	public final boolean isCompliant() {
		PrologEngine engine = newEngine();
		Set<PrologIndicator> implemented = engine.getBuiltIns();
		for (String prologIndicator : ISO_IEC_BUILT_INS) {
			if (implemented.contains(prologIndicator)) {
				// TODO Change to check using prolog indicator
				return true;
			}
		}
		return false;
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

	public final Object newObject(String className) {
		Class<?> cls = ReflectionUtil.classForName(className);
		return ReflectionUtil.newInstance(cls);
	}

	public final Object newObject(String className, Object... arguments) {
		Class<?>[] parameterTypes = new Class<?>[arguments.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = arguments[i].getClass();
		}
		Class<?> cls = ReflectionUtil.classForName(className);
		return ReflectionUtil.newInstance(cls, parameterTypes, arguments);
	}

	public final Object newObject(PrologAtom className) {
		return newObject(className.getFunctor());
	}

	public final Object newObject(PrologAtom className, PrologTerm[] arguments) {
		Object[] args = getJavaConverter().toObjectsArray(arguments);
		return newObject(className.getFunctor(), args);
	}

	public final Object getObject(Object reference, String fieldName) {
		Field field = ReflectionUtil.getDeclaredField(reference.getClass(), fieldName);
		return ReflectionUtil.readValue(field, reference);
	}

	public final Object getObject(Object reference, PrologAtom fieldName) {
		return getObject(reference, fieldName.getFunctor());
	}

	public final void setObject(Object reference, String fieldName, Object value) {
		Field field = ReflectionUtil.getDeclaredField(reference.getClass(), fieldName);
		ReflectionUtil.writeValue(field, reference, value);
	}

	public final void setObject(Object reference, PrologAtom fieldName, PrologTerm value) {
		setObject(reference, fieldName.getFunctor(), value);
	}

	public final Object callObject(Object reference, String methodName, Object... arguments) {
		Class<?>[] parameterTypes = new Class<?>[arguments.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = arguments[i].getClass();
		}
		Method method = ReflectionUtil.getDeclaredMethod(reference.getClass(), methodName, parameterTypes);
		return ReflectionUtil.invoke(reference, method, arguments);
	}

	public final Object callObject(Object reference, PrologAtom methodName, PrologTerm... arguments) {
		Object[] args = getJavaConverter().toObjectsArray(arguments);
		return callObject(reference, methodName.getFunctor(), args);
	}

	public final Object callObject(Object reference, String methodName) {
		return callObject(reference, methodName, new Object[0]);
	}

	public final Object callObject(Object reference, PrologAtom methodName) {
		return callObject(reference, methodName.getFunctor());
	}

	/**
	 * Casts a PrologTerm to the class or interface represented by this
	 * {@code Class} object.
	 *
	 * @param term the object to be cast
	 * @param type the class or interface to be casted
	 * @return the PrologTerm after casting, or null if term is null
	 *
	 * @throws ClassCastException if the object is not null and is not assignable to
	 *                            the type T.
	 * @since 1.1
	 */
	protected final <T extends PrologTerm> T cast(PrologTerm term, Class<T> type) {
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
