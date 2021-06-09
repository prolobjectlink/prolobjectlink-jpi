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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public final class PrologMap extends AbstractTerm implements PrologTerm, Map<PrologTerm, PrologTerm> {

	private Map<PrologTerm, PrologTerm> map;

	PrologMap(PrologProvider provider, int size) {
		super(PrologTermType.MAP_TYPE, provider);
		map = new HashMap<PrologTerm, PrologTerm>(size);
	}

	PrologMap(PrologProvider provider, Map<? extends PrologTerm, ? extends PrologTerm> m) {
		this(provider);
		putAll(m);
	}

	PrologMap(PrologProvider provider) {
		this(provider, 16);
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
		return true;
	}

	public boolean isStructure() {
		return false;
	}

	public boolean isNil() {
		return false;
	}

	public boolean isEmptyList() {
		return map.size() == 0;
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
		return ".";
	}

	public PrologTerm[] getArguments() {
		return map.entrySet().toArray(new PrologTerm[map.size()]);
	}

	public boolean unify(PrologTerm term) {
		// TODO Auto-generated method stub
		return false;
	}

	public int compareTo(PrologTerm o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public PrologTerm put(PrologTerm key, PrologTerm value) {
		return map.put(key, value);
	}

	public Set<Entry<PrologTerm, PrologTerm>> entrySet() {
		return map.entrySet();
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

}
