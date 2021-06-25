/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

import java.util.List;
import java.util.Map;

/**
 * Converter for convert {@code PrologTerm} to the equivalent Java object taking
 * like reference the following equivalence table.
 * 
 * <table BORDER > <caption>Equivalence table</caption>
 * <tr>
 * <td ALIGN=CENTER><b>Java Object</b></td>
 * <td ALIGN=CENTER><b>Prolog Term</b></td>
 * </tr>
 * <tr>
 * <td><code>null</code></td>
 * <td>{@link PrologProvider#prologNil()}</td>
 * </tr>
 * <tr>
 * <td>{@link String}</td>
 * <td>{@link PrologAtom}</td>
 * </tr>
 * <tr>
 * <td>{@link Boolean#FALSE}</td>
 * <td>{@link PrologProvider#prologFalse()}</td>
 * </tr>
 * <tr>
 * <td>{@link Boolean#TRUE}</td>
 * <td>{@link PrologProvider#prologTrue()}</td>
 * </tr>
 * <tr>
 * <td>{@link Integer}</td>
 * <td>{@link PrologInteger}</td>
 * </tr>
 * <tr>
 * <td>{@link Float}</td>
 * <td>{@link PrologFloat}</td>
 * </tr>
 * <tr>
 * <td>{@link Double}</td>
 * <td>{@link PrologDouble}</td>
 * </tr>
 * <tr>
 * <td>{@link Long}</td>
 * <td>{@link PrologLong}</td>
 * </tr>
 * <tr>
 * <td>{@link Object}[]</td>
 * <td>{@link PrologList}</td>
 * </tr>
 * </table>
 * 
 * There are special cases of Java object that can be converted to Prolog
 * equivalent but the inverse case it's not possible. They are {@link Byte},
 * {@link Character}, {@link Short} that can be converted to
 * {@link PrologInteger} using your numeric value. The main problems is that
 * after {@link PrologInteger} conversion this value will be converted in
 * {@link Integer}.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologJavaConverter extends Map<Class<?>, Prologable<?>> {

	/**
	 * Create an equivalent Java object map list using the given Prolog terms map
	 * array. The resulting map list contains maps that have the same string key and
	 * the value for every key is a conversion from Prolog term to Java object.
	 * 
	 * @param maps Prolog terms map list to be converted in Java objects map list.
	 * @return an equivalent Java objects map array using the given Prolog terms map
	 *         list.
	 * @since 1.0
	 */
	public List<Map<String, Object>> toObjectMaps(Map<String, PrologTerm>[] maps);

	/**
	 * Create an equivalent Java object map using the given Prolog terms map. The
	 * resulting map have the same string key and the value for every key is a
	 * conversion from Prolog term to Java object.
	 * 
	 * @param map Prolog terms map representation to be converted
	 * @return an equivalent Java object map using the given Prolog terms map.
	 * @since 1.0
	 */
	public Map<String, Object> toObjectMap(Map<String, PrologTerm> map);

	/**
	 * Create a Java objects list from given Prolog term array.
	 * 
	 * @param terms Prolog term array to be converted to Java objects array.
	 * @return Java objects list representation from given Prolog term array.
	 * @since 1.0
	 */
	public List<Object> toObjectList(PrologTerm[] terms);

	/**
	 * Create an equivalent list of objects lists using the given Prolog terms
	 * matrix.
	 * 
	 * @param terms Prolog terms matrix to be converted
	 * @return an equivalent list of objects lists using the given Prolog terms
	 *         matrix representation.
	 * @since 1.0
	 */
	public List<List<Object>> toObjectLists(PrologTerm[][] terms);

	/**
	 * Create a Java objects array from given Prolog term array.
	 * 
	 * @param terms Prolog term array to be converted to Java objects array.
	 * @return Java objects array representation from given Prolog term array.
	 * @since 1.0
	 */
	public Object[] toObjectsArray(PrologTerm[] terms);

	/**
	 * Create an equivalent Prolog terms array using the given Java objects array.
	 * 
	 * @param objects Java objects array representation to be converted
	 * @return an equivalent Prolog terms array using the given Java objects array.
	 * @since 1.0
	 */
	public PrologTerm[] toTermsArray(Object[] objects);

	/**
	 * Create a Java object from given Prolog term.
	 * 
	 * @param term Prolog term to be converted to Java object.
	 * @return a Java object from given Prolog term.
	 * @since 1.0
	 */
	public Object toObject(PrologTerm term);

	/**
	 * Create an equivalent Prolog term using the given Java object.
	 * 
	 * @param object Java object to be converted
	 * @return an equivalent Prolog term using the given Java object.
	 * @since 1.0
	 */
	public PrologTerm toTerm(Object object);

	/**
	 * Check if the current functor have quotes at the start and end of the given
	 * functor. Return true if the current functor is enclosing between functors or
	 * false in other case.
	 * 
	 * @param functor string functor to be checked
	 * @return true if the current functor is enclosing between functors or false in
	 *         other case.
	 * @since 1.0
	 */
	public boolean containQuotes(String functor);

	/**
	 * Remove functor quotes if they are present. If the string functor don't have
	 * quotes, this method return the functor itself.
	 * 
	 * @param functor string functor to remove quotes if they are present.
	 * @return functor without quotes if they are present. The functor itself if
	 *         quotes are not present.
	 * @since 1.0
	 */
	public String removeQuotes(String functor);

	/**
	 * Register a PrologMapping to be used in object conversions
	 * 
	 * @param mapping PrologMapping to be used in object conversions.
	 * @since 1.1
	 */
	public void register(Prologable<?> mapping);

	/**
	 * Return a the most general form PrologTerm implicit in the PrologMapping
	 * 
	 * @param mapping PrologMapping to resolve PrologTerm
	 * @return the most general form PrologTerm implicit in the PrologMapping
	 * @since 1.1
	 */
	public PrologTerm getTerm(Prologable<?> mapping);

	/**
	 * Return the PrologTerm equivalent to Java object using the correspondent
	 * PrologMapping
	 * 
	 * @param mapping mapping PrologMapping to resolve PrologTerm
	 * @param o       Java object to convert in PrologTerm
	 * @return the PrologTerm equivalent to Java object
	 * @since 1.1
	 */
	public <O> PrologTerm getTerm(Prologable<?> mapping, O o);

	/**
	 * Remove a PrologMapping to be used in object conversions
	 * 
	 * @param mapping PrologMapping to be removed.
	 * @since 1.1
	 */
	public void unregister(Prologable<?> mapping);

}
