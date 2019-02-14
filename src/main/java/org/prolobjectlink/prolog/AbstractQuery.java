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
