/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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

/**
 * Partial implementation of {@link PrologOperator}.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractOperator implements PrologOperator {

	private final int priority;
	private final String specifier;
	private final String operator;

	public AbstractOperator(int priority, String specifier, String operator) {
		this.priority = priority;
		this.specifier = specifier;
		this.operator = operator;
	}

	public final int getPriority() {
		return priority;
	}

	public final String getSpecifier() {
		return specifier;
	}

	public final String getOperator() {
		return operator;
	}

	@Override
	public final String toString() {
		return "op(" + priority + "," + specifier + "," + operator + ")";
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + priority;
		result = prime * result + ((specifier == null) ? 0 : specifier.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		AbstractOperator other = (AbstractOperator) object;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator)) {
			return false;
		}
		if (priority != other.priority)
			return false;
		if (specifier == null) {
			if (other.specifier != null)
				return false;
		} else if (!specifier.equals(other.specifier)) {
			return false;
		}
		return true;
	}

	public int compareTo(PrologOperator o) {
		if (operator != null) {
			if (priority > o.getPriority()) {
				return 1;
			} else if (priority < o.getPriority()) {
				return -1;
			}
		}
		return 0;
	}

}
