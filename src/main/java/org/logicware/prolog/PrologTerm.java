/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.prolog;

import org.logicware.Wrapper;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologTerm extends Wrapper, Comparable<PrologTerm> {

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

	public int getArity();

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

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

	public PrologProvider getProvider();

}
