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

import static io.github.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;

public class AbstractReference extends AbstractCompounds implements PrologReference {

	protected final Object reference;

	protected AbstractReference(PrologProvider provider, Object reference) {
		super(OBJECT_TYPE, provider);
		this.reference = reference;
	}

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
	public boolean isTrueType() {
		return reference.equals(Boolean.TRUE);
	}

	@Override
	public boolean isFalseType() {
		return reference.equals(Boolean.FALSE);
	}

	@Override
	public boolean isNullType() {
		return reference == null;
	}

	@Override
	public boolean isVoidType() {
		return reference == void.class;
	}

	@Override
	public boolean isObjectType() {
		return getType() == OBJECT_TYPE;
	}

	@Override
	public boolean isReference() {
		return isObjectType() || isNullType();
	}

	@Override
	public Object getObject() {
		return reference;
	}

	@Override
	public int getArity() {
		return 1;
	}

	@Override
	public String getFunctor() {
		return "@";
	}

	@Override
	public PrologTerm[] getArguments() {
		String string = reference.toString();
		PrologTerm tag = provider.newAtom(string);
		return new PrologTerm[] { tag };
	}

	@Override
	public PrologTerm getTerm() {
		String string = reference.toString();
		PrologTerm tag = provider.newAtom(string);
		return provider.newStructure(getFunctor(), tag);
	}

	@Override
	public String toString() {
		return "" + getTerm() + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		AbstractReference other = (AbstractReference) obj;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference)) {
			return false;
		}
		return true;
	}

}
