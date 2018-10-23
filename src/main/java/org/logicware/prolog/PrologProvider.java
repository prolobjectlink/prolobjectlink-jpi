/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
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

import java.util.Map;

public interface PrologProvider {

	/**
	 * True if wrapped engine implement ISO Prolog and false in other case
	 * 
	 * @return true if wrapped engine implement ISO Prolog and false in other case
	 * @since 1.0
	 */
	public boolean isCompliant();

	// prolog constants term

	public PrologTerm prologNil();

	public PrologTerm prologCut();

	public PrologTerm prologFail();

	public PrologTerm prologTrue();

	public PrologTerm prologFalse();

	public PrologTerm prologEmpty();

	// term parser helpers

	public PrologTerm parsePrologTerm(String term);

	public PrologTerm[] parsePrologTerms(String stringTerms);

	public PrologList parsePrologList(String stringList);

	public PrologStructure parsePrologStructure(String stringStructure);

	// engine

	public PrologEngine newEngine();

	public PrologEngine newEngine(String path);

	// prolog term instantiation

	public PrologAtom newAtom(String functor);

	/**
	 * Create a prolog float number instance with 0.0 value.
	 * 
	 * @return prolog float number with 0.0 value.
	 * @since 1.0
	 */
	public PrologFloat newFloat();

	/**
	 * Create a prolog float number instance with 0.0 value.
	 * 
	 * @param value numeric value
	 * @return prolog float number with 0.0 value.
	 * @since 1.0
	 */
	public PrologFloat newFloat(Number value);

	/**
	 * Create a prolog double number instance with 0.0 value.
	 * 
	 * @return prolog double number with 0.0 value.
	 * @since 1.0
	 */
	public PrologDouble newDouble();

	/**
	 * Create a prolog double number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog double number
	 * @since 1.0
	 */
	public PrologDouble newDouble(Number value);

	/**
	 * Create a prolog integer number instance with 0 value.
	 * 
	 * @return prolog integer number with 0 value.
	 * @since 1.0
	 */
	public PrologInteger newInteger();

	/**
	 * Create a prolog integer number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog integer number
	 * @since 1.0
	 */
	public PrologInteger newInteger(Number value);

	/**
	 * Create a prolog long number instance with 0 value.
	 * 
	 * @return prolog long number with 0 value.
	 * @since 1.0
	 */
	public PrologLong newLong();

	/**
	 * Create a prolog long number instance with the given value.
	 * 
	 * @param value numeric value
	 * @return prolog long number
	 * @since 1.0
	 */
	public PrologLong newLong(Number value);

	/**
	 * Create an anonymous variable instance with associated index. Index is a non
	 * negative integer that represent the variable position of the Structure where
	 * the variable is first time declared.
	 * 
	 * @param position Position of its Structure where the variable is first time
	 *                 declared.
	 * @return An anonymous variable instance with associated index.
	 * @throws IllegalArgumentException if position is a negative number
	 * @since 1.0
	 */
	public PrologVariable newVariable(int position);

	/**
	 * Create an named variable instance with associated index. Index is a non
	 * negative integer that represent the variable position of the Structure where
	 * the variable is first time declared.
	 * 
	 * @param name     variable name (upper case beginning)
	 * 
	 * @param position Position of its Structure where the variable is first time
	 *                 declared.
	 * @return A named variable instance with associated index.
	 * @throws IllegalArgumentException if position is a negative number
	 * @since 1.0
	 */
	public PrologVariable newVariable(String name, int position);

	public PrologList newList();

	public PrologList newList(PrologTerm[] arguments);

	public PrologList newList(PrologTerm head, PrologTerm tail);

	public PrologList newList(PrologTerm[] arguments, PrologTerm tail);

	public PrologStructure newStructure(String functor, PrologTerm... arguments);

	public PrologTerm newStructure(PrologTerm left, String operator, PrologTerm right);

	public <K extends PrologTerm, V extends Object> Map<String, PrologTerm>[] toTermMapArray(Map<String, V>[] map,
			Class<K> from);

	public <K extends PrologTerm, V extends Object> Map<String, PrologTerm> toTermMap(Map<String, V> map,
			Class<K> from);

	public <K extends PrologTerm> K[][] toTermMatrix(Object[][] oss, Class<K[][]> from);

	public <K extends PrologTerm> K[] toTermArray(Object[] os, Class<K[]> from);

	public <K extends PrologTerm> K toTerm(Object o, Class<K> from);

	public <K> K fromTerm(PrologTerm term, Class<K> to);

	public <K> K[] fromTermArray(PrologTerm[] terms, Class<K[]> to);

	public <K> K fromTerm(PrologTerm head, PrologTerm[] body, Class<K> to);

	public <K> PrologConverter<K> getConverter();

}
