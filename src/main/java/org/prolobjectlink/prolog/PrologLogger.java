/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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

	public static final String UNKNOW_PREDICATE = "Unknow predicate";

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
