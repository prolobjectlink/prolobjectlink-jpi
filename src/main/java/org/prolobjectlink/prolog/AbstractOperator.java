/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
package org.prolobjectlink.prolog;

/**
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
