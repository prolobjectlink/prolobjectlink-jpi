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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A PrologTerm that maps PrologTerm keys to PrologTerm values. A map cannot
 * contain duplicate keys. Each key can map to at most one value.
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public final class PrologMap extends AbstractMaps implements PrologList, Map<PrologTerm, PrologTerm> {

	private Map<PrologTerm, PrologTerm> map;

	PrologMap(PrologProvider provider, int size) {
		super(PrologTermType.MAP_TYPE, provider);
		map = new LinkedHashMap<PrologTerm, PrologTerm>(size);
	}

	PrologMap(PrologProvider provider, Map<? extends PrologTerm, ? extends PrologTerm> m) {
		this(provider);
		putAll(m);
	}

	PrologMap(PrologProvider provider) {
		this(provider, 16);
	}

	public boolean isList() {
		return true;
	}

	public boolean isStructure() {
		return false;
	}

	public boolean isEmptyList() {
		return map.size() == 0;
	}

	public String getFunctor() {
		return ".";
	}

	public PrologTerm[] getArguments() {
		return map.entrySet().toArray(new PrologTerm[map.size()]);
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrologMap other = (PrologMap) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map)) {
			return false;
		}
		return true;
	}

	@Override
	public Iterator<PrologTerm> iterator() {
		return new PrologMapIterator();
	}

	@Override
	public PrologTerm getHead() {
		return iterator().next();
	}

	@Override
	public PrologTerm getTail() {
		PrologMap m = new PrologMap(provider, map);
		m.remove(((Entry<?, ?>) getHead()).getKey());
		return m;
	}

	private class PrologMapIterator extends AbstractIterator<PrologTerm> implements Iterator<PrologTerm> {

		private final Iterator<Entry<PrologTerm, PrologTerm>> i;

		private PrologMapIterator() {
			i = map.entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return i.hasNext();
		}

		@Override
		public PrologTerm next() {
			return (PrologTerm) i.next();
		}

	}

	public PrologTerm put(PrologTerm key, PrologTerm value) {
		return map.put(key, value);
	}

	public Set<Entry<PrologTerm, PrologTerm>> entrySet() {
		return map.entrySet();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public PrologTerm get(Object key) {
		return map.get(key);
	}

	@Override
	public PrologTerm remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends PrologTerm, ? extends PrologTerm> m) {
		map.putAll(m);
	}

	@Override
	public Set<PrologTerm> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<PrologTerm> values() {
		return map.values();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public int size() {
		return map.size();
	}

}
