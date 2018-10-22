/*
 * #%L
 * prolobjectlink-jpe
 * %%
 * Copyright (C) 2012 - 2018 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.platform.logging;

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

	CLASS_CAST("Class cast error");

	private final String name;

	LoggerConstants(String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

}
