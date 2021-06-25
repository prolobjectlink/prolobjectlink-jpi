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
 * PrologMapper is an special converter that help to do casting operations from
 * Prolog terms to the equivalent native terms and vice versa. The methods
 * contained in this interface specify the class for the final resulting object.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
interface PrologMapper {

	/**
	 * Create an equivalent Prolog terms map array using the given native terms map
	 * array representation and cast every Prolog term to some specific given class.
	 * The resulting map array contains map that have the same string key and the
	 * value for every key is a conversion from native term to Prolog term.
	 * 
	 * @param map  native terms map array to be converted in Prolog terms map array.
	 * @param from class to be cast the result Prolog term
	 * @param      <K> generic type that extends from {@link PrologTerm}
	 * @param      <V> generic type that extends from {@link Object} representing a
	 *             native prolog term.
	 * @return an equivalent Prolog terms map array using the given native terms map
	 *         array representation of given class type.
	 * @since 1.0
	 */
	<K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(Map<String, V>[] map,
			Class<K> from);

	/**
	 * Create an equivalent Prolog terms map using the given native terms map
	 * representation and cast every Prolog term to some specific given class. The
	 * resulting map have the same string key and the value for every key is a
	 * conversion from native term to Prolog term.
	 * 
	 * @param map  native terms map representation to be converted
	 * @param from class to be cast the result Prolog term
	 * @param      <K> generic type that extends from {@link PrologTerm}
	 * @param      <V> generic type that extends from {@link Object} representing a
	 *             native prolog term.
	 * @return an equivalent Prolog terms map using the given native terms map
	 *         representation of given class type.
	 * @since 1.0
	 */
	<K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map, Class<K> from);

	/**
	 * Create an equivalent Prolog terms matrix using the given native terms matrix
	 * representation and cast every Prolog terms matrix to some specific matrix
	 * component class.
	 * 
	 * @param objects native terms matrix representation to be converted
	 * @param from    class to be cast the result Prolog term
	 * @param         <K> generic type that extends from {@link PrologTerm}
	 * @return an equivalent Prolog terms matrix using the given native terms matrix
	 *         representation of array component class type.
	 * @since 1.0
	 */
	<K extends PrologTerm> K[][] toTermMatrix(Object[][] objects, Class<K[][]> from);

	/**
	 * Create an equivalent Prolog terms array using the given native terms array
	 * representation and cast this Prolog term array to some specific array
	 * component class.
	 * 
	 * @param objects native terms array representation to be converted
	 * @param from    class to be cast the result Prolog term
	 * @param         <K> generic type that extends from {@link PrologTerm}
	 * @return an equivalent Prolog terms array using the given native terms array
	 *         representation of array component class type.
	 * @since 1.0
	 */
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
