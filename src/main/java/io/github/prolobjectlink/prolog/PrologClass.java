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

import java.util.ArrayList;
import java.util.List;

class PrologClass extends PrologInterface implements PrologTerm {

	private final List<PrologField> fields = new ArrayList<PrologField>();
	private final List<PrologMethod> constructors = new ArrayList<PrologMethod>();
	private final List<PrologMethod> methods = new ArrayList<PrologMethod>();
	private final List<PrologClass> nested = new ArrayList<PrologClass>();

	PrologClass(PrologProvider provider, String name) {
		super(provider, name);
	}

	PrologClass(PrologProvider provider, String namespace, String name) {
		super(provider, namespace, name);
	}

	PrologClass(PrologProvider provider, PrologTerm namespace, String name) {
		super(provider, namespace.getFunctor(), name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + constructors.hashCode();
		result = prime * result + fields.hashCode();
		result = prime * result + methods.hashCode();
		result = prime * result + nested.hashCode();
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
		PrologClass other = (PrologClass) obj;
		if (constructors == null) {
			if (other.constructors != null)
				return false;
		} else if (!constructors.equals(other.constructors))
			return false;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (methods == null) {
			if (other.methods != null)
				return false;
		} else if (!methods.equals(other.methods))
			return false;
		if (nested == null) {
			if (other.nested != null)
				return false;
		} else if (!nested.equals(other.nested))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrologClass [fields=" + fields + ", constructors=" + constructors + ", methods=" + methods + ", nested="
				+ nested + "]";
	}

}
