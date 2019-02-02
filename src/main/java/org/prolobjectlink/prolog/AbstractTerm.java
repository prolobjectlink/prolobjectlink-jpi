/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.prolobjectlink.prolog;

import java.util.Map;

import org.prolobjectlink.AbstractWrapper;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractTerm extends AbstractWrapper implements PrologTerm {

	protected int type;
	protected final PrologProvider provider;

	public AbstractTerm(PrologProvider provider) {
		this(0, provider);
	}

	public AbstractTerm(int type, PrologProvider provider) {
		this.type = type;
		this.provider = provider;
	}

	protected final void checkIndex(int index) {
		if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	protected final void checkIndex(int index, int max) {
		if (index < 0 || index > max) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	protected final void checkNumberType(PrologTerm term) {
		if (!term.isNumber()) {
			throw new NumberExpectedError(term);
		}
	}

	protected final String removeQuoted(String functor) {
		if (functor != null && functor.startsWith("\'") && functor.endsWith("\'")) {
			return functor.substring(1, functor.length() - 1);
		}
		return functor;
	}

	protected final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		return provider.toTerm(o, from);
	}

	protected final <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from) {
		return provider.toTermArray(os, from);
	}

	protected final <K extends PrologTerm> K[][] toTermTable(Object[][] oss, Class<K[][]> from) {
		return provider.toTermMatrix(oss, from);
	}

	protected final <K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map,
			Class<K> from) {
		return provider.toTermMap(map, from);
	}

	protected final <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(
			Map<String, V>[] map, Class<K> from) {
		return provider.toTermMapArray(map, from);
	}

	protected final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return provider.fromTerm(term, to);
	}

	protected final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		return provider.fromTermArray(terms, to);
	}

	protected final <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to) {
		return provider.fromTerm(head, body, to);
	}

	public PrologTerm getArgument(int index) {
		PrologTerm[] array = getArguments();
		checkIndex(index, array.length);
		return array[index];
	}

	public PrologTerm getTerm() {
		return this;
	}

	public final int getType() {
		return type;
	}

	public final PrologTerm getRight() {
		return getArgument(1);
	}

	public final PrologTerm getLeft() {
		return getArgument(0);
	}

	public final PrologProvider getProvider() {
		return provider;
	}

}
