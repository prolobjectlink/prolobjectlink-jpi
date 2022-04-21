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
package io.github.prolobjectlink.prolog;

/**
 * <p>
 * Represent all Prolog number data type. Is an abstract class that contains all
 * commons method related to number data types. In Prolog the number data types
 * are Integer and Float. Some Prolog implementations have and extension for
 * this legacy data types. They are Long (Large Integer Number) and Double
 * (Double precision Floating Point Number). For Prolog implementations that no
 * have support for Long and Double data types, they are implement this classes
 * holding a Prolog integer for Long case and Prolog float for Double case.
 * </p>
 * <p>
 * Two Prolog integers numbers are equals if and only if they are integers
 * number and have the same value.Two Prolog integers numbers unify if are
 * equals or at least one PrologTerm is a free variable Two Prolog floats
 * numbers are equals if and only if they are floats number and have the same
 * value.Two Prolog floats numbers unify if are equals or at least one
 * PrologTerm is a free variable Two Prolog longs numbers are equals if and only
 * if they are longs number and have the same value.Two Prolog longs numbers
 * unify if are equals or at least one PrologTerm is a free variable Two Prolog
 * doubles numbers are equals if and only if they are doubles number and have
 * the same value.Two Prolog doubles numbers unify if are equals or at least one
 * PrologTerm is a free variable
 * </p>
 * <p>
 * Some Prolog implementations consider that integers and longs are equals if
 * they have the same value and unify if have the same value or at least one
 * PrologTerm is a free variable. Some Prolog implementations consider that
 * floats and double are equals if they have the same value and unify if have
 * the same value or at least one PrologTerm is a free variable.
 * </p>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologNumber extends PrologTerm {

	/**
	 * Cast and return the equivalent Java Integer Number value from current Prolog
	 * Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Java Integer Number value from current Prolog Number.
	 */
	public int getIntegerValue();

	/**
	 * Cast and return the equivalent Java Long Number value from current Prolog
	 * Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Java Long Number value from current Prolog Number.
	 */
	public long getLongValue();

	/**
	 * Cast and return the equivalent Java Float Number value from current Prolog
	 * Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Java Float Number value from current Prolog Number.
	 */
	public float getFloatValue();

	/**
	 * Cast and return the equivalent Java Double Number value from current Prolog
	 * Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Java Double Number value from current Prolog Number.
	 */
	public double getDoubleValue();

	/**
	 * Cast and return the equivalent Prolog Float Number from current Prolog
	 * Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Prolog Float Number from current Prolog Number.
	 */
	public PrologFloat getPrologFloat();

	/**
	 * Cast and return the equivalent Prolog Integer Number from current Prolog
	 * Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Prolog Integer Number from current Prolog Number.
	 */
	public PrologInteger getPrologInteger();

	/**
	 * Cast and return the equivalent Prolog Double Number from current Prolog
	 * Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Prolog Double Number from current Prolog Number.
	 */
	public PrologDouble getPrologDouble();

	/**
	 * Cast and return the equivalent Prolog Long Number from current Prolog Number.
	 * 
	 * @since 1.0
	 * @return the equivalent Prolog Long Number from current Prolog Number.
	 */
	public PrologLong getPrologLong();

}
