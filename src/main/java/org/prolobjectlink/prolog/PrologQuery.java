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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologQuery extends Iterator<Collection<PrologTerm>>, Iterable<Collection<PrologTerm>> {

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
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
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
