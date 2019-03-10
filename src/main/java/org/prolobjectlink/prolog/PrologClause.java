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

import java.util.Iterator;

/**
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

	public String getIndicator();

	public boolean isDirective();

	/**
	 * True if this clause is a fact, false in other case. The implementation for
	 * this method can be {@code getHead()!=null && getBody==null}.
	 * 
	 * @return whether this clause is a fact.
	 * @since 1.0
	 */
	public boolean isFact();

	/**
	 * True if this clause is a rule, false in other case. The implementation for
	 * this method can be {@code getHead()!=null && getBody==null}.
	 * 
	 * @return whether this clause is a rule.
	 * @since 1.0
	 */
	public boolean isRule();

	public boolean unify(PrologClause clause);

	/**
	 * True if this clause is a dynamic, false in other case
	 * 
	 * @return whether this clause is a dynamic
	 * @since 1.0
	 */
	public boolean isDynamic();

	/**
	 * True if this clause is a multifile, false in other case
	 * 
	 * @return whether this clause is a multifile
	 * @since 1.0
	 */
	public boolean isMultifile();

	/**
	 * True if this clause is a discontiguos, false in other case
	 * 
	 * @return whether this clause is a discontiguos.
	 * @since 1.0
	 */
	public boolean isDiscontiguous();

	public PrologIndicator getPrologIndicator();

	public Iterator<PrologTerm> getBodyIterator();

	public PrologTerm[] getBodyArray();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
