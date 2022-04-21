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
