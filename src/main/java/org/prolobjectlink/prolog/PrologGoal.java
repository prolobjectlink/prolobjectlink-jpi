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

	public boolean equals(Object object);

	public String toString();

	void removeClause();

	PrologClause nextClause();

	boolean hasNextClause();

	PrologClause nextGoal();

	boolean hasNextGoal();

}
