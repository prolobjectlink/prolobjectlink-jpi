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

import static io.github.prolobjectlink.prolog.PrologTermType.CLASS_TYPE;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

public final class PrologClass extends PrologMixin implements PrologTerm {

	private PrologMixin superclass;
	private final Set<PrologTerm> fields = new LinkedHashSet<PrologTerm>();
	private final Set<PrologClause> constructors = new LinkedHashSet<PrologClause>();

	PrologClass(PrologProvider provider, String name) {
		super(CLASS_TYPE, provider, name);
	}

	@Deprecated
	PrologClass(PrologProvider provider, String namespace, String name) {
		super(CLASS_TYPE, provider, namespace, name);
	}

	@Deprecated
	PrologClass(PrologProvider provider, PrologTerm namespace, String name) {
		super(CLASS_TYPE, provider, namespace.getFunctor(), name);
	}

	protected final void checkClassFunctor(PrologClause emptyConstructor) {
		if (!emptyConstructor.getFunctor().equals(removeQuoted(getFunctor()))) {
			throw new IllegalArgumentException(
					"The given constructor name is not a valid constructor for " + getFunctor());
		}
	}

	public final int getArity() {
		return 1 + constructors.size() + methods.size() + nested.size();
	}

	public final PrologTerm[] getArguments() {
		int i = 0;
		Iterator<PrologMixin> nitr = nested.iterator();
		Iterator<PrologClause> mitr = methods.iterator();
		Iterator<PrologClause> citr = constructors.iterator();
		PrologTerm[] array = new PrologTerm[getArity()];
		PrologTerm[] arguments = fields.toArray(new PrologTerm[0]);
		array[i++] = provider.newList(arguments);
		for (; citr.hasNext(); i++) {
			array[i] = citr.next().getTerm();
		}
		for (; mitr.hasNext(); i++) {
			array[i] = mitr.next().getTerm();
		}
		for (; nitr.hasNext(); i++) {
			array[i] = nitr.next().getTerm();
		}
		return array;
	}

	public final PrologMixin getSuperclass() {
		return superclass;
	}

	public final void setSuperclass(PrologMixin interfacce) {
		this.superclass = interfacce;
	}

	public final Collection<PrologTerm> getFields() {
		return fields;
	}

	public final Collection<PrologClause> getConstructors() {
		return constructors;
	}

	public final void addField(PrologTerm prologTerm) {
		fields.add(prologTerm);
	}

	/**
	 * Create a new PrologField using name-type pair of PrologTerm type. The
	 * resulting term is an implementation of {@link Entry} and {@link PrologTerm}.
	 * 
	 * @param name key of the entry
	 * @param type value of the entry
	 * @return new PrologEntry term
	 * @since 1.1
	 */
	public final PrologTypedField addField(PrologTerm name, PrologTerm type) {
		PrologTypedField field = provider.newField(name, type).cast();
		fields.add(field);
		return field;
	}

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
	public final PrologTypedField addField(String name, String type) {
		PrologTypedField field = provider.newField(name, type).cast();
		fields.add(field);
		return field;
	}

	protected final void removeFields(PrologTypedField field) {
		fields.remove(field);
	}

	public final PrologClass addNestedClass(String name) {
		PrologClass cls = new PrologClass(provider, name);
		addNestedClass(cls);
		return cls;
	}

	@Deprecated
	private final PrologClass addNestedClass(String namespace, String name) {
		PrologClass cls = new PrologClass(provider, namespace, name);
		addNestedClass(cls);
		return cls;
	}

	@Deprecated
	private final PrologClass addNestedClass(PrologTerm namespace, String name) {
		PrologClass cls = new PrologClass(provider, namespace, name);
		addNestedClass(cls);
		return cls;
	}

	public final void addConstructor(PrologClause emptyConstructor) {
		checkClassFunctor(emptyConstructor);
		constructors.add(emptyConstructor);
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
	public final PrologClause addConstructor(PrologTerm head, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		PrologMethod method = new PrologMethod(provider, head, dynamic, multifile, discontiguous);
		addConstructor(method);
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
	public final PrologClause addConstructor(PrologTerm head, PrologTerm body, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		PrologMethod method = new PrologMethod(provider, head, body, dynamic, multifile, discontiguous);
		addConstructor(method);
		return method;
	}

	protected final void removeConstructor(PrologMethod constructor) {
		constructors.remove(constructor);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((constructors == null) ? 0 : constructors.hashCode());
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((superclass == null) ? 0 : superclass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrologClass other = (PrologClass) obj;
		if (constructors == null) {
			if (other.constructors != null)
				return false;
		} else if (!constructors.equals(other.constructors))
			return false;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (superclass == null) {
			if (other.superclass != null)
				return false;
		} else if (!superclass.equals(other.superclass))
			return false;
		return true;
	}

	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		for (PrologTerm prologTerm : directives) {
			builder.append(":-" + prologTerm);
			builder.append('.');
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
		builder.append('[');
		Iterator<?> i = fields.iterator();
		while (i.hasNext()) {
			Object field = i.next();
			builder.append(field);
			if (i.hasNext()) {
				builder.append(',');
				builder.append(' ');
			}
		}
		builder.append(']');
		builder.append('.');
		builder.append('\n');
		builder.append('\n');
		builder.append('\t');
		for (PrologClause constructor : constructors) {
			builder.append(constructor.getHead());
			if (constructor.isMethod()) {
				builder.append(":-\n\t\t");
				Iterator<PrologTerm> j = constructor.getBodyIterator();
				while (j.hasNext()) {
					builder.append(j.next());
					if (j.hasNext()) {
						builder.append(",\n\t\t");
					}
				}
			}
			builder.append('.');
			builder.append('\n');
			builder.append('\n');
			builder.append('\t');
		}
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
				builder.append(":-\n\t\t");
				Iterator<PrologTerm> j = method.getBodyIterator();
				while (j.hasNext()) {
					builder.append(j.next());
					if (j.hasNext()) {
						builder.append(",\n\t\t");
					}
				}
			}
			builder.append('.');
			builder.append('\n');
			builder.append('\n');
			builder.append('\t');
		}
		builder.append('\n');
		for (PrologMixin cls : nested) {
			builder.append(cls);
			builder.append('\n');
		}
		builder.append('\n');
		builder.append(getRightencloser());
		return builder.toString();
	}

}
