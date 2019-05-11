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

	public void log(Object sender, Level level, Object message);

	public void log(Object sender, Level level, Object message, Throwable t);

	public void trace(Object sender, Object message);

	public void trace(Object sender, Object message, Throwable t);

	public void debug(Object sender, Object message);

	public void debug(Object sender, Object message, Throwable t);

	public void info(Object sender, Object message);

	public void info(Object sender, Object message, Throwable t);

	public void warn(Object sender, Object message);

	public void warn(Object sender, Object message, Throwable t);

	public void error(Object sender, Object message);

	public void error(Object sender, Object message, Throwable t);

}
