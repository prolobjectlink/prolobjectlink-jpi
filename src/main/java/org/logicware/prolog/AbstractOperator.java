/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.prolog;

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
		return "(" + priority + "," + specifier + "," + operator + ")";
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
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractOperator other = (AbstractOperator) obj;
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
