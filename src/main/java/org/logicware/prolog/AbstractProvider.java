/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 WorkLogic Project
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

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.logicware.AbstractWrapper;

public abstract class AbstractProvider extends AbstractWrapper implements PrologProvider {

	protected final PrologConverter<?> converter;

	public AbstractProvider(PrologConverter<?> converter) {
		this.converter = converter;
	}

	public final PrologList parseList(String stringList) {
		PrologTerm term = parseTerm(stringList);
		checkListType(term);
		return (PrologList) term;
	}

	public final PrologClause parseClause(String clause) {
		return newEngine().iterator().next();
	}

	public final PrologStructure parseStructure(String stringStructure) {
		PrologTerm term = parseTerm(stringStructure);
		checkStructureType(term);
		return (PrologStructure) term;
	}

	public final Set<PrologClause> parseProgram(String file) {
		return newEngine(file).getProgramClauses();
	}

	public final Set<PrologClause> parseProgram(File in) {
		return parseProgram(in.getAbsolutePath());
	}

	public final PrologEngine newEngine(String path) {
		PrologEngine engine = newEngine();
		engine.consult(path);
		return engine;
	}

	public final PrologFloat newFloat() {
		return newFloat(0F);
	}

	public final PrologDouble newDouble() {
		return newDouble(0D);
	}

	public final PrologInteger newInteger() {
		return newInteger(0);
	}

	public final PrologLong newLong() {
		return newLong(0L);
	}

	public final <K extends PrologTerm> K toTerm(Object o, Class<K> from) {
		return converter.toTerm(o, from);
	}

	public final <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from) {
		return converter.toTermArray(os, from);
	}

	public final <K extends PrologTerm> K[][] toTermMatrix(Object[][] oss, Class<K[][]> from) {
		return converter.toTermMatrix(oss, from);
	}

	public final <K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map,
			Class<K> from) {
		return converter.toTermMap(map, from);
	}

	public final <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(Map<String, V>[] map,
			Class<K> from) {
		return converter.toTermMapArray(map, from);
	}

	public final PrologConverter<?> getConverter() {
		return converter;
	}

	public final <K> K fromTerm(PrologTerm term, Class<K> to) {
		return converter.fromTerm(term, to);
	}

	public final <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to) {
		return converter.fromTermArray(terms, to);
	}

	public final <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to) {
		return converter.fromTerm(head, body, to);
	}

	public final DefaultQueryBuilder newQueryBuilder() {
		return new DefaultQueryBuilder(this);
	}

	public final DefaultClauseBuilder newClauseBuilder() {
		return new DefaultClauseBuilder(this);
	}

	public final DefaultClauseBuilder newClauseBuilder(String file) {
		return new DefaultClauseBuilder(newEngine(file));
	}

	public PrologParser getParser() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((converter == null) ? 0 : converter.hashCode());
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
		AbstractProvider other = (AbstractProvider) obj;
		if (converter == null) {
			if (other.converter != null)
				return false;
		} else if (!converter.equals(other.converter)) {
			return false;
		}
		return true;
	}

	@Override
	public abstract String toString();

	protected final void checkNumberType(PrologTerm term) {
		if (!term.isNumber()) {
			throw new NumberExpectedError(term);
		}
	}

	protected final void checkListType(PrologTerm term) {
		if (!term.isList()) {
			throw new ListExpectedError(term);
		}
	}

	protected final void checkStructureType(PrologTerm term) {
		if (!term.isStructure()) {
			throw new StructureExpectedError(term);
		}
	}

	protected final void checkExpressionType(PrologTerm term) {
		if (!term.isEvaluable()) {
			throw new ExpressionExpectedError(term);
		}
	}

	protected final void checkIndexOutOfBound(int index, int lenght) {
		if (index < 0 || index > lenght) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

}
