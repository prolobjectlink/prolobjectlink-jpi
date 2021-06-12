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
 * Contains all PrologTerm types constants
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class PrologTermType {

	// constant for prolog variables

	/**
	 * Variable type constant
	 */
	public static final int VARIABLE_TYPE = 0x100;

	// constants for prolog numbers

	/**
	 * Integer type constant
	 */
	public static final int INTEGER_TYPE = 0x200;

	/**
	 * Long type constant
	 */
	public static final int LONG_TYPE = 0x201;

	/**
	 * Float type constant
	 */
	public static final int FLOAT_TYPE = 0x202;

	/**
	 * Double type constant
	 */
	public static final int DOUBLE_TYPE = 0x203;

	// constant for prolog atoms

	/**
	 * Atom type constant
	 */
	public static final int ATOM_TYPE = 0x300;

	/**
	 * Nil type constant
	 */
	public static final int NIL_TYPE = 0x301;

	/**
	 * False type constant
	 */
	public static final int FALSE_TYPE = 0x302;

	/**
	 * True type constant
	 */
	public static final int TRUE_TYPE = 0x303;

	/**
	 * Cut type constant
	 */
	public static final int CUT_TYPE = 0x304;

	/**
	 * Fail type constant
	 */
	public static final int FAIL_TYPE = 0x305;

	// constant for special objects

	/**
	 * Object type constant
	 */
	public static final int OBJECT_TYPE = 0x400;

	/**
	 * Stream type constant
	 */
	public static final int STREAM_TYPE = 0x401;

	// constants for prolog list and prolog structure

	/**
	 * List type constant
	 */
	public static final int LIST_TYPE = 0x501;

	/**
	 * Structure type constant
	 */
	public static final int STRUCTURE_TYPE = 0x502;

	/**
	 * Map Entry type constant
	 */
	public static final int MAP_ENTRY_TYPE = 0x503;

	/**
	 * Map type constant
	 */
	public static final int MAP_TYPE = 0x504;

	/**
	 * Interface type constant
	 */
	public static final int MIXIN_TYPE = 0x505;

	/**
	 * Class type constant
	 */
	public static final int CLASS_TYPE = 0x506;

	/**
	 * Namespace type constant
	 */
	public static final int NAMESPACE_TYPE = 0x507;

	/**
	 * Field type constant
	 */
	public static final int FIELD_TYPE = 0x508;

	/**
	 * Namespace type constant
	 */
	public static final int METHOD_TYPE = 0x509;

	private PrologTermType() {
	}

}
