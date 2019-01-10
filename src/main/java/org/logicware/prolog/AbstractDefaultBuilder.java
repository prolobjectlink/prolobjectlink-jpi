/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2018 WorkLogic Project
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
package org.logicware.prolog;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractDefaultBuilder implements PrologBuilder {

	protected final PrologEngine engine;
	protected final StringBuilder builder;
	protected final PrologProvider provider;

	public AbstractDefaultBuilder(PrologEngine engine) {
		this.provider = engine.getProvider();
		this.builder = new StringBuilder();
		this.engine = engine;
	}

	public AbstractDefaultBuilder(PrologProvider provider) {
		this.engine = provider.newEngine();
		this.builder = new StringBuilder();
		this.provider = provider;
	}

	public AbstractDefaultBuilder(PrologProvider provider, String file) {
		this.engine = provider.newEngine(file);
		this.builder = new StringBuilder();
		this.provider = provider;
	}

	protected final void append(Object object) {
		builder.append(object);
	}

	protected final void append(String functor, PrologTerm... arguments) {
		if (arguments != null && arguments.length > 0) {
			builder.append(provider.newStructure(functor, arguments));
		} else {
			builder.append(provider.newAtom(functor));
		}
	}

	protected final void append(Object left, String operator, Object right) {
		builder.append(left);
		append(' ');
		builder.append(operator);
		append(' ');
		builder.append(right);
	}

	public final PrologEngine getEngine() {
		return engine;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultQueryBuilder other = (DefaultQueryBuilder) obj;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.equals(other.engine)) {
			return false;
		}
		if (builder == null) {
			if (other.builder != null)
				return false;
		} else if (!builder.toString().equals(other.builder.toString())) {
			return false;
		}
		return true;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		result = prime * result + ((builder == null) ? 0 : builder.hashCode());
		return result;
	}

	@Override
	public final String toString() {
		return "" + builder + "";
	}

}
