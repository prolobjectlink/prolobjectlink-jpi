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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PrologMixin extends AbstractCompounds implements PrologTerm {

	protected final String name;
	protected final Set<PrologTerm> directives = new LinkedHashSet<PrologTerm>();
	protected final Set<PrologClause> methods = new LinkedHashSet<PrologClause>();
	protected final Set<PrologMixin> ancestors = new LinkedHashSet<PrologMixin>();
	protected final Set<PrologMixin> nesteds = new LinkedHashSet<PrologMixin>();

	private static final char SEPARATOR = '/';
	private static final char LEFT_ENCLOSER = '(';
	private static final char RIGHT_ENCLOSER = ')';

	PrologMixin(PrologProvider provider, String name) {
		super(PrologTermType.MIXIN_TYPE, provider);
		this.name = name;
	}

	protected PrologMixin(int type, PrologProvider provider, String name) {
		super(type, provider);
		this.name = name;
	}

	public final boolean isList() {
		return false;
	}

	public final boolean isStructure() {
		return true;
	}

	public final boolean isEmptyList() {
		return false;
	}

	public int getArity() {
		return methods.size() + nesteds.size();
	}

	public final String getFunctor() {
		return /* namesapce + SEPARATOR + */ name;
	}

	public PrologTerm[] getArguments() {
		int i = 0;
		Iterator<PrologMixin> nitr = nesteds.iterator();
		Iterator<PrologClause> mitr = methods.iterator();
		PrologTerm[] array = new PrologTerm[getArity()];
		for (; mitr.hasNext(); i++) {
			array[i] = mitr.next().getTerm();
		}
		for (; nitr.hasNext(); i++) {
			array[i] = mitr.next().getTerm();
		}
		return array;
	}

	public final Collection<PrologClause> getMethods() {
		return methods;
	}

	public final Collection<PrologTerm> getDirectives() {
		return directives;
	}

	public final Collection<PrologMixin> getAncestors() {
		return ancestors;
	}

	public final Collection<PrologMixin> getNesteds() {
		return nesteds;
	}

	public final String getName() {
		return name;
	}

	public final char getSeparator() {
		return SEPARATOR;
	}

	public final char getLeftencloser() {
		return LEFT_ENCLOSER;
	}

	public final char getRightencloser() {
		return RIGHT_ENCLOSER;
	}

	public final void addMethod(PrologClause method) {
		methods.add(method);
	}

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
	public final PrologClause addMethod(PrologTerm head, boolean dynamic, boolean multifile, boolean discontiguous) {
		PrologMethod method = new PrologMethod(provider, head, dynamic, multifile, discontiguous);
		methods.add(method);
		return method;
	}

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
	public final PrologClause addMethod(PrologTerm head, PrologTerm body, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		PrologMethod method = new PrologMethod(provider, head, body, dynamic, multifile, discontiguous);
		methods.add(method);
		return method;
	}

	protected final void removeMethod(PrologClause method) {
		methods.remove(method);
	}

	public final void addFunction(PrologClause function) {
		methods.add(function);
	}

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
	public final PrologClause addFunction(PrologTerm head, PrologTerm result, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		PrologFunction function = new PrologFunction(provider, head, result, dynamic, multifile, discontiguous);
		methods.add(function);
		return function;
	}

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
	public final PrologClause addFunction(PrologTerm head, PrologTerm body, PrologTerm result, boolean dynamic,
			boolean multifile, boolean discontiguous) {
		PrologFunction function = new PrologFunction(provider, head, body, result, dynamic, multifile, discontiguous);
		methods.add(function);
		return function;
	}

	protected final void removFunction(PrologClause function) {
		methods.remove(function);
	}

	public final void addDirective(PrologTerm directive) {
		directives.add(directive);
	}

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
	 * @since 1.1
	 */
	public final PrologTerm addDirective(String functor, PrologTerm... arguments) {
		PrologTerm directive = provider.newStructure(functor, arguments);
		addDirective(directive);
		return directive;
	}

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
	 * @since 1.1
	 */
	public final PrologTerm addDirective(String functor, Object... arguments) {
		PrologTerm directive = provider.newStructure(functor, arguments);
		addDirective(directive);
		return directive;
	}

	protected final void removeDirective(PrologTerm directive) {
		directives.remove(directive);
	}

	public final void addAncestor(PrologMixin ancestor) {
		ancestors.add(ancestor);
	}

	protected final void removeAncestor(PrologMixin ancestor) {
		ancestors.remove(ancestor);
	}

	public final void addNestedClass(PrologMixin mixin) {
		nesteds.add(mixin);
	}

	protected final void removeNestedClass(PrologMixin mixin) {
		nesteds.remove(mixin);
	}

	public final PrologClause[] findMethod(String name, PrologTerm... parameters) {
		PrologClause[] clauses = new PrologClause[0];
		List<PrologClause> list = new ArrayList<PrologClause>();
		for (PrologClause clause : methods) {
			PrologMethod method = clause.cast();
			PrologTerm[] arguments = method.getArguments();
			String functor = method.getHead().getFunctor();
			if (functor.equals(name) && Arrays.equals(arguments, parameters)) {
				list.add(method);
			}
		}
		return list.toArray(clauses);
	}

	public final PrologClause[] findMethod(String name, PrologTerm result, PrologTerm... parameters) {
		PrologClause[] clauses = new PrologClause[0];
		List<PrologClause> list = new ArrayList<PrologClause>();
		for (PrologClause clause : methods) {
			if (clause.isFunction()) {
				PrologFunction f = clause.cast();
				PrologTerm[] arguments = f.getArguments();
				String functor = f.getHead().getFunctor();
				if (functor.equals(name) && f.getResult().equals(result) && Arrays.equals(arguments, parameters)) {
					list.add(f);
				}
			}
		}
		return list.toArray(clauses);
	}

	public final PrologMixin findAncestor(String name) {
		String s = getName() + " no exxtends from " + name + " class";
		for (PrologMixin prologMixin : ancestors) {
			if (prologMixin.getName().equals(name)) {
				return prologMixin;
			}
		}
		throw new NoClassDefFoundError(s);
	}

	public final PrologMixin findNestedClass(String name) {
		String s = getName() + " no declare " + name + " class";
		for (PrologMixin prologMixin : nesteds) {
			if (prologMixin.getName().equals(name)) {
				return prologMixin;
			}
		}
		throw new NoClassDefFoundError(s);
	}

	public final String toPath() {
		String path = getName().replace('.', '/');
		path = path.replace('\'', ' ');
		path = path.trim();
		return path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ancestors.hashCode();
		result = prime * result + directives.hashCode();
		result = prime * result + methods.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + nesteds.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrologMixin other = (PrologMixin) obj;
		if (ancestors == null) {
			if (other.ancestors != null)
				return false;
		} else if (!ancestors.equals(other.ancestors))
			return false;
		if (directives == null) {
			if (other.directives != null)
				return false;
		} else if (!directives.equals(other.directives))
			return false;
		if (methods == null) {
			if (other.methods != null)
				return false;
		} else if (!methods.equals(other.methods))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nesteds == null) {
			if (other.nesteds != null)
				return false;
		} else if (!nesteds.equals(other.nesteds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (PrologTerm prologTerm : directives) {
			builder.append(":-" + prologTerm);
			builder.append('\n');
		}
		builder.append('\n');
		for (PrologMixin ancestor : ancestors) {
			builder.append(":-include(" + ancestor.toPath() + ").");
			builder.append('\n');
		}
		builder.append('\n');
		builder.append(name);
		builder.append(getLeftencloser());
		builder.append('\n');
		builder.append('\n');
		builder.append('\t');
		for (PrologClause method : methods) {
			builder.append(method.getHead());
			if (method.isMethod()) {
				if (method.isFunction()) {
					builder.append(' ');
					builder.append('=');
					builder.append(' ');
					PrologFunction f = method.cast();
					builder.append(f.getResult());
					builder.append(' ');
				}
				if (method.isRule()) {
					builder.append(":-\n\t\t");
					Iterator<PrologTerm> j = method.getBodyIterator();
					while (j.hasNext()) {
						builder.append(j.next());
						if (j.hasNext()) {
							builder.append(",\n\t\t");
						}
					}
				}
			}
			builder.append('.');
			builder.append('\n');
			builder.append('\n');
			builder.append('\t');
		}
		builder.append('\n');
		builder.append(getRightencloser());
		return builder.toString();
	}

}
