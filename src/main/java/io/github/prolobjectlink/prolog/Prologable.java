/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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
