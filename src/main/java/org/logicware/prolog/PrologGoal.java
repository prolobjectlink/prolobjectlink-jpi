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

import java.util.Map;

public interface PrologGoal extends PrologClause, Iterable<PrologClause> {

	/**
	 * Link the current goal with a clause recovery in the program database or
	 * runtime built-in.
	 * 
	 * @param program  program for lookup clause that match with the current goal
	 * @param builtins prolog built-ins for lookup clause that match with the
	 *                 current goal
	 * @param next     continuation goal to be execute
	 * @return the current goal linked with the matched clause
	 * @since 1.0
	 */
	public PrologGoal resolve(PrologProgram program, Map<String, PrologClauses> builtins, PrologGoal next);

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

	void removeClause();

	PrologClause nextClause();

	boolean hasNextClause();

	PrologClause nextGoal();

	boolean hasNextGoal();

}
