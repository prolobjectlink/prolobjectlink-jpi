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

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
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

	public PrologTerm getLeft();

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

	public PrologTerm[] getArguments();

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

	public PrologProvider getProvider();

	public int hashCode();

	public boolean equals(Object object);

	public String toString();

}
