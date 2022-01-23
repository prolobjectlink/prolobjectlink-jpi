/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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

	public final boolean unify(PrologTerm t1, PrologTerm t2) {
		return t1.unify(t2);
	}

	public final boolean contains(String goal) {
		return query(goal).hasSolution();
	}

	public final boolean contains(PrologTerm goal) {
		return query(goal).hasSolution();
	}

	public final boolean contains(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).hasSolution();
	}

	public final Map<String, PrologTerm> queryOne(String goal) {
		return query(goal).oneVariablesSolution();
	}

	public final Map<String, PrologTerm> queryOne(PrologTerm goal) {
		return query(goal).oneVariablesSolution();
	}

	public final Map<String, PrologTerm> queryOne(PrologTerm goal, PrologTerm... goals) {
		return query(goal, goals).oneVariablesSolution();
	}

	public final List<Map<String, PrologTerm>> queryN(int n, String goal) {
		return Arrays.asList(query(goal).nVariablesSolutions(n));
	}

	public final List<Map<String, PrologTerm>> queryN(int n, PrologTerm term) {
		return Arrays.asList(query(term).nVariablesSolutions(n));
	}

	public final List<Map<String, PrologTerm>> queryN(int n, PrologTerm term, PrologTerm... terms) {
		return Arrays.asList(query(term, terms).nVariablesSolutions(n));
	}

	public final List<Map<String, PrologTerm>> queryAll(String goal) {
		return query(goal).all();
	}

	public final List<Map<String, PrologTerm>> queryAll(PrologTerm goal) {
		return query(goal).all();
	}

	public final List<Map<String, PrologTerm>> queryAll(PrologTerm goal, PrologTerm... goals) {
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

	public PrologTerm newMixin(String name) {
		return new PrologMixin(this, name);
	}

	public PrologTerm newMixin(String name, PrologTerm... declarations) {
		PrologMixin pi = new PrologMixin(this, name);
		for (int i = 0; i < declarations.length; i++) {
			pi.addMethod(declarations[i], false, false, false);
		}
		return pi;
	}

	public PrologTerm newClass(String name) {
		return new PrologClass(this, name);
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

	public final String getOSVersion() {
		return System.getProperty("os.version");
	}

	public final String getOSArch() {
		return System.getProperty("os.arch");
	}

	public final PrologLogger getLogger() {
		return provider.getLogger();
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
