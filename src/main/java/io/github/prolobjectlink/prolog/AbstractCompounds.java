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

public abstract class AbstractCompounds extends AbstractTerm implements PrologTerm {

	public AbstractCompounds(int type, PrologProvider provider) {
		super(type, provider);
	}

	public final boolean isAtom() {
		return false;
	}

	public final boolean isNumber() {
		return false;
	}

	public final boolean isFloat() {
		return false;
	}

	public final boolean isInteger() {
		return false;
	}

	public final boolean isDouble() {
		return false;
	}

	public final boolean isLong() {
		return false;
	}

	public final boolean isVariable() {
		return false;
	}

	public final boolean isNil() {
		return false;
	}

	public final boolean isAtomic() {
		return false;
	}

	public final boolean isCompound() {
		return true;
	}

	public final boolean isEvaluable() {
		return false;
	}

	public boolean isTrueType() {
		return false;
	}

	public boolean isFalseType() {
		return false;
	}

	public boolean isNullType() {
		return false;
	}

	public boolean isVoidType() {
		return false;
	}

	public boolean isObjectType() {
		return false;
	}

	public boolean isReference() {
		return false;
	}

	public Object getObject() {
		return null;
	}

	public final int compareTo(PrologTerm term) {
		PrologTerm thisCompound = this;
		PrologTerm otherCompound = term;

		if (!otherCompound.isCompound()) {
			if (otherCompound.isEmptyList() && thisCompound.isEmptyList()) {
				return 0;
			}
			return 1;
		}

		if (otherCompound.isEmptyList() && thisCompound.isEmptyList()) {
			return 0;
		}

		// comparison by arity
		if (thisCompound.getArity() < otherCompound.getArity()) {
			return -1;
		} else if (thisCompound.getArity() > otherCompound.getArity()) {
			return 1;
		}

		// alphabetic functor comparison
		int result = thisCompound.getFunctor().compareTo(otherCompound.getFunctor());
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		}

		// arguments comparison
		PrologTerm[] thisArguments = thisCompound.getArguments();
		PrologTerm[] otherArguments = otherCompound.getArguments();

		for (int i = 0; i < thisArguments.length; i++) {
			PrologTerm thisArgument = thisArguments[i];
			PrologTerm otherArgument = otherArguments[i];
			if (thisArgument != null && otherArgument != null) {
				result = thisArgument.compareTo(otherArgument);
				if (result != 0) {
					return result;
				}
			}
		}

		return 0;
	}

	public final boolean unify(PrologTerm term) {
		PrologTerm thisTerm = this;
		PrologTerm otherTerm = term;
		if (thisTerm == otherTerm) {
			return true;
		} else if (thisTerm.isVariable()) {
			if (thisTerm == thisTerm.getTerm()) {
				return true;
			}
			return thisTerm.getTerm().unify(otherTerm);
		} else if (otherTerm.isVariable()) {
			if (otherTerm == otherTerm.getTerm()) {
				return true;
			}
			return otherTerm.getTerm().unify(thisTerm);
		} else if (otherTerm.isCompound()) {
			int thisArity = thisTerm.getArity();
			int otherArity = otherTerm.getArity();
			String thisFunctor = thisTerm.getFunctor();
			String otherFunctor = otherTerm.getFunctor();
			if (thisFunctor.equals(otherFunctor) && thisArity == otherArity) {
				PrologTerm[] thisArguments = thisTerm.getArguments();
				PrologTerm[] otherArguments = otherTerm.getArguments();
				if (thisArguments.length == otherArguments.length) {
					for (int i = 0; i < thisArguments.length; i++) {
						if (thisArguments[i] != null && otherArguments[i] != null) {
							PrologTerm thisArgument = thisArguments[i];
							PrologTerm otherArgument = otherArguments[i];
							if (!thisArgument.unify(otherArgument)) {
								return false;
							}
						}
					}
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

}
