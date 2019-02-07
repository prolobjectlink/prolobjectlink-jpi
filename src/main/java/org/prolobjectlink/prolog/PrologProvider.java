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

	// prolog constants term

	public PrologTerm prologNil();

	public PrologTerm prologCut();

	public PrologTerm prologFail();

	public PrologTerm prologTrue();

	public PrologTerm prologFalse();

	public PrologTerm prologEmpty();

	// engine

	public PrologEngine newEngine();

	public PrologEngine newEngine(String path);

	// prolog term instantiation

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

	public PrologList newList();

	public PrologList newList(PrologTerm[] arguments);

	public PrologList newList(PrologTerm head, PrologTerm tail);

	public PrologList newList(PrologTerm[] arguments, PrologTerm tail);

	public PrologStructure newStructure(String functor, PrologTerm... arguments);

	public PrologTerm newStructure(PrologTerm left, String operator, PrologTerm right);

	public <K> PrologConverter<K> getConverter();

	public PrologParser getParser();

}
