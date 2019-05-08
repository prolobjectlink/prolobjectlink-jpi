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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologProgram extends Set<PrologClauses>, Iterable<PrologClauses> {

	public PrologClauses get(String key);

	public void add(PrologClause clause);

	public void add(PrologProgram program);

	public void push(PrologClause clause);

	public void removeAll(String key);

	public void removeAll(String functor, int arity);

	public List<PrologGoal> getGoals();

	public boolean addGoal(PrologGoal goal);

	public boolean removeGoal(PrologGoal goal);

	public List<PrologGoal> getDirectives();

	public boolean addDirective(PrologGoal directive);

	public boolean removeDirective(PrologGoal directive);

	public void markDynamic(String functor, int arity);

	public void unmarkDynamic(String functor, int arity);

	public boolean isDynamic(String functor, int arity);

	public void markMultifile(String functor, int arity);

	public void unmarkMultifile(String functor, int arity);

	public boolean isMultifile(String functor, int arity);

	public void markDiscontiguous(String functor, int arity);

	public void unmarkDiscontiguous(String functor, int arity);

	public boolean isDiscontiguous(String functor, int arity);

	public Map<String, PrologClauses> getClauses();

	public Set<String> getIndicators();

	public String toString();

	public int hashCode();

	public boolean equals(Object object);
}
