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

	public PrologTerm[][] nSolutions(int n);

	public Map<String, PrologTerm>[] nVariablesSolutions(int n);

	public PrologTerm[][] allSolutions();

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

	public List<List<Object>> allResults();

	public List<Map<String, Object>> allVariablesResults();

	public Map<String, PrologTerm> one();

	public List<Map<String, PrologTerm>> all();

	/**
	 * Release all allocations for the query
	 * 
	 * @since 1.0
	 */
	public void dispose();

}
