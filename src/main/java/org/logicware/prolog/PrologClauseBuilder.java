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
package org.logicware.prolog;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologClauseBuilder extends PrologBuilder {

	void retract();

	boolean clause();

	void assertz();

	void asserta();

	PrologClauseBuilder semicolon(PrologTerm left, String operator, PrologTerm right);

	PrologClauseBuilder semicolon(double left, String operator, PrologTerm right);

	PrologClauseBuilder semicolon(PrologTerm left, String operator, double right);

	PrologClauseBuilder semicolon(long left, String operator, PrologTerm right);

	PrologClauseBuilder semicolon(PrologTerm left, String operator, long right);

	PrologClauseBuilder semicolon(int left, String operator, PrologTerm right);

	PrologClauseBuilder semicolon(PrologTerm left, String operator, int right);

	PrologClauseBuilder semicolon(String functor, PrologTerm... arguments);

	PrologClauseBuilder semicolon(PrologTerm term);

	PrologClauseBuilder comma(PrologTerm left, String operator, PrologTerm right);

	PrologClauseBuilder comma(double left, String operator, PrologTerm right);

	PrologClauseBuilder comma(PrologTerm left, String operator, double right);

	PrologClauseBuilder comma(long left, String operator, PrologTerm right);

	PrologClauseBuilder comma(PrologTerm left, String operator, long right);

	PrologClauseBuilder comma(int left, String operator, PrologTerm right);

	PrologClauseBuilder comma(PrologTerm left, String operator, int right);

	PrologClauseBuilder comma(String functor, PrologTerm... arguments);

	PrologClauseBuilder comma(PrologTerm body);

	PrologClauseBuilder begin(String functor, PrologTerm... arguments);

	PrologClauseBuilder choice(PrologTerm left, String operator, PrologTerm right);

	PrologClauseBuilder choice(double left, String operator, PrologTerm right);

	PrologClauseBuilder choice(PrologTerm left, String operator, double right);

	PrologClauseBuilder choice(long left, String operator, PrologTerm right);

	PrologClauseBuilder choice(PrologTerm left, String operator, long right);

	PrologClauseBuilder choice(int left, String operator, PrologTerm right);

	PrologClauseBuilder choice(PrologTerm left, String operator, int right);

	PrologClauseBuilder choice(String functor, PrologTerm... arguments);

	PrologClauseBuilder choice(PrologTerm head);

	PrologClauseBuilder neck(PrologTerm left, String operator, PrologTerm right);

	PrologClauseBuilder neck(double left, String operator, PrologTerm right);

	PrologClauseBuilder neck(PrologTerm left, String operator, double right);

	PrologClauseBuilder neck(long left, String operator, PrologTerm right);

	PrologClauseBuilder neck(PrologTerm left, String operator, long right);

	PrologClauseBuilder neck(int left, String operator, PrologTerm right);

	PrologClauseBuilder neck(PrologTerm left, String operator, int right);

	PrologClauseBuilder neck(String functor, PrologTerm... arguments);

	PrologClauseBuilder neck(PrologTerm body);

	PrologClauseBuilder begin(PrologTerm term);

	String getClauseString();

}
