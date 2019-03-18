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

/**
 * Prolog Provider is the class to interact with all prolog components (data
 * types, constants, logger, parser, converter and engine). Allow create data
 * types like atoms, numbers, lists, structures and variable terms. Allow
 * interact with the under-lying prolog engine to realize inferences operations.
 * Other used components are the prolog parser to parse prolog terms in string
 * from. The Prolog Provider offer a prolog system logger to report every errors
 * or exceptions.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologProvider extends PrologParser {

	/**
	 * True if wrapped engine implement ISO Prolog and false in other case
	 * 
	 * @return true if wrapped engine implement ISO Prolog and false in other case
	 * @since 1.0
	 */
	public boolean isCompliant();

	/**
	 * Get the prolog nil term representing the null data type for prolog data type
	 * system.
	 * 
	 * @return prolog nil term.
	 * @since 1.0
	 */
	public PrologTerm prologNil();

	/**
	 * Get the prolog term that represent the prolog cut built-in.
	 * 
	 * @return prolog cut term
	 * @since 1.0
	 */
	public PrologTerm prologCut();

	/**
	 * Get the prolog fail term that represent fail built-in.
	 * 
	 * @return prolog fail term
	 * @since 1.0
	 */
	public PrologTerm prologFail();

	/**
	 * Get the prolog true term that represent true built-in.
	 * 
	 * @return prolog true term
	 * @since 1.0
	 */
	public PrologTerm prologTrue();

	/**
	 * Get the prolog false term that represent false built-in.
	 * 
	 * @return prolog false term
	 * @since 1.0
	 */
	public PrologTerm prologFalse();

	/**
	 * Get the prolog empty list term.
	 * 
	 * @return prolog empty list term.
	 * @since 1.0
	 */
	public PrologTerm prologEmpty();

	/**
	 * Create a new prolog engine instance ready to be operate. The created engine
	 * is clause empty and only have the defaults supported built-ins.
	 * 
	 * @return new prolog engine instance
	 * @since 1.0
	 */
	public PrologEngine newEngine();

	/**
	 * Create a new prolog engine instance ready to be operate. The created engine
	 * consult the given file loading the clauses present in this file and the
	 * defaults supported built-ins. Is equivalent to
	 * {@code newEngine().consult(file);}
	 * 
	 * @param file file path to be consulted
	 * @return new prolog engine instance
	 * @since 1.0
	 */
	public PrologEngine newEngine(String file);

	/**
	 * Create a prolog atom term setting like atom value the given string.
	 * 
	 * @param functor string value for the atom
	 * @return a prolog atom term
	 * @since 1.0
	 */
	public PrologAtom newAtom(String functor);

	/**
	 * Create a prolog float number instance with 0.0 value.
	 * 
	 * @return prolog float number with 0.0 value.
	 * @since 1.0
	 */
	public PrologFloat newFloat();

	/**
	 * Create a prolog float number instance with 0.0 value.
	 * 
	 * @param value numeric value
	 * @return prolog float number with 0.0 value.
	 * @since 1.0
	 */
	public PrologFloat newFloat(Number value);

	/**
	 * Create a prolog double number instance with 0.0 value.
	 * 
	 * @return prolog double number with 0.0 value.
	 * @since 1.0
	 */
	public PrologDouble newDouble();

	/**
	 * Create a prolog double number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog double number
	 * @since 1.0
	 */
	public PrologDouble newDouble(Number value);

	/**
	 * Create a prolog integer number instance with 0 value.
	 * 
	 * @return prolog integer number with 0 value.
	 * @since 1.0
	 */
	public PrologInteger newInteger();

	/**
	 * Create a prolog integer number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog integer number
	 * @since 1.0
	 */
	public PrologInteger newInteger(Number value);

	/**
	 * Create a prolog long number instance with 0 value.
	 * 
	 * @return prolog long number with 0 value.
	 * @since 1.0
	 */
	public PrologLong newLong();

	/**
	 * Create a prolog long number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog long number
	 * @since 1.0
	 */
	public PrologLong newLong(Number value);

	/**
	 * Create an anonymous variable instance with associated index. Index is a non
	 * negative integer that represent the variable position of the Structure where
	 * the variable is first time declared.
	 * 
	 * @param position Position of its Structure where the variable is first time
	 *                 declared.
	 * @return An anonymous variable instance with associated index.
	 * @throws IllegalArgumentException if position is a negative number
	 * @since 1.0
	 */
	public PrologVariable newVariable(int position);

	/**
	 * Create an named variable instance with associated index. Index is a non
	 * negative integer that represent the variable position of the Structure where
	 * the variable is first time declared.
	 * 
	 * @param name     variable name (upper case beginning)
	 * 
	 * @param position Position of its Structure where the variable is first time
	 *                 declared.
	 * @return A named variable instance with associated index.
	 * @throws IllegalArgumentException if position is a negative number
	 * @since 1.0
	 */
	public PrologVariable newVariable(String name, int position);

	/**
	 * Create an empty prolog list term. The created prolog list is equivalent to
	 * empty list term if the returned instance is not the prolog empty list itself.
	 * 
	 * @return an empty prolog list term
	 * @since 1.0
	 */
	public PrologList newList();

	public PrologList newList(PrologTerm head);

	public PrologList newList(PrologTerm[] arguments);

	public PrologList newList(PrologTerm head, PrologTerm tail);

	public PrologList newList(PrologTerm[] arguments, PrologTerm tail);

	public PrologStructure newStructure(String functor, PrologTerm... arguments);

	/**
	 * Create a prolog structure that represent an expression defined by your left
	 * and right operands separated by infix operator. The structure instance have
	 * like functor the expression operator and have two operands arguments terms.
	 * In other words the indicator for the resulting instance term is
	 * <tt>operator/2</tt>. The term creation not check operator definition and for
	 * this reason during inference process if the operator is not a supported
	 * built-in or define by <tt>op/3</tt> the inference fail.
	 * 
	 * @param left     left hand operand
	 * @param operator infix operand
	 * @param right    right hand operand
	 * @return a prolog structure that represent an expression
	 * @since 1.0
	 */
	public PrologTerm newStructure(PrologTerm left, String operator, PrologTerm right);

	/**
	 * Get a prolog converter instance to map the abstract prolog data types to
	 * under-laying prolog implementations data types.
	 * 
	 * @return prolog converter instance
	 * @since 1.0
	 */
	public <K> PrologConverter<K> getConverter();

	/**
	 * Get a prolog parser instance to parser the strings with prolog syntax.
	 * 
	 * @return prolog parser instance
	 * @since 1.0
	 */
	public PrologParser getParser();

	/**
	 * Get the prolog system logger instance to report any errors or exceptions
	 * 
	 * @return prolog system logger instance
	 * @since 1.0
	 */
	public PrologLogger getLogger();

}
