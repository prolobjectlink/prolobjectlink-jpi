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

	public boolean equals(Object object);

	public String toString();

}
