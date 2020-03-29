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

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator class implementation for Prolog Query
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
final class PrologQueryIterator extends AbstractIterator<Collection<PrologTerm>>
		implements Iterator<Collection<PrologTerm>> {

	private final PrologQuery query;

	/**
	 * Create a new query iterator over given PrologQuery.
	 * 
	 * @param query PrologQuery to iterate
	 */
	PrologQueryIterator(PrologQuery query) {
		this.query = query;
	}

	/**
	 * True if the current Prolog query has more solution, false if not has more
	 * solution
	 */
	public boolean hasNext() {
		return query.hasMoreSolutions();
	}

	/**
	 * Next Prolog solution terms
	 */
	public Collection<PrologTerm> next() {
		if (!query.hasMoreSolutions()) {
			throw new NoSuchElementException();
		}
		return query.nextVariablesSolution().values();
	}

	/**
	 * Skip the next solution invoking next solution method
	 */
	@Override
	public void remove() {
		query.nextSolution();
	}

}
