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
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
final class DefaultClauseBuilder extends AbstractBuilder implements PrologClauseBuilder {

	DefaultClauseBuilder(PrologEngine engine) {
		super(engine);
	}

	public PrologClauseBuilder begin(PrologTerm term) {
		append(' ');
		append(term);
		return this;
	}

	public PrologClauseBuilder begin(String functor, PrologTerm... arguments) {
		append(functor, arguments);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm body) {
		append(':');
		append('-');
		append(' ');
		append(body);
		return this;
	}

	public PrologClauseBuilder neck(String functor, PrologTerm... arguments) {
		append(':');
		append('-');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm left, String operator, int right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm left, String operator, PrologTerm right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(PrologTerm body) {
		append(',');
		append(' ');
		append(body);
		return this;
	}

	public PrologClauseBuilder comma(String functor, PrologTerm... arguments) {
		append(',');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public PrologClauseBuilder comma(PrologTerm left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public String getClauseString() {
		return "" + builder + "";
	}

	public boolean clause() {
		String c = getClauseString();
		builder = new StringBuilder();
		return engine.clause(c);
	}

	public void asserta() {
		String c = getClauseString();
		builder = new StringBuilder();
		engine.asserta(c);
	}

	public void assertz() {
		String c = getClauseString();
		builder = new StringBuilder();
		engine.assertz(c);
	}

	public void retract() {
		String c = getClauseString();
		builder = new StringBuilder();
		engine.retract(c);
	}

}
