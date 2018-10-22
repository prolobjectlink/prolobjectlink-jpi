/*
 * #%L
 * prolobjectlink-jpe
 * %%
 * Copyright (C) 2012 - 2018 Logicware Project
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
package org.logicware.graph;

import org.logicware.platform.GraphElement;

public abstract class AbstractGraphElement<E> implements GraphElement<E> {

	protected final E element;
	protected final Class<?> elementClass;

	public AbstractGraphElement(E element) {
		this.element = element;
		this.elementClass = element != null ? element.getClass() : null;
	}

	public E getElement() {
		return element;
	}

	public Class<E> getElementClass() {
		return (Class<E>) elementClass;
	}

	@Override
	public abstract String toString();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((elementClass == null) ? 0 : elementClass.hashCode());
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
		AbstractGraphElement<?> other = (AbstractGraphElement<?>) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element)) {
			return false;
		}
		return true;
	}

}
