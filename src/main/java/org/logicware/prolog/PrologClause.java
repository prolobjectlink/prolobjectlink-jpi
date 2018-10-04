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

import java.util.Iterator;

public interface PrologClause {

	public int getArity();

	public String getFunctor();

	public PrologTerm getTerm();

	public PrologTerm getHead();

	public PrologTerm getBody();

	public String getIndicator();

	public boolean isDirective();

	public boolean isFact();

	public boolean isRule();

	public boolean unify(PrologClause clause);

	public boolean isDynamic();

	public boolean isMultifile();

	public boolean isDiscontiguous();

	public PrologIndicator getPrologIndicator();

	public Iterator<PrologTerm> getBodyIterator();

	public PrologTerm[] getBodyArray();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
