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
package io.github.prolobjectlink.prolog;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger platform interface to log message at any level. Is an adapter for
 * {@link Logger} adapting the Java logger mechanism for use with the most
 * popular logger methods. This logger mechanism is accessible from
 * {@link PrologProvider#getLogger()} or {@link PrologEngine#getLogger()} This
 * logger interface have all traditional methods used to log messages at
 * different levels (trace, debug, info,warn,error).
 * 
 * The levels used for this logger interface are {@link Level} constants present
 * in the table.
 * 
 * <table BORDER > <caption>Level table</caption>
 * <tr>
 * <td ALIGN=CENTER><b>Method</b></td>
 * <td ALIGN=CENTER><b>Level</b></td>
 * </tr>
 * <tr>
 * <td>{@link #trace(Object, Object, Throwable)}</td>
 * <td>{@link Level#FINEST}</td>
 * </tr>
 * <tr>
 * <td>{@link #debug(Object, Object, Throwable)}</td>
 * <td>{@link Level#FINE}</td>
 * </tr>
 * <tr>
 * <td>{@link #info(Object, Object, Throwable)}</td>
 * <td>{@link Level#INFO}</td>
 * </tr>
 * <tr>
 * <td>{@link #warn(Object, Object, Throwable)}</td>
 * <td>{@link Level#WARNING}</td>
 * </tr>
 * <tr>
 * <td>{@link #error(Object, Object, Throwable)}</td>
 * <td>{@link Level#SEVERE}</td>
 * </tr>
 * </table>
 * 
 * By default the platform implement a logger mechanism for drop log messages in
 * Operating System temporal directory into files named
 * prolobjectlink-YYYY.MM.DD.
 * 
 * In {@link AbstractLogger} class there are many implementations for this
 * interface. Every final implementation class can extends from
 * {@link AbstractLogger}.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologLogger {

	public static final String RUNTIME_ERROR = "Runtime error ";

	public static final String FILE_NOT_FOUND = "File not found ";

	public static final String CLASS_NOT_FOUND = "Class not found ";

	public static final String UNKNOWN_PREDICATE = "Unknow predicate";

	public static final String SYNTAX_ERROR = "Syntax error in the file ";

	public static final String NON_SOLUTION = "The query no have solution ";

	public static final String INDICATOR_NOT_FOUND = "Predicate not found for";

	public static final String IO = "Some error occurs opening the file";

	public static final String ERROR_LOADING_BUILT_INS = "Error loading prolog built-ins ";

	public static final String DONT_WORRY = "Don't worry about it, the file was create for you ";

	public static final String INTERRUPTED_ERROR = "Thread interrupted error";

	public static final String EXECUTION_ERROR = "Thread execution error";

	public static final String FILE_NOT_DELETE = "File not delete ";

	public static final String INSTANTIATION = "Instantiation error ";

	public static final String ILLEGAL_ACCESS = "Illegal access error ";

	public static final String NO_SUCH_METHOD = "No such method error";

	public static final String SECURITY = "Security error ";

	public static final String SQL_ERROR = "SQL error ";

	public static final String UNKNOWN_HOST = "Unknow Host error";

	public static final String ILLEGAL_ARGUMENT = "Illegal argument error";

	public static final String INVOCATION_TARGET = "Invocation target error";

	public static final String NO_SUCH_FIELD = "No such field error";

	public static final String CLASS_CAST = "Class cast error";

	public static final String URI = "URI Syntax error";

	public static final String URL = "URL Syntax error";

	public static final String LINK = "Link library error";

	/**
	 * Log a message from a given object sender at the given level.
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param level   log level.
	 * @param message message to be logged.
	 * @since 1.0
	 */
	public void log(Object sender, Level level, Object message);

	/**
	 * Log a message from a given object sender at the given level. Append a
	 * {@link Throwable} argument used for log exceptions if is needed.
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param level   log level.
	 * @param message message to be logged.
	 * @param t       argument used for log exceptions.
	 * @since 1.0
	 */
	public void log(Object sender, Level level, Object message, Throwable t);

	/**
	 * Log a message from a given object sender at {@link Level#FINEST} level. Is a
	 * shortcut to {@code log(sender, Level.FINEST, message);}
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @since 1.0
	 */
	public void trace(Object sender, Object message);

	/**
	 * Log a message from a given object sender at {@link Level#FINEST} level.
	 * Append a {@link Throwable} argument used for log exceptions if is needed. Is
	 * a shortcut to {@code log(sender, Level.FINEST, message, t);}
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @param t       argument used for log exceptions.
	 * @since 1.0
	 */
	public void trace(Object sender, Object message, Throwable t);

	/**
	 * Log a message from a given object sender at {@link Level#FINE} level. Is a
	 * shortcut to {@code log(sender, Level.FINE, message);}
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @since 1.0
	 */
	public void debug(Object sender, Object message);

	/**
	 * Log a message from a given object sender at {@link Level#FINE} level. Append
	 * a {@link Throwable} argument used for log exceptions if is needed. Is a
	 * shortcut to {@code log(sender, Level.FINE, message, t);}
	 * 
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @param t       argument used for log exceptions.
	 * @since 1.0
	 */
	public void debug(Object sender, Object message, Throwable t);

	/**
	 * Log a message from a given object sender at {@link Level#INFO} level. Is a
	 * shortcut to {@code log(sender, Level.INFO, message);}
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @since 1.0
	 */
	public void info(Object sender, Object message);

	/**
	 * Log a message from a given object sender at {@link Level#INFO} level. Append
	 * a {@link Throwable} argument used for log exceptions if is needed. Is a
	 * shortcut to {@code log(sender, Level.INFO, message, t);}
	 * 
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @param t       argument used for log exceptions.
	 * @since 1.0
	 */
	public void info(Object sender, Object message, Throwable t);

	/**
	 * Log a message from a given object sender at {@link Level#WARNING} level. Is a
	 * shortcut to {@code log(sender, Level.WARNING, message);}
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @since 1.0
	 */
	public void warn(Object sender, Object message);

	/**
	 * Log a message from a given object sender at {@link Level#WARNING} level.
	 * Append a {@link Throwable} argument used for log exceptions if is needed. Is
	 * a shortcut to {@code log(sender, Level.WARNING, message, t);}
	 * 
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @param t       argument used for log exceptions.
	 * @since 1.0
	 */
	public void warn(Object sender, Object message, Throwable t);

	/**
	 * Log a message from a given object sender at {@link Level#SEVERE} level. Is a
	 * shortcut to {@code log(sender, Level.SEVERE, message);}
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @since 1.0
	 */
	public void error(Object sender, Object message);

	/**
	 * Log a message from a given object sender at {@link Level#SEVERE} level.
	 * Append a {@link Throwable} argument used for log exceptions if is needed. Is
	 * a shortcut to {@code log(sender, Level.SEVERE, message, t);}
	 * 
	 * 
	 * @param sender  object that invoke the logger service.
	 * @param message message to be logged.
	 * @param t       argument used for log exceptions.
	 * @since 1.0
	 */
	public void error(Object sender, Object message, Throwable t);

}
