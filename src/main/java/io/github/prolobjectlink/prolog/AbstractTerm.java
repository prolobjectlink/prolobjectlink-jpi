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

import static io.github.prolobjectlink.prolog.PrologTermType.ATOM_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.CLASS_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.CUT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.DOUBLE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FAIL_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FALSE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FIELD_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FLOAT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.INTEGER_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LIST_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LONG_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.MAP_ENTRY_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.MAP_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.MIXIN_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.NIL_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.PARAMETER_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.RESULT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.TRUE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.VARIABLE_TYPE;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Partial implementation of {@link PrologTerm} interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractTerm implements PrologTerm {

	protected int type;
	protected final PrologProvider provider;

	protected AbstractTerm(int type, PrologProvider provider) {
		this.type = type;
		this.provider = provider;
	}

	protected final void checkIndex(int index) {
		if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	protected final void checkIndex(int index, int max) {
		if (index < 0 || index > max) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	protected final String removeQuoted(String functor) {
		if (functor != null && functor.startsWith("\'") && functor.endsWith("\'")) {
			return functor.substring(1, functor.length() - 1);
		}
		return functor;
	}

	protected final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		return provider.toTerm(o, from);
	}

	protected final <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from) {
		return provider.toTermArray(os, from);
	}

	protected final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return provider.fromTerm(term, to);
	}

	protected final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		return provider.fromTermArray(terms, to);
	}

	protected final PrologLogger getLogger() {
		return provider.getLogger();
	}

	public PrologTerm getArgument(int index) {
		PrologTerm[] array = getArguments();
		checkIndex(index, array.length);
		return array[index];
	}

	public final String getIndicator() {
		return getFunctor() + "/" + getArity();
	}

	public final boolean hasIndicator(String functor, int arity) {
		return getFunctor().equals(functor) && getArity() == arity;
	}

	public PrologTerm getTerm() {
		return this;
	}

	public final int getType() {
		return type;
	}

	public final PrologProvider getProvider() {
		return provider;
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

	/**
	 * Casts a PrologTerm to the class or interface represented by this
	 * {@code Class} object.
	 *
	 * @param term the object to be cast
	 * @return the PrologTerm after casting, or null if term is null
	 *
	 * @throws ClassCastException if the object is not null and is not assignable to
	 *                            the type T.
	 * @since 1.1
	 */
	protected final <T extends PrologTerm> T cast(PrologTerm term) {
		return (T) term;
	}

	public final <T extends PrologTerm> T cast() {
		return cast(this);
	}

	public Object getObject() {
		PrologTerm term = this;
		switch (term.getType()) {
		case NIL_TYPE:
			return null;
		case CUT_TYPE:
			return "!";
		case FAIL_TYPE:
			return "fail";
		case TRUE_TYPE:
			return true;
		case FALSE_TYPE:
			return false;
		case ATOM_TYPE:
			return term.getFunctor();
		case FLOAT_TYPE:
			return ((PrologNumber) term).getFloatValue();
		case INTEGER_TYPE:
			return ((PrologNumber) term).getIntegerValue();
		case DOUBLE_TYPE:
			return ((PrologNumber) term).getDoubleValue();
		case LONG_TYPE:
			return ((PrologNumber) term).getLongValue();
		case VARIABLE_TYPE:
			return ((PrologVariable) term).getName();
		case LIST_TYPE:
			return fromTermArray(term.getArguments(), Object[].class);
		case STRUCTURE_TYPE:
			return term;
		case OBJECT_TYPE:
			return term.getObject();
		case FIELD_TYPE:
			PrologVariable field = term.cast();
			return "field " + field.getName();
		case RESULT_TYPE:
			PrologVariable result = term.cast();
			return "result " + result.getName();
		case PARAMETER_TYPE:
			PrologVariable parameter = term.cast();
			return "parameter " + parameter.getName();
		case CLASS_TYPE:
		case MIXIN_TYPE:
			return "class " + term.getFunctor();
		default:
			return null;
		}
	}

	public final boolean isEntry() {
		return getType() == MAP_ENTRY_TYPE;
	}

	public final boolean isMap() {
		return getType() == MAP_TYPE;
	}

	public boolean isField() {
		return getType() == FIELD_TYPE;
	}

	public boolean isResult() {
		return getType() == RESULT_TYPE;
	}

	public boolean isParameter() {
		return getType() == PARAMETER_TYPE;
	}

	public final boolean isMixin() {
		return getType() == MIXIN_TYPE;
	}

	public final boolean isClass() {
		return getType() == CLASS_TYPE;
	}

	public boolean isVariableBound() {
		return isVariable() && getTerm() != this;
	}

	public boolean isVariableNotBound() {
		return isVariable() && getTerm() == this;
	}

	public final boolean isClause() {
		return false;
	}

	public final boolean isTerm() {
		return true;
	}

	/**
	 * Match to other term returning list of substitutions.
	 * 
	 * @param term - term to match check
	 * @return list of substitutions.
	 */
	public final Map<String, PrologTerm> match(PrologTerm term) {
		Deque<PrologTerm> stack = new ArrayDeque<PrologTerm>();
		if (unify(term, stack)) {
			int size = stack.size();
			HashMap<String, PrologTerm> substitution = new HashMap<String, PrologTerm>(size);
			while (size > 0) {
				PrologVariable variable = (PrologVariable) stack.pop();
				substitution.put(variable.getName(), variable.getTerm());
				// variable.unbind();
				size--;
			}
			return substitution;
		}
		return new HashMap<String, PrologTerm>();
	}

	/**
	 * Unification is the basic primitive operation in logic programming. Check that
	 * two terms (x and y) unify. Prolog unification algorithm is based on three
	 * principals rules:
	 * <ul>
	 * <li>If x and y are atomics constants then x and y unify only if they are same
	 * object.</li>
	 * <li>If x is a variable and y is anything then they unify and x is
	 * instantiated to y. Conversely, if y is a variable then this is instantiated
	 * to x.</li>
	 * <li>If x and y are structured terms then unify only if they match (equals
	 * funtor and arity) and all their correspondents arguments unify.</li>
	 * </ul>
	 * 
	 * 
	 * @param term  - the term to unify with the current term
	 * @param stack - the stack is used to store the addresses of variables which
	 *              are bound by the unification. This is needed when backtracking.
	 * @return true if the specified term unify whit the current term, false if not
	 */
	private boolean unify(PrologTerm term, Deque<PrologTerm> stack) throws PrologError {

		PrologTerm thisTerm = this;
		PrologTerm otherTerm = term;

		if (thisTerm.isVariableBound()) {
			return ((AbstractTerm) thisTerm.getTerm()).unify(otherTerm, stack);
		}

		else if (otherTerm.isVariableBound()) {
			return ((AbstractTerm) otherTerm.getTerm()).unify(thisTerm, stack);
		}

		// current term is a free variable
		else if (thisTerm.isVariableNotBound()) {
			// if (!thisTerm.occurs(otherTerm)) {
			// thisTerm.bind(otherTerm);
			stack.push(thisTerm);
			return true;
			// }
		}

		// the other term is a free variable
		else if (otherTerm.isVariableNotBound()) {
			// if (!otherTerm.occurs(thisTerm)) {
			// otherTerm.bind(thisTerm);
			stack.push(otherTerm);
			return true;
			// }
		}

		// if at least term is a number then check equivalence
		else if (thisTerm.isNumber() || otherTerm.isNumber()) {
			if ((thisTerm.isInteger() || thisTerm.isLong()) && (otherTerm.isInteger() || otherTerm.isLong())) {
				int thisInt = ((PrologNumber) thisTerm).getIntegerValue();
				int otherInt = ((PrologNumber) otherTerm).getIntegerValue();
				return thisInt == otherInt;
			}
			return thisTerm.equals(otherTerm);
		}

		else {

			int thisArity = thisTerm.getArity();
			int otherArity = otherTerm.getArity();
			String thisFunctor = thisTerm.getFunctor();
			String otherFunctor = otherTerm.getFunctor();

			if (thisFunctor.equals(otherFunctor) && thisArity == otherArity) {
				for (int i = 0; i < thisArity; i++) {
					if (thisTerm.getArgument(i) != null && otherTerm.getArgument(i) != null) {
						if (!((AbstractTerm) thisTerm.getArgument(i)).unify(otherTerm.getArgument(i), stack)) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}

}
