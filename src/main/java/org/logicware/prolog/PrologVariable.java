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

public interface PrologVariable extends PrologTerm {

	/**
	 * Check that current variable be an anonymous variable
	 * 
	 * @return - true if the current variable is an anonymous variable, false in
	 *         other case
	 * @since 1.0
	 */
	public boolean isAnonymous();

	/**
	 * Name that identify this variable
	 * 
	 * @return variable name
	 * @since 1.0
	 */
	public String getName();

	public void setName(String name);

	/**
	 * Non negative integer that represent the variable position of the
	 * Structure where the variable is first time declared.
	 * 
	 * @return
	 * @since 1.0
	 */
	public int getPosition();

}
