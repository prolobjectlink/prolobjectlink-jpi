/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package io.github.prolobjectlink.prolog;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologProgram extends Map<String, PrologClauses>, Iterable<PrologClauses> {

	public PrologClauses get(String functor, int arity);

	public void add(PrologClause clause);

	public void add(PrologProgram program);

	public void push(PrologClause clause);

	public void removeAll(String functor, int arity);

	public void markDynamic(String functor, int arity);

	public void unmarkDynamic(String functor, int arity);

	public boolean isDynamic(String functor, int arity);

	public void markMultifile(String functor, int arity);

	public void unmarkMultifile(String functor, int arity);

	public boolean isMultifile(String functor, int arity);

	public void markDiscontiguous(String functor, int arity);

	public void unmarkDiscontiguous(String functor, int arity);

	public boolean isDiscontiguous(String functor, int arity);

	public PrologClauses newClauses(String functor, int arity);

	public boolean removeAll(PrologProgram program);

	public boolean removeAll(PrologClauses clauses);

	public Map<String, PrologClauses> getClauses();

	public Set<String> getIndicators();

	public void add(PrologClauses clauses);

	public void addAll(PrologProgram program);

	public boolean retainAll(PrologClauses parents);

	public Object[] toArray(PrologClauses[] prologClauses);

	public Object[] toArray();

}
