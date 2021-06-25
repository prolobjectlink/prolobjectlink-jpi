/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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

import java.util.Map;

/**
 * Converter for convert {@code PrologTerm} to the equivalent native {@code T}
 * term representation.
 * 
 * @author Jose Zalacain
 * @since 1.0
 * @param <T> native Term Representation
 */
public interface PrologConverter<T> extends PrologMapper {

	/**
	 * Create an equivalent Prolog terms map array using the given native terms map
	 * array representation. The resulting map array contains map that have the same
	 * string key and the value for every key is a conversion from native term to
	 * Prolog term.
	 * 
	 * @param map native terms map array to be converted in Prolog terms map array.
	 * @return an equivalent Prolog terms map array using the given native terms map
	 *         array representation.
	 * @since 1.0
	 */
	public Map<String, PrologTerm>[] toTermMapArray(Map<String, T>[] map);

	/**
	 * Create an equivalent Prolog terms map using the given native terms map
	 * representation. The resulting map have the same string key and the value for
	 * every key is a conversion from native term to Prolog term.
	 * 
	 * @param map native terms map representation to be converted
	 * @return an equivalent Prolog terms map using the given native terms map
	 *         representation.
	 * @since 1.0
	 */
	public Map<String, PrologTerm> toTermMap(Map<String, T> map);

	/**
	 * Create an equivalent Prolog terms matrix using the given native terms matrix
	 * representation.
	 * 
	 * @param terms native terms matrix representation to be converted
	 * @return an equivalent Prolog terms matrix using the given native terms matrix
	 *         representation.
	 * @since 1.0
	 */
	public PrologTerm[][] toTermMatrix(T[][] terms);

	/**
	 * Create an equivalent Prolog terms array using the given native terms array
	 * representation.
	 * 
	 * @param terms native terms array representation to be converted
	 * @return an equivalent Prolog terms array using the given native terms array
	 *         representation.
	 * @since 1.0
	 */
	public PrologTerm[] toTermArray(T[] terms);

	/**
	 * Create an equivalent Prolog term using the given native term representation.
	 * 
	 * @param prologTerm native term representation to be converted
	 * @return an equivalent Prolog term using the given native term representation.
	 * @since 1.0
	 */
	public PrologTerm toTerm(T prologTerm);

	/**
	 * Create a native term representation from given Prolog term.
	 * 
	 * @param term Prolog term to be converted to native prolog term
	 * @return native term from given Prolog term.
	 * @since 1.0
	 */
	public T fromTerm(PrologTerm term);

	/**
	 * Create a native term array representation from given Prolog term array.
	 * 
	 * @param terms Prolog term array to be converted to native array.
	 * @return native term array representation from given Prolog term array.
	 * @since 1.0
	 */
	public T[] fromTermArray(PrologTerm[] terms);

	/**
	 * Create a native rule representation term from given head and body.
	 * 
	 * @param head rule head
	 * @param body rule body
	 * @return rule representation from given head and body.
	 * @since 1.0
	 */
	public T fromTerm(PrologTerm head, PrologTerm[] body);

	/**
	 * Get the generic class for the current Prolog converter at runtime.
	 * 
	 * @return the converter generic class
	 * @since 1.0
	 */
	public Class<T> getGenericClass();

	/**
	 * Create a Prolog provider instance.
	 * 
	 * @return a Prolog provider instance.
	 * @since 1.0
	 */
	public PrologProvider createProvider();

}
