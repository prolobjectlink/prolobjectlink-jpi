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

public interface PrologTerm extends Comparable<PrologTerm> {

	/**
	 * Gets the term indicator represented by one string with the format
	 * functor/arity.
	 * 
	 * @return term indicator
	 * @since 1.0
	 */
	public String getIndicator();

	/**
	 * True if term has an indicator with the format functor/arity that match with
	 * the given functor and arity.
	 * 
	 * @param functor term functor
	 * @param arity   term arity
	 * @return True if term has an indicator functor/arity.
	 * @since 1.0
	 */
	public boolean hasIndicator(String functor, int arity);

	/**
	 * Get the term type.
	 * 
	 * @return term type.
	 * @since 1.0
	 */
	public int getType();

	/**
	 * True if this term is an atom
	 * 
	 * @return whether this Term represents an atom, false in other case
	 * @since 1.0
	 */
	public boolean isAtom();

	/**
	 * True if this term is an number
	 * 
	 * @return whether this Term represents an number, false in other case
	 * @since 1.0
	 */
	public boolean isNumber();

	/**
	 * True if this Term is a single precision floating point number, false in other
	 * case
	 * 
	 * @return whether this Term represents a single precision floating point
	 *         number, false in other case
	 * @since 1.0
	 */
	public boolean isFloat();

	/**
	 * True if this Term is an integer number, false in other case
	 * 
	 * @return whether this Term represents an integer number
	 * @since 1.0
	 */
	public boolean isInteger();

	/**
	 * True if this Term is a double precision floating point number, false in other
	 * case
	 * 
	 * @return whether this Term represents a double precision floating point
	 *         number, false in other case
	 * @since 1.0
	 */
	public boolean isDouble();

	/**
	 * True if this Term is a large integer number, false in other case
	 * 
	 * @return whether this Term represents a large integer number, false in other
	 *         case
	 * @since 1.0
	 */
	public boolean isLong();

	/**
	 * True if this Term is a variable, false in other case
	 * 
	 * @return whether this Term is a variable
	 * @since 1.0
	 */
	public boolean isVariable();

	/**
	 * True if this Term is a list, false in other case
	 * 
	 * @return whether this Term is a list
	 * @since 1.0
	 */
	public boolean isList();

	/**
	 * True if this Term is a structured term, false in other case
	 * 
	 * @return whether this Term is a structured term
	 * @since 1.0
	 */
	public boolean isStructure();

	/**
	 * True if this Term is a nil term (null term for prolog), false in other case
	 * 
	 * @return whether this Term is a nil term
	 * @since 1.0
	 */
	public boolean isNil();

	/**
	 * True if this Term is a empty list term ([]), false in other case
	 * 
	 * @return whether this Term is a empty list term
	 * @since 1.0
	 */
	public boolean isEmptyList();

	/**
	 * True if this Term is a atomic term, false in other case
	 * 
	 * @return whether this Term is a atomic term
	 * @since 1.0
	 */
	public boolean isAtomic();

	/**
	 * True if this Term is a compound term, false in other case
	 * 
	 * @return whether this Term is a compound term
	 * @since 1.0
	 */
	public boolean isCompound();

	/**
	 * Check if the current term is a compound term and have like functor an
	 * operator.
	 * 
	 * @return true if current term have like functor an operator.
	 * @since 1.0
	 */
	public boolean isEvaluable();

	/**
	 * Return current term instance if current term is not a variable or is a free
	 * variable term. If current term is a variable bound term then return the
	 * linked term hold by this variable after dereferencing.
	 * 
	 * @return return a dereferencing term if current term is bound variable,
	 *         current term in otherwise
	 * @since 1.0
	 */
	public PrologTerm getTerm();

	/**
	 * Return the left operand of the current term if the current term is an
	 * evalueble structure (expression).
	 * 
	 * @return return the left operand of the current term if the current term is an
	 *         expression.
	 * @since 1.0
	 */
	public PrologTerm getLeft();

	/**
	 * Return the right operand of the current term if the current term is an
	 * evalueble structure (expression).
	 * 
	 * @return return the right operand of the current term if the current term is
	 *         an expression.
	 * @since 1.0
	 */
	public PrologTerm getRight();

	/**
	 * Term arity. The arity of a term is a argument number for compound terms. If a
	 * the current term is not a compound term, then an exception is raised
	 * indicating a violation.
	 * 
	 * @return term arity for compound terms or an exception is raised if the term
	 *         is not a compound term.
	 * @since 1.0
	 */
	public int getArity();

	/**
	 * Term functor.The functor of a term is a name for compound terms. If a the
	 * current term is not a compound term, then an exception is raised indicating a
	 * violation.
	 * 
	 * @return term functor for compound terms or an exception is raised if the term
	 *         is not a compound term.
	 * @since 1.0
	 */
	public String getFunctor();

	/**
	 * Term arguments if the current term is a compound term. Compound terms are
	 * Structures and List data types. They are compoused by a PrologTerm[] array.
	 * Atoms, Variables and Numbers return an empty term array.
	 * 
	 * @return Term arguments if the current term is a compound term.
	 * @since 1.0
	 */
	public PrologTerm[] getArguments();

	/**
	 * Term located at some given index position in the current term arguments if
	 * current term is a compound term. If the current term is not compound term an
	 * {@link ArrayIndexOutOfBoundsException} exception is raised.
	 * 
	 * @param index position to retrieve the correspondent term.
	 * @return Term located at some given index position.
	 * @throws ArrayIndexOutOfBoundsException if the index value is out of term
	 *                                        array bound.
	 * @since 1.0
	 */
	public PrologTerm getArgument(int index);

	/**
	 * Check that two terms (x and y) unify. Prolog unification algorithm is based
	 * on three principals rules:
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
	 * @param term the term to unify with the current term
	 * @return true if the specified term unify whit the current term, false if not
	 * @since 1.0
	 */
	public boolean unify(PrologTerm term);

	/**
	 * Prolog provider associated to the current term.
	 * 
	 * @return Prolog provider associated to the current term.
	 * @since 1.0
	 */
	public PrologProvider getProvider();

}
