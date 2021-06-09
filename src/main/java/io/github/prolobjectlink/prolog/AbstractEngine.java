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

import static io.github.prolobjectlink.prolog.PrologLogger.IO;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Partial implementation of {@link PrologEngine}.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractEngine implements PrologEngine {

	protected final PrologProvider provider;
	private static final String UNKNOWN = "unknown";

	protected AbstractEngine(PrologProvider provider) {
		this.provider = provider;
	}

	public final PrologProvider getProvider() {
		return provider;
	}

	public final void persist(Writer writer) {
		PrologEngine thisEngine = this;
		for (PrologClause prologClause : thisEngine) {
			try {
				writer.write("" + prologClause + "");
			} catch (IOException e) {
				getLogger().error(getClass(), IO, e);
			}
		}
	}

	public final void abolish(Class<?> cls) {
		PrologTerm term = getTerm(get(cls));
		String functor = term.getFunctor();
		int arity = term.getArity();
		abolish(functor, arity);
	}

	public final <O> void asserta(O o) {
		asserta(getTerm(get(o.getClass()), o));
	}

	public final void asserta(Class<?> cls) {
		asserta(getTerm(get(cls)));
	}

	public final <O> void asserta(O head, O... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i].getClass()), body[i]);
		}
		PrologTerm term = getTerm(get(head.getClass()), head);
		asserta(term, terms);
	}

	public final void asserta(Class<?> head, Class<?>... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i]));
		}
		PrologTerm term = getTerm(get(head));
		asserta(term, terms);
	}

	public final <O> void assertz(O o) {
		assertz(getTerm(get(o.getClass()), o));
	}

	public final void assertz(Class<?> cls) {
		assertz(getTerm(get(cls)));
	}

	public final <O> void assertz(O head, O... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i].getClass()), body[i]);
		}
		PrologTerm term = getTerm(get(head.getClass()), head);
		assertz(term, terms);
	}

	public final void assertz(Class<?> head, Class<?>... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i]));
		}
		PrologTerm term = getTerm(get(head));
		assertz(term, terms);
	}

	public final <O> boolean clause(O o) {
		return clause(getTerm(get(o.getClass()), o));
	}

	public final boolean clause(Class<?> cls) {
		return clause(getTerm(get(cls)));
	}

	public final <O> boolean clause(O head, O... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i].getClass()), body[i]);
		}
		PrologTerm term = getTerm(get(head.getClass()), head);
		return clause(term, terms);
	}

	public final boolean clause(Class<?> head, Class<?>... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i]));
		}
		PrologTerm term = getTerm(get(head));
		return clause(term, terms);
	}

	public final <O> void retract(O o) {
		retract(getTerm(get(o.getClass()), o));
	}

	public final void retract(Class<?> cls) {
		retract(getTerm(get(cls)));
	}

	public final <O> void retract(O head, O... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i].getClass()), body[i]);
		}
		PrologTerm term = getTerm(get(head.getClass()), head);
		retract(term, terms);
	}

	public final void retract(Class<?> head, Class<?>... body) {
		PrologTerm[] terms = new PrologTerm[body.length];
		for (int i = 0; i < body.length; i++) {
			terms[i] = getTerm(get(body[i]));
		}
		PrologTerm term = getTerm(get(head));
		retract(term, terms);
	}

	public <O> boolean unify(O o1, O o2) {
		if (o1.equals(o2)) {
			return true;
		}
		PrologTerm t1 = get(o1.getClass()).toTerm(provider);
		PrologTerm t2 = get(o2.getClass()).toTerm(provider);
		return unify(t1, t2);
	}

	public boolean unify(Class<?> cls1, Class<?> cls2) {
		if (cls1 == cls2) {
			return true;
		}
		PrologTerm t1 = get(cls1).toTerm(provider);
		PrologTerm t2 = get(cls2).toTerm(provider);
		return unify(t1, t2);
	}

	public final boolean unify(PrologTerm t1, PrologTerm t2) {
		return t1.unify(t2);
	}

	public final boolean contains(String goal) {
		return query(goal).hasSolution();
	}

	public final <O> boolean contains(O goal) {
		return query(goal).hasSolution();
	}

	public final boolean contains(Class<?> goal) {
		return query(goal).hasSolution();
	}

	public final boolean contains(PrologTerm goal) {
		return query(goal).hasSolution();
	}

	public final boolean contains(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).hasSolution();
	}

	public final <O> boolean contains(O goal, O... goals) {
		return query(goal, goals).hasSolution();
	}

	public final boolean contains(Class<?> goal, Class<?>... goals) {
		return query(goal, goals).hasSolution();
	}

	public final <O> PrologQuery query(O goal) {
		return query(get(goal.getClass()).toTerm(provider, goal));
	}

	public final PrologQuery query(Class<?> goal) {
		return query(get(goal).toTerm(provider));
	}

	public final <O> PrologQuery query(O goal, O... goals) {
		PrologTerm[] terms = new PrologTerm[goals.length];
		for (int i = 0; i < goals.length; i++) {
			terms[i] = getTerm(get(goals[i].getClass()), goals[i]);
		}
		PrologTerm term = getTerm(get(goal.getClass()), goal);
		return query(term, terms);
	}

	public final PrologQuery query(Class<?> goal, Class<?>... goals) {
		PrologTerm[] terms = new PrologTerm[goals.length];
		for (int i = 0; i < goals.length; i++) {
			terms[i] = getTerm(get(goals[i]));
		}
		PrologTerm term = getTerm(get(goal));
		return query(term, terms);
	}

	public final Map<String, PrologTerm> queryOne(String goal) {
		return query(goal).oneVariablesSolution();
	}

	public final Map<String, PrologTerm> queryOne(Class<?> goal) {
		return query(goal).oneVariablesSolution();
	}

	public final <O> Map<String, PrologTerm> queryOne(O goal) {
		return query(goal).oneVariablesSolution();
	}

	public final Map<String, PrologTerm> queryOne(PrologTerm goal) {
		return query(goal).oneVariablesSolution();
	}

	public final Map<String, PrologTerm> queryOne(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).oneVariablesSolution();
	}

	public final Map<String, PrologTerm> queryOne(Class<?> goal, Class<?>... goals) {
		return query(goal, goals).oneVariablesSolution();
	}

	public final <O> Map<String, PrologTerm> queryOne(O goal, O... goals) {
		return query(goal, goals).oneVariablesSolution();
	}

	public final List<Map<String, PrologTerm>> queryN(int n, String goal) {
		return Arrays.asList(query(goal).nVariablesSolutions(n));
	}

	@Override
	public final List<Map<String, PrologTerm>> queryN(int n, Class<?> goal) {
		return Arrays.asList(query(get(goal).toTerm(provider)).nVariablesSolutions(n));
	}

	@Override
	public final <O> List<Map<String, PrologTerm>> queryN(int n, O goal) {
		return Arrays.asList(query(goal).nVariablesSolutions(n));
	}

	public final List<Map<String, PrologTerm>> queryN(int n, PrologTerm term) {
		return Arrays.asList(query(term).nVariablesSolutions(n));
	}

	public final List<Map<String, PrologTerm>> queryN(int n, PrologTerm term, PrologTerm... terms) {
		return Arrays.asList(query(term, terms).nVariablesSolutions(n));
	}

	@Override
	public final List<Map<String, PrologTerm>> queryN(int n, Class<?> goal, Class<?>... goals) {
		return Arrays.asList(query(goal, goals).nVariablesSolutions(n));
	}

	@Override
	public final <O> List<Map<String, PrologTerm>> queryN(int n, O goal, O... goals) {
		return Arrays.asList(query(goal, goals).nVariablesSolutions(n));
	}

	public final List<Map<String, PrologTerm>> queryAll(String goal) {
		return query(goal).all();
	}

	@Override
	public final List<Map<String, PrologTerm>> queryAll(Class<?> goal) {
		PrologTerm term = get(goal).toTerm(provider);
		return query(term).all();
	}

	@Override
	public final <O> List<Map<String, PrologTerm>> queryAll(O goal) {
		return query(goal).all();
	}

	public final List<Map<String, PrologTerm>> queryAll(PrologTerm goal) {
		return query(goal).all();
	}

	public final List<Map<String, PrologTerm>> queryAll(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).all();
	}

	@Override
	public final List<Map<String, PrologTerm>> queryAll(Class<?> goal, Class<?>... goals) {
		return query(goal, goals).all();
	}

	@Override
	public final <O> List<Map<String, PrologTerm>> queryAll(O goal, O... goals) {
		return query(goal, goals).all();
	}

	public final Map<String, List<PrologClause>> getProgramMap() {
		Map<String, List<PrologClause>> m = new HashMap<String, List<PrologClause>>();
		for (PrologClause clause : this) {
			String key = clause.getIndicator();
			List<PrologClause> l = m.get(key);
			if (l == null) {
				l = new ArrayList<PrologClause>();
				l.add(clause);
				m.put(key, l);
			} else {
				l.add(clause);
			}
		}
		return m;
	}

	public final Set<PrologClause> getProgramClauses() {
		Set<PrologClause> c = new LinkedHashSet<PrologClause>();
		for (PrologClause prologClause : this) {
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

	public final DefaultQueryBuilder newQueryBuilder() {
		return new DefaultQueryBuilder(this);
	}

	public final DefaultClauseBuilder newClauseBuilder() {
		return new DefaultClauseBuilder(this);
	}

	public final boolean runOnOSX() {
		return getOSName().equals("Mac OS X") || getOSName().equals("Darwin");
	}

	public final boolean runOnWindows() {
		return getOSName().startsWith("Windows");
	}

	public final boolean runOnLinux() {
		return getOSName().equals("Linux");
	}

	public final String getOSName() {
		String os = System.getProperty("os.name");
		if (os == null)
			return UNKNOWN;
		return os;
	}

	public final String getOSArch() {
		return System.getProperty("os.arch");
	}

	public final PrologLogger getLogger() {
		return provider.getLogger();
	}

	@Override
	public final int size() {
		return provider.size();
	}

	@Override
	public final boolean isEmpty() {
		return provider.isEmpty();
	}

	@Override
	public final boolean containsKey(Object key) {
		return provider.containsKey(key);
	}

	@Override
	public final boolean containsValue(Object value) {
		return provider.containsValue(value);
	}

	@Override
	public final PrologMapping<?> get(Object key) {
		return provider.get(key);
	}

	@Override
	public final PrologMapping<?> put(Class<?> key, PrologMapping<?> value) {
		return provider.put(key, value);
	}

	@Override
	public final PrologMapping<?> remove(Object key) {
		return provider.remove(key);
	}

	@Override
	public final void putAll(Map<? extends Class<?>, ? extends PrologMapping<?>> m) {
		provider.putAll(m);
	}

	@Override
	public final void clear() {
		provider.clear();
	}

	@Override
	public final Set<Class<?>> keySet() {
		return provider.keySet();
	}

	@Override
	public final Collection<PrologMapping<?>> values() {
		return provider.values();
	}

	@Override
	public final Set<Entry<Class<?>, PrologMapping<?>>> entrySet() {
		return provider.entrySet();
	}

	@Override
	public final void register(PrologMapping<?> mapping) {
		put(mapping.getType(), mapping);
	}

	public final PrologTerm getTerm(PrologMapping<?> mapping) {
		return mapping.toTerm(provider);
	}

	public final <O> PrologTerm getTerm(PrologMapping<?> mapping, O o) {
		return mapping.toTerm(provider, o);
	}

	public final void unregister(PrologMapping<?> mapping) {
		remove(mapping.getType());
	}

	protected final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		return provider.toTerm(o, from);
	}

	protected final <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(
			Map<String, V>[] map, Class<K> from) {
		return provider.toTermMapArray(map, from);
	}

	protected final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return provider.fromTerm(term, to);
	}

	protected final <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to) {
		return provider.fromTerm(head, body, to);
	}

	protected final String removeQuoted(String functor) {
		if (functor != null && functor.startsWith("\'") && functor.endsWith("\'")) {
			return functor.substring(1, functor.length() - 1);
		}
		return functor;
	}

	@Override
	public final String toString() {
		return getName() + " " + getVersion();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		AbstractEngine other = (AbstractEngine) object;
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
