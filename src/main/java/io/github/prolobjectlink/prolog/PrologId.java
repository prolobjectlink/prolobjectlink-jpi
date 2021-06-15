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

import static io.github.prolobjectlink.prolog.PrologTermType.LONG_TYPE;

/**
 * Prolog Id is an implementation of Prolog Long. Is an intent to use a
 * specialized type for entity identifier. In typed prolog variable declaration
 * the Prolog Id use is reserved to specify that the declare variable is Long id
 * typed.
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
class PrologId extends AbstractTerm implements PrologLong {

	private final Number value;

	PrologId(PrologProvider provider, Number value) {
		super(LONG_TYPE, provider);
		this.value = value;
	}

	@Override
	public int getIntegerValue() {
		return value.intValue();
	}

	@Override
	public long getLongValue() {
		return value.longValue();
	}

	@Override
	public float getFloatValue() {
		return value.floatValue();
	}

	@Override
	public double getDoubleValue() {
		return value.doubleValue();
	}

	@Override
	public PrologFloat getPrologFloat() {
		return provider.newFloat(value);
	}

	@Override
	public PrologInteger getPrologInteger() {
		return provider.newInteger(value);
	}

	@Override
	public PrologDouble getPrologDouble() {
		return provider.newDouble(value);
	}

	@Override
	public PrologLong getPrologLong() {
		return provider.newLong(value);
	}

	@Override
	public boolean isAtom() {
		return false;
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public boolean isFloat() {
		return false;
	}

	@Override
	public boolean isInteger() {
		return true;
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public boolean isLong() {
		return true;
	}

	@Override
	public boolean isVariable() {
		return false;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isStructure() {
		return false;
	}

	@Override
	public boolean isNil() {
		return false;
	}

	@Override
	public boolean isEmptyList() {
		return false;
	}

	@Override
	public boolean isAtomic() {
		return false;
	}

	@Override
	public boolean isCompound() {
		return false;
	}

	@Override
	public boolean isEvaluable() {
		return false;
	}

	@Override
	public boolean isTrueType() {
		return false;
	}

	@Override
	public boolean isFalseType() {
		return false;
	}

	@Override
	public boolean isNullType() {
		return false;
	}

	@Override
	public boolean isVoidType() {
		return false;
	}

	@Override
	public boolean isObjectType() {
		return false;
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public int getArity() {
		return getPrologLong().getArity();
	}

	@Override
	public String getFunctor() {
		return getPrologLong().getFunctor();
	}

	@Override
	public PrologTerm[] getArguments() {
		return new PrologTerm[0];
	}

	@Override
	public boolean unify(PrologTerm term) {
		return getPrologLong().unify(term);
	}

	@Override
	public int compareTo(PrologTerm o) {
		return getPrologLong().compareTo(o);
	}

}
