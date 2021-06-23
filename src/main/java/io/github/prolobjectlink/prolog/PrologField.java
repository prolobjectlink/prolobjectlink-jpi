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

public class PrologField extends AbstractCompounds implements PrologEntry {

	private final String name;
	private final String kind;

	PrologField(PrologProvider provider, String name, String kind) {
		super(PrologTermType.FIELD_TYPE, provider);
		this.name = name;
		this.kind = kind;
	}

	PrologField(PrologProvider provider, PrologTerm name, PrologTerm kind) {
		super(PrologTermType.FIELD_TYPE, provider);
		this.name = name.getFunctor();
		this.kind = kind.getFunctor();
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isStructure() {
		return true;
	}

	@Override
	public boolean isEmptyList() {
		return false;
	}

	@Override
	public int getArity() {
		return 2;
	}

	@Override
	public String getFunctor() {
		return name + "-" + kind;
	}

	@Override
	public PrologTerm[] getArguments() {
		return new PrologTerm[] { getKey(), getValue() };
	}

	@Override
	public PrologTerm getKey() {
		return provider.newAtom(name);
	}

	@Override
	public PrologTerm getValue() {
		return provider.newAtom(kind);
	}

	@Override
	public PrologTerm setValue(PrologTerm value) {
		// this.type = value.getFunctor()
		getLogger().debug(getClass(), "No value setting allow");
		return provider.newAtom(kind);
	}

	public PrologTerm getNameTerm() {
		return getKey();
	}

	public PrologTerm getKindTerm() {
		return getValue();
	}

	public String getName() {
		return name;
	}

	public String getKind() {
		return kind;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PrologField other = (PrologField) obj;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind)) {
			return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getFunctor();
	}

}
