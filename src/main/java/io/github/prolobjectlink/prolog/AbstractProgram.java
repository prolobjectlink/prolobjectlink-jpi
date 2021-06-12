/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package io.github.prolobjectlink.prolog;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractProgram extends AbstractMap<String, PrologClauses> implements PrologProgram {

	protected final PrologEngine engine;

	protected AbstractProgram(PrologEngine engine) {
		this.engine = engine;
	}

	public Iterator<PrologClauses> iterator() {
		return getClauses().values().iterator();
	}

	public PrologClauses get(String functor, int arity) {
		String key = functor + "/" + arity;
		List<PrologClause> l = engine.getProgramMap().get(key);
		PrologClauses clauses = newClauses(functor, arity);
		for (PrologClause prologClause : l) {
			clauses.add(prologClause);
		}
		return clauses;
	}

	public void add(PrologClause clause) {
		engine.assertz(clause.getHead(), clause.getBody());
	}

	public void add(PrologProgram program) {
		for (PrologClauses prologClauses : program) {
			for (PrologClause clause : prologClauses) {
				engine.assertz(clause.getHead(), clause.getBody());
			}
		}
	}

	public void push(PrologClause clause) {
		engine.asserta(clause.getHead(), clause.getBody());
	}

	public void removeAll(String key) {
		String functor = key.substring(0, key.lastIndexOf('/'));
		String number = key.substring(key.lastIndexOf('/') + 1, key.length());
		int arity = Integer.parseInt(number);
		engine.abolish(functor, arity);
	}

	public void removeAll(String functor, int arity) {
		engine.abolish(functor, arity);
	}

	public void markDynamic(String functor, int arity) {
		// do nothing
	}

	public void unmarkDynamic(String functor, int arity) {
		// do nothing
	}

	public boolean isDynamic(String functor, int arity) {
		return getClauses().get(functor + "/" + arity).isDynamic();
	}

	public void markMultifile(String functor, int arity) {
		// do nothing
	}

	public void unmarkMultifile(String functor, int arity) {
		// do nothing
	}

	public boolean isMultifile(String functor, int arity) {
		return getClauses().get(functor + "/" + arity).isMultifile();
	}

	public void markDiscontiguous(String functor, int arity) {
		// do nothing
	}

	public void unmarkDiscontiguous(String functor, int arity) {
		// do nothing
	}

	public boolean isDiscontiguous(String functor, int arity) {
		return getClauses().get(functor + "/" + arity).isDiscontiguous();
	}

	public boolean removeAll(PrologProgram program) {
		for (Entry<String, PrologClauses> entry : program.entrySet()) {
			getClauses().remove(entry.getKey());
		}
		return true;
	}

	public boolean removeAll(PrologClauses clauses) {
		getClauses().remove(clauses.getIndicator());
		return true;
	}

	public Map<String, PrologClauses> getClauses() {
		Map<String, List<PrologClause>> p = engine.getProgramMap();
		Map<String, PrologClauses> m = new HashMap<String, PrologClauses>(p.size());
		for (List<PrologClause> clauses : p.values()) {
			for (PrologClause clause : clauses) {
				PrologClauses c = m.get(clause.getIndicator());
				if (c == null) {
					c = newClauses(clause.getFunctor(), clause.getArity());
				}
				c.add(clause);
			}
		}
		return m;
	}

	public Set<String> getIndicators() {
		Set<PrologIndicator> is = engine.currentPredicates();
		Set<String> i = new HashSet<String>(is.size());
		for (PrologIndicator prologIndicator : is) {
			i.add(prologIndicator.toString());
		}
		return i;
	}

	public void add(PrologClauses clauses) {
		for (PrologClause prologClause : clauses) {
			engine.assertz(prologClause.getHead(), prologClause.getBody());
		}
	}

	public void addAll(PrologProgram program) {
		for (PrologClauses clauses : program) {
			for (PrologClause prologClause : clauses) {
				engine.assertz(prologClause.getHead(), prologClause.getBody());
			}
		}
	}

	public boolean retainAll(PrologClauses parents) {
		Map<String, PrologClauses> m = getClauses();
		for (PrologClauses prologClauses : m.values()) {
			if (!parents.contains((Object) prologClauses)) {
				String functor = prologClauses.get(0).getFunctor();
				int arity = prologClauses.get(0).getArity();
				engine.abolish(functor, arity);
			}
		}
		return true;
	}

	public Object[] toArray(PrologClauses[] prologClauses) {
		return getClauses().values().toArray(prologClauses);
	}

	public Object[] toArray() {
		return getClauses().values().toArray();
	}

	public Set<Entry<String, PrologClauses>> entrySet() {
		return getClauses().entrySet();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractProgram other = (AbstractProgram) obj;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.equals(other.engine)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AbstractProgram [engine=" + engine + "]";
	}

}
