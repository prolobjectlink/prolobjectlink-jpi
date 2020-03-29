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
 * Runtime error raised when occurs one call to get get indicator method over a
 * term that no have indicator property. The indicator is a compound term and
 * atom property only. This runtime error is raised when we invoke
 * {@link PrologTerm#getIndicator()} over terms like Numbers and Variables
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class IndicatorError extends PrologError {

	private static final long serialVersionUID = 3634744000779459116L;

	/**
	 * Create an indicator runtime error. This constructor give the term over
	 * runtime error occurs.
	 * 
	 * @param term the term over runtime error occurs.
	 * @since 1.0
	 */
	public IndicatorError(Object term) {
		super("The term not have indicator: " + term);
	}

}
