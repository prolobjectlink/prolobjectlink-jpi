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
	public static final int INTERFACE_TYPE = 0x505;

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
