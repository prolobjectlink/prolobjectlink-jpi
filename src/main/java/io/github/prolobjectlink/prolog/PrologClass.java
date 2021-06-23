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

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

final class PrologClass extends PrologMixin implements PrologTerm {

	private PrologClass superclass;
	private final List<PrologField> fields = new ArrayList<PrologField>();
	private final List<PrologClass> nested = new ArrayList<PrologClass>();
	private final List<PrologMethod> constructors = new ArrayList<PrologMethod>();

	PrologClass(PrologProvider provider, String name) {
		super(provider, name);
	}

	PrologClass(PrologProvider provider, String namespace, String name) {
		super(provider, namespace, name);
	}

	PrologClass(PrologProvider provider, PrologTerm namespace, String name) {
		super(provider, namespace.getFunctor(), name);
	}

	protected final void checkClassFunctor(PrologMethod constructor) {
		if (!constructor.getFunctor().equals(getFunctor())) {
			throw new IllegalArgumentException(
					"The given constructor name is not a valid constructor for " + getFunctor());
		}
	}

	public final PrologClass getSuperclass() {
		return superclass;
	}

	public final void setSuperclass(PrologClass superclass) {
		this.superclass = superclass;
	}

	public final List<PrologField> getFields() {
		return fields;
	}

	public final List<PrologClass> getNested() {
		return nested;
	}

	public final List<PrologMethod> getConstructors() {
		return constructors;
	}

	public final void addFields(PrologField field) {
		fields.add(field);
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
	public final PrologField addField(PrologTerm name, PrologTerm type) {
		return addField(name.getFunctor(), type.getFunctor());
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
	public final PrologField addField(String name, String type) {
		PrologField field = new PrologField(provider, name, type);
		fields.add(field);
		return field;
	}

	protected final void removeFields(PrologField field) {
		fields.remove(field);
	}

	public final void addNestedClass(PrologClass cls) {
		nested.add(cls);
	}

	public final PrologClass addNestedClass(String name) {
		PrologClass cls = new PrologClass(provider, name);
		addNestedClass(cls);
		return cls;
	}

	public final PrologClass addNestedClass(String namespace, String name) {
		PrologClass cls = new PrologClass(provider, namespace, name);
		addNestedClass(cls);
		return cls;
	}

	public final PrologClass addNestedClass(PrologTerm namespace, String name) {
		PrologClass cls = new PrologClass(provider, namespace, name);
		addNestedClass(cls);
		return cls;
	}

	protected final void removeNestedClass(PrologClass cls) {
		nested.remove(cls);
	}

	public final void addConstructor(PrologMethod constructor) {
		checkClassFunctor(constructor);
		constructors.add(constructor);
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
	public final int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + constructors.hashCode();
		result = prime * result + fields.hashCode();
		result = prime * result + methods.hashCode();
		result = prime * result + nested.hashCode();
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrologClass other = (PrologClass) obj;
		if (other.constructors != null)
			return false;
		else if (!constructors.equals(other.constructors)) {
			return false;
		}
		if (other.fields != null)
			return false;
		else if (!fields.equals(other.fields)) {
			return false;
		}
		if (methods == null) {
			if (other.methods != null)
				return false;
		} else if (!methods.equals(other.methods)) {
			return false;
		}
		if (other.nested != null)
			return false;
		else if (!nested.equals(other.nested)) {
			return false;
		}
		return true;
	}

	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(":-namespace(" + namesapce + ").");
		builder.append('\n');
		for (PrologTerm prologTerm : directives) {
			builder.append(":-" + prologTerm);
			builder.append('\n');
		}
		builder.append('\n');
		for (PrologMixin ancestor : ancestors) {
			builder.append(":-" + ancestor);
			builder.append('\n');
		}
		builder.append('\n');
		builder.append(name);
		builder.append(getLeftencloser());
		builder.append('\n');
		builder.append('[');
		for (PrologField field : fields) {
			builder.append(field);
			builder.append(',');
		}
		builder.append(']');
		builder.append('\n');
		for (PrologClause constructor : constructors) {
			builder.append(constructor);
			builder.append('\n');
		}
		builder.append('\n');
		for (PrologClause method : methods) {
			builder.append(method);
			builder.append('\n');
		}
		builder.append('\n');
		for (PrologClass cls : nested) {
			builder.append(cls);
			builder.append('\n');
		}
		builder.append('\n');
		builder.append(getRightencloser());
		return builder.toString();
	}

}
