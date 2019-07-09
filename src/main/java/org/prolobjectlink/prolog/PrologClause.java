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

import java.util.Iterator;

/**
 * <p>
 * Prolog clause is composed by two prolog terms that define a prolog clause,
 * the head and the body. This representation consider the prolog clause body
 * like a single term. If the body is a conjunctive set of terms, the body is an
 * structure with functor/arity (,/2) and the first argument is the first
 * element in the conjunction and the rest is a recursive functor/arity (,/2).
 * </p>
 * <p>
 * The functor and arity for the clause is given from head term functor and
 * arity.
 * </p>
 * <p>
 * This class define some properties for commons prolog clause implementations.
 * They are boolean flags that indicate if the prolog clause is dynamic
 * multifile and discontiguos.
 * </p>
 * <p>
 * This class have several methods to access to the clause components and
 * retrieve some clause properties and information about it.
 * </p>
 * <p>
 * Additionally this class contains a prolog provider reference for build terms
 * in some operations.
 * </p>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologClause {

	/**
	 * Integer number that represent the arguments number in the clause head. The
	 * arity for this clause can be implemented like {@code getHead().getArity()}.
	 * 
	 * @return the arguments number in the clause head.
	 * @since 1.0
	 */
	public int getArity();

	/**
	 * String that represent the functor in the clause head. The functor for this
	 * clause can be implemented like {@code getHead().getFunctor()}.
	 * 
	 * @return the functor in the clause head
	 * @since 1.0
	 */
	public String getFunctor();

	/**
	 * Prolog term representation of the current clause. This prolog term is a
	 * structure with functor/arity (:-/2) and the arguments head and body.
	 * 
	 * @return term representation of the current clause.
	 * @since 1.0
	 */
	public PrologTerm getTerm();

	/**
	 * Prolog term that represent the clause head.
	 * 
	 * @return the clause head.
	 * @since 1.0
	 */
	public PrologTerm getHead();

	/**
	 * Prolog term that represent the clause body. If the clause body representation
	 * it's not the term itself, the prolog term should be created and returned.
	 * 
	 * @return the clause body.
	 * @since 1.0
	 */
	public PrologTerm getBody();

	/**
	 * Clause family functor/arity based indicator. The clause family indicator is
	 * the same indicator for all clauses head in the clause family.
	 * 
	 * @return functor/arity based indicator of the current clause family.
	 * @since 1.0
	 */
	public String getIndicator();

	/**
	 * True if this clause is a directive, false in other case. The implementation
	 * for this method can be {@code getHead()==null && getBody()!=null}.
	 * 
	 * @return whether this clause is a directive.
	 * @since 1.0
	 */
	public boolean isDirective();

	/**
	 * True if this clause is a fact, false in other case. The implementation for
	 * this method can be {@code getHead()!=null && getBody()==null}.
	 * 
	 * @return whether this clause is a fact.
	 * @since 1.0
	 */
	public boolean isFact();

	/**
	 * True if this clause is a rule, false in other case. The implementation for
	 * this method can be {@code getHead()!=null && getBody()==null}.
	 * 
	 * @return whether this clause is a rule.
	 * @since 1.0
	 */
	public boolean isRule();

	/**
	 * Check that two clauses unify. Prolog clauses unify if and only if the head
	 * and the body for both clauses unify.
	 * 
	 * @param clause the clause to unify with the current clause
	 * @return true if the given clause unify whit the current clause, false
	 *         otherwise
	 * @since 1.0
	 */
	public boolean unify(PrologClause clause);

	/**
	 * True if this clause is a dynamic, false in other case
	 * 
	 * @deprecated Natives engine don't offer information about that.
	 * @return whether this clause is a dynamic
	 * @since 1.0
	 */
	@Deprecated
	public boolean isDynamic();

	/**
	 * True if this clause is a multifile, false in other case
	 * 
	 * @deprecated Natives engine don't offer information about that.
	 * @return whether this clause is a multifile
	 * @since 1.0
	 */
	@Deprecated
	public boolean isMultifile();

	/**
	 * True if this clause is a discontiguos, false in other case
	 * 
	 * @deprecated Natives engine don't offer information about that.
	 * @return whether this clause is a discontiguos.
	 * @since 1.0
	 */
	@Deprecated
	public boolean isDiscontiguous();

	/**
	 * Clause family PrologIndicator based indicator. The clause family indicator is
	 * the same indicator for all clauses head in the clause family.
	 * 
	 * @return PrologIndicator based indicator of the current clause family.
	 * @since 1.0
	 */
	public PrologIndicator getPrologIndicator();

	/**
	 * Iterator to iterate over all body terms.
	 * 
	 * @return Iterator to iterate over all body terms.
	 * @since 1.0
	 */
	public Iterator<PrologTerm> getBodyIterator();

	/**
	 * Get the clause body as terms array.
	 * 
	 * @return clause terms body array.
	 * @since 1.0
	 */
	public PrologTerm[] getBodyArray();

}
