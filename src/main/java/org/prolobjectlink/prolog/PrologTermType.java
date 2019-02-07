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

public final class PrologTermType {

	// constant for prolog variables
	public static final int VARIABLE_TYPE = 0x100;

	// constants for prolog numbers
	public static final int INTEGER_TYPE = 0x200;
	public static final int LONG_TYPE = 0x201;
	public static final int FLOAT_TYPE = 0x202;
	public static final int DOUBLE_TYPE = 0x203;

	// constant for prolog atoms
	public static final int ATOM_TYPE = 0x300;
	public static final int NIL_TYPE = 0x301;
	public static final int FALSE_TYPE = 0x302;
	public static final int TRUE_TYPE = 0x303;
	public static final int CUT_TYPE = 0x304;
	public static final int FAIL_TYPE = 0x305;

	// constant for special objects
	public static final int OBJECT_TYPE = 0x400;
	public static final int STREAM_TYPE = 0x401;

	// constants for prolog list and prolog structure
	/**
	 * @deprecated Empty list use list type
	 */
	@Deprecated
	public static final int EMPTY_TYPE = 0x500;
	public static final int LIST_TYPE = 0x501;
	public static final int STRUCTURE_TYPE = 0x502;

	private PrologTermType() {
	}

}
