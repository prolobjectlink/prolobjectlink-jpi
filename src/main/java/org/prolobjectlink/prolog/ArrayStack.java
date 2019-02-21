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
public class ArrayStack<E> extends ArrayList<E> {

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
