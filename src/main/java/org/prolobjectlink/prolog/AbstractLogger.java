/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

import java.util.logging.Level;

/**
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
