/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 WorkLogic Project
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

import org.worklogic.RuntimeError;

public final class VariableExpectedError extends RuntimeError {

	private static final long serialVersionUID = -3064952286859633255L;

	public VariableExpectedError(PrologTerm term) {
		super("The expected term is not a variable : " + term);
	}

	public VariableExpectedError(Class<?> sender, Object term) {
		super(sender, "The expected term is not a variable : " + term);
	}

}
