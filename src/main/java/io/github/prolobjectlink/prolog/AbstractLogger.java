/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

import java.util.logging.Level;

/**
 * Partial implementation of {@link PrologLogger} interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractLogger implements PrologLogger {

	public final void log(Object sender, Level level, Object message) {
		log(sender, level, message, null);
	}

	public final void trace(Object sender, Object message) {
		log(sender, Level.FINEST, message);
	}

	public final void trace(Object sender, Object message, Throwable t) {
		log(sender, Level.FINEST, message, t);
	}

	public final void debug(Object sender, Object message) {
		log(sender, Level.FINE, message);
	}

	public final void debug(Object sender, Object message, Throwable t) {
		log(sender, Level.FINE, message, t);
	}

	public final void info(Object sender, Object message) {
		log(sender, Level.INFO, message);
	}

	public final void info(Object sender, Object message, Throwable t) {
		log(sender, Level.INFO, message, t);
	}

	public final void warn(Object sender, Object message) {
		log(sender, Level.WARNING, message);
	}

	public final void warn(Object sender, Object message, Throwable t) {
		log(sender, Level.WARNING, message, t);
	}

	public final void error(Object sender, Object message) {
		log(sender, Level.SEVERE, message);
	}

	public final void error(Object sender, Object message, Throwable t) {
		log(sender, Level.SEVERE, message, t);
	}

}
