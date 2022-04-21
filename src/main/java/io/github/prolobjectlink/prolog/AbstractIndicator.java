/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

/**
 * Partial implementation of {@link PrologIndicator} interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public class AbstractIndicator implements PrologIndicator {

	private final String functor;
	private final int arity;

	protected AbstractIndicator(String functor, int arity) {
		this.functor = functor;
		this.arity = arity;
	}

	public final String getIndicator() {
		return functor + "/" + arity;
	}

	public final String getFunctor() {
		return functor;
	}

	public final int getArity() {
		return arity;
	}

	@Override
	public final String toString() {
		return getIndicator();
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + arity;
		result = prime * result + ((functor == null) ? 0 : functor.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
//		if (getClass() != object.getClass())
//			return false;
		AbstractIndicator other = (AbstractIndicator) object;
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
