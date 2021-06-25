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

/**
 * <p>
 * Represent prolog list compound term. List are an special compound term that
 * have like functor a dot (.) and arity equals 2. Prolog list are recursively
 * defined. The first item in the list is referred like list head and the second
 * item list tail. The list tail can be another list that contains head and
 * tail. An special list case is the empty list denoted by no items brackets
 * ([]). The arity for this empty list is zero.
 * </p>
 * <p>
 * The Prolog Provider is the mechanism to create a new Prolog structures
 * invoking {@link PrologProvider#newList()} for empty list or
 * {@link PrologProvider#newList(PrologTerm)} for one item list or
 * {@link PrologProvider#newList(PrologTerm[])} for many items.
 * </p>
 * <p>
 * Two list are equals if and only if are list and have equals arguments. List
 * terms unify only with the same arguments list, with free variable or with
 * lists where your arguments unify.
 * </p>
 * 
 * <pre>
 * PrologList empty = provider.newList();
 * </pre>
 * 
 * <pre>
 * PrologInteger one = provider.newInteger(1);
 * PrologInteger two = provider.newInteger(2);
 * PrologInteger three = provider.newInteger(3);
 * PrologList list = provider.newList(new PrologTerm[] { one, two, three });
 * </pre>
 * 
 * PrologList implement {@link Iterable} interface to be used in for each
 * sentence iterating over every element present in the list.
 * 
 * <pre>
 * for (PrologTerm prologTerm : list) {
 * 	System.out.println(prologTerm);
 * }
 * </pre>
 * 
 * <pre>
 * Iterator&lt;PrologTerm&gt; i = list.iterator();
 * while (i.hasNext()) {
 * 	PrologTerm prologTerm = i.next();
 * 	System.out.println(prologTerm);
 * }
 * </pre>
 * 
 * <pre>
 * for (Iterator&lt;PrologTerm&gt; i = list.iterator(); i.hasNext();) {
 * 	PrologTerm prologTerm = i.next();
 * 	System.out.println(prologTerm);
 * }
 * </pre>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologList extends PrologTerm, Iterable<PrologTerm> {

	/**
	 * Return the head term of the current list if the current list have at least
	 * one element. If the list is an instance of {@link Prolog} empty list term,
	 * the result for invoke get head is the empty list term properly.
	 * 
	 * @return the head term of the current list if the current list have at least
	 *         one element.
	 * @since 1.0
	 */
	public PrologTerm getHead();

	/**
	 * Return the tail term of the current list if the current list have tail. If
	 * the list is a sole term list, the result for invoke get tail is the empty
	 * list term.
	 * 
	 * @return the tail term of the current list if the current list have tail.
	 * @since 1.0
	 */
	public PrologTerm getTail();

	/**
	 * Return true if the current list don't have any elements, false in other case.
	 * More formally {@code return #size()==0}
	 * 
	 * @return true if the current list don't have any elements, false in other
	 *         case.
	 * @since 1.0
	 */
	public boolean isEmpty();

	/**
	 * Clear the current list removing all contained prolog term. After clear the
	 * list the resulting list is equal and unify with empty list.
	 * 
	 * @since 1.0
	 */
	public void clear();

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list.
	 * @since 1.0
	 */
	public int size();

}
