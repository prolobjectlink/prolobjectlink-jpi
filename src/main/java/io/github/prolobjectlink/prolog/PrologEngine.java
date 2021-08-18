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

import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A PrologEngine instance is used in order to interact with the concrete prolog
 * engine. Is used for interact with the Prolog database or to invoke a built-in
 * predicate. It is initialized using a provider instance and calling new engine
 * {@link PrologProvider#newEngine()}
 * 
 * <pre>
 * PrologProvider provider = Prolog.getProvider(Some_PrologProvider_Impl.class);
 * PrologEngine engine = provider.newEngine();
 * </pre>
 * 
 * The {@link PrologProvider#newEngine(String)} method is used if the specified
 * file to consult is provided.
 * 
 * The prolog engine have a life cycle on the user application. After create an
 * instance we can consult any file with prolog programs using
 * {@link #consult(Reader)} or {@link #consult(String)}. If another prolog file
 * need to be include in main memory we can use {@link #include(Reader)} or
 * {@link #include(String)}. We can include so much files as we needed.
 * 
 * <pre>
 * engine.consult("family.pl");
 * engine.include("company.pl");
 * engine.include("zoo.pl");
 * </pre>
 * 
 * To check if the given goal have solution using the resolution engine
 * mechanism we can use {@link #contains(String)} or
 * {@link #contains(PrologTerm, PrologTerm...)}. Both methods returning true if
 * the given goal have solution or false in other case.
 * 
 * <pre>
 * boolean b = engine.clause("parent( pam, bob)");
 * </pre>
 * 
 * To check if there are a clause in the prolog engine we can use
 * {@link #clause(String)} or {@link #clause(PrologTerm, PrologTerm...)}. This
 * methods is based on the prolog engine built-in clause/2 that check if in the
 * currents predicates exist a clause definition that match with the given
 * clause. This methods return true if the given clause is defined in the prolog
 * engine or false in other case. The following cases return true.
 * 
 * <pre>
 * engine.assertz("parent( pam, bob)");
 * boolean b = engine.clause("parent( X, Y);
 * boolean t = engine.clause("parent( pam, bob)")
 * </pre>
 * 
 * Java Prolog Interface have an special interface to query to prolog engine
 * about some specific clauses an operate over solution set. {@link PrologQuery}
 * is the mechanism to query the prolog database loaded in prolog engine. The
 * way to create a new prolog query is invoking
 * {@link PrologEngine#query(String)},
 * {@link PrologEngine#query(PrologTerm, PrologTerm...)} or
 * {@link PrologEngine#query(PrologTerm[])}. This interface have several methods
 * to obtain one, at least N or all query results.
 * 
 * <pre>
 * PrologEngine engine = provider.newEngine("zoo.pl");
 * PrologVariable x = provider.newVariable("X", 0);
 * PrologQuery query = engine.query(provider.newStructure("dark", x));
 * List&lt;Object&gt; solution = query.oneResult();
 * for (int i = 0; i &lt; solution.size(); i++) {
 * 	System.out.println(solution.get(i));
 * }
 * </pre>
 * 
 * Java Prolog Interface have shortcuts methods to obtain queries result from
 * the engine. The shortcuts methods to obtain one result are
 * {@link PrologEngine#queryOne(Class)}, {@link PrologEngine#queryOne(Object)},
 * {@link PrologEngine#queryOne(PrologTerm)},
 * {@link PrologEngine#queryOne(String)},
 * {@link PrologEngine#queryOne(Class, Class...)},
 * {@link PrologEngine#queryOne(Object, Object...)},
 * {@link PrologEngine#queryOne(PrologTerm, PrologTerm...)}. This methods are
 * equivalent to call {@code PrologEngine.query(...).oneVariablesResult()}.
 * 
 * queryN
 * 
 * The shortcuts methods to obtain all results are
 * {@link PrologEngine#queryAll(Class)}, {@link PrologEngine#queryAll(Object)},
 * {@link PrologEngine#queryAll(PrologTerm)},
 * {@link PrologEngine#queryAll(String)},
 * {@link PrologEngine#queryAll(Class, Class...)},
 * {@link PrologEngine#queryAll(Object, Object...)},
 * {@link PrologEngine#queryAll(PrologTerm, PrologTerm...)}. This methods are
 * equivalent to call {@code PrologEngine.query(...).allVariablesResult()}.
 * 
 * After create an empty prolog engine and load any prolog program, we can
 * modify the program/database. For this propose we have the methods
 * {@link #asserta(String)}, {@link #asserta(PrologTerm,PrologTerm...)} or
 * {@link #assertz(String)}, {@link #assertz(PrologTerm,PrologTerm...)}. The
 * asserta methods are used when we need store a prolog clause term on top in
 * the clause family and assertz by other hand, are when we need store a prolog
 * clause term on the tail in the clause family.
 * 
 * <pre>
 * engine.asserta("grandparent(X,Z):-parent(X,Y),parent(Y,Z)");
 * engine.asserta("parent( pat, jim)");
 * </pre>
 * 
 * <pre>
 * engine.assertz("parent( pat, jim)");
 * engine.assertz("grandparent(X,Z):-parent(X,Y),parent(Y,Z)");
 * </pre>
 * 
 * For remove clauses in the main memory program if the clause exist can be used
 * {@link #retract(String)} or {@link #retract(PrologTerm, PrologTerm...)}. Both
 * methods after build internal clause representation find the specific clause
 * and if this exist remove it. By other hand if we need remove all clause
 * family we can use {@link #abolish(String, int)}. This method remove all
 * predicates that match with the predicate indicator (PI) formed by the
 * concatenation of the given string functor and integer arity separated by
 * slash (functor/arity).
 * 
 * <pre>
 * engine.retract("parent( tom, bob)");
 * </pre>
 * 
 * <pre>
 * engine.abolish("parent", 2);
 * </pre>
 * 
 * All change that take place in the current engine can save to some specific
 * file using {@link #persist(String)} or {@link #persist(Writer)}.
 * 
 * <pre>
 * engine.assertz(provider.newStructure("parent", pam, bob));
 * engine.assertz(provider.newStructure("parent", tom, bob));
 * engine.assertz(provider.newStructure("parent", tom, liz));
 * engine.assertz(provider.newStructure("parent", bob, ann));
 * engine.assertz(provider.newStructure("parent", bob, pat));
 * engine.assertz(provider.newStructure("parent", pat, jim));
 * 
 * engine.persist("family.pl");
 * </pre>
 * 
 * By the last {@link #dispose()} clear program in main memory. Release all
 * resources used and prepare the current engine for realize some new task.
 * 
 * <pre>
 * engine.dispose();
 * </pre>
 * 
 * An additional capability of the prolog engine is the {@link Iterable}
 * implementation. This allow to prolog engine iterate over all user defined
 * clauses using a for-each loop or iterator loop.
 * 
 * <pre>
 * for (PrologClause c : engine) {
 * 	// do something
 * }
 * </pre>
 * 
 * <pre>
 * Iterator&lt;?&gt; i = engine.iterator();
 * while (i.hasNext()) {
 * 	// do something
 * }
 * </pre>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologEngine extends Iterable<PrologClause>, Map<Class<?>, Prologable<?>> {

	/**
	 * Register a PrologMapping to be used in object conversions
	 * 
	 * @param mapping PrologMapping to be used in object conversions.
	 * @since 1.1
	 */
	public void register(Prologable<?> mapping);

	/**
	 * Return a the most general form PrologTerm implicit in the PrologMapping
	 * 
	 * @param mapping PrologMapping to resolve PrologTerm
	 * @return the most general form PrologTerm implicit in the PrologMapping
	 * @since 1.1
	 */
	public PrologTerm getTerm(Prologable<?> mapping);

	/**
	 * Return the PrologTerm equivalent to Java object using the correspondent
	 * PrologMapping
	 * 
	 * @param mapping mapping PrologMapping to resolve PrologTerm
	 * @param o       Java object to convert in PrologTerm
	 * @return the PrologTerm equivalent to Java object
	 * @since 1.1
	 */
	public <O> PrologTerm getTerm(Prologable<?> mapping, O o);

	/**
	 * Remove a PrologMapping to be used in object conversions
	 * 
	 * @param mapping PrologMapping to be removed.
	 * @since 1.1
	 */
	public void unregister(Prologable<?> mapping);

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
	 * Remove all predicates that match with the predicate indicator (PI) formed by
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
	 * Remove all predicates that match with the predicate indicator (PI) formed by
	 * the concatenation of the given string functor and integer arity separated by
	 * slash (functor/arity). The predicate indicator is formed after resolve the
	 * class mapping {@link Prologable} correspondent to the given class. For
	 * example if the class have two fields and have a prolog mapping to a predicate
	 * with "parent" functor two arguments, call this method will be equivalent to
	 * call {@link #abolish(String, int)} with "parent" string functor and arity
	 * equals to 2.
	 * 
	 * @param cls class to determine the predicate indicator after mapping resolve.
	 * @since 1.1
	 */
	public void abolish(Class<?> cls);

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
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * create internal prolog clause and add the clause in the main memory program
	 * if the clause non exist. If the clause exist, will not overwritten and the
	 * clause will not added. The added clause will be the first clause for a clause
	 * lot with the same predicate indicator (PI).
	 * 
	 * @param o object to be added after resolve the prolog equivalent term
	 * @since 1.1
	 */
	public <O> void asserta(O o);

	/**
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * create internal prolog clause and add the clause in the main memory program
	 * if the clause non exist. If the clause exist, will not overwritten and the
	 * clause will not added. The added clause will be the first clause for a clause
	 * lot with the same predicate indicator (PI). The given class will be mapping
	 * to the most general predicate.
	 * 
	 * @param cls object to be added after resolve the prolog equivalent term
	 * @since 1.1
	 */
	public void asserta(Class<?> cls);

	/**
	 * Create internal prolog clause and add the clause in the main memory program
	 * if the clause non exist. If the clause exist, will not overwritten and the
	 * clause will not added. The added clause will be the first clause for a clause
	 * lot with the same predicate indicator (PI).
	 * 
	 * @param term term to be added
	 * @since 1.1
	 */
	public void asserta(PrologTerm term);

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
	 * 
	 * @param head
	 * @param body
	 * @since 1.1
	 */
	public <O> void asserta(O head, O... body);

	/**
	 * 
	 * @param head
	 * @param body
	 * @since 1.1
	 */
	public void asserta(Class<?> head, Class<?>... body);

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
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * create internal prolog clause and add the clause in the main memory program
	 * if the clause non exist. If the clause exist, will not overwritten and the
	 * clause will not added. The added clause will be the last clause for a clause
	 * lot with the same predicate indicator (PI).
	 * 
	 * @param o object to be added after resolve the prolog equivalent term
	 * @since 1.1
	 */
	public <O> void assertz(O o);

	/**
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * create internal prolog clause and add the clause in the main memory program
	 * if the clause non exist. If the clause exist, will not overwritten and the
	 * clause will not added. The added clause will be the last clause for a clause
	 * lot with the same predicate indicator (PI).The given class will be mapping to
	 * the most general predicate.
	 * 
	 * @param cls object to be added after resolve the prolog equivalent term
	 * @since 1.1
	 */
	public void assertz(Class<?> cls);

	/**
	 * Create internal prolog clause and add the clause in the main memory program
	 * if the clause non exist. If the clause exist, will not overwritten and the
	 * clause will not added. The added clause will be the last clause for a clause
	 * lot with the same predicate indicator (PI).
	 * 
	 * @param term term to be added
	 * @since 1.1
	 */
	public void assertz(PrologTerm term);

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
	 * 
	 * @param head
	 * @param body
	 * @since 1.1
	 */
	public <O> void assertz(O head, O... body);

	/**
	 * 
	 * @param head
	 * @param body
	 * @since 1.1
	 */
	public void assertz(Class<?> head, Class<?>... body);

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
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * create internal prolog clause and returning true if the clause in the main
	 * memory program unify with the given clause. If the clause not exist in main
	 * memory program or exist but not unify with the given clause false value is
	 * returned.
	 * 
	 * @param o object to be match with some clause in main memory program
	 * @return true if the clause in the main memory program unify with the clause
	 *         equivalent to the given object, false otherwise
	 * @since 1.1
	 */
	public <O> boolean clause(O o);

	/**
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * create internal prolog clause and returning true if the clause in the main
	 * memory program unify with the given clause. If the clause not exist in main
	 * memory program or exist but not unify with the given clause false value is
	 * returned. The given class will be mapping to the most general predicate.
	 * 
	 * @param cls class to be match with some clause in main memory program
	 * @return true if the clause in the main memory program unify with the clause
	 *         equivalent to the given class, false otherwise
	 * @since 1.1
	 */
	public boolean clause(Class<?> cls);

	/**
	 * Find a rule specified by the rule head and rule body in main memory program
	 * that unify with the given clause returning true in this case.If the clause
	 * not exist in main memory program or exist but not unify with the given clause
	 * false value is returned. The shared variables in the clause are declared once
	 * and use for build the terms that conform the clause to be found.
	 * 
	 * @param term term to be match with some clause in main memory program
	 * @return true if the clause in the main memory program unify with the clause
	 *         equivalent to the given term, false otherwise
	 * @since 1.1
	 */
	public boolean clause(PrologTerm term);

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
	 * 
	 * @param head
	 * @param body
	 * @return
	 * @since 1.1
	 */
	public <O> boolean clause(O head, O... body);

	/**
	 * 
	 * @param head
	 * @param body
	 * @return
	 * @since 1.1
	 */
	public boolean clause(Class<?> head, Class<?>... body);

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
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * remove a fact specified by the head if the specified fact clause exist. The
	 * shared variables in the clause are declared once and use for build the terms
	 * that conform the clause to be removed.
	 * 
	 * @param o class mapped to fact to be removed
	 * @since 1.1
	 */
	public <O> void retract(O o);

	/**
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * remove a fact specified by the head if the specified fact clause exist. The
	 * shared variables in the clause are declared once and use for build the terms
	 * that conform the clause to be removed. The given class will be mapping to the
	 * most general predicate.
	 * 
	 * @param cls class mapped to fact to be removed
	 * @since 1.1
	 */
	public void retract(Class<?> cls);

	/**
	 * Remove a fact specified by the head if the specified fact clause exist. The
	 * shared variables in the clause are declared once and use for build the terms
	 * that conform the clause to be removed.
	 * 
	 * @param term fact to be removed
	 * @since 1.1
	 */
	public void retract(PrologTerm term);

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
	 * 
	 * @param head
	 * @param body
	 * @since 1.1
	 */
	public <O> void retract(O head, O... body);

	/**
	 * 
	 * @param head
	 * @param body
	 * @since 1.1
	 */
	public void retract(Class<?> head, Class<?>... body);

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
	 * Check that two class (cls1 and cls2) unify. Two classes unify if are equals
	 * (==) or your equivalents PrologTerm unify.
	 * 
	 * @param cls1 the class to unify.
	 * @param cls2 the class to unify.
	 * @return true if cls1 and cls2 unify, false otherwise.
	 * @since 1.1
	 */
	public boolean unify(Class<?> cls1, Class<?> cls2);

	/**
	 * Check that two objects (o1 and o2) unify. Two objects unify if are equals or
	 * your equivalents PrologTerm unify.
	 * 
	 * @param o1 the object to unify
	 * @param o2 the object to unify
	 * @return true if o1 and o2 unify, false otherwise.
	 * @since 1.1
	 */
	public boolean unify(Object o1, Object o2);

	/**
	 * Parse the string creating internal prolog clause and returning true if the
	 * given goal have solution using the resolution engine mechanism. If wrapped
	 * engine not support a dedicated method then contains can be defined like
	 * 
	 * {@code query(goal).hasSolution()}
	 * 
	 * @param goal goal to be queried
	 * @return true if the given goal has solution
	 * @since 1.0
	 */
	public boolean contains(String goal);

	/**
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * check if the resolved goal have solution using the resolution engine
	 * mechanism. If wrapped engine not support a dedicated method then contains can
	 * be defined like:
	 * 
	 * {@code query(goal).hasSolution()}
	 * 
	 * @param goal goal to be checked
	 * @return true if the given goal has solution
	 * @since 1.1
	 */
	public <O> boolean contains(O goal);

	/**
	 * Resolve the prolog equivalent term using user defined {@link Prologable} and
	 * check if the resolved goal have solution using the resolution engine
	 * mechanism. If wrapped engine not support a dedicated method then contains can
	 * be defined like:
	 * 
	 * {@code query(goal).hasSolution()}
	 * 
	 * The given class will be mapping to the most general predicate.
	 * 
	 * @param goal goal to be checked
	 * @return true if the given goal has solution
	 * @since 1.1
	 */
	public boolean contains(Class<?> goal);

	/**
	 * Check if the given goal array have solution using the resolution engine
	 * mechanism. If wrapped engine not support a dedicated method then contains can
	 * be defined like:
	 * 
	 * {@code query(goal).hasSolution()}
	 * 
	 * @param goal goal to be checked
	 * @return true if the given goal has solution
	 * @since 1.1
	 */
	public boolean contains(PrologTerm goal);

	/**
	 * Check if the given goal array have solution using the resolution engine
	 * mechanism. If wrapped engine not support a dedicated method then contains can
	 * be defined like:
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
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public <O> boolean contains(O goal, O... goals);

	/**
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public boolean contains(Class<?> goal, Class<?>... goals);

	/**
	 * Create a new query being the goal the given string with prolog syntax. The
	 * query creation process call the wrapped prolog engine and after query
	 * creation the query instance is ready to use. This particular query method can
	 * raise an syntax exception if the string query have prolog errors.
	 * 
	 * <pre>
	 * PrologQuery query = engine.query(&quot;parent(X, Y)&quot;);
	 * </pre>
	 * 
	 * @param query string goal with prolog syntax.
	 * @return a new query instance.
	 * @since 1.0
	 */
	public PrologQuery query(String query);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public <O> PrologQuery query(O goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public PrologQuery query(Class<?> goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public PrologQuery query(PrologTerm goal);

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
	 * PrologQuery query = engine.query(goals);
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
	 * PrologQuery query = engine.query(dark, big);
	 * </pre>
	 * 
	 * @param term  prolog term to be query
	 * @param terms prolog term array to be query.
	 * @return a new query instance.
	 * @since 1.0
	 */
	public PrologQuery query(PrologTerm term, PrologTerm... terms);

	/**
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public <O> PrologQuery query(O goal, O... goals);

	/**
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public PrologQuery query(Class<?> goal, Class<?>... goals);

	/**
	 * Create a new prolog query and return the prolog terms that conform the
	 * solution set for the current query. The solution set is a prolog terms map
	 * and every map entry is a pair variable name and variable instance value for
	 * the variables not anonymous involved in the query.
	 * 
	 * <pre>
	 * List&lt;Map&lt;String, PrologTerm&gt;&gt; m = engine.queryAll("parent(X, Y)");
	 * </pre>
	 * 
	 * @param goal string query with prolog format.
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
	 * @since 1.0
	 */
	public Map<String, PrologTerm> queryOne(String goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public <O> Map<String, PrologTerm> queryOne(O goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public Map<String, PrologTerm> queryOne(PrologTerm goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public Map<String, PrologTerm> queryOne(Class<?> goal);

	/**
	 * Create a new prolog query and return the prolog terms that conform the
	 * solution set for the current query. The solution set is a prolog terms map
	 * and every map entry is a pair variable name and variable instance value for
	 * the variables not anonymous involved in the query.
	 * 
	 * <pre>
	 * PrologVariable x = provider.newVariable("X", 0);
	 * PrologVariable y = provider.newVariable("Y", 1);
	 * Map&lt;String, PrologTerm&gt; m = engine.queryOne(provider.newStructure("parent", x, y));
	 * </pre>
	 * 
	 * @param term  prolog term to be query
	 * @param terms prolog term array to be query.
	 * @return variable name - variable instance (key - value) map that conform the
	 *         solution set for the current query.
	 * @since 1.0
	 */
	public Map<String, PrologTerm> queryOne(PrologTerm term, PrologTerm... terms);

	/**
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public <O> Map<String, PrologTerm> queryOne(O goal, O... goals);

	/**
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public Map<String, PrologTerm> queryOne(Class<?> goal, Class<?>... goals);

	/**
	 * Create a new prolog query and return the list of (N) prolog terms that
	 * conform the solution set for the current query. Each list item is a prolog
	 * terms map and every map entry is a pair variable name and variable instance
	 * value for the variables not anonymous involved in the query.
	 * 
	 * @param n    query result instance number
	 * @param goal string with prolog syntax to be query
	 * @return the list of prolog terms that conform the solution set for the
	 *         current query.
	 * @since 1.0
	 */
	public List<Map<String, PrologTerm>> queryN(int n, String goal);

	/**
	 * 
	 * @param n
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public <O> List<Map<String, PrologTerm>> queryN(int n, O goal);

	/**
	 * 
	 * @param n
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public List<Map<String, PrologTerm>> queryN(int n, PrologTerm goal);

	/**
	 * 
	 * @param n
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public List<Map<String, PrologTerm>> queryN(int n, Class<?> goal);

	/**
	 * Create a new prolog query and return the list of (N) prolog terms that
	 * conform the solution set for the current query. Each list item is a prolog
	 * terms map and every map entry is a pair variable name and variable instance
	 * value for the variables not anonymous involved in the query.
	 * 
	 * <pre>
	 * PrologVariable x = provider.newVariable("X", 0);
	 * PrologVariable y = provider.newVariable("Y", 1);
	 * List&lt;Map&lt;String, PrologTerm&gt;&gt; m = engine.queryN(5, provider.newStructure("parent", x, y));
	 * </pre>
	 * 
	 * @param n     query result instance number
	 * @param term  prolog term to be query
	 * @param terms prolog term array to be query.
	 * @return the list of prolog terms that conform the solution set for the
	 *         current query.
	 * @since 1.0
	 */
	public List<Map<String, PrologTerm>> queryN(int n, PrologTerm term, PrologTerm... terms);

	/**
	 * 
	 * @param n
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public <O> List<Map<String, PrologTerm>> queryN(int n, O goal, O... goals);

	/**
	 * 
	 * @param n
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public List<Map<String, PrologTerm>> queryN(int n, Class<?> goal, Class<?>... goals);

	/**
	 * Create a new prolog query and return the list of prolog terms that conform
	 * the solution set for the current query. Each list item is a prolog terms map
	 * and every map entry is a pair variable name and variable instance value for
	 * the variables not anonymous involved in the query.
	 * 
	 * @param goal string with prolog syntax to be query
	 * @return the list of prolog terms that conform the solution set for the
	 *         current query.
	 * @since 1.0
	 */
	public List<Map<String, PrologTerm>> queryAll(String goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public <O> List<Map<String, PrologTerm>> queryAll(O goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public List<Map<String, PrologTerm>> queryAll(PrologTerm goal);

	/**
	 * 
	 * @param goal
	 * @return
	 * @since 1.1
	 */
	public List<Map<String, PrologTerm>> queryAll(Class<?> goal);

	/**
	 * Create a new prolog query and return the list of prolog terms that conform
	 * the solution set for the current query. Each list item is a prolog terms map
	 * and every map entry is a pair variable name and variable instance value for
	 * the variables not anonymous involved in the query.
	 * 
	 * <pre>
	 * PrologVariable x = provider.newVariable("X", 0);
	 * PrologVariable y = provider.newVariable("Y", 1);
	 * List&lt;Map&lt;String, PrologTerm&gt;&gt; m = engine.queryAll(provider.newStructure("parent", x, y));
	 * </pre>
	 * 
	 * @param term  prolog term to be query
	 * @param terms prolog term array to be query.
	 * @return the list of prolog terms that conform the solution set for the
	 *         current query.
	 * @since 1.0
	 */
	public List<Map<String, PrologTerm>> queryAll(PrologTerm term, PrologTerm... terms);

	/**
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public <O> List<Map<String, PrologTerm>> queryAll(O goal, O... goals);

	/**
	 * 
	 * @param goal
	 * @param goals
	 * @return
	 * @since 1.1
	 */
	public List<Map<String, PrologTerm>> queryAll(Class<?> goal, Class<?>... goals);

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
	 * Create a PrologMixin with the given name. This mixin use is for declare
	 * virtual prolog methods. More formally this constructor method is used for
	 * define Prolog interfaces. The given name is a combination of namespace and
	 * class name. e.g <tt>"'com.acme.Mixin'"</tt>.
	 * 
	 * @param name         name of the mixin
	 * @param declarations prolog facts that define the interface.
	 * @return PrologMixin
	 * @since 1.1
	 */
	public PrologTerm newMixin(String name, PrologTerm... declarations);

	/**
	 * Create a PrologMixin with the given name. This mixin use is for declare
	 * extensible prolog methods. More formally this constructor method is used for
	 * define Prolog abstract classes. The given name is a combination of namespace
	 * and class name. e.g <tt>"'com.acme.Mixin'"</tt>.
	 * 
	 * @param name name of the mixin
	 * @return PrologMixin
	 * @since 1.1
	 */
	public PrologTerm newMixin(String name);

	/**
	 * Create a PrologClass with the given name. More formally this constructor
	 * method is used for define Prolog concrete classes. The given name is a
	 * combination of namespace and class name. e.g <tt>"'com.acme.Mixin'"</tt>.
	 * 
	 * @param name name of the class
	 * @return PrologMixin
	 * @since 1.1
	 */
	public PrologTerm newClass(String name);

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
	 * and user defined predicates present in the prolog program/database. More
	 * formally current predicate set is defined by the union between user defined
	 * predicates and prolog engine built-ins.
	 * 
	 * <pre>
	 * Set&lt;PrologIndicator&gt; cp = new HashSet&lt;PrologIndicator&gt;();
	 * cp.addAll(getPredicates());
	 * cp.addAll(getBuiltIns());
	 * </pre>
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
	 * Make and return a copy of the program.
	 * 
	 * @return a program in the current engine.
	 * @since 1.1
	 */
	public PrologProgram getProgram();

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

	/**
	 * User defined predicate set defined in the wrapped prolog engine. The
	 * predicate set will be constituted by user defined predicates present in the
	 * prolog program/database. The supported built-ins predicate in the wrapped
	 * prolog engine not be include in this set.
	 * 
	 * @return User Defined Predicate Set.
	 * @since 1.0
	 */
	public Set<PrologIndicator> getPredicates();

	/**
	 * Predicate set defined by the supported built-ins predicate in the wrapped
	 * prolog engine. The user defined predicates in the wrapped prolog engine not
	 * be include in this set.
	 * 
	 * @return built-ins predicates set.
	 * @since 1.0
	 */
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
	 * Under-laying engine vendor
	 * 
	 * @return engine vendor
	 * @since 1.1
	 */
	public String getVendor();

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
