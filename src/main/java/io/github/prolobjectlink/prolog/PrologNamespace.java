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

public class PrologNamespace extends AbstractCompounds implements PrologTerm {

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
