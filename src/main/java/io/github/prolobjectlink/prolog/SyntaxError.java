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
 * Runtime error raised when occurs one syntax error. Used when Prolog parser
 * can't continue parsing the string entry because a syntax error is detected
 * and need report this error to fix the prolog syntax error.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class SyntaxError extends PrologError {

	private static final long serialVersionUID = 2828526751667597579L;

	/**
	 * Create a syntax error exception passing the prolog text where syntax error
	 * take place.
	 * 
	 * @param string prolog text where syntax error take place.
	 * @since 1.0
	 */
	public SyntaxError(String string) {
		super("The string parsed have prolog syntax error: " + string);
	}

	/**
	 * Create a syntax error exception passing the prolog text where syntax error
	 * take place and the cause. The cause is used if the Prolog error was detected
	 * by other exception type and we need more specific cause in the reported
	 * error.
	 * 
	 * @param string prolog text where syntax error take place.
	 * @param cause  cause of the prolog error.
	 * @since 1.0
	 */
	public SyntaxError(String string, Throwable cause) {
		super("The string parsed have prolog syntax error: " + string, cause);
	}

}
