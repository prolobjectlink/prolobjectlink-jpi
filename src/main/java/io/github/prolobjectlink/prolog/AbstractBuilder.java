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

/**
 * Partial implementation of Prolog Builder interface. Is an String Builder
 * wrapper to append the term in string form that compound the final clause
 * (Fact, Rule or Query).
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
abstract class AbstractBuilder implements PrologBuilder {

	protected StringBuilder builder;
	protected final PrologEngine engine;
	private final PrologProvider provider;

	AbstractBuilder(PrologEngine engine) {
		this.provider = engine.getProvider();
		this.builder = new StringBuilder();
		this.engine = engine;
	}

	protected final void append(Object object) {
		builder.append(object);
	}

	protected final void append(String functor, PrologTerm... arguments) {
		if (arguments != null && arguments.length > 0) {
			builder.append(provider.newStructure(functor, arguments));
		} else {
			builder.append(provider.newAtom(functor));
		}
	}

	protected final void append(Object left, String operator, Object right) {
		builder.append(left);
		append(' ');
		builder.append(operator);
		append(' ');
		builder.append(right);
	}

	public final PrologEngine getEngine() {
		return engine;
	}

	@Override
	public final boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		DefaultQueryBuilder other = (DefaultQueryBuilder) object;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.equals(other.engine)) {
			return false;
		}
		if (builder == null) {
			if (other.builder != null)
				return false;
		} else if (!builder.toString().equals(other.builder.toString())) {
			return false;
		}
		return true;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		result = prime * result + ((builder == null) ? 0 : builder.hashCode());
		return result;
	}

	@Override
	public final String toString() {
		return "" + builder + "";
	}

}
