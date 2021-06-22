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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

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
public interface PrologProvider extends PrologParser, Map<Class<?>, Prologable<?>> {

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
	 * Create a new PrologEntry using key-value pair of PrologTerm type. The
	 * resulting term is an implementation of {@link Entry} and {@link PrologTerm}.
	 * 
	 * @param key   key of the entry
	 * @param value value of the entry
	 * @return new PrologEntry term
	 * @since 1.1
	 */
	public PrologTerm newEntry(PrologTerm key, PrologTerm value);

	/**
	 * Create a new PrologEntry using key-value pair of Java object type.The given
	 * objects are converted to PrologTerm before entry creation. The resulting term
	 * is an implementation of {@link Entry} and {@link PrologTerm}.
	 * 
	 * @param key   key of the entry
	 * @param value value of the entry
	 * @return new PrologEntry term
	 * @since 1.1
	 */
	public PrologTerm newEntry(Object key, Object value);

	/**
	 * Constructs a new {@link PrologMap} with the same mappings as the specified
	 * {@link Map} of {@link PrologTerm} keys and values. The {@link PrologMap} is
	 * created with an initial capacity sufficient to hold the mappings in the
	 * specified {@link Map}. The resulting term is an implementation of {@link Map}
	 * and {@link PrologTerm}.
	 *
	 * @param map the map whose mappings are to be placed in this map
	 * @return a PrologMap with the given maps entries.
	 * @since 1.1
	 */
	public PrologTerm newMap(Map<PrologTerm, PrologTerm> map);

	/**
	 * Constructs an empty {@link PrologMap} with the specified initial capacity.
	 * The resulting term is an implementation of {@link Map} and
	 * {@link PrologTerm}.
	 *
	 * @param initialCapacity the initial capacity.
	 * @return an empty PrologMap
	 * @since 1.1
	 */
	public PrologTerm newMap(int initialCapacity);

	/**
	 * Constructs an empty {@link PrologMap} with the default initial capacity (16).
	 * The resulting term is an implementation of {@link Map} and
	 * {@link PrologTerm}.
	 * 
	 * @return an empty PrologMap
	 * @since 1.1
	 */
	public PrologTerm newMap();

	/**
	 * Create a prolog object reference term that hold the given object. This
	 * reference term is equivalent on JPL JRef. This term is like a structure
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
	 * Create a prolog object reference term that hold the Java false object
	 * i.e. @(false). This reference term is equivalent on JPL JFALSE.
	 * 
	 * @return a prolog object reference term that hold the Java false object.
	 * @since 1.1
	 */
	public PrologTerm falseReference();

	/**
	 * Create a prolog object reference term that hold the Java true object
	 * i.e. @(true). This reference term is equivalent on JPL JTRUE.
	 * 
	 * @return a prolog object reference term that hold the Java true object.
	 * @since 1.1
	 */
	public PrologTerm trueReference();

	/**
	 * Create a prolog object reference term that hold the Java null object
	 * i.e. @(null). This reference term is equivalent on JPL JNULL.
	 * 
	 * @return a prolog object reference term that hold the Java null object.
	 * @since 1.1
	 */
	public PrologTerm nullReference();

	/**
	 * Create a prolog object reference term that hold the Java void object
	 * i.e. @(void). This reference term is equivalent on JPL JVOID.
	 * 
	 * @return a prolog object reference term that hold the Java void object.
	 * @since 1.1
	 */
	public PrologTerm voidReference();

	/**
	 * Casts a PrologTerm to the class or interface represented by this
	 * {@code Class} object.
	 *
	 * @param term the object to be cast
	 * @return the PrologTerm after casting, or null if term is null
	 *
	 * @throws ClassCastException if the object is not null and is not assignable to
	 *                            the type T.
	 * @since 1.1
	 */
	public <T extends PrologTerm> T cast(PrologTerm term);

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
	 * Create an object instance using the class name for instantiation to the
	 * resulting object. More formally this method call
	 * {@link Class#forName(String)} using the class name. This method is called
	 * when the class have non arguments constructor.
	 * 
	 * @param className class name for instantiation
	 * @return an object instance of class name type
	 * @since 1.1
	 */
	public Object newObject(String className);

	/**
	 * Create an object instance using the class name for instantiation and object
	 * arguments array to initialize the resulting object. More formally this method
	 * call {@link Class#forName(String)} using the class name and
	 * {@link Constructor#newInstance(Object...)}.
	 * 
	 * @param className class name for instantiation
	 * @param arguments object arguments array
	 * @return an object instance of class name type
	 * @since 1.1
	 */
	public Object newObject(String className, Object... arguments);

	/**
	 * Create an object instance using the class name for instantiation to the
	 * resulting object. More formally this method call
	 * {@link Class#forName(String)} using the class name. This method is called
	 * when the class have non arguments constructor. To resolve the {@link String}
	 * class name this method call {@link PrologAtom#getFunctor()}.
	 * 
	 * @param className class name for instantiation
	 * @return an object instance of class name type
	 * @since 1.1
	 */
	public Object newObject(PrologAtom className);

	/**
	 * Create an object instance using the class name for instantiation and object
	 * arguments array to initialize the resulting object. More formally this method
	 * call {@link Class#forName(String)} using the class name and
	 * {@link Constructor#newInstance(Object...)}. To resolve the {@link String}
	 * class name this method call {@link PrologAtom#getFunctor()} and convert every
	 * {@link PrologTerm} in arguments array to equivalent object array.
	 * 
	 * @param className class name for instantiation
	 * @param arguments object arguments array
	 * @return an object instance of class name type
	 * @since 1.1
	 */
	public Object newObject(PrologAtom className, PrologTerm[] arguments);

	/**
	 * Access to some object field using the given field name.
	 * 
	 * @param reference object reference
	 * @param fieldName field name to access
	 * @return the value of specified field.
	 * @since 1.1
	 */
	public Object getObject(Object reference, String fieldName);

	/**
	 * Access to some object field using the given field name. To resolve the
	 * {@link String} field name this method call {@link PrologAtom#getFunctor()}
	 * 
	 * @param reference object reference
	 * @param fieldName field name to access
	 * @return the value of specified field.
	 * @since 1.1
	 */
	public Object getObject(Object reference, PrologAtom fieldName);

	/**
	 * Write in the given object reference in the specified field an object value
	 * using the given field name.
	 * 
	 * @param reference object reference
	 * @param fieldName field name to access
	 * @param value     object value to be write in the specified field
	 * @since 1.1
	 */
	public void setObject(Object reference, String fieldName, Object value);

	/**
	 * Write in the given object reference in the specified field an object value
	 * using the given field name. To resolve the {@link String} field name this
	 * method call {@link PrologAtom#getFunctor()}
	 * 
	 * @param reference object reference
	 * @param fieldName field name to access
	 * @param value     object value to be write in the specified field
	 * @since 1.1
	 */
	public void setObject(Object reference, PrologAtom fieldName, PrologTerm value);

	/**
	 * Call an object method specifying the string method name and the object array
	 * arguments. More formally this method determine the object {@link Method} and
	 * over this class call {@link Method#invoke(Object, Object...)}.
	 * 
	 * @param reference  object reference to invoke some method
	 * @param methodName string method name
	 * @param arguments  object array arguments
	 * @return the resulting object after invoke the given method name
	 * @since 1.1
	 */
	public Object callObject(Object reference, String methodName, Object... arguments);

	/**
	 * Call an object method specifying the string method name and the object array
	 * arguments. More formally this method determine the object {@link Method} and
	 * over this class call {@link Method#invoke(Object, Object...)}. To resolve the
	 * {@link String} class name this method call {@link PrologAtom#getFunctor()}
	 * and convert every {@link PrologTerm} in arguments array to equivalent object
	 * array.
	 * 
	 * @param reference  object reference to invoke some method
	 * @param methodName string method name
	 * @param arguments  object array arguments
	 * @return the resulting object after invoke the given method name
	 * @since 1.1
	 */
	public Object callObject(Object reference, PrologAtom methodName, PrologTerm... arguments);

	/**
	 * Call an object method specifying the string method name and the object array
	 * arguments. More formally this method determine the object {@link Method} and
	 * over this class call {@link Method#invoke(Object, Object...)}.
	 * 
	 * @param reference  object reference to invoke some method
	 * @param methodName string method name
	 * @return the resulting object after invoke the given method name
	 * @since 1.1
	 */
	public Object callObject(Object reference, String methodName);

	/**
	 * Call an object method specifying the string method name and the object array
	 * arguments. More formally this method determine the object {@link Method} and
	 * over this class call {@link Method#invoke(Object, Object...)}. To resolve the
	 * {@link String} class name this method call {@link PrologAtom#getFunctor()}
	 * and convert every {@link PrologTerm} in arguments array to equivalent object
	 * array.
	 * 
	 * @param reference  object reference to invoke some method
	 * @param methodName string method name
	 * @return the resulting object after invoke the given method name
	 * @since 1.1
	 */
	public Object callObject(Object reference, PrologAtom methodName);

	/**
	 * Prolog thread is a thread of execution in a program. The Java Virtual Machine
	 * allows an application to have multiple threads of execution running
	 * concurrently.
	 * 
	 * Every thread has a priority. Threads with higher priority are executed in
	 * preference to threads with lower priority. Each thread may or may not also be
	 * marked as a daemon. When code running in some thread creates a new Thread
	 * object, the new thread has its priority initially set equal to the priority
	 * of the creating thread, and is a daemon thread if and only if the creating
	 * thread is a daemon.
	 * 
	 * @param goals targets goals to be executed
	 * @return new prolog thread
	 * @since 1.1
	 */
	public PrologThread newThread(PrologTerm... goals);

	/**
	 * Prolog thread is a thread of execution in a program. The Java Virtual Machine
	 * allows an application to have multiple threads of execution running
	 * concurrently.
	 * 
	 * Every thread has a priority. Threads with higher priority are executed in
	 * preference to threads with lower priority. Each thread may or may not also be
	 * marked as a daemon. When code running in some thread creates a new Thread
	 * object, the new thread has its priority initially set equal to the priority
	 * of the creating thread, and is a daemon thread if and only if the creating
	 * thread is a daemon.
	 * 
	 * @param name  name of the thread
	 * @param goals targets goals to be executed
	 * @return new prolog thread
	 * @since 1.1
	 */
	public PrologThread newThread(String name, PrologTerm... goals);

	/**
	 * Get the current prolog thread. More formally wrap the current runtime thread
	 * into prolog thread class and return an instance.
	 * 
	 * @param goals targets goals to be executed in the current thread
	 * @return current prolog thread
	 * @since 1.1
	 */
	public PrologThread currentThread(PrologTerm... goals);

	/**
	 * Return a single thread that is the union of all given threads
	 * 
	 * @param threads thread array to be joined
	 * @return a single thread that is the union of all given threads
	 * @since 1.1
	 */
	public PrologThread joinThreads(PrologThread... threads);

	/**
	 * Return a single thread that is the union of all given threads
	 * 
	 * @param name    resulting thread name
	 * @param threads thread array to be joined
	 * @return a single thread that is the union of all given threads
	 * @since 1.1
	 */
	public PrologThread joinThreads(String name, PrologThread... threads);

	/**
	 * 
	 * @return
	 * @since 1.1
	 */
	public PrologThreadPool newThreadPool();

	/**
	 * 
	 * @return
	 * @since 1.1
	 */
	public PrologThreadPool newThreadPool(int parallelismLevel);

	/**
	 * 
	 * @return
	 * @since 1.1
	 */
	public PrologTerm newNamespace();

	/**
	 * 
	 * @return
	 * @since 1.1
	 */
	public PrologTerm newNamespace(String path);

	/**
	 * 
	 * @return
	 * @since 1.1
	 */
	public PrologTerm newNamespace(String parent, PrologNamespace chields);

	/**
	 * Create a new PrologField using name-type pair of PrologTerm type. The
	 * resulting term is an implementation of {@link Entry} and {@link PrologTerm}.
	 * 
	 * @param name key of the entry
	 * @param type value of the entry
	 * @return new PrologEntry term
	 * @since 1.1
	 */
	public PrologTerm newField(PrologTerm name, PrologTerm type);

	/**
	 * Create a new PrologField using name-type pair of Java object type.The given
	 * objects are converted to PrologTerm before entry creation. The resulting term
	 * is an implementation of {@link Entry} and {@link PrologTerm}.
	 * 
	 * @param name key of the entry
	 * @param type value of the entry
	 * @return new PrologEntry term
	 * @since 1.1
	 */
	public PrologTerm newField(Object name, Object type);

	public PrologTerm newInterface(String name, PrologTerm... declarations);

	public PrologTerm newInterface(String namespace, String name, PrologTerm... declarations);

	public PrologTerm newInterface(PrologTerm namespace, String name, PrologTerm... declarations);

	/**
	 * Create a new fact clause. A fatc clause is only represented by clause head
	 * and no have clause body. The body for this clause type is null. The other
	 * parameters are boolean clause properties. If a clause have any of this
	 * properties specify with true value.
	 * 
	 * 
	 * @param head          clause head
	 * @param dynamic       true if clause is dynamic, false otherwise
	 * @param multifile     true if clause is multifile, false otherwise
	 * @param discontiguous true if clause is discontiguous, false otherwise
	 * @since 1.1
	 */
	public PrologClause newMethod(PrologTerm head, boolean dynamic, boolean multifile, boolean discontiguous);

	/**
	 * Create a new rule clause. A rule clause is represented by clause head and
	 * body. The other parameters are boolean clause properties. If a clause have
	 * any of this properties specify with true value.
	 * 
	 * 
	 * @param head          clause head
	 * @param body          clause body
	 * @param dynamic       true if clause is dynamic, false otherwise
	 * @param multifile     true if clause is multifile, false otherwise
	 * @param discontiguous true if clause is discontiguous, false otherwise
	 * @since 1.1
	 */
	public PrologClause newMethod(PrologTerm head, PrologTerm body, boolean dynamic, boolean multifile,
			boolean discontiguous);

	/**
	 * Create a new fact clause. A fatc clause is only represented by clause head
	 * and no have clause body. The body for this clause type is null. The other
	 * parameters are boolean clause properties. If a clause have any of this
	 * properties specify with true value.
	 * 
	 * 
	 * @param head          clause head
	 * @param result        function result
	 * @param dynamic       true if clause is dynamic, false otherwise
	 * @param multifile     true if clause is multifile, false otherwise
	 * @param discontiguous true if clause is discontiguous, false otherwise
	 * @since 1.1
	 */
	public PrologClause newFunction(PrologTerm head, PrologTerm result, boolean dynamic, boolean multifile,
			boolean discontiguous);

	/**
	 * Create a new rule clause. A rule clause is represented by clause head and
	 * body. The other parameters are boolean clause properties. If a clause have
	 * any of this properties specify with true value.
	 * 
	 * 
	 * @param head          clause head
	 * @param body          clause body
	 * @param result        function result
	 * @param dynamic       true if clause is dynamic, false otherwise
	 * @param multifile     true if clause is multifile, false otherwise
	 * @param discontiguous true if clause is discontiguous, false otherwise
	 * @since 1.1
	 */
	public PrologClause newFunction(PrologTerm head, PrologTerm body, PrologTerm result, boolean dynamic,
			boolean multifile, boolean discontiguous);

	public PrologTerm newClass(String name);

	public PrologTerm newClass(String namespace, String name);

	public PrologTerm newClass(PrologTerm namespace, String name);

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
