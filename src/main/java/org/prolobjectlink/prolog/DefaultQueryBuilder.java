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
final class DefaultQueryBuilder extends AbstractBuilder implements PrologQueryBuilder {

	DefaultQueryBuilder(PrologEngine engine) {
		super(engine);
	}

	public PrologQueryBuilder begin(String functor, PrologTerm... arguments) {
		append(functor, arguments);
		return this;
	}

	public PrologQueryBuilder begin(PrologTerm term) {
		append(term);
		return this;
	}

	public PrologQueryBuilder comma(PrologTerm body) {
		append(',');
		append(' ');
		append(body);
		return this;
	}

	public PrologQueryBuilder comma(String functor, PrologTerm... arguments) {
		append(',');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public PrologQueryBuilder comma(PrologTerm left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologQueryBuilder semicolon(PrologTerm term) {
		append(';');
		append(' ');
		append(term);
		return this;
	}

	public PrologQueryBuilder semicolon(String functor, PrologTerm... arguments) {
		append(';');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public PrologQueryBuilder semicolon(PrologTerm left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologQuery dot() {
		String q = "" + builder + "";
		builder = new StringBuilder();
		return engine.query(q);
	}

	public String getQueryString() {
		return "" + builder + "";
	}

}
