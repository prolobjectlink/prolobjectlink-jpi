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
public class DefaultQueryBuilder extends AbstractDefaultBuilder implements PrologQueryBuilder {

	public DefaultQueryBuilder(PrologEngine engine) {
		super(engine);
	}

	public DefaultQueryBuilder(PrologProvider provider) {
		super(provider);
	}

	public DefaultQueryBuilder(PrologProvider provider, String file) {
		super(provider, file);
	}

	public final PrologQueryBuilder begin(String functor, PrologTerm... arguments) {
		append(functor, arguments);
		return this;
	}

	public final PrologQueryBuilder comma(PrologTerm body) {
		append(',');
		append(' ');
		append(body);
		return this;
	}

	public final PrologQueryBuilder comma(String functor, PrologTerm... arguments) {
		append(',');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public final PrologQueryBuilder comma(PrologTerm left, String operator, int right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder comma(int left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder comma(PrologTerm left, String operator, long right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder comma(long left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder comma(PrologTerm left, String operator, double right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder comma(double left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder comma(PrologTerm left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder semicolon(PrologTerm term) {
		append(';');
		append(' ');
		append(term);
		return this;
	}

	public final PrologQueryBuilder semicolon(String functor, PrologTerm... arguments) {
		append(';');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public final PrologQueryBuilder semicolon(PrologTerm left, String operator, int right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder semicolon(int left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder semicolon(PrologTerm left, String operator, long right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder semicolon(long left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder semicolon(PrologTerm left, String operator, double right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder semicolon(double left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQueryBuilder semicolon(PrologTerm left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public final PrologQuery dot() {
		String q = "" + builder + "";
		return engine.query(q);
	}

	public final String getQueryString() {
		return "" + builder + "";
	}

}
