/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
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
