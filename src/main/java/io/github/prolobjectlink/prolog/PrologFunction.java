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

public class PrologFunction extends PrologMethod implements PrologClause {

	private final PrologTerm result;

	public PrologFunction(PrologProvider provider, PrologTerm head, PrologTerm result, boolean dynamic,
			boolean multifile, boolean discontiguous) {
		super(provider, head, dynamic, multifile, discontiguous);
		this.result = result;
	}

	public PrologFunction(PrologProvider provider, PrologTerm head, PrologTerm body, PrologTerm result, boolean dynamic,
			boolean multifile, boolean discontiguous) {
		super(provider, head, body, dynamic, multifile, discontiguous);
		this.result = result;
	}

	public PrologTerm getResult() {
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int x = super.hashCode();
		x = prime * x + ((this.result == null) ? 0 : this.result.hashCode());
		return x;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrologFunction other = (PrologFunction) obj;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getHead() + " = " + result + " :- \n\t" + getBody() + ".";
	}

}
