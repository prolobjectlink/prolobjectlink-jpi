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

import static io.github.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;

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

	public final boolean isTrueType() {
		Object object = getObject();
		return object != null && object.equals(true);
	}

	public final boolean isFalseType() {
		Object object = getObject();
		return object != null && object.equals(false);
	}

	public final boolean isNullType() {
		return isObjectType() && getObject() == null;
	}

	public final boolean isVoidType() {
		return getObject() == void.class;
	}

	public final boolean isObjectType() {
		return getType() == OBJECT_TYPE;
	}

	public final boolean isReference() {
		return isObjectType();
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
