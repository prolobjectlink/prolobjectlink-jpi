/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
	private static final Set<PrologIndicator> ISO_IEC_BUILT_INS;

	static {

		ISO_IEC_BUILT_INS = new HashSet<PrologIndicator>();

		// 7.4 directives
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("dynamic", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("include", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("multifile", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("set_prolog_flag", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("ensure_loaded", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("discontiguous", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("current_prolog_flag", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("initialization", 1));

		// 7.8 control constructs
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("nil", 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("fail", 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("true", 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("false", 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.THROW, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CATCH, 3));

		// 8.2 term unification
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("=", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("\\=", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("subsume", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.UNIFY_WITH_OCCURS_CHECK, 2));

		// 8.3 type testing
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.VAR, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ATOM, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.FLOAT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.NUMBER, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.NONVAR, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.OBJECT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.GROUND, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ATOMIC, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.INTEGER, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.COMPOUND, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CALLABLE, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CYCLIC_TERM, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ACYCLIC_TERM, 1));

		// 8.4 term comparison
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("@>", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("@<", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("==", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("@>=", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("@=<", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("\\==", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SORT, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.COMPARE, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.KEYSORT, 2));

		// 8.5 term creation and decomposition
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ARG, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.FUNCTOR, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.COPY_TERM, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.TERM_VARIABLES, 2));

		// 8.6 arithmetics evaluation (operator)
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("is", 2));

		// 8.7 arithmetic comparison (operator)
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(">", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("<", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("=<", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(">=", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("=:=", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("=\\=", 2));

		// 8.8 clause retrieval and information ( missing predicate_property/2)
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("clause", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("current_predicate", 2));

		// 8.9 clause creation and destruction
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("abolish", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("asserta", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("assertz", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("retract", 1));

		// 8.10 All solutions
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("forall", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.BAGOF, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SETOF, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.FINDALL, 3));

		// 8.11 Stream Selection and Control
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.OPEN, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CLOSE, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.OPEN, 4));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CLOSE, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SET_INPUT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SET_OUTPUT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CURRENT_INPUT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CURRENT_OUTPUT, 1));

		// 8.12 character input/output
		// 8.13 byte input/output

		// 8.14 Term input/output
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.NL, 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.READ, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.WRITE, 1));

		// 8.15 logic and control
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("call", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("once", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("repeat", 0));

		// 8.16 atomic term processing
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SUB_ATOM, 5));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CHAR_CODE, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ATOM_CHARS, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ATOM_CODES, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ATOM_LENGTH, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ATOM_CONCAT, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.NUMBER_CHARS, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.NUMBER_CODES, 2));

		// 8.17 Implementation defined hooks
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.OP, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.HALT, 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.HALT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CURRENT_OP, 3));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CHAR_CONVERSION, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CURRENT_CHAR_CONVERSION, 2));

		// 9.1 simple arithmetic functors
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ABS, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.EXP, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.LOG, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SQRT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CBRT, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.FLOOR, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ROUND, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.CEILING, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.TRUNCATE, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.FLOAT_INTEGER_PART, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.FLOAT_FRACTIONAL_PART, 1));

		// 9.2 ???

		// 9.3 other arithmetic functors
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.MAX, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.MIN, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.GCD, 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.LCM, 2));

		// 9.4 bitwise functors
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("\\//", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("><", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("/\\", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("<<", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(">>", 2));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("\\/", 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator("//", 2));

		// 9.5 trigonometric functors
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SIN, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.COS, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.TAN, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ASIN, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ACOS, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.ATAN, 1));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.SIGN, 1));

		// 9.6 mathematical constants
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.E, 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.PI, 0));
		ISO_IEC_BUILT_INS.add(new DefaultPrologIndicator(PrologBuiltin.EPSILON, 0));

	}

	public AbstractProvider(PrologConverter<?> converter) {
		this.converter = converter;
	}

	public final boolean isCompliant() {
		PrologEngine engine = newEngine();
		Set<PrologIndicator> implemented = engine.getBuiltIns();
		for (PrologIndicator prologIndicator : ISO_IEC_BUILT_INS) {
			if (implemented.contains(prologIndicator)) {
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

	public final PrologThread newThread(PrologTerm... goals) {
		return new PrologThread(this, goals);
	}

	public final PrologThread newThread(String name, PrologTerm... goals) {
		return new PrologThread(this, name, goals);
	}

	public PrologThread currentThread(PrologTerm... goals) {
		Thread thread = Thread.currentThread();
		return new PrologThread(this, thread, goals);
	}

	public PrologThread joinThreads(PrologThread... threads) {
		throw new UnsupportedOperationException();
	}

	public PrologThread joinThreads(String name, PrologThread... threads) {
		throw new UnsupportedOperationException();
	}

	public PrologThreadPool newThreadPool() {
		return new PrologThreadPool();
	}

	public PrologThreadPool newThreadPool(int parallelismLevel) {
		return new PrologThreadPool(parallelismLevel);
	}

	protected PrologTerm newNamespace() {
		return new PrologNamespace(this);
	}

	protected PrologTerm newNamespace(String path) {
		return new PrologNamespace(this, path);
	}

	protected PrologTerm newNamespace(String parent, PrologNamespace chields) {
		return new PrologNamespace(this, parent, chields);
	}

	public PrologTerm newField(PrologTerm name, PrologTerm type) {
		return new PrologField(this, name, type);
	}

	public PrologTerm newField(String name, String type) {
		PrologTerm oname = toTerm(name, PrologTerm.class);
		PrologTerm otype = toTerm(type, PrologTerm.class);
		return new PrologField(this, oname, otype);
	}

	public PrologTerm newMixin(String name) {
		return new PrologMixin(this, name);
	}

	protected PrologTerm newMixin(String namespace, String name) {
		return new PrologMixin(this, namespace, name);
	}

	protected PrologTerm newMixin(PrologTerm namespace, String name) {
		return new PrologMixin(this, namespace.getFunctor(), name);
	}

	public PrologTerm newMixin(String name, PrologTerm... declarations) {
		PrologMixin pi = new PrologMixin(this, name);
		for (int i = 0; i < declarations.length; i++) {
			pi.addMethod(declarations[i], false, false, false);
		}
		return pi;
	}

	protected PrologTerm newMixin(String namespace, String name, PrologTerm... declarations) {
		PrologMixin pi = new PrologMixin(this, namespace, name);
		for (int i = 0; i < declarations.length; i++) {
			pi.addMethod(declarations[i], false, false, false);
		}
		return pi;
	}

	protected PrologTerm newMixin(PrologTerm namespace, String name, PrologTerm... declarations) {
		PrologMixin pi = new PrologMixin(this, namespace.getFunctor(), name);
		for (int i = 0; i < declarations.length; i++) {
			pi.addMethod(declarations[i], false, false, false);
		}
		return pi;
	}

	public PrologClause newMethod(PrologTerm head) {
		return new PrologMethod(this, head);
	}

	public PrologClause newMethod(PrologTerm head, PrologTerm body) {
		return new PrologMethod(this, head, body);
	}

	public PrologClause newMethod(PrologTerm head, PrologTerm... body) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClause newMethod(PrologTerm head, boolean dynamic, boolean multifile, boolean discontiguous) {
		return new PrologMethod(this, head, dynamic, multifile, discontiguous);
	}

	public PrologClause newMethod(PrologTerm head, PrologTerm body, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		return new PrologMethod(this, head, body, dynamic, multifile, discontiguous);
	}

	public PrologClause newFunction(PrologTerm head, PrologTerm result) {
		return new PrologFunction(this, head, result);
	}

	public PrologClause newFunction(PrologTerm head, PrologTerm result, PrologTerm body) {
		return new PrologFunction(this, head, result, body);
	}

	public PrologClause newFunction(PrologTerm head, PrologTerm result, PrologTerm... body) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClause newFunction(PrologTerm head, Object result) {
		return new PrologFunction(this, head, result);
	}

	public PrologClause newFunction(PrologTerm head, Object result, PrologTerm body) {
		return new PrologFunction(this, head, result, body);
	}

	public PrologClause newFunction(PrologTerm head, Object result, PrologTerm... body) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClause newFunction(PrologTerm head, PrologTerm result, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		return new PrologFunction(this, head, result, dynamic, multifile, discontiguous);
	}

	public PrologClause newFunction(PrologTerm head, PrologTerm body, PrologTerm result, boolean dynamic,
			boolean multifile, boolean discontiguous) {
		return new PrologFunction(this, head, body, result, dynamic, multifile, discontiguous);
	}

	public PrologTerm newClass(String name) {
		return new PrologClass(this, name);
	}

	public PrologTerm newClass(String namespace, String name) {
		return new PrologClass(this, namespace, name);
	}

	public PrologTerm newClass(PrologTerm namespace, String name) {
		return new PrologClass(this, namespace, name);
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

	public final Prologable<?> get(Object key) {
		return getJavaConverter().get(key);
	}

	public final Prologable<?> put(Class<?> key, Prologable<?> value) {
		return getJavaConverter().put(key, value);
	}

	public final Prologable<?> remove(Object key) {
		return getJavaConverter().remove(key);
	}

	public final void putAll(Map<? extends Class<?>, ? extends Prologable<?>> m) {
		getJavaConverter().putAll(m);
	}

	public final void clear() {
		getJavaConverter().clear();
	}

	public final Set<Class<?>> keySet() {
		return getJavaConverter().keySet();
	}

	public final Collection<Prologable<?>> values() {
		return getJavaConverter().values();
	}

	public final Set<Entry<Class<?>, Prologable<?>>> entrySet() {
		return getJavaConverter().entrySet();
	}

	public final void register(Prologable<?> mapping) {
		put(mapping.getType(), mapping);
	}

	public final PrologTerm getTerm(Prologable<?> mapping) {
		return mapping.toTerm(this);
	}

	public final <O> PrologTerm getTerm(Prologable<?> mapping, O o) {
		return mapping.toTerm(this, o);
	}

	public void unregister(Prologable<?> mapping) {
		remove(mapping.getType());
	}

}
