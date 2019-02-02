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

/**
 * Converter for convert {@code PrologTerm} to the equivalent native {@code T}
 * term representation.
 * 
 * @author Jose Zalacain
 * @since 1.0
 * @param <T> Native Term Representation
 */
public interface PrologConverter<T> extends PrologWrapper {

	public Map<String, PrologTerm>[] toTermMapArray(Map<String, T>[] map);

	public Map<String, PrologTerm> toTermMap(Map<String, T> map);

	public PrologTerm[][] toTermMatrix(T[][] terms);

	public PrologTerm[] toTermArray(T[] terms);

	public PrologTerm toTerm(T prologTerm);

	public T fromTerm(PrologTerm term);

	public T[] fromTermArray(PrologTerm[] terms);

	public T fromTerm(PrologTerm head, PrologTerm[] body);

	public Class<T> getGenericClass();

	public PrologProvider createProvider();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
