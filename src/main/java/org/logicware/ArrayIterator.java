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
