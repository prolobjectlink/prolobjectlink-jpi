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

import java.util.Map;

/**
 * PrologMapper is an special converter that help to do casting operations from
 * Prolog terms to the equivalent native terms and vice versa. The methods
 * contained in this interface specify the class for the final resulting object.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
interface PrologMapper {

	<K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(Map<String, V>[] map,
			Class<K> from);

	<K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map, Class<K> from);

	<K extends PrologTerm> K[][] toTermMatrix(Object[][] objects, Class<K[][]> from);

	<K extends PrologTerm> K[] toTermArray(Object[] objects, Class<K[]> from);

	/**
	 * Create an equivalent Prolog term using the given native term representation
	 * and cast this Prolog term to some specific given class.
	 * 
	 * @param o    native term representation to be converted
	 * @param from class to be cast the result Prolog term
	 * @param      <K> generic type that extends from {@link PrologTerm}
	 * @return an equivalent Prolog term using the given native term representation
	 *         of given class type.
	 * @since 1.0
	 */
	<K extends PrologTerm> K toTerm(Object o, Class<K> from);

	/**
	 * Create a native rule representation term from given head and body and cast
	 * this native term to some specific given class.
	 * 
	 * @param term Prolog term to be converted to native term.
	 * @param to   class to be cast the result native term
	 * @param      <K> generic type that represent native prolog type
	 * @return rule representation from given head and body of given class type.
	 * @since 1.0
	 */
	<K> K fromTerm(PrologTerm term, Class<K> to);

	/**
	 * Create a native term array representation from given Prolog term array and
	 * cast this native term array to some specific given array class.
	 * 
	 * @param terms Prolog term array to be converted to native array.
	 * @param to    class to be cast the result native term
	 * @param       <K> generic type that represent native prolog type
	 * @return native term array representation from given Prolog term array of
	 *         given array class type.
	 * @since 1.0
	 */
	<K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to);

	/**
	 * Create a native rule representation term from given head and body and cast
	 * this native term to some specific given class.
	 * 
	 * @param head rule head
	 * @param body rule body
	 * @param to   class to be cast the result native term
	 * @param      <K> generic type that represent native prolog type
	 * @return rule representation from given head and body of given class type.
	 * @since 1.0
	 */
	<K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to);

}
