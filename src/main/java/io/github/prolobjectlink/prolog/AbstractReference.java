/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

	public Class<?> getReferenceType() {
		return reference.getClass();
	}

}
