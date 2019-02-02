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

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologQueryBuilder extends PrologBuilder {

	PrologQueryBuilder begin(PrologTerm term);

	PrologQueryBuilder begin(String functor, PrologTerm... arguments);

	PrologQueryBuilder semicolon(PrologTerm left, String operator, PrologTerm right);

	PrologQueryBuilder semicolon(double left, String operator, PrologTerm right);

	PrologQueryBuilder semicolon(PrologTerm left, String operator, double right);

	PrologQueryBuilder semicolon(long left, String operator, PrologTerm right);

	PrologQueryBuilder semicolon(PrologTerm left, String operator, long right);

	PrologQueryBuilder semicolon(int left, String operator, PrologTerm right);

	PrologQueryBuilder semicolon(PrologTerm left, String operator, int right);

	PrologQueryBuilder semicolon(String functor, PrologTerm... arguments);

	PrologQueryBuilder semicolon(PrologTerm term);

	PrologQueryBuilder comma(PrologTerm left, String operator, PrologTerm right);

	PrologQueryBuilder comma(double left, String operator, PrologTerm right);

	PrologQueryBuilder comma(PrologTerm left, String operator, double right);

	PrologQueryBuilder comma(long left, String operator, PrologTerm right);

	PrologQueryBuilder comma(PrologTerm left, String operator, long right);

	PrologQueryBuilder comma(int left, String operator, PrologTerm right);

	PrologQueryBuilder comma(PrologTerm left, String operator, int right);

	PrologQueryBuilder comma(String functor, PrologTerm... arguments);

	PrologQueryBuilder comma(PrologTerm body);

	String getQueryString();

	PrologQuery dot();

}
