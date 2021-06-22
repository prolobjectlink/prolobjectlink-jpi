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
package io.github.prolobjectlink.prolog;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Persistent {@link Map} interface implementation for persist
 * {@link LinkedHashMap}. Don't have the same performance like
 * {@link LinkedHashMap} but is Prolog structure persistent. Is implemented used
 * a persistent linked list to preserve the insertion order.
 * 
 * @author Jose Zalacain
 *
 * @param <K> the type of the map keys
 * @param <V> the type of mapped values
 * @since 1.0
 */
class PrologLinkedMap<K, V> extends PrologHashMap<K, V> implements Map<K, V>, Serializable {

	private HashLinkedEntry<K, V> first;
	private HashLinkedEntry<K, V> last;

	private static final long serialVersionUID = -949826295407477806L;

	PrologLinkedMap() {
		super();
	}

	PrologLinkedMap(int initialCapacity) {
		super(initialCapacity);
	}

	PrologLinkedMap(Map<? extends K, ? extends V> m) {
		super(m);
	}

	public V put(K key, V value) {

		int hash = key.hashCode();
		int index = indexOf(hash);
		HashLinkedEntry<K, V> entry = new HashLinkedEntry<K, V>(key, value);
		V old = table[index] != null ? table[index].getValue() : null;
		if (old != null) {
			table[index].setValue(value);
		} else {
			table[index] = entry;
			size++;
		}

		float loadFactor = 0.75f;
		int capacity = table.length;
		if (size >= capacity * loadFactor) {

			// rehashing and copy

			int newCapacity = 2 * capacity;
			HashEntry<K, V>[] oldTable = table;

			size = 0;
			table = new HashLinkedEntry[newCapacity];
			for (int i = 0; i < oldTable.length; i++) {
				if (oldTable[i] != null) {
					HashEntry<K, V> e = oldTable[i];
					put(e.getKey(), e.getValue());
				}
			}

		}

		return old;

	}

	public V remove(Object key) {
		int hash = key.hashCode();
		int index = indexOf(hash);
		HashEntry<K, V> e = table[index];
		V v = e.getValue();
		table[index] = null;
		size--;
		return v;
	}

	public void clear() {
		size = 0;
		int i = 0;
		while (i < table.length) {
			table[i++] = null;
		}
	}

	class HashLinkedEntry<K0, V0> extends HashEntry<K0, V0> implements Map.Entry<K0, V0> {

		private final HashLinkedEntry<K0, V0> prev;
		private final HashLinkedEntry<K0, V0> next;

		@Deprecated
		HashLinkedEntry(K0 key, V0 value) {
			super(key, value);
			this.prev = null;
			this.next = null;
		}

		HashLinkedEntry(K0 key, V0 value, HashLinkedEntry<K0, V0> prev, HashLinkedEntry<K0, V0> next) {
			super(key, value);
			this.prev = prev;
			this.next = next;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			result = prime * result + ((prev == null) ? 0 : prev.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			HashLinkedEntry<K0, V0> other = (HashLinkedEntry<K0, V0>) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (next == null) {
				if (other.next != null)
					return false;
			} else if (!next.equals(other.next)) {
				return false;
			}
			if (prev == null) {
				if (other.prev != null)
					return false;
			} else if (!prev.equals(other.prev)) {
				return false;
			}
			return true;
		}

		private PrologLinkedMap<K, V> getOuterType() {
			return PrologLinkedMap.this;
		}

	}

}
