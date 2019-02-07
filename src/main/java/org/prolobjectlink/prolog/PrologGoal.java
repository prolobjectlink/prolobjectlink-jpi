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

import java.util.Map;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
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
