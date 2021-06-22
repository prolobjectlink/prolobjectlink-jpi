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

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

class PrologHashMap<K, V> extends AbstractMap<K, V> {

	protected int size;
	protected HashEntry<K, V>[] table;
	private static final long serialVersionUID = -5488211660761949073L;

	PrologHashMap() {
		this(16);
	}

	PrologHashMap(int initialCapacity) {
		table = new HashEntry[initialCapacity];
	}

	protected final void checkIndex(int index) {
		if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	protected final void checkIndex(int index, int max) {
		if (index < 0 || index > max) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	PrologHashMap(Map<? extends K, ? extends V> m) {
		if (m != null) {
			table = new HashEntry[m.size()];
			putAll(m);
		}
	}

	protected final int indexOf(int hash) {
		int capacity = table.length;
		int i = hash < 0 ? -hash % capacity : hash % capacity;
		K key = table[i] != null ? table[i].getKey() : null;
		while (key != null && key.hashCode() != hash) {
			i = (i + 1) % capacity;
			key = table[i] != null ? table[i].getKey() : null;
		}
		return i;
	}

	@Override
	protected final HashEntry<K, V> getEntry(Object key) {
		int hash = (key == null) ? 0 : key.hashCode();
		int i = indexOf(hash);
		return table[i];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + size;
		result = prime * result + Arrays.hashCode(table);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrologHashMap<?, ?> other = (PrologHashMap<?, ?>) obj;
		if (size != other.size)
			return false;
		return Arrays.equals(table, other.table);
	}

	public final int size() {
		return size;
	}

	public V put(K key, V value) {

		int hash = key.hashCode();
		int index = indexOf(hash);
		HashEntry<K, V> entry = new HashEntry<K, V>(key, value);
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
			table = new HashEntry[newCapacity];
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

	public final Set<K> keySet() {
		return new KeySet();
	}

	public final Collection<V> values() {
		return new ValueCollection();
	}

	public final Set<Map.Entry<K, V>> entrySet() {
		return new EntrySet();
	}

	class HashEntry<K0, V0> implements Map.Entry<K0, V0> {

		private K0 key;
		private V0 value;

		public HashEntry(K0 key, V0 value) {
			this.key = key;
			this.value = value;
		}

		public K0 getKey() {
			return key;
		}

		public V0 getValue() {
			return value;
		}

		public V0 setValue(V0 value) {
			this.value = value;
			return value;
		}

		@Override
		public String toString() {
			return key + " = " + value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HashEntry<?, ?> other = (HashEntry<?, ?>) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

	}

	private class KeySet extends AbstractSet<K> {

		@Override
		public Iterator<K> iterator() {
			return new KeySetIterator();
		}

		@Override
		public int size() {
			return size;
		}

	}

	private class ValueCollection extends AbstractCollection<V> {

		@Override
		public Iterator<V> iterator() {
			return new ValueSetIterator();
		}

		@Override
		public int size() {
			return size;
		}

	}

	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			return new EntrySetIterator();
		}

		@Override
		public int size() {
			return size;
		}

	}

	private abstract class AbstractIterator {

		private int lastIndex;
		private HashEntry<K, V> next;

		public AbstractIterator() {
			int capacity = table.length;
			while (lastIndex < capacity && next == null) {
				next = table[lastIndex];
				lastIndex++;
			}
		}

		public boolean hasNext() {
			return next != null;
		}

		public void remove() {
			table[lastIndex] = null;
		}

		protected HashEntry<K, V> nextEntry() {
			int capacity = table.length;
			HashEntry<K, V> lastReturned = next;
			next = lastIndex < capacity ? table[lastIndex++] : null;
			for (; lastIndex < capacity && next == null; lastIndex++) {
				next = table[lastIndex];
			}
			return lastReturned;
		}

	}

	private class KeySetIterator extends AbstractIterator implements Iterator<K> {

		public K next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return nextEntry().getKey();
		}

	}

	private class ValueSetIterator extends AbstractIterator implements Iterator<V> {

		public V next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return nextEntry().getValue();
		}

	}

	private class EntrySetIterator extends AbstractIterator implements Iterator<Map.Entry<K, V>> {

		public HashEntry<K, V> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return nextEntry();
		}

	}

}
