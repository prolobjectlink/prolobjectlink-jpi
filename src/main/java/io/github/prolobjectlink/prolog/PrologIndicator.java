/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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

/**
 * Indicator to denote the signature for Prolog Terms using a functor/arity
 * format. More formally the indicator is formed by the concatenation of the
 * term functor and term arity separated by slash.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologIndicator {

	/**
	 * Indicator arity that is the argument number for compound terms. If the
	 * indicator is not associate to a compound term then the arity is zero.
	 * 
	 * @return indicator arity.
	 * @since 1.0
	 */
	public int getArity();

	/**
	 * Indicator functor that is the name for compound terms.
	 * 
	 * @return indicator functor for compound terms.
	 * @since 1.0
	 */
	public String getFunctor();

	/**
	 * Gets the term indicator represented by one string with the format
	 * functor/arity. More formally the indicator string is formed by the
	 * concatenation of {@link #getFunctor()} and {@link #getArity()} separated by
	 * slash.
	 * 
	 * @return term indicator
	 * @since 1.0
	 */
	public String getIndicator();

}
