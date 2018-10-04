/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.prolog;

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
	public static final int EMPTY_TYPE = 0x500;
	public static final int LIST_TYPE = 0x501;
	public static final int STRUCTURE_TYPE = 0x502;

	private PrologTermType() {
	}

}
