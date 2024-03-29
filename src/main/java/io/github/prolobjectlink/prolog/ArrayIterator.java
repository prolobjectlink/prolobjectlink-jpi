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
package io.github.prolobjectlink.prolog;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator implementation over array of elements.
 * 
 * @param <E> Generic Element Type
 * @author Jose Zalacain
 * @since 1.0
 */
public class ArrayIterator<E> extends AbstractIterator<E> implements Iterator<E> {

	private int next = 0;
	private final int size;
	private final E[] elements;

	/**
	 * Create an instance of {@link ArrayIterator} to iterate over given array of
	 * elements
	 * 
	 * @param elements array of elements to be iterated
	 * @since 1.0
	 */
	public ArrayIterator(E[] elements) {
		this.size = elements.length;
		this.elements = elements;
	}

	public boolean hasNext() {
		return next != size;
	}

	public E next() {
		if (next >= size) {
			throw new NoSuchElementException();
		}
		return elements[next++];
	}

}
