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
 * <p>
 * Represent the Prolog atom data type. Prolog atoms are can be of two kinds
 * simple or complex. Simple atoms are defined like a single alpha numeric word
 * that begin like initial lower case character. The complex atom are define
 * like any character sequence that begin and end with simple quotes.
 * </p>
 * <p>
 * The string passed to build a simple atoms should be match with
 * [a-z][A-Za-z0-9_]* regular expression. If the string passed to build an atom
 * don't match with the before mentioned regular expression the atom constructor
 * can be capable of create a complex atom automatically.
 * </p>
 * <p>
 * For complex atom the string value can have the quotes or just can be absent.
 * The printed string representation of the complex atom implementation set the
 * quotes if they are needed.
 * </p>
 * <p>
 * The Prolog Provider is the mechanism to create a new Prolog structures
 * invoking {@link PrologProvider#newAtom(String)}.
 * </p>
 * <p>
 * Two atoms are equals if and only if are atoms and have equals functor
 * (value). Atoms terms unify only with same atoms or with free variable.
 * </p>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologAtom extends PrologTerm {

	/**
	 * String value for atom term.
	 * 
	 * @return value for atom term.
	 * @since 1.0
	 */
	public String getStringValue();

	/**
	 * Set the string value for this atom instance. The string value should be match
	 * with [a-z][A-Za-z0-9_]* regular expression. If the string passed to build an
	 * atom don't match with the before mentioned regular expression the atom
	 * constructor can be capable of create a complex atom automatically.
	 * 
	 * @param value string value for this atom
	 * @since 1.0
	 */
	public void setStringValue(String value);

}
