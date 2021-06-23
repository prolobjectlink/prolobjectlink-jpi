/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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

import java.util.Collection;
import java.util.Map;

/**
 * A PrologTerm that maps PrologTerm keys to PrologTerm values. A map cannot
 * contain duplicate keys. Each key can map to at most one value.
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public interface PrologMap extends PrologList, Map<PrologTerm, PrologTerm> {

	/**
	 * Put all collections of entries in the map.
	 * 
	 * @param entries entries to be put in the map.
	 * @since 1.1
	 */
	public void putAll(Collection<Entry<PrologTerm, PrologTerm>> entries);

	/**
	 * True if this map contains an entry previously created
	 * 
	 * @return whether this map contains an entry previously created
	 * @since 1.1
	 */
	public boolean contains(Entry<PrologTerm, PrologTerm> entry);

	/**
	 * Remove in the map an entry previously created
	 * 
	 * @param entry entry to be removed from this map
	 * @since 1.1
	 */
	public void remove(Entry<PrologTerm, PrologTerm> entry);

	/**
	 * Put in the map an entry previously created
	 * 
	 * @param entry entry to be put in this map
	 * @since 1.1
	 */
	public void put(Entry<PrologTerm, PrologTerm> entry);

}
