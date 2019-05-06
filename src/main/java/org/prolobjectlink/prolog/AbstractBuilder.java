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
