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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.logicware.Wrapper;

public interface PrologQuery extends Wrapper, Iterator<Collection<PrologTerm>>, Iterable<Collection<PrologTerm>> {

	/**
	 * Engine hold by the current query
	 * 
	 * @return wrapped engine
	 * @since 1.0
	 */
	public PrologEngine getEngine();

	/**
	 * Provider instance
	 * 
	 * @return provider instance
	 * @since 1.0
	 */
	public PrologProvider getProvider();

	/**
	 * <p>
	 * Check that the current query has solution.
	 * </p>
	 * 
	 * 
	 * 
	 * @return true if the current query has solution, false if not
	 * @since 1.0
	 */
	public boolean hasSolution();

	/**
	 * <p>
	 * Check if the current query has more solutions.
	 * </p>
	 * 
	 * 
	 * @return true if the current query has more solutions, false if not
	 * @since 1.0
	 */
	public boolean hasMoreSolutions();

	/**
	 * 
	 * Return the prolog terms that conform the solution set for the current query.
	 * The solution set is a prolog terms array and every term is an instance value
	 * for the variables not anonymous involved in the query.
	 * 
	 * 
	 * <pre>
	 * PrologTerm[] solution = query.oneSolution();
	 * for (int i = 0; i &lt; solution.length; i++) {
	 * 	System.out.println(solution[i]);
	 * }
	 * </pre>
	 * 
	 * @return prolog terms solution set for the current query
	 * @since 1.0
	 */
	public PrologTerm[] oneSolution();

	/**
	 * 
	 * Return the prolog terms that conform the solution set for the current query.
	 * The solution set is a prolog terms map and every map entry is a pair variable
	 * name and variable instance value for the variables not anonymous involved in
	 * the query.
	 * 
	 * @return
	 * @since 1.0
	 */
	public Map<String, PrologTerm> oneVariablesSolution();

	public PrologTerm[] nextSolution();

	public Map<String, PrologTerm> nextVariablesSolution();

	public PrologTerm[][] nSolutions(int n);

	public Map<String, PrologTerm>[] nVariablesSolutions(int n);

	public PrologTerm[][] allSolutions();

	public Map<String, PrologTerm>[] allVariablesSolutions();

	public Map<String, PrologTerm> one();

	public List<Map<String, PrologTerm>> all();

	/**
	 * Release all allocations for the query
	 * 
	 * @since 1.0
	 */
	public void dispose();

}
