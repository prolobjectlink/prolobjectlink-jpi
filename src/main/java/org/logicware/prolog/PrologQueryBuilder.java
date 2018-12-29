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
public final class PrologQueryBuilder extends AbstractBuilder implements PrologBuilder {

	public PrologQueryBuilder(PrologEngine engine) {
		super(engine);
	}

	public PrologQueryBuilder begin(String functor, int... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder begin(String functor, long... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder begin(String functor, double... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder begin(String functor, PrologTerm... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(PrologTerm body) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(String functor, int... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(String functor, long... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(String functor, double... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(String functor, PrologTerm... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(PrologTerm left, String operator, int right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(int left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(PrologTerm left, String operator, long right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(long left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(PrologTerm left, String operator, double right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(double left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder comma(PrologTerm left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(PrologTerm term) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(String functor, int... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(String functor, long... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(String functor, double... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(String functor, PrologTerm... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(PrologTerm left, String operator, int right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(int left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(PrologTerm left, String operator, long right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(long left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(PrologTerm left, String operator, double right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(double left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQueryBuilder semicolon(PrologTerm left, String operator, PrologTerm right) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologQuery dot() {
		String q = "" + builder + "";
		return engine.query(q);
	}

	public String getQueryString() {
		return "" + builder + "";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PrologEngine getEngine() {
		// TODO Auto-generated method stub
		return null;
	}

}
