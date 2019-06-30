/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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
public interface PrologJavaConverter {

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

}
