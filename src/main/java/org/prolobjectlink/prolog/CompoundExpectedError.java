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
 * Runtime error raised when occurs one call to some method over no compound
 * term like get arguments or get argument at some position. all atomics term no
 * have arguments and optionally over related invocations of the mentioned
 * methods this runtime error take place.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class CompoundExpectedError extends PrologError {

	private static final long serialVersionUID = -3064952286859633255L;

	/**
	 * Create an compound runtime error. This constructor give the term over runtime
	 * error occurs.
	 * 
	 * @param term the term over runtime error occurs.
	 * @since 1.0
	 */
	public CompoundExpectedError(Object term) {
		super("The expected term is not a compound term : " + term);
	}

}
