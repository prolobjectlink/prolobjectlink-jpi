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
package org.prolobjectlink.prolog;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
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
