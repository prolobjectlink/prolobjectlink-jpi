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
	 * Term arguments present in the clause head.
	 * 
	 * @return Term arguments present in the clause head.
	 * @since 1.0
	 */
	public PrologTerm[] getArguments();

	/**
	 * Term located at some given index position in the clause head arguments.
	 * 
	 * @param index position to retrieve the correspondent term.
	 * @return Term located at some given index position.
	 * @throws ArrayIndexOutOfBoundsException if the index value is out of term
	 *                                        array bound.
	 * @since 1.0
	 */
	public PrologTerm getArgument(int index);

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
	 * Check if the current clause have functor/arity based indicator specified by
	 * arguments, false in otherwise.
	 * 
	 * @param functor clause functor to be checked
	 * @param arity   clause arity to be checked
	 * @return true if the current clause have functor/arity based indicator
	 *         specified by arguments, false in otherwise.
	 * @since 1.0
	 */
	public boolean hasIndicator(String functor, int arity);

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
	 * this method can be {@code getHead()!=null && getBody()!=null}.
	 * 
	 * @return whether this clause is a rule.
	 * @since 1.0
	 */
	public boolean isRule();

	/**
	 * True if this clause is a rule, false in other case. The implementation for
	 * this method can be {@code getHead()!=null && getBody()!=null}.
	 * 
	 * @return whether this clause is a rule.
	 * @since 1.1
	 */
	public boolean isMethod();

	/**
	 * True if this clause is a function, false in other case. The implementation
	 * for this method can be {@code getHead()!=null && getBody()!=null}.
	 * 
	 * @return whether this clause is a rule.
	 * @since 1.1
	 */
	public boolean isFunction();

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

	/**
	 * Casts the current PrologClause to the class or interface represented by this
	 * {@code Class} object.
	 *
	 * @return the PrologTerm after casting, or null if term is null
	 *
	 * @throws ClassCastException if the object is not null and is not assignable to
	 *                            the type T.
	 * @since 1.1
	 */
	public <T extends PrologClause> T cast();

}
