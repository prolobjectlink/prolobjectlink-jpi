/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
package org.logicware.prolog;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.worklogic.AbstractIterator;

/**
 * Iterator class implementation for Prolog Query
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class PrologQueryIterator extends AbstractIterator<Collection<PrologTerm>>
		implements Iterator<Collection<PrologTerm>> {

	private final PrologQuery query;

	public PrologQueryIterator(PrologQuery query) {
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
