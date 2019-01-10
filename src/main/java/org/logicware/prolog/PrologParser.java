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

import java.io.File;
import java.util.Set;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologParser extends PrologWrapper {

	public PrologTerm parseTerm(String term);

	public PrologTerm[] parseTerms(String stringTerms);

	public PrologList parseList(String stringList);

	public PrologStructure parseStructure(String stringStructure);

	public PrologClause parseClause(String clause);

	public Set<PrologClause> parseProgram(String file);

	public Set<PrologClause> parseProgram(File in);

}
