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

import java.util.List;

public class PrologInterface extends AbstractCompounds implements PrologTerm {

	private String namesapce;
	private final String name;
	private List<PrologTerm> methods;

	private static final String SEPARATOR = "/";
	private static final String LEFT_ENCLOSER = "{";
	private static final String RIGHT_ENCLOSER = "}";

	PrologInterface(PrologProvider provider, String name) {
		super(PrologTermType.INTERFACE_TYPE, provider);
		this.name = name;
	}

	PrologInterface(PrologProvider provider, String namespace, String name) {
		super(PrologTermType.INTERFACE_TYPE, provider);
		this.namesapce = namespace;
		this.name = name;
	}

	PrologInterface(PrologProvider provider, PrologNamespace namespace, String name) {
		super(PrologTermType.INTERFACE_TYPE, provider);
		this.namesapce = namespace.getFunctor();
		this.name = name;
	}

	public final boolean isList() {
		return true;
	}

	public final boolean isStructure() {
		return true;
	}

	public final boolean isEmptyList() {
		return methods.isEmpty();
	}

	public final int getArity() {
		return methods.size();
	}

	public final String getFunctor() {
		return namesapce + SEPARATOR + name;
	}

	public final PrologTerm[] getArguments() {
		return methods.toArray(new PrologTerm[0]);
	}

	public final String getNamesapce() {
		return namesapce;
	}

	public final void setNamesapce(String namesapce) {
		this.namesapce = namesapce;
	}

	public final List<PrologTerm> getMethods() {
		return methods;
	}

	public final void setMethods(List<PrologTerm> methods) {
		this.methods = methods;
	}

	public final String getName() {
		return name;
	}

	public final String getSeparator() {
		return SEPARATOR;
	}

	public final String getLeftencloser() {
		return LEFT_ENCLOSER;
	}

	public final String getRightencloser() {
		return RIGHT_ENCLOSER;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((methods == null) ? 0 : methods.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((namesapce == null) ? 0 : namesapce.hashCode());
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
		PrologInterface other = (PrologInterface) obj;
		if (methods == null) {
			if (other.methods != null)
				return false;
		} else if (!methods.equals(other.methods)) {
			return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (namesapce == null) {
			if (other.namesapce != null)
				return false;
		} else if (!namesapce.equals(other.namesapce)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PrologInterface [namesapce=" + namesapce + ", name=" + name + ", methods=" + methods + "]";
	}

}
