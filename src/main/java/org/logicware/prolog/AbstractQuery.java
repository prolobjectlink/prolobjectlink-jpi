/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.logicware.platform.AbstractIterator;

public abstract class AbstractQuery extends AbstractIterator<Collection<PrologTerm>> implements PrologQuery {

	// engine for execute queries
	protected final AbstractEngine engine;

	public AbstractQuery(AbstractEngine engine) {
		this.engine = engine;
	}

	public final PrologEngine getEngine() {
		return engine;
	}

	public final PrologProvider getProvider() {
		return engine.getProvider();
	}

	protected final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		return engine.toTerm(o, from);
	}

	protected final <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from) {
		return engine.toTermArray(os, from);
	}

	protected final <K extends PrologTerm> K[][] toTermMatrix(Object[][] oss, Class<K[][]> from) {
		return engine.toTermMatrix(oss, from);
	}

	protected final <K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map,
			Class<K> from) {
		return engine.toTermMap(map, from);
	}

	protected final <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(
			Map<String, V>[] map, Class<K> from) {
		return engine.toTermMapArray(map, from);
	}

	protected final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return engine.fromTerm(term, to);
	}

	protected final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		return engine.fromTermArray(terms, to);
	}

	protected final <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to) {
		return engine.fromTerm(head, body, to);
	}

	protected final boolean contains(List<Map<String, PrologTerm>> maps, Map<String, PrologTerm> map) {
		for (Map<String, PrologTerm> m : maps) {
			if (m.equals(map)) {
				return true;
			}
		}
		return false;
	}

	protected final boolean contains(Map<String, PrologTerm>[] maps, Map<String, PrologTerm> map) {
		for (Map<String, PrologTerm> m : maps) {
			if (m.equals(map)) {
				return true;
			}
		}
		return false;
	}

	protected final boolean contains(List<PrologTerm[]> arrays, PrologTerm[] array) {
		for (PrologTerm[] a : arrays) {
			if (Arrays.equals(a, array)) {
				return true;
			}
		}
		return false;
	}

	public final Iterator<Collection<PrologTerm>> iterator() {
		return new PrologQueryIterator(this);
	}

	public final boolean hasNext() {
		return hasMoreSolutions();
	}

	public final Collection<PrologTerm> next() {
		// don't check has next
		// don't raise NoSuchElementException
//		return Arrays.asList(nextSolution());
		return nextVariablesSolution().values();
	}

	@Override
	public final void remove() {
		// skip invoking next
		nextSolution();
	}

	public final Map<String, PrologTerm> one() {
		return oneVariablesSolution();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractQuery other = (AbstractQuery) obj;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.equals(other.engine)) {
			return false;
		}
		return true;
	}

}
