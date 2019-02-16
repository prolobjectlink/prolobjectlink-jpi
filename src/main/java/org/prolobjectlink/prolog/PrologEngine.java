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

import org.prolobjectlink.Platform;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologEngine extends Platform, Iterable<PrologClause> {

	public void include(String path);

	public void consult(String path);

	public void persist(String path);

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
	 * PrologAtom pam = provider.newPrologAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newPrologAtom(&quot;bob&quot;);
	 * PrologAtom ann = provider.newPrologAtom(&quot;ann&quot;);
	 * PrologAtom pat = provider.newPrologAtom(&quot;pat&quot;);
	 * 
	 * PrologVariable x = provider.newPrologVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newPrologVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newPrologVariable(&quot;Z&quot;);
	 * 
	 * engine.asserta(provider.newPrologStructure(grandparent, x, z), provider.newPrologStructure(parent, x, y),
	 * 		provider.newPrologStructure(parent, y, z));
	 * engine.asserta(provider.newPrologStructure(parent, pat, jim));
	 * engine.asserta(provider.newPrologStructure(parent, bob, pat));
	 * engine.asserta(provider.newPrologStructure(parent, bob, ann));
	 * engine.asserta(provider.newPrologStructure(parent, tom, liz));
	 * engine.asserta(provider.newPrologStructure(parent, tom, bob));
	 * engine.asserta(provider.newPrologStructure(parent, pam, bob));
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
	 * PrologAtom pam = provider.newPrologAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newPrologAtom(&quot;bob&quot;);
	 * PrologAtom ann = provider.newPrologAtom(&quot;ann&quot;);
	 * PrologAtom pat = provider.newPrologAtom(&quot;pat&quot;);
	 * PrologVariable x = provider.newPrologVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newPrologVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newPrologVariable(&quot;Z&quot;);
	 * engine.assertz(provider.newPrologStructure(parent, pam, bob));
	 * engine.assertz(provider.newPrologStructure(parent, tom, bob));
	 * engine.assertz(provider.newPrologStructure(parent, tom, liz));
	 * engine.assertz(provider.newPrologStructure(parent, bob, ann));
	 * engine.assertz(provider.newPrologStructure(parent, bob, pat));
	 * engine.assertz(provider.newPrologStructure(parent, pat, jim));
	 * engine.assertz(provider.newPrologStructure(grandparent, x, z), provider.newPrologStructure(parent, x, y),
	 * 		provider.newPrologStructure(parent, y, z));
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
	 * PrologAtom pam = provider.newPrologAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newPrologAtom(&quot;bob&quot;);
	 * boolean b = engine.clause(provider.newPrologStructure(parent, pam, bob));
	 * </pre>
	 * 
	 * <pre>
	 * String grandparent = &quot;grandparent&quot;;
	 * PrologVariable x = provider.newPrologVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newPrologVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newPrologVariable(&quot;Z&quot;);
	 * boolean b = engine.clause(provider.newPrologStructure(grandparent, x, z),
	 * 		provider.newPrologStructure(parent, x, y), provider.newPrologStructure(parent, y, z));
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
	 * PrologAtom pam = provider.newPrologAtom(&quot;pam&quot;);
	 * PrologAtom bob = provider.newPrologAtom(&quot;bob&quot;);
	 * PrologAtom ann = provider.newPrologAtom(&quot;ann&quot;);
	 * PrologAtom pat = provider.newPrologAtom(&quot;pat&quot;);
	 * PrologVariable x = provider.newPrologVariable(&quot;X&quot;);
	 * PrologVariable y = provider.newPrologVariable(&quot;Y&quot;);
	 * PrologVariable z = provider.newPrologVariable(&quot;Z&quot;);
	 * engine.retract(provider.newPrologStructure(parent, pam, bob));
	 * engine.retract(provider.newPrologStructure(parent, tom, bob));
	 * engine.retract(provider.newPrologStructure(parent, tom, liz));
	 * engine.retract(provider.newPrologStructure(parent, bob, ann));
	 * engine.retract(provider.newPrologStructure(parent, bob, pat));
	 * engine.retract(provider.newPrologStructure(parent, pat, jim));
	 * engine.retract(provider.newPrologStructure(grandparent, x, z), provider.newPrologStructure(parent, x, y),
	 * 		provider.newPrologStructure(parent, y, z));
	 * </pre>
	 * 
	 * @param head head of rule
	 * @param body array of terms that compound the body of the rule
	 * @since 1.0
	 */
	public void retract(PrologTerm head, PrologTerm... body);

	public boolean unify(PrologTerm t1, PrologTerm t2);

	public Map<String, PrologTerm> match(PrologTerm t1, PrologTerm t2);

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
	 * @param goal goal array to be queried
	 * @return true if the given goal has solution
	 * @since 1.0
	 */
	public boolean contains(PrologTerm goal, PrologTerm... goals);

	public PrologQuery query(String query);

	PrologQuery query(PrologTerm[] terms);

	public PrologQuery query(PrologTerm term, PrologTerm... terms);

	public Map<String, PrologTerm> queryOne(String goal);

	public Map<String, PrologTerm> queryOne(PrologTerm term, PrologTerm... goal);

	public List<Map<String, PrologTerm>> queryAll(String goal);

	public List<Map<String, PrologTerm>> queryAll(PrologTerm term, PrologTerm... goal);

	public PrologClauseBuilder newClauseBuilder();

	public PrologQueryBuilder newQueryBuilder();

	/**
	 * Define an operator in the wrapped prolog engine with priority priority
	 * between 0 and 1200 and associativity determined by specifier according to the
	 * table
	 * 
	 * <pre>
	 * Specifier----Type----Associativity
	 * ----fx------prefix--------no
	 * ----fy------prefix--------yes
	 * ----xf------postfix-------no
	 * ----yf------postfix-------yes
	 * ----xfx------infix--------no
	 * ----yfx------infix--------left
	 * ----xfy------infix--------right
	 * </pre>
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
	 * defined by prolog engine built-in or by user definition return true and false
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

	public Set<PrologClause> getProgramClauses();

	/**
	 * Number of clauses in main memory program.
	 * 
	 * @return number of clauses in the program.
	 * @since 1.0
	 */
	public int getProgramSize();

	/**
	 * Check if the program in main memory is empty returning true if the clause
	 * number in the program is 0 and false in otherwise. If wrapped engine not
	 * support a dedicated method {@link #isProgramEmpty()} will be defined like
	 * {@code IPrologEngine#getProgramSize()==0;}.
	 * 
	 * @return true if the clause number in the program is 0 and false in otherwise.
	 * @since 1.0
	 */
	public boolean isProgramEmpty();

	public Set<PrologIndicator> getPredicates();

	public Set<PrologIndicator> getBuiltIns();

	public PrologProvider getProvider();
	
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

	public int hashCode();

	public boolean equals(Object obj);

	/**
	 * Clear program in main memory. Release all resources used and prepare the
	 * current engine for realize some new task.
	 * 
	 * @since 1.0
	 */
	public void dispose();

}
