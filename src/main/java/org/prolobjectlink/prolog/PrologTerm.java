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

/**
 * <p>
 * Ancestor prolog data type. All Prolog data types {@link PrologAtom},
 * {@link PrologNumber}, {@link PrologList}, {@link PrologStructure} and
 * {@link PrologVariable} are derived from this class. All before mentioned
 * classes extends from this class the commons responsibilities. Extends from
 * {@link Comparable} to compare the current term with another term based on
 * Standard Order.
 * </p>
 * 
 * <p>
 * The Prolog Provider is the mechanism to create a new Prolog terms invoking
 * the correspondent methods. {@link PrologProvider#newAtom(String)} for atoms,
 * {@link PrologProvider#newStructure(String, PrologTerm...)} for structures,
 * {@link PrologProvider#newVariable(String, int)} for variables and so on.
 * </p>
 * 
 * <p>
 * With {@link #getType()} the term type can be retrieve. Every term have a type
 * defined in {@link PrologTermType}. The prolog type have by definition one
 * order (Standard Order) where Variables &lt; Atoms &lt; Numbers &lt;
 * Compounds. This order is usable to compare two terms.
 * </p>
 * 
 * <p>
 * Prolog terms have two specials properties, the functor and the arity. Functor
 * is the compound term or atom name and arity is the number of arguments
 * present in the compound terms. Variable and Number don't have this
 * properties. This properties are accessible from {@link #getArity()} and
 * {@link #getFunctor()} methods. Variable and Number raise an
 * {@link ArityError} and {@link FunctorError} respectively. Based on functor
 * and arity atoms and compound terms have a predicate indicator that is the
 * signature for the term. The term indicator is an string formed by the
 * concatenation of {@link #getFunctor()} and {@link #getArity()} separated by
 * slash.
 * </p>
 * 
 * <p>
 * The methods {@link #getArguments()} and {@link #getArgument(int)} allow
 * retrieve the arguments and the some specific arguments for compounds terms
 * respectively. For atomics terms {@link #getArguments()} return an empty term
 * array and {@link #getArgument(int)} raise a
 * {@link ArrayIndexOutOfBoundsException} because don't have arguments. For this
 * cases is a good practice check compound terms before invoke this methods.
 * </p>
 * 
 * <pre>
 * if (t.isCompound()) {
 * 	PrologTerm x = t.getArgument(i);
 * 	System.out.println(x);
 * }
 * </pre>
 * 
 * <p>
 * Talk about {@link #unify(PrologTerm)}
 * </p>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologTerm extends Comparable<PrologTerm> {

	/**
	 * Gets the term indicator represented by one string with the format
	 * functor/arity. More formally the indicator string is formed by the
	 * concatenation of {@link #getFunctor()} and {@link #getArity()} separated by
	 * slash.
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
	 * Check if the current term is a reference term and the referenced object is an
	 * instance of java true value. Most formally check if the reference term
	 * is @(true) structure.
	 * 
	 * @return true if current term have like referenced object an instance of java
	 *         true value.
	 * @since 1.0
	 */
	public boolean isTrueType();

	/**
	 * Check if the current term is a reference term and the referenced object is an
	 * instance of java false value. Most formally check if the reference term
	 * is @(false) structure.
	 * 
	 * @return true if current term have like referenced object an instance of java
	 *         false value.
	 * @since 1.0
	 */
	public boolean isFalseType();

	/**
	 * Check if the current term is a reference term and the referenced object is a
	 * java null value. Most formally check if the reference term is @(null)
	 * structure.
	 * 
	 * @return true if current term have like referenced object a java null value.
	 * @since 1.0
	 */
	public boolean isNullType();

	/**
	 * Check if the current term is a reference term for java void type. Most
	 * formally check if the reference term is @(void) structure.
	 * 
	 * @return true if current term is a referenced to java void type.
	 * @since 1.0
	 */
	public boolean isVoidType();

	/**
	 * Check if the current term is a reference term for some java object instance.
	 * Most formally check if the reference term is an structure
	 * like @(J#00000000000000000425).
	 * 
	 * 
	 * 
	 * @return true if current term is a referenced for some java object instance.
	 * @since 1.0
	 */
	public boolean isObjectType();

	/**
	 * Check if the current term is a reference term for some java object instance
	 * or is a reference term and the referenced object is a java null value.
	 * 
	 * @return true if current term is a reference term for object instance or java
	 *         null value.
	 * @since 1.0
	 */
	public boolean isReference();

	/**
	 * For references terms return the referenced object.
	 * 
	 * @return the referenced object if the current term is a reference prolog term
	 * @since 1.0
	 */
	public Object getObject();

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
	 * Check that the current term unify with the given term. Prolog unification
	 * algorithm is based on three principals rules:
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
