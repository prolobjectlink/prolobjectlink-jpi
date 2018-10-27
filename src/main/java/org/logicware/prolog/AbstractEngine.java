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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.logicware.AbstractIterator;

public abstract class AbstractEngine extends AbstractPrologPlatform implements PrologEngine {

	protected static final String UNKNOW_VERSION = "unknow";

	protected AbstractEngine(PrologProvider provider) {
		super(provider);
	}

	public final PrologProvider getProvider() {
		return provider;
	}

	public final boolean contains(String goal) {
		return query(goal).hasSolution();
	}

	public final boolean contains(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).hasSolution();
	}

	public final Map<String, PrologTerm> queryOne(String goal) {
		return query(goal).oneVariablesSolution();
	}

	public final Map<String, PrologTerm> queryOne(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).oneVariablesSolution();
	}

	public final List<Map<String, PrologTerm>> queryAll(String goal) {
		return query(goal).all();
	}

	public final List<Map<String, PrologTerm>> queryAll(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).all();
	}

	public final Set<PrologClause> getProgramClauses() {
		Set<PrologClause> c = new LinkedHashSet<PrologClause>();
		for (Iterator<PrologClause> i = iterator(); i.hasNext();) {
			PrologClause prologClause = i.next();
			c.add(prologClause);
		}
		return c;
	}

	public final boolean isProgramEmpty() {
		return getProgramSize() == 0;
	}

	public final Set<PrologIndicator> currentPredicates() {
		Set<PrologIndicator> pis = new HashSet<PrologIndicator>();
		pis.addAll(getPredicates());
		pis.addAll(getBuiltIns());
		return pis;
	}

	protected final String removeQuoted(String functor) {
		if (functor != null && functor.startsWith("\'") && functor.endsWith("\'")) {
			return functor.substring(1, functor.length() - 1);
		}
		return functor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
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
		AbstractEngine other = (AbstractEngine) obj;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider)) {
			return false;
		}
		return true;
	}

	protected class PrologProgramIterator extends AbstractIterator<PrologClause> implements Iterator<PrologClause> {

		private PrologClause last;
		private final Iterator<PrologClause> i;

		public PrologProgramIterator(Collection<PrologClause> cls) {
			i = cls.iterator();
		}

		public boolean hasNext() {
			return i.hasNext();
		}

		public PrologClause next() {
			if (!i.hasNext()) {
				throw new NoSuchElementException();
			}
			last = i.next();
			return last;
		}

		@Override
		public void remove() {
			PrologTerm h = last.getHead();
			PrologTerm b = last.getBody();
			retract(h, b);
			i.remove();
		}

	}

}
