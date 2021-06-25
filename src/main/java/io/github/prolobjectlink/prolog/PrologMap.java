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
