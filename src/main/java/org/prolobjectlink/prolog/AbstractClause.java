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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Partial implementation of {@link PrologClause} interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractClause implements PrologClause {

	private boolean dynamic;
	private boolean multifile;
	private boolean discontiguous;

	private final PrologTerm head;
	private final PrologTerm body;

	private final PrologProvider provider;

	/**
	 * Create a new fact clause. A fatc clause is only represented by clause head
	 * and no have clause body. The body for this clause type is null. The other
	 * parameters are boolean clause properties. If a clause have any of this
	 * properties specify with true value.
	 * 
	 * @param provider      prolog provider
	 * @param head          clause head
	 * @param dynamic       true if clause is dynamic, false otherwise
	 * @param multifile     true if clause is multifile, false otherwise
	 * @param discontiguous true if clause is discontiguous, false otherwise
	 * @since 1.0
	 */
	protected AbstractClause(PrologProvider provider, PrologTerm head, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		this(provider, head, null, dynamic, multifile, discontiguous);
	}

	/**
	 * Create a new rule clause. A rule clause is represented by clause head and
	 * body. The other parameters are boolean clause properties. If a clause have
	 * any of this properties specify with true value.
	 * 
	 * @param provider      prolog provider
	 * @param head          clause head
	 * @param body          clause body
	 * @param dynamic       true if clause is dynamic, false otherwise
	 * @param multifile     true if clause is multifile, false otherwise
	 * @param discontiguous true if clause is discontiguous, false otherwise
	 * @since 1.0
	 */
	protected AbstractClause(PrologProvider provider, PrologTerm head, PrologTerm body, boolean dynamic,
			boolean multifile, boolean discontiguous) {
		this.head = head;
		this.body = body;
		this.provider = provider;
		this.dynamic = dynamic;
		this.multifile = multifile;
		this.discontiguous = discontiguous;
	}

	public final PrologTerm getTerm() {
		String neck = ":-";
		PrologTerm h = getHead();
		PrologTerm b = getBody();
		return provider.newStructure(neck, h, b);
	}

	public final PrologTerm getHead() {
		return head;
	}

	public final PrologTerm getBody() {
		return body;
	}

	public final PrologTerm[] getBodyArray() {
		PrologTerm ptr = getBody();
		List<PrologTerm> terms = new ArrayList<PrologTerm>();
		while (ptr != null && ptr.isCompound() && ptr.hasIndicator(",", 2)) {
			terms.add(ptr.getArgument(0));
			ptr = ptr.getArgument(1);
		}
		terms.add(ptr);
		return terms.toArray(new PrologTerm[0]);
	}

	public final Iterator<PrologTerm> getBodyIterator() {
		return new BodyIterator(getBodyArray());
	}

	public final String getFunctor() {
		return head.getFunctor();
	}

	public final int getArity() {
		return head.getArity();
	}

	public PrologTerm[] getArguments() {
		return head.getArguments();
	}

	public PrologTerm getArgument(int index) {
		return head.getArgument(index);
	}

	public final String getIndicator() {
		return head.getIndicator();
	}

	public final boolean isDirective() {
		return head == null && body != null;
	}

	public final boolean isFact() {
		return head != null && body == null;
	}

	public final boolean isRule() {
		return head != null && body != null;
	}

	public final boolean unify(PrologClause clause) {
		return head.unify(clause.getHead()) && body.unify(clause.getBody());
	}

	public final boolean isDynamic() {
		return dynamic;
	}

	public final boolean isMultifile() {
		return multifile;
	}

	public final boolean isDiscontiguous() {
		return discontiguous;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + (discontiguous ? 1231 : 1237);
		result = prime * result + (dynamic ? 1231 : 1237);
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + (multifile ? 1231 : 1237);
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
		AbstractClause other = (AbstractClause) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (discontiguous != other.discontiguous)
			return false;
		if (dynamic != other.dynamic)
			return false;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head)) {
			return false;
		}
		return multifile == other.multifile;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(getHead());
		if (isRule()) {
			b.append(":-\n\t");
			Iterator<PrologTerm> i = getBodyIterator();
			while (i.hasNext()) {
				b.append(i.next());
				if (i.hasNext()) {
					b.append(",\n\t");
				}
			}
		}
		b.append('.');
		return "" + b + "";
	}

	private class BodyIterator extends AbstractIterator<PrologTerm> implements Iterator<PrologTerm> {

		private int nextIndex;

		private final PrologTerm[] elements;

		protected BodyIterator(PrologTerm[] elements) {
			this.elements = elements;
		}

		public boolean hasNext() {
			return nextIndex < elements.length;
		}

		public PrologTerm next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return elements[nextIndex++];
		}

	}

}
