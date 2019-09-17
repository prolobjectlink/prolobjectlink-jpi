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

/**
 * Prolog Provider is the class to interact with all prolog components (data
 * types, constants, logger, parser, converter and engine). Allow create data
 * types like atoms, numbers, lists, structures and variable terms.
 * 
 * Allow interact with the under-lying prolog engine to realize inferences
 * operations. Other used components are the prolog parser to parse prolog terms
 * in string form. The Prolog Provider offer a prolog system logger to report
 * every errors or exceptions.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologProvider extends PrologParser {

	/**
	 * True if wrapped engine implement ISO Prolog and false in other case
	 * 
	 * @return true if wrapped engine implement ISO Prolog and false in other case
	 * @since 1.0
	 */
	public boolean isCompliant();

	/**
	 * Get the prolog nil term representing the null data type for prolog data type
	 * system.
	 * 
	 * @return prolog nil term.
	 * @since 1.0
	 */
	public PrologTerm prologNil();

	/**
	 * Get the prolog term that represent the prolog cut built-in.
	 * 
	 * @return prolog cut term
	 * @since 1.0
	 */
	public PrologTerm prologCut();

	/**
	 * Get the prolog fail term that represent fail built-in.
	 * 
	 * @return prolog fail term
	 * @since 1.0
	 */
	public PrologTerm prologFail();

	/**
	 * Get the prolog true term that represent true built-in.
	 * 
	 * @return prolog true term
	 * @since 1.0
	 */
	public PrologTerm prologTrue();

	/**
	 * Get the prolog false term that represent false built-in.
	 * 
	 * @return prolog false term
	 * @since 1.0
	 */
	public PrologTerm prologFalse();

	/**
	 * Get the prolog empty list term.
	 * 
	 * @return prolog empty list term.
	 * @since 1.0
	 */
	public PrologTerm prologEmpty();

	/**
	 * Get the prolog term representing the directive use by under-laying prolog
	 * implementation for file inclusion. This directive is implementation
	 * depending. Prolog engines use at least one of
	 * (<tt>include/1 or consult/1 or ensure_loaded/1</tt>). A string file path is
	 * used to create the directive with the file to be include at runtime. In other
	 * words invoke this method for <tt>foo.pl</tt> return
	 * <tt>:-consult('foo.pl')</tt> prolog term if the under-laying prolog
	 * implementation use consult/1 directive for file inclusion.
	 * 
	 * @param file file path to be include
	 * @return prolog term representing include directive
	 * @since 1.0
	 */
	public PrologTerm prologInclude(String file);

	/**
	 * Create a new prolog engine instance ready to be operate. The created engine
	 * is clause empty and only have the defaults supported built-ins.
	 * 
	 * @return new prolog engine instance
	 * @since 1.0
	 */
	public PrologEngine newEngine();

	/**
	 * Create a new prolog engine instance ready to be operate. The created engine
	 * consult the given file loading the clauses present in this file and the
	 * defaults supported built-ins. Is equivalent to
	 * {@code newEngine().consult(file);}
	 * 
	 * @param file file path to be consulted
	 * @return new prolog engine instance
	 * @since 1.0
	 */
	public PrologEngine newEngine(String file);

	/**
	 * Create a prolog atom term setting like atom value the given string.
	 * 
	 * @param functor string value for the atom
	 * @return a prolog atom term
	 * @since 1.0
	 */
	public PrologAtom newAtom(String functor);

	/**
	 * Create a prolog float number instance with 0.0 value.
	 * 
	 * @return prolog float number with 0.0 value.
	 * @since 1.0
	 */
	public PrologFloat newFloat();

	/**
	 * Create a prolog float number instance with 0.0 value.
	 * 
	 * @param value numeric value
	 * @return prolog float number with 0.0 value.
	 * @since 1.0
	 */
	public PrologFloat newFloat(Number value);

	/**
	 * Create a prolog double number instance with 0.0 value.
	 * 
	 * @return prolog double number with 0.0 value.
	 * @since 1.0
	 */
	public PrologDouble newDouble();

	/**
	 * Create a prolog double number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog double number
	 * @since 1.0
	 */
	public PrologDouble newDouble(Number value);

	/**
	 * Create a prolog integer number instance with 0 value.
	 * 
	 * @return prolog integer number with 0 value.
	 * @since 1.0
	 */
	public PrologInteger newInteger();

	/**
	 * Create a prolog integer number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog integer number
	 * @since 1.0
	 */
	public PrologInteger newInteger(Number value);

	/**
	 * Create a prolog long number instance with 0 value.
	 * 
	 * @return prolog long number with 0 value.
	 * @since 1.0
	 */
	public PrologLong newLong();

	/**
	 * Create a prolog long number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog long number
	 * @since 1.0
	 */
	public PrologLong newLong(Number value);

	/**
	 * Create an anonymous variable instance with associated index. Index is a non
	 * negative integer that represent the variable position of the Structure where
	 * the variable is first time declared.
	 * 
	 * @param position Position of its Structure where the variable is first time
	 *                 declared.
	 * @return An anonymous variable instance with associated index.
	 * @throws IllegalArgumentException if position is a negative number
	 * @since 1.0
	 */
	public PrologVariable newVariable(int position);

	/**
	 * Create an named variable instance with associated index. Index is a non
	 * negative integer that represent the variable position of the Structure where
	 * the variable is first time declared.
	 * 
	 * @param name     variable name (upper case beginning)
	 * 
	 * @param position Position of its Structure where the variable is first time
	 *                 declared.
	 * @return A named variable instance with associated index.
	 * @throws IllegalArgumentException if position is a negative number
	 * @since 1.0
	 */
	public PrologVariable newVariable(String name, int position);

	/**
	 * Create an empty prolog list term. The created prolog list is equivalent to
	 * empty list term if the returned instance is not the prolog empty list itself.
	 * 
	 * @return an empty prolog list term
	 * @since 1.0
	 */
	public PrologList newList();

	/**
	 * Create a prolog list with one term item.
	 * 
	 * @param head term item to be include in the prolog list
	 * @return prolog list with one term item.
	 * @since 1.0
	 */
	public PrologList newList(PrologTerm head);

	/**
	 * Create a prolog list from prolog terms arguments array and the tail item is
	 * an empty list.
	 * 
	 * @param arguments prolog terms arguments array.
	 * @return a prolog list contained all terms in the prolog term array.
	 * @since 1.0
	 */
	public PrologList newList(PrologTerm[] arguments);

	/**
	 * Create a prolog list with two terms items [head | tail].
	 * 
	 * @param head term item to be include like head in the prolog list.
	 * @param tail term item to be include like tail in the prolog list.
	 * @return prolog list with two terms items [head | tail].
	 * @since 1.0
	 */
	public PrologList newList(PrologTerm head, PrologTerm tail);

	/**
	 * Create a prolog list from prolog terms arguments array and the tail item is
	 * the given prolog term.
	 * 
	 * @param arguments prolog terms arguments array.
	 * @param tail      prolog term to be the tail item.
	 * @return a prolog list contained all terms in the prolog term array and tail
	 *         item the specific term.
	 * @since 1.0
	 */
	public PrologList newList(PrologTerm[] arguments, PrologTerm tail);

	/**
	 * Create a prolog list with one object item.
	 * 
	 * @param head object item to be include in the prolog list
	 * @return prolog list with one term item.
	 * @since 1.0
	 */
	public PrologList newList(Object head);

	/**
	 * Create a prolog list from java objects arguments array and the tail item is
	 * an empty list.
	 * 
	 * @param arguments java objects arguments array.
	 * @return a prolog list contained all terms in the prolog term array.
	 * @since 1.0
	 */
	public PrologList newList(Object[] arguments);

	/**
	 * Create a prolog list with two java objects items [head | tail].
	 * 
	 * @param head object item to be include like head in the prolog list.
	 * @param tail object item to be include like tail in the prolog list.
	 * @return prolog list with two terms items [head | tail].
	 * @since 1.0
	 */
	public PrologList newList(Object head, Object tail);

	/**
	 * Create a prolog list from java objects arguments array and the tail item is
	 * the given java object.
	 * 
	 * @param arguments java objects arguments array.
	 * @param tail      java object to be the tail item.
	 * @return a prolog list contained all terms in the prolog term array and tail
	 *         item the specific term.
	 * @since 1.0
	 */
	public PrologList newList(Object[] arguments, Object tail);

	/**
	 * Create a prolog structure with the functor (structure name) and prolog terms
	 * arguments array.
	 * 
	 * <pre>
	 * PrologAtom bob = provider.newAtom("bob");
	 * PrologAtom tom = provider.newAtom("tom");
	 * PrologStructure parent = provider.newStructure("parent", tom, bob);
	 * System.out.println(parent);
	 * </pre>
	 * 
	 * @param functor   structure name.
	 * @param arguments prolog terms arguments array.
	 * @return prolog structure instance with the given functor and arguments.
	 * @since 1.0
	 */
	public PrologStructure newStructure(String functor, PrologTerm... arguments);

	/**
	 * Create a prolog structure with the functor (structure name) and java objects
	 * arguments array. The java objects arguments array is converter to prolog
	 * terms arguments array.
	 * 
	 * <pre>
	 * PrologStructure parent = provider.newStructure("parent", "tom", "bob");
	 * System.out.println(parent);
	 * </pre>
	 * 
	 * @param functor   structure name.
	 * @param arguments java objects arguments array.
	 * @return prolog structure instance with the given functor and arguments.
	 * @since 1.0
	 */
	public PrologTerm newStructure(String functor, Object... arguments);

	/**
	 * Create a prolog structure that represent an expression defined by your left
	 * and right operands separated by infix operator. The structure instance have
	 * like functor the expression operator and have two operands arguments terms.
	 * In other words the indicator for the resulting instance term is
	 * <tt>operator/2</tt>. The term creation not check operator definition and for
	 * this reason during inference process if the operator is not a supported
	 * built-in or define by <tt>op/3</tt> the inference fail.
	 * 
	 * <pre>
	 * PrologVariable x = provider.newVariable("X", 0);
	 * PrologDouble pi = provider.newDouble(Math.PI);
	 * PrologStructure plusExp = provider.newStructure(x, "+", pi);
	 * System.out.println(plusExp);
	 * </pre>
	 * 
	 * @param left     left hand operand
	 * @param operator infix operand
	 * @param right    right hand operand
	 * @return a prolog structure that represent an expression
	 * @since 1.0
	 */
	public PrologTerm newStructure(PrologTerm left, String operator, PrologTerm right);

	/**
	 * Create a prolog structure that represent an expression defined by your left
	 * and right operands separated by infix operator. The structure instance have
	 * like functor the expression operator and have two operands arguments terms.
	 * In other words the indicator for the resulting instance term is
	 * <tt>operator/2</tt>. The term creation not check operator definition and for
	 * this reason during inference process if the operator is not a supported
	 * built-in or define by <tt>op/3</tt> the inference fail.
	 * 
	 * <pre>
	 * PrologVariable x = provider.newVariable("X", 0);
	 * PrologDouble pi = provider.newDouble(Math.PI);
	 * PrologStructure plusExp = provider.newStructure(x, "+", pi);
	 * System.out.println(plusExp);
	 * </pre>
	 * 
	 * @param left     left hand operand
	 * @param operator infix operand
	 * @param right    right hand operand
	 * @return a prolog structure that represent an expression
	 * @since 1.0
	 */
	public PrologTerm newStructure(Object left, String operator, Object right);

	/**
	 * Create a prolog object reference term that hold the given object. This
	 * reference term is inspired on JPL JRef. This term is like a structure
	 * compound term that have like argument the object identification atom. The
	 * functor is the <tt>@</tt> character and the arity is 1. An example of this
	 * prolog term is e.g <tt>@(J#00000000000000425)</tt>. To access to the
	 * referenced object we need use {@link PrologTerm#getObject()}.
	 * 
	 * @param object object to be referenced
	 * @return a prolog object reference term
	 * @since 1.0
	 */
	public PrologTerm newReference(Object object);

	/**
	 * Get a Java to Prolog converter instance to map the abstract prolog data types
	 * to Java types.
	 * 
	 * @return Java to Prolog converter instance.
	 * @since 1.0
	 */
	public PrologJavaConverter getJavaConverter();

	/**
	 * Get a prolog converter instance to map the abstract prolog data types to
	 * under-laying prolog implementations data types.
	 * 
	 * @param <K> under-laying prolog data types
	 * @return prolog converter instance
	 * @since 1.0
	 */
	public <K> PrologConverter<K> getConverter();

	/**
	 * Get a prolog parser instance to parser the strings with prolog syntax.
	 * 
	 * @return prolog parser instance
	 * @since 1.0
	 */
	public PrologParser getParser();

	/**
	 * Get the prolog system logger instance to report any errors or exceptions
	 * 
	 * @return prolog system logger instance
	 * @since 1.0
	 */
	public PrologLogger getLogger();

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

}
