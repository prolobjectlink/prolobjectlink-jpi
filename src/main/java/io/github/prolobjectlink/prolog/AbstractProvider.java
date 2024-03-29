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

	protected final String removeQuoted(String functor) {
		if (functor != null && functor.startsWith("\'") && functor.endsWith("\'")) {
			return functor.substring(1, functor.length() - 1);
		}
		return functor;
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

}
