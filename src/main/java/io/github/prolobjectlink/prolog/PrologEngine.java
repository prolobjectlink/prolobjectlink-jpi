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

import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologEngine extends Iterable<PrologClause> {

	/**
	 * Check if the host operating system name refer to Windows OS.
	 * 
	 * @return true if the host operating system name refer to Windows OS and false
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean runOnWindows();

	/**
	 * Check if the host operating system name refer to Linux OS.
	 * 
	 * @return true if the host operating system name refer to Linux OS and false
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean runOnLinux();

	/**
	 * Check if the host operating system name refer to OsX.
	 * 
	 * @return true if the host operating system name refer to OsX and false
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean runOnOSX();

	/**
	 * Return the host operating system name. Is a shortcut to
	 * {@code System.getProperty("os.name");}.
	 * 
	 * @return the host operating system name.
	 * @since 1.0
	 */
	public String getOSName();

	/**
	 * Return the host operating system architecture. Is a shortcut to
	 * {@code System.getProperty("os.arch");}.
	 * 
	 * @return the host operating system architecture.
	 * @since 1.0
	 */
	public String getOSArch();

	/**
	 * Test the correct integration between under-laying prolog engine and top level
	 * interface. Return a string list with the needed conditions to link the
	 * under-laying prolog engine. if the under-laying prolog engine is linked the
	 * returned set only contain the "OK" string indicating a correct integration.
	 * 
	 * @return string list with only "OK" string if the prolog engine integration is
	 *         correct or the conditions string list needed to link the prolog
	 *         engine.
	 * @since 1.0
	 */
	public List<String> verify();

	/**
	 * Consult a file specified by the string path loading an parsing the prolog
	 * program. If the prolog program contains syntax error a syntax exception
	 * should be raised.
	 * 
	 * @param path location of the file to be consulted
	 * @since 1.0
	 */
	public void consult(String path);

	/**
	 * Consult a prolog program from specified reader parsing the prolog program and
	 * put this program into prolog engine. If the prolog program contains syntax
	 * error a syntax exception should be raised.
	 * 
	 * @param reader The reader to read the prolog program from character streams
	 * @since 1.0
	 */
	public void consult(Reader reader);

	/**
	 * Consult a file specified by the string path loading an parsing the prolog
	 * program and include the loaded program into current engine. If the prolog
	 * program contains syntax error a syntax exception should be raised.
	 * 
	 * @param path location of the file to be included
	 * @since 1.0
	 */
	public void include(String path);

	/**
	 * Consult a prolog program from specified reader parsing the prolog program and
	 * include this program into current prolog engine. If the prolog program
	 * contains syntax error a syntax exception should be raised.
	 * 
	 * @param reader The reader to read the prolog program from character streams
	 * @since 1.0
	 */
	public void include(Reader reader);

	/**
	 * Save the prolog program present in the current engine to some specific file
	 * specified by string path.
	 * 
	 * @param path location of the file.
	 * @since 1.0
	 */
	public void persist(String path);

	/**
	 * Write the prolog clauses in program present in the current engine using the
	 * given driver.
	 * 
	 * @param writer writer for write prolog clauses in the program.
	 * @since 1.0
	 */
	public void persist(Writer writer);

	/**
	 * Remove all predicate that match with the predicate indicator (PI) formed by
	 * the concatenation of the given string functor and integer arity separated by
	 * slash (functor/arity).
	 * 
	 * <pre>
	 * engine.abolish(&quot;parent&quot;, 2); // remove all parent/2 predicates
	 * </pre>
	 * 
	 * @param functor string functor that identify the predicate family to be
	 *                remove.
	 * @param arity   argument number that identify the predicate family to be
	 *                remove.
	 * @since 1.0
	 */
	public void abolish(String functor, int arity);

	/**
	 * Parse the string creating internal prolog clause and add the clause in the
	 * main memory program if the clause non exist. If the clause exist, will not
	 * overwritten and the clause will not added. The string have prolog fact or
	 * rule syntax. The added clause will be the first clause for a clause lot with
	 * the same predicate indicator (PI).
	 * 
	 * <pre>
	 * engine.asserta(&quot;grandparent(X,Z):-parent(X,Y),parent(Y,Z)&quot;);
	 * engine.asserta(&quot;parent( pat, jim)&quot;);
	 * engine.asserta(&quot;parent( bob, pat)&quot;);
	 * engine.asserta(&quot;parent( bob, ann)&quot;);
	 * engine.asserta(&quot;parent( tom, liz)&quot;);
	 * engine.asserta(&quot;parent( tom, bob)&quot;);
	 * engine.asserta(&quot;parent( pam, bob)&quot;);
	 * </pre>
	 * 
	 * @param stringClause - string for create internal prolog clause
	 * @since 1.0
	 */
	public void asserta(String stringClause);

	/**
	 * Add a rule specified by the rule head and rule body if the specified rule
	 * clause non exist. If the rule clause exist, will not overwritten and the
	 * clause will not added. The head term is the first argument and the body is
	 * constituted by the terms array followed of the rule head. If the body array
	 * is empty the clause will be considered like fact specified by head only. The
	 * added clause will be the first clause for a clause lot with the same
	 * predicate indicator (PI). The shared variables in the clause are declared
	 * once and use for build the terms that conform the clause to be added
	 * 
	 * <pre>
	 * String parent = &quot;parent&quot;;
	 * String grandparent = &quot;grandparent&quot;;
	 * 
	 * PrologAtom pam = provider.newAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newAtom(&quot;bob&quot;);
	 * PrologAtom ann = provider.newAtom(&quot;ann&quot;);
	 * PrologAtom pat = provider.newAtom(&quot;pat&quot;);
	 * 
	 * PrologVariable x = provider.newVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newVariable(&quot;Z&quot;);
	 * 
	 * engine.asserta(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
	 * 		provider.newStructure(parent, y, z));
	 * engine.asserta(provider.newStructure(parent, pat, jim));
	 * engine.asserta(provider.newStructure(parent, bob, pat));
	 * engine.asserta(provider.newStructure(parent, bob, ann));
	 * engine.asserta(provider.newStructure(parent, tom, liz));
	 * engine.asserta(provider.newStructure(parent, tom, bob));
	 * engine.asserta(provider.newStructure(parent, pam, bob));
	 * </pre>
	 * 
	 * @param head head of rule
	 * @param body array of terms that compound the body of the rule
	 * @since 1.0
	 */
	public void asserta(PrologTerm head, PrologTerm... body);

	/**
	 * Parse the string creating internal prolog clause and add the clause in the
	 * main memory program if the clause non exist. If the clause exist, will not
	 * overwritten and the clause will not added. The string have prolog fact or
	 * rule syntax. The added clause will be the last clause for a clause lot with
	 * the same predicate indicator (PI).
	 * 
	 * <pre>
	 * engine.assertz(&quot;parent( pam, bob)&quot;);
	 * engine.assertz(&quot;parent( tom, bob)&quot;);
	 * engine.assertz(&quot;parent( tom, liz)&quot;);
	 * engine.assertz(&quot;parent( bob, ann)&quot;);
	 * engine.assertz(&quot;parent( bob, pat)&quot;);
	 * engine.assertz(&quot;parent( pat, jim)&quot;);
	 * engine.assertz(&quot;grandparent(X,Z):-parent(X,Y),parent(Y,Z)&quot;);
	 * </pre>
	 * 
	 * @param stringClause string clause to be added
	 * @since 1.0
	 */
	public void assertz(String stringClause);

	/**
	 * <p>
	 * Add a rule specified by the rule head and rule body if the specified rule
	 * clause non exist. If the rule clause exist, will not overwritten and the
	 * clause will not added. The head term is the first argument and the body is
	 * constituted by the terms array followed of the rule head. If the body array
	 * is empty the clause will be considered like fact specified by head only. The
	 * added clause will be the last clause for a clause lot with the same predicate
	 * indicator (PI). The shared variables in the clause are declared once and use
	 * for build the terms that conform the clause to be added
	 * </p>
	 * 
	 * <pre>
	 * String parent = &quot;parent&quot;;
	 * String grandparent = &quot;grandparent&quot;;
	 * PrologAtom pam = provider.newAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newAtom(&quot;bob&quot;);
	 * PrologAtom ann = provider.newAtom(&quot;ann&quot;);
	 * PrologAtom pat = provider.newAtom(&quot;pat&quot;);
	 * PrologVariable x = provider.newVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newVariable(&quot;Z&quot;);
	 * engine.assertz(provider.newStructure(parent, pam, bob));
	 * engine.assertz(provider.newStructure(parent, tom, bob));
	 * engine.assertz(provider.newStructure(parent, tom, liz));
	 * engine.assertz(provider.newStructure(parent, bob, ann));
	 * engine.assertz(provider.newStructure(parent, bob, pat));
	 * engine.assertz(provider.newStructure(parent, pat, jim));
	 * engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
	 * 		provider.newStructure(parent, y, z));
	 * </pre>
	 * 
	 * @param head head of rule
	 * @param body array of terms that compound the body of the rule
	 * @since 1.0
	 */
	public void assertz(PrologTerm head, PrologTerm... body);

	/**
	 * Parse the string creating internal prolog clause and returning true if the
	 * clause in the main memory program unify with the given clause. If the clause
	 * not exist in main memory program or exist but not unify with the given clause
	 * false value is returned. The string have prolog fact or rule syntax.
	 * 
	 * <pre>
	 * boolean b = engine.clause(&quot;parent( pam, bob)&quot;);
	 * </pre>
	 * 
	 * <pre>
	 * boolean b = engine.clause(&quot;grandparent(X,Z):-parent(X,Y),parent(Y,Z)&quot;);
	 * </pre>
	 * 
	 * @param stringClause string clause to be match
	 * @return true if the clause in the main memory program unify with the given
	 *         clause, false otherwise
	 * @since 1.0
	 */
	public boolean clause(String stringClause);

	/**
	 * Find a rule specified by the rule head and rule body in main memory program
	 * that unify with the given clause returning true in this case.If the clause
	 * not exist in main memory program or exist but not unify with the given clause
	 * false value is returned. The head term is the first argument and the body is
	 * constituted by the terms array followed of the rule head. If the body array
	 * is empty the clause will be considered like fact specified by head only. The
	 * shared variables in the clause are declared once and use for build the terms
	 * that conform the clause to be found.
	 * 
	 * <pre>
	 * String parent = &quot;parent&quot;;
	 * PrologAtom pam = provider.newAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newAtom(&quot;bob&quot;);
	 * boolean b = engine.clause(provider.newStructure(parent, pam, bob));
	 * </pre>
	 * 
	 * <pre>
	 * String grandparent = &quot;grandparent&quot;;
	 * PrologVariable x = provider.newVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newVariable(&quot;Z&quot;);
	 * boolean b = engine.clause(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
	 * 		provider.newStructure(parent, y, z));
	 * </pre>
	 * 
	 * @param head head of rule
	 * @param body array of terms that compound the body of the rule
	 * @return true if the clause in the main memory program unify with the given
	 *         clause, false otherwise
	 * @since 1.0
	 */
	public boolean clause(PrologTerm head, PrologTerm... body);

	/**
	 * Parse the string creating internal prolog clause and remove the clause in the
	 * main memory program if the clause exist. The string have prolog fact or rule
	 * syntax.
	 * 
	 * <pre>
	 * engine.retract(&quot;parent( pam, bob).&quot;);
	 * engine.retract(&quot;parent( tom, bob).&quot;);
	 * engine.retract(&quot;parent( tom, liz).&quot;);
	 * engine.retract(&quot;parent( bob, ann).&quot;);
	 * engine.retract(&quot;parent( bob, pat).&quot;);
	 * engine.retract(&quot;parent( pat, jim).&quot;);
	 * engine.retract(&quot;grandparent(X,Z):-parent(X,Y),parent(Y,Z)&quot;);
	 * </pre>
	 * 
	 * @param stringClause string clause to be removed
	 * @since 1.0
	 */
	public void retract(String stringClause);

	/**
	 * Remove a rule specified by the rule head and rule body if the specified rule
	 * clause exist. The head term is the first argument and the body is constituted
	 * by the terms array followed of the rule head. If the body array is empty the
	 * clause will be considered like fact specified by head only. The shared
	 * variables in the clause are declared once and use for build the terms that
	 * conform the clause to be removed.
	 * 
	 * <pre>
	 * String parent = &quot;parent&quot;;
	 * String grandparent = &quot;grandparent&quot;;
	 * PrologAtom pam = provider.newAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newAtom(&quot;bob&quot;);
	 * PrologAtom ann = provider.newAtom(&quot;ann&quot;);
	 * PrologAtom pat = provider.newAtom(&quot;pat&quot;);
	 * PrologVariable x = provider.newVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newVariable(&quot;Z&quot;);
	 * engine.retract(provider.newStructure(parent, pam, bob));
	 * engine.retract(provider.newStructure(parent, tom, bob));
	 * engine.retract(provider.newStructure(parent, tom, liz));
	 * engine.retract(provider.newStructure(parent, bob, ann));
	 * engine.retract(provider.newStructure(parent, bob, pat));
	 * engine.retract(provider.newStructure(parent, pat, jim));
	 * engine.retract(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
	 * 		provider.newStructure(parent, y, z));
	 * </pre>
	 * 
	 * @param head head of rule
	 * @param body array of terms that compound the body of the rule
	 * @since 1.0
	 */
	public void retract(PrologTerm head, PrologTerm... body);

	/**
	 * Check that two terms (x and y) unify. Prolog unification algorithm is based
	 * on three principals rules:
	 * <ul>
	 * <li>If x and y are atomics constants then x and y unify only if they are same
	 * object.</li>
	 * <li>If x is a variable and y is anything then they unify and x is
	 * instantiated to y. Conversely, if y is a variable then this is instantiated
	 * to x.</li>
	 * <li>If x and y are structured terms then unify only if they match (equals
	 * funtor and arity) and all their correspondents arguments unify.</li>
	 * </ul>
	 * 
	 * 
	 * @param t1 the term to unify.
	 * @param t2 the term to unify.
	 * @return true if the specified term unify whit the current term, false
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean unify(PrologTerm t1, PrologTerm t2);

	/**
	 * Parse the string creating internal prolog clause and returning true if the
	 * given goal have solution using the resolution engine mechanism. If wrapped
	 * engine not support a dedicated method the
	 * 
	 * {@code query(goal).hasSolution()}
	 * 
	 * @param goal goal to be queried
	 * @return true if the given goal has solution
	 * @since 1.0
	 */
	public boolean contains(String goal);

	/**
	 * Check if the given goal array have solution using the resolution engine
	 * mechanism. If wrapped engine not support a dedicated method the
	 * 
	 * {@code query(goal).hasSolution()}
	 * 
	 * @param goal  goal to be checked
	 * @param goals goals array with the rest of conjunctive goals term (can be
	 *              empty)
	 * @return true if the given goal has solution
	 * @since 1.0
	 */
	public boolean contains(PrologTerm goal, PrologTerm... goals);

	/**
	 * Create a new query being the goal the given string with prolog syntax. The
	 * query creation process call the wrapped prolog engine and after query
	 * creation the query instance is ready to use. This particular query method can
	 * raise an syntax exception if the string query have prolog errors.
	 * 
	 * <pre>
	 * engine.query(&quot;parent(X, Y)&quot;);
	 * </pre>
	 * 
	 * @param query string goal with prolog syntax.
	 * @return a new query instance.
	 * @since 1.0
	 */
	public PrologQuery query(String query);

	/**
	 * Create a new query being the goal the given prolog term array. The given
	 * array is treated like a conjunctive goal and after success the first element
	 * the next term will be resolved. The query creation process call the wrapped
	 * prolog engine and after query creation the query instance is ready to use.
	 * 
	 * <pre>
	 * PrologTerm x = provider.newVariable(&quot;X&quot;, 0);
	 * PrologTerm dark = provider.newStructure(&quot;dark&quot;, x);
	 * PrologTerm big = provider.newStructure(&quot;big&quot;, x);
	 * PrologTerm[] goals = new PrologTerm[] { dark, big };
	 * engine.query(goals);
	 * </pre>
	 * 
	 * @param terms prolog term array to be query.
	 * @return a new query instance.
	 * @since 1.0
	 */
	public PrologQuery query(PrologTerm[] terms);

	/**
	 * Create a new query with at least one prolog term goal. The rest terms are
	 * treated like a conjunctive goal and after success the first element the next
	 * term will be resolved. The query creation process call the wrapped prolog
	 * engine and after query creation the query instance is ready to use.
	 * 
	 * <pre>
	 * PrologTerm x = provider.newVariable(&quot;X&quot;, 0);
	 * PrologTerm dark = provider.newStructure(&quot;dark&quot;, x);
	 * PrologTerm big = provider.newStructure(&quot;big&quot;, x);
	 * engine.query(dark, big);
	 * </pre>
	 * 
	 * @param term  prolog term to be query
	 * @param terms prolog term array to be query.
	 * @return a new query instance.
	 * @since 1.0
	 */
	public PrologQuery query(PrologTerm term, PrologTerm... terms);

	/**
	 * Create a new prolog query and return the prolog terms that conform the
	 * solution set for the current query. The solution set is a prolog terms map
	 * and every map entry is a pair variable name and variable instance value for
	 * the variables not anonymous involved in the query.
	 * 
	 * @param goal string query with prolog format.
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
	 * @since 1.0
	 */
	public Map<String, PrologTerm> queryOne(String goal);

	/**
	 * Create a new prolog query and return the prolog terms that conform the
	 * solution set for the current query. The solution set is a prolog terms map
	 * and every map entry is a pair variable name and variable instance value for
	 * the variables not anonymous involved in the query.
	 * 
	 * @param term  prolog term to be query
	 * @param terms prolog term array to be query.
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
	 * @since 1.0
	 */
	public Map<String, PrologTerm> queryOne(PrologTerm term, PrologTerm... terms);

	public List<Map<String, PrologTerm>> queryAll(String goal);

	public List<Map<String, PrologTerm>> queryAll(PrologTerm term, PrologTerm... terms);

	/**
	 * Create a new clause builder instance to build prolog clauses
	 * programmatically.
	 * 
	 * @return a new clause builder instance
	 * @since 1.0
	 */
	public PrologClauseBuilder newClauseBuilder();

	/**
	 * Create a new query builder instance to build prolog goal programmatically.
	 * 
	 * @return a new query builder instance
	 * @since 1.0
	 */
	public PrologQueryBuilder newQueryBuilder();

	/**
	 * Define an operator in the wrapped prolog engine with priority between 0 and
	 * 1200 and associativity determined by specifier according to the table below
	 * 
	 * <table BORDER > <caption>Specification table</caption>
	 * <tr>
	 * <td ALIGN=CENTER><b>Specifier</b></td>
	 * <td ALIGN=CENTER><b>Type</b></td>
	 * <td ALIGN=CENTER><b>Associativity</b></td>
	 * </tr>
	 * <tr>
	 * <td>fx</td>
	 * <td>prefix</td>
	 * <td>no</td>
	 * </tr>
	 * <tr>
	 * <td>fy</td>
	 * <td>prefix</td>
	 * <td>yes</td>
	 * </tr>
	 * <tr>
	 * <td>xf</td>
	 * <td>postfix</td>
	 * <td>no</td>
	 * </tr>
	 * <tr>
	 * <td>yf</td>
	 * <td>postfix</td>
	 * <td>yes</td>
	 * </tr>
	 * <tr>
	 * <td>xfx</td>
	 * <td>infix</td>
	 * <td>no</td>
	 * </tr>
	 * <tr>
	 * <td>yfx</td>
	 * <td>infix</td>
	 * <td>left</td>
	 * </tr>
	 * <tr>
	 * <td>xfy</td>
	 * <td>infix</td>
	 * <td>right</td>
	 * </tr>
	 * </table>
	 * 
	 * @since 1.0
	 * @param priority  operator priority between 0 and 1200
	 * @param specifier associative and position of the operator
	 * @param operator  operator to be defined
	 */
	public void operator(int priority, String specifier, String operator);

	/**
	 * Check if in the wrapped prolog engine is defined some particular predicate
	 * specified by your predicate indicator (PI = functor/arity). If the predicate
	 * is defined by prolog engine built-in or by user definition return true and
	 * false in other case. If wrapped engine not support a dedicated method the
	 * 
	 * {@link #currentPredicate(String, int)} will be defined like
	 * 
	 * {@code currentPredicates().contains(new PredicateIndicator(functor, arity));}
	 * 
	 * @since 1.0
	 * @param functor name of the predicate to be check.
	 * @param arity   argument number of the predicate to be check.
	 * @return true in functor/arity predicate is defined and false otherwise.
	 */
	public boolean currentPredicate(String functor, int arity);

	/**
	 * Check if in the wrapped prolog engine is defined some particular operator
	 * specified by your Priority, Specifier and Operator. If the operator is
	 * defined by prolog engine built-in or by user definition return true or false
	 * in other case. If wrapped engine not support a dedicated method the
	 * 
	 * {@link #currentOperator(int, String, String)}
	 * 
	 * will be defined like
	 * 
	 * {@code currentOperators().contains(new PrologOperator(priority, specifier, operator));}
	 * 
	 * @param priority  operator priority between 0 and 1200
	 * @param specifier associative and position of the operator
	 * @param operator  operator to be checked
	 * @return true if the operator is defined by prolog engine built-in or by user
	 *         definition, false otherwise.
	 * @since 1.0
	 */
	public boolean currentOperator(int priority, String specifier, String operator);

	/**
	 * Predicate set defined in the wrapped prolog engine. The predicate set will be
	 * constituted by the supported built-ins predicate in the wrapped prolog engine
	 * and user defined predicates present in the prolog program/database.
	 * 
	 * @return Defined Predicate Set.
	 * @since 1.0
	 */
	public Set<PrologIndicator> currentPredicates();

	/**
	 * Operator set defined in the wrapped prolog engine. The operator set will be
	 * constituted by the supported built-ins operator in the wrapped prolog engine
	 * and user defined operators present in the prolog program/database.
	 * 
	 * @return defined operator set.
	 * @since 1.0
	 */
	public Set<PrologOperator> currentOperators();

	/**
	 * Make and return a copy of the clause map present in the current engine. The
	 * map key is a functor/arity string and the value is a prolog clause family
	 * list.
	 * 
	 * @return a clause map present in the current engine.
	 * @since 1.0
	 */
	public Map<String, List<PrologClause>> getProgramMap();

	/**
	 * Make and return a copy of the clause set present in the current engine.
	 * 
	 * @return a clause set present in the current engine.
	 * @since 1.0
	 */
	public Set<PrologClause> getProgramClauses();

	/**
	 * Number of clauses in the current engine.
	 * 
	 * @return number of clauses in the engine.
	 * @since 1.0
	 */
	public int getProgramSize();

	/**
	 * Check if the program in main memory is empty returning true if the clause
	 * number in the program is 0 and false in otherwise. If wrapped engine not
	 * support a dedicated method {@link #isProgramEmpty()} will be defined like
	 * {@code PrologEngine#getProgramSize()==0;}.
	 * 
	 * @return true if the clause number in the program is 0 and false in otherwise.
	 * @since 1.0
	 */
	public boolean isProgramEmpty();

	public Set<PrologIndicator> getPredicates();

	public Set<PrologIndicator> getBuiltIns();

	/**
	 * Get a Prolog provider instance hold in the current engine.
	 * 
	 * @return a Prolog provider instance.
	 * @since 1.0
	 */
	public PrologProvider getProvider();

	/**
	 * Get the prolog system logger instance to report any errors or exceptions
	 * 
	 * @return prolog system logger instance
	 * @since 1.0
	 */
	public PrologLogger getLogger();

	/**
	 * License of the wrapped engine.
	 * 
	 * @return String license of the wrapped engine.
	 * @since 1.0
	 */
	public String getLicense();

	/**
	 * Version of the wrapped engine.
	 * 
	 * @return String version of the wrapped engine.
	 * @since 1.0
	 */
	public String getVersion();

	/**
	 * Name of the wrapped engine.
	 * 
	 * @return String name of the wrapped engine.
	 * @since 1.0
	 */
	public String getName();

	/**
	 * Clear program in main memory. Release all resources used and prepare the
	 * current engine for realize some new task.
	 * 
	 * @since 1.0
	 */
	public void dispose();

}
