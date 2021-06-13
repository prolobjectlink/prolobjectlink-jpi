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

import java.util.Map.Entry;

/**
 * PrologEntry is key-value pair of PrologTerm. Is an implementation of
 * {@link Entry} and {@link PrologTerm}.
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public final class PrologEntry extends AbstractCompounds implements PrologTerm, Entry<PrologTerm, PrologTerm> {

	private final PrologTerm key;
	private PrologTerm value;

	PrologEntry(PrologProvider provider, PrologTerm key, PrologTerm value) {
		super(PrologTermType.MAP_ENTRY_TYPE, provider);
		this.value = value;
		this.key = key;
	}

	public PrologTerm getKey() {
		return key;
	}

	public PrologTerm getValue() {
		return value;
	}

	public PrologTerm setValue(PrologTerm value) {
		this.value = value;
		return value;
	}

	public boolean isList() {
		return false;
	}

	public boolean isStructure() {
		return true;
	}

	public boolean isEmptyList() {
		return false;
	}

	public String getFunctor() {
		return "-";
	}

	public int getArity() {
		return 2;
	}

	public PrologTerm[] getArguments() {
		return new PrologTerm[] { key, value };
	}

	@Override
	public int hashCode() {
		int result = 0;
		final int prime = 31;
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
		PrologEntry other = (PrologEntry) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "" + key + "-" + value + "";
	}

}
