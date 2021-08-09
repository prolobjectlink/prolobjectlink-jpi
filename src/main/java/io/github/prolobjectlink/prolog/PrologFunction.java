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

public class PrologFunction extends PrologMethod implements PrologClause {

	private final PrologTerm result;

	PrologFunction(PrologProvider provider, PrologTerm head, PrologTerm result) {
		super(provider, head);
		this.result = result;
	}

	PrologFunction(PrologProvider provider, PrologTerm head, PrologTerm result, PrologTerm body) {
		super(provider, head, body);
		this.result = result;
	}

	PrologFunction(PrologProvider provider, PrologTerm head, Object result) {
		this(provider, head, provider.getJavaConverter().toTerm(result));
	}

	PrologFunction(PrologProvider provider, PrologTerm head, Object result, PrologTerm body) {
		this(provider, head, body, provider.getJavaConverter().toTerm(result));
	}

	@Deprecated
	PrologFunction(PrologProvider provider, PrologTerm head, PrologTerm result, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		super(provider, head, dynamic, multifile, discontiguous);
		this.result = result;
	}

	@Deprecated
	PrologFunction(PrologProvider provider, PrologTerm head, PrologTerm result, PrologTerm body, boolean dynamic,
			boolean multifile, boolean discontiguous) {
		super(provider, head, body, dynamic, multifile, discontiguous);
		this.result = result;
	}

	public final PrologTerm getResult() {
		return result;
	}

	public final boolean isFunction() {
		return true;
	}

	public final PrologTerm getTerm() {
		String neck = ":-";
		PrologTerm h = getHead();
		PrologTerm b = getBody();
		PrologTerm f = provider.newStructure("=", h, result);
		return provider.newStructure(neck, f, b);
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
