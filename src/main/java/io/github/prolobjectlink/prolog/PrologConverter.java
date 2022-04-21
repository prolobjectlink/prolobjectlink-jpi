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
