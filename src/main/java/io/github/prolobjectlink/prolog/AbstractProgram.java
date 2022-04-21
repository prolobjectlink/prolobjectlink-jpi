/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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
