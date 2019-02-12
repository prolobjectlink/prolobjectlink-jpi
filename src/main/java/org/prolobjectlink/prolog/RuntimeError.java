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

public class RuntimeError extends Error {

	private static final long serialVersionUID = 4344277636991967495L;

	// parser errors
	static final String UNRECOGNIZABLE_TOKEN = "The current token is unrecognizable.";
	static final String LPAR_EXPECTED_TOKEN = "The current token is differents to expected prolog tokens left parenthesis '(' ";
	static final String RPAR_EXPECTED_TOKEN = "The current token is differents to expected prolog tokens right parenthesis ')' ";
	static final String COMMA_EXPECTED_TOKEN = "The current token is differents to expected prolog tokens comma ',' ";
	static final String NOT_DEFINED_OPERATOR = "The current token is not defined operator";
	static final String NOT_DEFINED_BUILT_IN = "The current token is not defined built-in predicate";
	static final String INVALID_IDICATOR_TERM = "The current token is not a correct predicate indicator";
	static final String DOT_EXPECTED_TOKEN = "The current token is not expected end dot";
	static final String NUMBER_EXPECTED = "The current token is not a number";

	// runtime errors
	static final String NULL_ARGUMENTS = "Arguments null.";
	static final String STACK_OVERFLOW = "Stack overflow.";
	static final String ARGUMENTS_OVERFLOW = "Arguments overflow.";
	static final String NULL_FUNCTOR = "The specified term's functor is null";
	static final String EMPTY_ARGUMENTS = "The Prolog term don't have argument.";
	static final String NUMBERS_HAVE_NOT_FUNCTOR = "Numbers don't have functor.";
	static final String VAR_HAVE_NOT_FUNCTOR = "Variables don't have functor.";
	static final String VAR_HAVE_NOT_ARITY = "Variables don't have arity value";
	static final String VAR_HAVE_NOT_ARGUMENTS = "Variables don't have arguments";
	static final String RANGE_NOT_VALID = "The specified range is not valid range";
	public static final String INDEX_OUT_OF_BOUND = "The indexed position is out of bound.";
	static final String CALLABLE_ERROR = "The term don't correspond with a predication";
	static final String ILLEGAL_FUNCTOR = "The specified term's functor is empty string";
	static final String INSTANTATION_ERROR = "The term is not instance sufficiently";
	static final String ILLEGAL_VAR_NAME = "Illegal variable name. Variable name should match with [A-Z_][A-Za-z0-9_]* regular expression.";
	static final String ILLEGAL_ATOM_VALUE = "Illegal atom value. Prolog atom value should match with [a-z][A-Za-z0-9_]* regular expression.";
	static final String ILLEGAL_FLOAT_VALUE = "Illegal float value. Prolog float number should match with [0-9]+|[0-9]+\\.[0-9]+|[-][0-9]+\\.[0-9]+ regular expression";
	static final String ILLEGAL_INTEGER_VALUE = "Illegal integer value. Prolog integer number should match with [0-9]+|[-][0-9]+ regular expression";
	static final String ILLEGAL_ARITY = "The specified arity is negative or greater than maximum arguments allowed";
	static final String NUMBERS_HAVE_NOT_KEY = "Numbers don't have key value.";
	static final String VAR_HAVE_NOT_KEY = "Variables don't have key value";
	static final String ATOMIC_HAVE_NOT_ARGUMENT_AT = "Atomics terms have not any argument at specified position";
	static final String NON_ARITMETHIC_OPERAND = "The term is not an aritmethic operand";
	static final String DOMAIN_ERROR = "Not less than zero number is expected";

	static final String ATOM_TYPE_EXPECTED = "Atom type expected";
	static final String INTEGER_TYPE_EXPECTED = "Integer type expected";
	static final String FLOAT_TYPE_EXPECTED = "Float type expected";
	static final String VARIABLE_TYPE_EXPECTED = "Variable type expected";
	static final String STRUCTURE_TYPE_EXPECTED = "Structure type expected";
	static final String LIST_TYPE_EXPECTED = "List type expected";
	static final String NUMBER_EXPECTED_TYPES = "Number type expected";
	static final String CALLABLE_EXPECTED_TYPES = "Callable type expected";
	static final String CHARACTER_TYPE_EXPECTED = "Character type expected";
	static final String EXPRESSION_TYPE_EXPECTED = "Expression type expected";
	static final String OBJECT_TYPE_EXPECTED = "Java Object type expected";
	static final String STREAM_TYPE_EXPECTED = "Stream type expected";
	static final String WRON_CHAR_REPRESENTATION = "Wron character representation";
	static final String WRON_INTEGER_REPRESENTATION = "Wron integer representation";
	static final String ARGUMENTS_INSTANTATION_ERROR = "Arguments are not sufficiently instantiated";
	static final String TYPE_ERROR = "Type error";
	static final String HEAD_NOT_ATOM = "The head of the list is not an atom term";
	static final String ILLEGAL_NUMBER_REPRESENTATION = "Illegal number reresentation";
	static final String NOT_PROLOG_TERM_INSTANCE = "The object is not prolog term instance";
	static final String LEFT_TERM_EXPECTED = "The expression don't have defined most left term";
	public static final String IMPOSSIBLE_UPDATE = "Impossible update for diferent clauses keys";
	static final String ILLEGAL_OPERATOR_PRIORITY = "Not valid operator prority";
	static final String ILLEGAL_OPERATOR_SPECIFIER = "Not valid operator specifier";

	public RuntimeError(String message) {
		super(message);
	}

	public RuntimeError(String message, Throwable t) {
		super(message + "\n Caused by: " + t);
	}

}
