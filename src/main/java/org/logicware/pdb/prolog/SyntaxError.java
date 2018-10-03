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
package org.logicware.pdb.prolog;

import org.logicware.pdb.RuntimeError;

public final class SyntaxError extends RuntimeError {

	private static final long serialVersionUID = 2828526751667597579L;

	public SyntaxError(String string) {
		super("The string parsed have prolog syntax error: " + string);
	}

	public SyntaxError(String string, Throwable cause) {
		super("The string parsed have prolog syntax error: " + string, cause);
	}

}
