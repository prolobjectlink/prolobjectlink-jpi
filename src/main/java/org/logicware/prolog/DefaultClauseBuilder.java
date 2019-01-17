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
public final class DefaultClauseBuilder extends AbstractDefaultBuilder implements PrologClauseBuilder {

	public DefaultClauseBuilder(PrologEngine engine) {
		super(engine);
	}

	public DefaultClauseBuilder(PrologProvider provider) {
		super(provider);
	}

	public DefaultClauseBuilder(PrologProvider provider, String file) {
		super(provider, file);
	}

	public PrologClauseBuilder begin(PrologTerm term) {
		append(' ');
		append(term);
		return this;
	}

	public PrologClauseBuilder begin(String functor, PrologTerm... arguments) {
		append(functor, arguments);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm body) {
		append(':');
		append('-');
		append(' ');
		append(body);
		return this;
	}

	public PrologClauseBuilder neck(String functor, PrologTerm... arguments) {
		append(':');
		append('-');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm left, String operator, int right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder neck(int left, String operator, PrologTerm right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm left, String operator, long right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder neck(long left, String operator, PrologTerm right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm left, String operator, double right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder neck(double left, String operator, PrologTerm right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder neck(PrologTerm left, String operator, PrologTerm right) {
		append(':');
		append('-');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(PrologTerm body) {
		append(',');
		append(' ');
		append(body);
		return this;
	}

	public PrologClauseBuilder comma(String functor, PrologTerm... arguments) {
		append(',');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public PrologClauseBuilder comma(PrologTerm left, String operator, int right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(int left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(PrologTerm left, String operator, long right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(long left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(PrologTerm left, String operator, double right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(double left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder comma(PrologTerm left, String operator, PrologTerm right) {
		append(',');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder semicolon(PrologTerm term) {
		append(';');
		append(' ');
		append(term);
		return this;
	}

	public PrologClauseBuilder semicolon(String functor, PrologTerm... arguments) {
		append(';');
		append(' ');
		append(functor, arguments);
		return this;
	}

	public PrologClauseBuilder semicolon(PrologTerm left, String operator, int right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder semicolon(int left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder semicolon(PrologTerm left, String operator, long right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder semicolon(long left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder semicolon(PrologTerm left, String operator, double right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder semicolon(double left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder semicolon(PrologTerm left, String operator, PrologTerm right) {
		append(';');
		append(' ');
		append(left, operator, right);
		return this;
	}

	public PrologClauseBuilder choice(PrologTerm head) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(String functor, PrologTerm... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(PrologTerm left, String operator, int right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(int left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(PrologTerm left, String operator, long right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(long left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(PrologTerm left, String operator, double right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(double left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologClauseBuilder choice(PrologTerm left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public void asserta() {
		String c = getClauseString();
		engine.asserta(c);
	}

	public void assertz() {
		String c = getClauseString();
		engine.assertz(c);
	}

	public boolean clause() {
		String c = getClauseString();
		return engine.clause(c);
	}

	public void retract() {
		String c = getClauseString();
		engine.retract(c);
	}

	public String getClauseString() {
		return "" + builder + "";
	}

}
