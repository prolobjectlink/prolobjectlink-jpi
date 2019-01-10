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
public interface PrologBuilder {

	public PrologEngine getEngine();

	public boolean equals(Object obj);

	public String toString();

	public int hashCode();

	PrologBuilder semicolon(PrologTerm left, String operator, PrologTerm right);

	PrologBuilder semicolon(double left, String operator, PrologTerm right);

	PrologBuilder semicolon(PrologTerm left, String operator, double right);

	PrologBuilder semicolon(long left, String operator, PrologTerm right);

	PrologBuilder semicolon(PrologTerm left, String operator, long right);

	PrologBuilder semicolon(int left, String operator, PrologTerm right);

	PrologBuilder semicolon(PrologTerm left, String operator, int right);

	PrologBuilder semicolon(String functor, PrologTerm... arguments);

	PrologBuilder semicolon(PrologTerm term);

	PrologBuilder comma(PrologTerm left, String operator, PrologTerm right);

	PrologBuilder comma(double left, String operator, PrologTerm right);

	PrologBuilder comma(PrologTerm left, String operator, double right);

	PrologBuilder comma(long left, String operator, PrologTerm right);

	PrologBuilder comma(PrologTerm left, String operator, long right);

	PrologBuilder comma(int left, String operator, PrologTerm right);

	PrologBuilder comma(PrologTerm left, String operator, int right);

	PrologBuilder comma(String functor, PrologTerm... arguments);

	PrologBuilder comma(PrologTerm body);

	PrologBuilder begin(String functor, PrologTerm... arguments);

}
