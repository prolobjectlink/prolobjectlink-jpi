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

@Deprecated
class PrologNamespace extends AbstractCompounds implements PrologTerm {

	private String parent;
	private PrologNamespace chields;

	PrologNamespace(PrologProvider provider) {
		super(PrologTermType.NAMESPACE_TYPE, provider);
		this.parent = "..";
	}

	PrologNamespace(PrologProvider provider, String parent) {
		this(provider);
		this.parent = parent;
	}

	PrologNamespace(PrologProvider provider, String parent, PrologNamespace chields) {
		this(provider);
		this.parent = parent;
		this.chields = chields;
	}

	public boolean isList() {
		return true;
	}

	public boolean isStructure() {
		return false;
	}

	public boolean isEmptyList() {
		return parent.equals("") && chields.isEmptyList();
	}

	public int getArity() {
		return 1 + chields.getArity();
	}

	public String getFunctor() {
		return parent + chields.getFunctor();
	}

	public PrologTerm[] getArguments() {
		PrologProvider provider = getProvider();
		PrologAtom atom = provider.newAtom(parent);
		PrologTerm[] args = chields.getArguments();
		PrologList list = provider.newList(args);
		return new PrologTerm[] { atom, list };
	}

}
