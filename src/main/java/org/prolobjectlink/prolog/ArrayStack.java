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

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Vector;

/**
 * Array Stack implementation to replace {@link java.util.Stack} synchronized
 * implementation but with the same logic. Extends from {@link List} like
 * {@link java.util.Stack} extends from {@link Vector}.
 * 
 * @author Jose Zalacain
 *
 * @param <E> Generic Element Type
 * @since 1.0
 */
public class ArrayStack<E> extends ArrayList<E> implements List<E> {

	private static final long serialVersionUID = -6769914163671435871L;

	/**
	 * Create an empty stack
	 */
	public ArrayStack() {
		super();
	}

	/**
	 * Check if this stack is empty.
	 *
	 * @return true if and only if this stack no contains any item; false otherwise.
	 * @since 1.0
	 */
	public boolean empty() {
		return isEmpty();
	}

	/**
	 * Looks at the object at the top of this stack without removing it from the
	 * stack.
	 *
	 * @return the object at the top of this stack and the last item of the list.
	 * @throws EmptyStackException if this stack is empty.
	 * @since 1.0
	 */
	public E peek() {
		int n = size();
		if (n <= 0) {
			throw new EmptyStackException();
		} else {
			return get(n - 1);
		}
	}

	/**
	 * Removes the object at the top of this stack and returns that object as the
	 * value of this function.
	 * 
	 * @return The object at the top of this stack and the last item of the list.
	 * @since 1.0
	 */
	public E pop() {
		int n = size();
		if (n <= 0) {
			throw new EmptyStackException();
		} else {
			return remove(n - 1);
		}
	}

	/**
	 * Pushes an item onto the top of this stack.
	 * 
	 * @param item the item to be pushed onto this stack.
	 * @return the item argument.
	 * @since 1.0
	 */
	public E push(E item) {
		add(item);
		return item;
	}

}
