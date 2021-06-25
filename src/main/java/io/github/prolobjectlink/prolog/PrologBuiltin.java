/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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

public abstract class PrologBuiltin implements PrologStructure {

	// 7.4 directives
	static final String INCLUDE = "include";
	static final String DYNAMIC = "dynamic";
	static final String MULTIFILE = "multifile";
	static final String DISCONTIGUOUS = "discontiguous";
	static final String ENSURE_LOADED = "ensure_loaded";
	static final String INITIALIZATION = "initialization";
	static final String CHAR_CONVERSION = "char_conversion";

	// 7.8 control constructs
	static final String CUT = "!";
	static final String NIL = "nil";
	static final String THROW = "throw";
	static final String CATCH = "catch";
	static final String EMPTY_FUNCTOR = "[]";
	static final String FAIL_FUNCTOR = "fail";
	static final String TRUE_FUNCTOR = "true";
	static final String FALSE_FUNCTOR = "false";

	// 8.2 term unification
	static final String UNIFY_WITH_OCCURS_CHECK = "unify_with_occurs_check";

	// 8.3 type testing
	static final String VAR = "var";
	static final String ATOM = "atom";
	static final String FLOAT = "float";
	static final String ATOMIC = "atomic";
	static final String NONVAR = "nonvar";
	static final String NUMBER = "number";
	static final String GROUND = "ground";
	static final String INTEGER = "integer";
	static final String COMPOUND = "compound";
	static final String CALLABLE = "callable";
	static final String CYCLIC_TERM = "cyclic_term";
	static final String ACYCLIC_TERM = "acyclic_term";

	// 8.4 term comparison
	static final String SORT = "sort";
	static final String KEYSORT = "keysort";
	static final String COMPARE = "compare";

	// 8.5 term creation and decomposition
	static final String ARG = "arg";
	static final String FUNCTOR = "functor";
	static final String COPY_TERM = "copy_term";
	static final String TERM_VARIABLES = "term_variables";

	// 8.6 arithmetics evaluation (operator)
	// 8.7 arithmetic comparison (operator)

	// 8.8 clause retrieval and information
	static final String CLAUSE = "clause";
	static final String CURRENT_PREDICATE = "current_predicate";

	// 8.9 clause creation and destruction
	static final String ABOLISH = "abolish";
	static final String ASSERTA = "asserta";
	static final String ASSERTZ = "assertz";
	static final String RETRACT = "retract";

	// 8.10 All solutions
	static final String BAGOF = "bagof";
	static final String SETOF = "setof";
	static final String FINDALL = "findall";

	// 8.11 Stream Selection and Control
	static final String OPEN = "open";
	static final String CLOSE = "close";
	static final String SET_INPUT = "set_input";
	static final String SET_OUTPUT = "set_output";
	static final String CURRENT_INPUT = "current_input";
	static final String CURRENT_OUTPUT = "current_output";

	// 8.12 character input/output
	// 8.13 byte input/output

	// 8.14 Term input/output
	static final String NL = "nl";
	static final String READ = "read";
	static final String WRITE = "write";

	// 8.15 logic and control
	static final String NOT = "\\+";
	static final String CALL = "call";
	static final String ONCE = "once";
	static final String REPEAT = "repeat";

	// 8.16 atomic term processing
	static final String SUB_ATOM = "sub_atom";
	static final String ATOM_CHARS = "atom_chars";
	static final String ATOM_CODES = "atom_codes";
	static final String ATOM_LENGTH = "atom_length";
	static final String ATOM_CONCAT = "atom_concat";
	static final String NUMBER_CODES = "number_codes";
	static final String NUMBER_CHARS = "number_chars";

	// 8.17 Implementation defined hooks
	static final String OP = "op";
	static final String HALT = "halt";
	static final String CHAR_CODE = "char_code";
	static final String CURRENT_OP = "current_op";
	static final String SET_PROLOG_FLAG = "set_prolog_flag";
	static final String CURRENT_PROLOG_FLAG = "current_prolog_flag";
	static final String CURRENT_CHAR_CONVERSION = "current_char_conversion";

	// 9.1 simple arithmetic functors
	static final String ABS = "abs";
	static final String EXP = "exp";
	static final String LOG = "log";
	static final String CBRT = "cbrt";
	static final String SIGN = "sign";
	static final String SQRT = "sqrt";
	static final String ROUND = "round";
	static final String FLOOR = "floor";
	static final String CEILING = "ceiling";
	static final String FLOAT_INTEGER_PART = "float_integer_part";
	static final String FLOAT_FRACTIONAL_PART = "float_fractional_part";
	static final String TRUNCATE = "truncate";

	// 9.3 other arithmetic functors
	static final String MAX = "max";
	static final String MIN = "min";
	static final String GCD = "gcd";
	static final String LCM = "lcm";

	// 9.4 bitwise functors

	// 9.5 trigonometric functors
	static final String SIN = "sin";
	static final String COS = "cos";
	static final String TAN = "tan";
	static final String ASIN = "asin";
	static final String ACOS = "acos";
	static final String ATAN = "atan";

	// 9.6 mathematical constants
	static final String E = "e";
	static final String PI = "pi";
	static final String EPSILON = "epsilon";

	// non ISO

	// java foreign integration
	static final String GET = "get";
	static final String SET = "set";
	static final String CAST = "cast";
	static final String INVOKE = "invoke";
	static final String OBJECT = "object";
	static final String INSTANCE_OF = "instance_of";
	static final String NEW_INSTANCE = "new_instance";
	static final String LOAD_LIBRARY = "load_library";
	static final String OBJECT_CONVERSION = "object_conversion";

	// java runtime reflection
	static final String CLASS_OF = "class_of";
	static final String FIELDS_OF = "fields_of";
	static final String METHODS_OF = "methods_of";
	static final String SUPER_CLASS_OF = "super_class_of";
	static final String CONSTRUCTORS_OF = "constructors_of";

	// runtime statistics
	static final String STATISTICS = "statistics";
	static final String CURRENT_TIME = "current_time";

	private PrologBuiltin() {
	}

}
