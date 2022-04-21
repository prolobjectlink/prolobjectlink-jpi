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
 * Runtime error raised when {@link PrologConverter} don't have an equivalent
 * term for some passed object. This error can be raised specifically when we
 * try to convert from {@link PrologTerm} to driver term and when we try to
 * convert from driver term to {@link PrologTerm}. In both cases if the given
 * conversion term object don't have equivalent, this error is raised.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class UnknownTermError extends PrologError {

	private static final long serialVersionUID = 3634055905766091444L;

	/**
	 * Create an unknown term runtime error. This constructor give the term that
	 * don't have equivalent.
	 * 
	 * @param unknow the term that don't have equivalent
	 * @since 1.0
	 */
	public UnknownTermError(Object unknow) {
		super("The object " + unknow + " is not a correct prolog term");
	}

}
