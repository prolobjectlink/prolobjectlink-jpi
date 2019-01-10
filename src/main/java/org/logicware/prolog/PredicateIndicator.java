/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 WorkLogic Project
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

public final class PredicateIndicator implements PrologIndicator {

	private static final long serialVersionUID = 4354260669799638894L;
	private final String functor;
	private final int arity;

	public PredicateIndicator(String functor, int arity) {
		this.functor = functor;
		this.arity = arity;
	}

	public String getIndicator() {
		return functor + "/" + arity;
	}

	public String getFunctor() {
		return functor;
	}

	public int getArity() {
		return arity;
	}

	@Override
	public String toString() {
		return getIndicator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + arity;
		result = prime * result + ((functor == null) ? 0 : functor.hashCode());
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
		PredicateIndicator other = (PredicateIndicator) obj;
		if (arity != other.arity)
			return false;
		if (functor == null) {
			if (other.functor != null)
				return false;
		} else if (!functor.equals(other.functor)) {
			return false;
		}
		return true;
	}

}
