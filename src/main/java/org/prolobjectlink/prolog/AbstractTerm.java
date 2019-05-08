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

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractTerm implements PrologTerm {

	protected int type;
	protected final PrologProvider provider;

	protected AbstractTerm(int type, PrologProvider provider) {
		this.type = type;
		this.provider = provider;
	}

	protected final void checkIndex(int index) {
		if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	protected final void checkIndex(int index, int max) {
		if (index < 0 || index > max) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	protected final String removeQuoted(String functor) {
		if (functor != null && functor.startsWith("\'") && functor.endsWith("\'")) {
			return functor.substring(1, functor.length() - 1);
		}
		return functor;
	}

	protected final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		return provider.toTerm(o, from);
	}

	protected final <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from) {
		return provider.toTermArray(os, from);
	}

	protected final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return provider.fromTerm(term, to);
	}

	protected final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		return provider.fromTermArray(terms, to);
	}

	protected final PrologLogger getLogger() {
		return provider.getLogger();
	}

	public PrologTerm getArgument(int index) {
		PrologTerm[] array = getArguments();
		checkIndex(index, array.length);
		return array[index];
	}

	public PrologTerm getTerm() {
		return this;
	}

	public final int getType() {
		return type;
	}

	public final PrologTerm getRight() {
		return getArgument(1);
	}

	public final PrologTerm getLeft() {
		return getArgument(0);
	}

	public final PrologProvider getProvider() {
		return provider;
	}

}
