/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2018 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<E> extends AbstractIterator<E> implements Iterator<E> {

	private int next = 0;
	private final int size;
	private final E[] elements;

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
