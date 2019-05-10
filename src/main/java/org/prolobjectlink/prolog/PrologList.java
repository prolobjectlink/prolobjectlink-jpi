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
	 */
	public int size();

}
