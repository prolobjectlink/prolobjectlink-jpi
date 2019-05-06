/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.prolobjectlink.prolog;

/**
 * Represent all Prolog number data type. Is an abstract class that contains all
 * commons method related to number data types. In Prolog the number data types
 * are Integer and Float. Some Prolog implementations have and extension for
 * this legacy data types. They are Long (Large Integer Number) and Double
 * (Double precision Floating Point Number). For Prolog implementations that no
 * have support for Long and Double data types, they are implement this classes
 * holding a Prolog integer for Long case and Prolog float for Double case.
 * 
 * Two Prolog integers numbers are equals if and only if they are integers
 * number and have the same value.Two Prolog integers numbers unify if are
 * equals or at least one PrologTerm is a free variable
 * Two Prolog floats numbers are equals if and only if they are floats
 * number and have the same value.Two Prolog floats numbers unify if are equals
 * or at least one PrologTerm is a free variable
 * Two Prolog longs numbers are equals if and only if they are longs number
 * and have the same value.Two Prolog longs numbers unify if are equals or at
 * least one PrologTerm is a free variable
 * Two Prolog doubles numbers are equals if and only if they are doubles
 * number and have the same value.Two Prolog doubles numbers unify if are equals
 * or at least one PrologTerm is a free variable
 * 
 * Some Prolog implementations consider that integers and longs are equals
 * if they have the same value and unify if have the same value or at least one
 * PrologTerm is a free variable.
 * Some Prolog implementations consider that floats and double are equals if
 * they have the same value and unify if have the same value or at least one
 * PrologTerm is a free variable.
 * 
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
