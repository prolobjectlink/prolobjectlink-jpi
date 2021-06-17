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

import static io.github.prolobjectlink.prolog.PrologTermType.ATOM_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.CUT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.DOUBLE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FAIL_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FALSE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FLOAT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.INTEGER_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LIST_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LONG_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.NIL_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.TRUE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.VARIABLE_TYPE;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Partial implementation of {@link PrologTerm} interface.
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

	public final String getIndicator() {
		return getFunctor() + "/" + getArity();
	}

	public final boolean hasIndicator(String functor, int arity) {
		return getFunctor().equals(functor) && getArity() == arity;
	}

	public PrologTerm getTerm() {
		return this;
	}

	public final int getType() {
		return type;
	}

	public final PrologProvider getProvider() {
		return provider;
	}

	/**
	 * Casts a PrologTerm to the class or interface represented by this
	 * {@code Class} object.
	 *
	 * @param term the object to be cast
	 * @param type the class or interface to be casted
	 * @return the PrologTerm after casting, or null if term is null
	 *
	 * @throws ClassCastException if the object is not null and is not assignable to
	 *                            the type T.
	 * @since 1.1
	 */
	protected final <T extends PrologTerm> T cast(PrologTerm term, Class<T> type) {
		return type.cast(term);
	}

	/**
	 * Casts a PrologTerm to the class or interface represented by this
	 * {@code Class} object.
	 *
	 * @param term the object to be cast
	 * @return the PrologTerm after casting, or null if term is null
	 *
	 * @throws ClassCastException if the object is not null and is not assignable to
	 *                            the type T.
	 * @since 1.1
	 */
	protected final <T extends PrologTerm> T cast(PrologTerm term) {
		return (T) term;
	}

	public final <T extends PrologTerm> T cast() {
		return cast(this);
	}

	public Object getObject() {
		PrologTerm term = this;
		switch (term.getType()) {
		case NIL_TYPE:
			return null;
		case CUT_TYPE:
			return "!";
		case FAIL_TYPE:
			return "fail";
		case TRUE_TYPE:
			return true;
		case FALSE_TYPE:
			return false;
		case ATOM_TYPE:
			return term.getFunctor();
		case FLOAT_TYPE:
			return ((PrologNumber) term).getFloatValue();
		case INTEGER_TYPE:
			return ((PrologNumber) term).getIntegerValue();
		case DOUBLE_TYPE:
			return ((PrologNumber) term).getDoubleValue();
		case LONG_TYPE:
			return ((PrologNumber) term).getLongValue();
		case VARIABLE_TYPE:
			return ((PrologVariable) term).getName();
		case LIST_TYPE:
			return fromTermArray(term.getArguments(), Object[].class);
		case STRUCTURE_TYPE:
			// FIXME Use mappings here;
		case OBJECT_TYPE:
			return term.getObject();
		default:
			return null;
		}
	}

	public final boolean isEntry() {
		return this instanceof Entry;
	}

	public final boolean isMap() {
		return this instanceof Map;
	}

}
