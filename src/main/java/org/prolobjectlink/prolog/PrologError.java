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
package org.prolobjectlink.prolog;

public class PrologError extends Error {

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
	static final String INDEX_OUT_OF_BOUND = "The indexed position is out of bound.";
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
	static final String IMPOSSIBLE_UPDATE = "Impossible update for diferent clauses keys";
	static final String ILLEGAL_OPERATOR_PRIORITY = "Not valid operator prority";
	static final String ILLEGAL_OPERATOR_SPECIFIER = "Not valid operator specifier";

	public PrologError(String message) {
		super(message);
	}

	public PrologError(String message, Throwable t) {
		super(message + "\n Caused by: " + t);
	}

}
