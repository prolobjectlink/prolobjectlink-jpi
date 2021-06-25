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
