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

/**
 * Describe the possible types accepted by a variable.
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public final class PrologType {

	//
	private static final PrologProvider provider = Prolog.getProvider();

	/**
	 * Describe a generic term type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm TERM = provider.newVariable("TERM", 0);

	/**
	 * Describe a atom type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm ATOM = provider.newVariable("ATOM", 0);

	/**
	 * Describe a integer type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm INT = provider.newVariable("INT", 0);

	/**
	 * Describe a long type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm LONG = provider.newVariable("LONG", 0);

	/**
	 * Describe a double type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm DOUBLE = provider.newVariable("DOUBLE", 0);

	/**
	 * Describe a float type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm FLOAT = provider.newVariable("FLOAT", 0);

	/**
	 * Describe a boolean type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm BOOL = provider.newVariable("BOOL", 0);

	/**
	 * Describe a list type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm LIST = provider.newVariable("LIST", 0);

	/**
	 * Describe a structure type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm STRUCT = provider.newVariable("STRUCT", 0);

	/**
	 * Describe a object type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm OBJ = provider.newVariable("OBJ", 0);

	/**
	 * Describe a map type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm MAP = provider.newVariable("MAP", 0);

	/**
	 * Describe a integer type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm INTEGER = provider.newVariable("INTEGER", 0);

	/**
	 * Describe a boolean type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm BOOLEAN = provider.newVariable("BOOLEAN", 0);

	/**
	 * Describe a structure type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm STRUCTURE = provider.newVariable("STRUCTURE", 0);

	/**
	 * Describe a object type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm OBJECT = provider.newVariable("OBJECT", 0);

	/**
	 * Describe a long id type
	 * 
	 * @since 1.1
	 */
	public static final PrologTerm ID = provider.newVariable("ID", 0);

	private PrologType() {
		// do nothing
	}

}
