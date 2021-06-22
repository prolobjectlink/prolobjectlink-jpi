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

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Jose Zalacain
 * @since 1.0
 * @param <K> generic object type for key
 * @param <V> generic object type for value
 */
abstract class AbstractMap<K, V> implements Map<K, V>, Serializable {

	private static final long serialVersionUID = 6977777072929966719L;

	public AbstractMap() {
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	protected abstract Entry<K, V> getEntry(Object key);

	public final boolean containsKey(Object key) {
		return getEntry(key) != null;
	}

	public final boolean containsValue(Object value) {
		for (V v : values()) {
			if (value == null) {
				return v == null;
			} else if (v.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public final V get(Object key) {
		Entry<K, V> e = getEntry(key);
		return e != null ? e.getValue() : null;
	}

	public final void putAll(Map<? extends K, ? extends V> m) {
		for (Entry<? extends K, ? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	@Override
	public final String toString() {
		StringBuilder result = new StringBuilder("[");
		Iterator<Entry<K, V>> i = entrySet().iterator();
		if (i.hasNext()) {
			Entry<K, V> entry = i.next();
			result.append(entry.getKey());
			result.append("-");
			result.append(entry.getValue());
			while (i.hasNext()) {
				entry = i.next();
				result.append(", ");
				result.append(entry.getKey());
				result.append("-");
				result.append(entry.getValue());
			}
		}
		return result + "]";
	}

}
