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
package org.prolobjectlink.prolog;

import static org.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;

public class AbstractReference extends AbstractTerm implements PrologReference {

	protected final Object reference;

	public AbstractReference(PrologProvider provider, Object reference) {
		super(OBJECT_TYPE, provider);
		this.reference = reference;
	}

	@Override
	public String getIndicator() {
		return getFunctor() + "/" + getArity();
	}

	@Override
	public boolean hasIndicator(String functor, int arity) {
		return functor != null && functor.equals("@") && arity == 1;
	}

	@Override
	public boolean isAtom() {
		return false;
	}

	@Override
	public boolean isNumber() {
		return false;
	}

	@Override
	public boolean isFloat() {
		return false;
	}

	@Override
	public boolean isInteger() {
		return false;
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public boolean isLong() {
		return false;
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
		return true;
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
		return true;
	}

	@Override
	public boolean isEvaluable() {
		return false;
	}

	@Override
	public boolean isTrueType() {
		return reference.equals(Boolean.TRUE);
	}

	@Override
	public boolean isFalseType() {
		return reference.equals(Boolean.FALSE);
	}

	@Override
	public boolean isNullType() {
		return reference == null;
	}

	@Override
	public boolean isVoidType() {
		return reference == Void.class;
	}

	@Override
	public boolean isObjectType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReference() {
		return isObjectType() || isNullType();
	}

	@Override
	public Object getObject() {
		return reference;
	}

	@Override
	public int getArity() {
		return 1;
	}

	@Override
	public String getFunctor() {
		return "@";
	}

	@Override
	public PrologTerm[] getArguments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unify(PrologTerm term) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(PrologTerm o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
