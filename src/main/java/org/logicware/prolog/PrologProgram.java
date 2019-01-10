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

	public Set<String> getIndicators();

	public String toString();

	public int hashCode();

	public boolean equals(Object obj);

	public abstract Map<String, PrologClauses> getClauses();
}
