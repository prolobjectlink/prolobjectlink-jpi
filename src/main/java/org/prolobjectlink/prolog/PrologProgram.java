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
