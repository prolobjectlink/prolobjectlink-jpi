/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package io.github.prolobjectlink.prolog;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Prolog query is the mechanism to query the prolog database loaded in prolog
 * engine. The way to create a new prolog query is invoking
 * {@link PrologEngine#query(String)},
 * {@link PrologEngine#query(PrologTerm, PrologTerm...)} or
 * {@link PrologEngine#query(PrologTerm[])}. When this methods are called the
 * prolog query is open an only with {@link #dispose()} close the current query
 * and release all internal resources.
 * </p>
 * 
 * <p>
 * Prolog query implement {@link Iterable} and {@link Iterator}. This
 * implementation help to obtain successive solutions present in the query.
 * </p>
 * 
 * <pre>
 * PrologEngine engine = provider.newEngine("zoo.pl");
 * PrologVariable x = provider.newVariable("X", 0);
 * PrologQuery query = engine.query(provider.newStructure("dark", x));
 * while (query.hasNext()) {
 * 	PrologTerm value = query.nextVariablesSolution().get(&quot;X&quot;);
 * 	System.out.println(value);
 * }
 * query.dispose();
 * </pre>
 * 
 * <pre>
 * PrologEngine engine = provider.newEngine("zoo.pl");
 * PrologVariable x = provider.newVariable("X", 0);
 * PrologQuery query = engine.query(provider.newStructure("dark", x));
 * for (Collection&lt;PrologTerm&gt; col : query) {
 * 	for (PrologTerm prologTerm : col) {
 * 		System.out.println(prologTerm);
 * 	}
 * }
 * query.dispose();
 * </pre>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologQuery extends Iterator<Collection<PrologTerm>>, Iterable<Collection<PrologTerm>> {

	/**
	 * Provider instance
	 * 
	 * @return provider instance
	 * @since 1.0
	 */
	public PrologProvider getProvider();

	/**
	 * Engine hold by the current query
	 * 
	 * @return used by the current query
	 * @since 1.0
	 */
	public PrologEngine getEngine();

	/**
	 * <p>
	 * Check that the current query has solution.
	 * </p>
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
	 * @return true if the current query has more solutions, false if not
	 * @since 1.0
	 */
	public boolean hasMoreSolutions();

	/**
	 * Return the prolog terms that conform the solution set for the current query.
	 * The solution is a prolog terms array and every term is an instance value for
	 * the variables not anonymous involved in the query.
	 * 
	 * @return prolog terms solution array for the current query
	 * @since 1.0
	 */
	public PrologTerm[] oneSolution();

	/**
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

	/**
	 * Return the next prolog terms solution array for the current query. The
	 * solution is a prolog terms array and every term is an instance value for the
	 * variables not anonymous involved in the query.
	 * 
	 * @return prolog terms solution array for the current query
	 * @since 1.0
	 */
	public PrologTerm[] nextSolution();

	/**
	 * Return the next prolog terms that conform the solution set for the current
	 * query. The solution set is a prolog terms map and every map entry is a pair
	 * variable name and variable instance value for the variables not anonymous
	 * involved in the query.
	 * 
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
	 * @since 1.0
	 */
	public Map<String, PrologTerm> nextVariablesSolution();

	/**
	 * Return a Prolog terms matrix of n x m order that conform the solution set for
	 * the current query where n is the solution number and m is a free variable
	 * number in the query.
	 * 
	 * @param n array order or Prolog term rows number
	 * @return a Prolog terms matrix of n x m order that conform the solution set
	 * @since 1.0
	 */
	public PrologTerm[][] nSolutions(int n);

	/**
	 * Return an array of n size with maps of variables name key and Prolog terms as
	 * value that conform the solution set for the current query where n is the
	 * solution number.
	 * 
	 * @param n array order or Prolog term items number
	 * @return an array of n size with maps of variables name key and Prolog terms
	 *         as value that conform the solution set
	 * @since 1.0
	 */
	public Map<String, PrologTerm>[] nVariablesSolutions(int n);

	/**
	 * Return a Prolog terms matrix of n x m order that conform the solution set for
	 * the current query where n is the solution number and m is a free variable
	 * number in the query.
	 * 
	 * @return a Prolog terms matrix of n x m order that conform the solution set
	 * @since 1.0
	 */
	public PrologTerm[][] allSolutions();

	/**
	 * Return an array of map of variables name key and Prolog terms as value that
	 * conform the solution set for the current query.
	 * 
	 * @return an array of map of variables name key and Prolog terms as value that
	 *         conform the solution set
	 * @since 1.0
	 */
	public Map<String, PrologTerm>[] allVariablesSolutions();

	/**
	 * 
	 * Return the equivalent Java objects that conform the solution set for the
	 * current query. The solution set is a Java objects list and every term is a
	 * conversion of the instance value for the variables not anonymous involved in
	 * the query.
	 * 
	 * 
	 * <pre>
	 * List&lt;Object&gt; solution = query.oneResult();
	 * for (int i = 0; i &lt; solution.size(); i++) {
	 * 	System.out.println(solution.get(i));
	 * }
	 * </pre>
	 * 
	 * @return Java objects solution list for the current query
	 * @since 1.0
	 */
	public List<Object> oneResult();

	/**
	 * 
	 * Return the equivalent Java objects that conform the solution set for the
	 * current query. The solution set is a Java object map and every map entry is a
	 * pair variable name and a Java object conversion of the variable instance
	 * value for the variables not anonymous involved in the query.
	 * 
	 * @return variable name - Java object conversion of the variable instance (key
	 *         - value) map that conform the solution set for the current query.
	 * @since 1.0
	 */
	public Map<String, Object> oneVariablesResult();

	/**
	 * Return the next Java objects solution list for the current query. The
	 * solution is a Java objects list and every object is an instance value for the
	 * variables not anonymous involved in the query.
	 * 
	 * @return Java objects solution list for the current query
	 * @since 1.1
	 */
	public List<Object> nextResult();

	/**
	 * Return the next Java objects that conform the solution set for the current
	 * query. The solution set is an objects map and every map entry is a pair
	 * variable name and variable instance value for the variables not anonymous
	 * involved in the query.
	 * 
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
	 * @since 1.1
	 */
	public Map<String, Object> nextVariablesResult();

	/**
	 * Return a list of list of Java Objects that conform the solution set for the
	 * current query where n is the solution number and m is a free variable number
	 * in the query.
	 * 
	 * @param n list order or Java objects rows number
	 * @return a list of list of Java Objects that conform the solution set
	 * @since 1.1
	 */
	public List<List<Object>> nResult(int n);

	/**
	 * Return a list of n size with maps of variables name key and Java objects as
	 * value that conform the solution set for the current query where n is the
	 * solution number.
	 * 
	 * @param n list order or Java objects items number
	 * @return a list of n size with maps of variables name key and Java objects as
	 *         value that conform the solution set
	 * @since 1.1
	 */
	public List<Map<String, Object>> nVariablesResults(int n);

	/**
	 * Return a list of list of Java Objects that conform the solution set for the
	 * current query.
	 * 
	 * @return a list of list of Java Objects that conform the solution set for the
	 *         current query.
	 * @since 1.0
	 */
	public List<List<Object>> allResults();

	/**
	 * Return a list of map of variables name key and Java objects as value that
	 * conform the solution set for the current query.
	 * 
	 * @return a list of map of variables name key and Java objects as value that
	 *         conform the solution set
	 * @since 1.0
	 */
	public List<Map<String, Object>> allVariablesResults();

	/**
	 * Return a map of variables name key and Prolog terms as value that conform the
	 * solution set for the current query.
	 * 
	 * @return a map of variables name key and Prolog terms as value that conform
	 *         the solution set
	 * @since 1.0
	 */
	public Map<String, PrologTerm> one();

	/**
	 * Return the next prolog terms that conform the solution set for the current
	 * query. The solution set is a prolog terms map and every map entry is a pair
	 * variable name and variable instance value for the variables not anonymous
	 * involved in the query.
	 * 
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
	 * @since 1.1
	 */
	public Map<String, PrologTerm> more();

	/**
	 * Return a list of n size with maps of variables name key and Prolog terms as
	 * value that conform the solution set for the current query where n is the
	 * solution number.
	 * 
	 * @return an list of n size with maps of variables name key and Prolog terms as
	 *         value that conform the solution set
	 * @param n list order or Prolog term items number
	 * @since 1.1
	 */
	public List<Map<String, PrologTerm>> nths(int n);

	/**
	 * Return a list of map of variables name key and Prolog terms as value that
	 * conform the solution set for the current query.
	 * 
	 * @return a list of map of variables name key and Prolog terms as value that
	 *         conform the solution set
	 * @since 1.0
	 */
	public List<Map<String, PrologTerm>> all();

	/**
	 * Release all allocations for the query
	 * 
	 * @since 1.0
	 */
	public void dispose();

}
