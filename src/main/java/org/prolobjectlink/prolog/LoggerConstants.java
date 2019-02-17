/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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

public enum LoggerConstants {

	RUNTIME_ERROR("Runtime error "),

	FILE_NOT_FOUND("File not found "),

	CLASS_NOT_FOUND("Class not found "),

	UNKNOW_PREDICATE("Unknow predicate"),

	SYNTAX_ERROR("Syntax error in the file "),

	NON_SOLUTION("The query no have solution "),

	INDICATOR_NOT_FOUND("Predicate not found for"),

	IO("Some error occurs opening the file"),

	ERROR_LOADING_BUILT_INS("Error loading prolog built-ins "),

	DONT_WORRY("Don't worry about it, the file was create for you "),

	INTERRUPTED_ERROR("Thread interrupted error"),

	EXECUTION_ERROR("Thread execution error"),

	FILE_NOT_DELETE("File not delete "),

	INSTANTIATION("Instantiation error "),

	ILLEGAL_ACCESS("Illegal access error "),

	NO_SUCH_METHOD("No such method error"),

	SECURITY("Security error "),

	SQL_ERROR("SQL error "),

	UNKNOWN_HOST("Unknow Host error"),

	ILLEGAL_ARGUMENT("Illegal argument error"),

	INVOCATION_TARGET("Invocation target error"),

	NO_SUCH_FIELD("No such field error"),

	CLASS_CAST("Class cast error"),

	URI("URI Syntax error"),

	URL("URL Syntax error"),

	LINK("Link library error");

	private final String name;

	LoggerConstants(String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

}
