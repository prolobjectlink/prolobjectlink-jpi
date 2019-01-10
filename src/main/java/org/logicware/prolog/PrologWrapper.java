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

import java.util.Map;

import org.worklogic.Wrapper;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologWrapper extends Wrapper {

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

}
