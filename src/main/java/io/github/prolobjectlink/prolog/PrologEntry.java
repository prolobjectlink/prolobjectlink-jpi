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
public final class PrologEntry extends AbstractTerm implements PrologTerm, Entry<PrologTerm, PrologTerm> {

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

	public int compareTo(PrologTerm o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isAtom() {
		return false;
	}

	public boolean isNumber() {
		return false;
	}

	public boolean isFloat() {
		return false;
	}

	public boolean isInteger() {
		return false;
	}

	public boolean isDouble() {
		return false;
	}

	public boolean isLong() {
		return false;
	}

	public boolean isVariable() {
		return false;
	}

	public boolean isList() {
		return false;
	}

	public boolean isStructure() {
		return false;
	}

	public boolean isNil() {
		return false;
	}

	public boolean isEmptyList() {
		return false;
	}

	public boolean isAtomic() {
		return false;
	}

	public boolean isCompound() {
		return true;
	}

	public boolean isEvaluable() {
		return false;
	}

	public boolean isTrueType() {
		return false;
	}

	public boolean isFalseType() {
		return false;
	}

	public boolean isNullType() {
		return false;
	}

	public boolean isVoidType() {
		return false;
	}

	public boolean isObjectType() {
		return false;
	}

	public boolean isReference() {
		return false;
	}

	public Object getObject() {
		return null;
	}

	public int getArity() {
		return 2;
	}

	public String getFunctor() {
		return "-";
	}

	public PrologTerm[] getArguments() {
		return new PrologTerm[] { key, value };
	}

	public boolean unify(PrologTerm term) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
