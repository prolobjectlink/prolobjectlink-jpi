/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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

/**
 * PrologMapping is an interface for custom converters. The users classes
 * implementing this interface for configure a special conversion from
 * PrologTerm to Java Object and to Java Object to PrologTerm. When registered
 * into a conversion context, a converter adds to the defined conversions a new
 * mapping.
 * 
 * <pre>
 * public class Parent {
 * 
 * 	private String parent;
 * 	private String child;
 * 
 * 	public Parent() {
 * 	}
 * 
 * 	public Parent(String parent, String child) {
 * 		this.parent = parent;
 * 		this.child = child;
 * 	}
 * 
 * 	public String getParent() {
 * 		return parent;
 * 	}
 * 
 * 	public void setParent(String parent) {
 * 		this.parent = parent;
 * 	}
 * 
 * 	public String getChild() {
 * 		return child;
 * 	}
 * 
 * 	public void setChild(String child) {
 * 		this.child = child;
 * 	}
 * 
 * }
 * </pre>
 * 
 * The mapping class
 * 
 * <pre>
 * public class ParentMapping implements PrologMapping&lt;Parent&gt; {
 * 
 * 	public Parent fromTerm(PrologProvider provider, PrologTerm t) {
 * 		String name = t.getArgument(0).getFunctor();
 * 		String child = t.getArgument(1).getFunctor();
 * 		Parent parent = new Parent(name, child);
 * 		return parent;
 * 	}
 * 
 * 	public PrologTerm toTerm(PrologProvider provider, Object o) {
 * 		if (o instanceof Parent) {
 * 			Parent p = (Parent) o;
 * 			String name = p.getParent();
 * 			String child = p.getChild();
 * 			return provider.newStructure("parent", name, child);
 * 		}
 * 		return provider.prologNil();
 * 	}
 * 
 * 	public PrologTerm toTerm(PrologProvider provider) {
 * 		PrologTerm name = provider.newVariable("Name", 0);
 * 		PrologTerm child = provider.newVariable("Child", 1);
 * 		PrologTerm[] arguments = new PrologTerm[] { name, child };
 * 		return provider.newStructure("parent", arguments);
 * 	}
 * 
 * 	public Class&gt;Parent&gt; getType() {
 * 		return Parent.class;
 * 	}
 * 
 * }
 * </pre>
 * 
 * @author Jose Zalacain
 * @param <O> Java Object implicit in the conversion.
 * @since 1.1
 */
public interface Prologable<O> {

	/**
	 * Create a Java Object representation from given PrologTerm.
	 * 
	 * @param t PrologTerm to be converted to Java Object
	 * @return An equivalent Java Object.
	 * @since 1.1
	 */
	public O fromTerm(PrologProvider provider, PrologTerm t);

	/**
	 * Create an equivalent PrologTerm using the given Java Object.
	 * 
	 * @param o Java Object to be converted in PrologTerm.
	 * @return An equivalent PrologTerm.
	 * @since 1.1
	 */
	public PrologTerm toTerm(PrologProvider provider, Object o);

	/**
	 * Return a PrologTerm. The resulting term is the most general predicate.
	 * 
	 * @param provider PrologProvider for create PrologTerm
	 * @return the most general for of PrologTerm
	 * @since 1.1
	 */
	public PrologTerm toTerm(PrologProvider provider);

	/**
	 * Return the Java class representation from given PrologTerm.
	 * 
	 * @return An equivalent Java Class.
	 * @since 1.1
	 */
	public Class<O> getType();

}
