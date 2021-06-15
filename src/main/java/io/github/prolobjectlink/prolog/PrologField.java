/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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

public class PrologField extends AbstractCompounds implements PrologVariable {

	private final PrologTerm name;

	@Deprecated
	PrologField(PrologProvider provider, String name) {
		super(PrologTermType.FIELD_TYPE, provider);
		this.name = provider.newVariable(name, 0);
	}

	PrologField(PrologProvider provider, PrologTerm name) {
		super(PrologTermType.FIELD_TYPE, provider);
		this.name = name;
	}

	PrologField(PrologProvider provider, int position) {
		super(PrologTermType.FIELD_TYPE, provider);
		this.name = provider.newVariable(position);
	}

	PrologField(PrologProvider provider, String name, int position) {
		super(PrologTermType.FIELD_TYPE, provider);
		this.name = provider.newVariable(name, position);
	}

	public final boolean isList() {
		return false;
	}

	public final boolean isStructure() {
		return true;
	}

	public final boolean isEmptyList() {
		return false;
	}

	public final int getArity() {
		return 2;
	}

	public final String getFunctor() {
		return "-";
	}

	public PrologTerm[] getArguments() {
		return new PrologTerm[] { name };
	}

	public final PrologTerm getNameTerm() {
		return name;
	}

	public final String getName() {
		return ((PrologVariable) name).getName();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrologField other = (PrologField) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "" + name + "";
	}

	public final boolean isAnonymous() {
		PrologVariable v = (PrologVariable) name;
		return v.isAnonymous();
	}

	public final void setName(String name) {
		PrologVariable v = (PrologVariable) this.name;
		v.setName(name);
	}

	public final int getPosition() {
		PrologVariable v = (PrologVariable) name;
		return v.getPosition();
	}

	public final boolean isVariable() {
		return true;
	}

}
