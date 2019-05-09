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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
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

	protected final <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(
			Map<String, V>[] map, Class<K> from) {
		return engine.toTermMapArray(map, from);
	}

	protected final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return engine.fromTerm(term, to);
	}

	protected final boolean contains(List<Map<String, PrologTerm>> maps, Map<String, PrologTerm> map) {
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

	protected final PrologLogger getLogger() {
		return getProvider().getLogger();
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

	public final Map<String, Object> oneResult() {
		Map<String, PrologTerm> map = oneVariablesSolution();
		return getProvider().getJavaConverter().toObjectMap(map);
	}

	public final List<Map<String, Object>> allResult() {
		Map<String, PrologTerm>[] maps = allVariablesSolutions();
		return getProvider().getJavaConverter().toObjectMaps(maps);
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
