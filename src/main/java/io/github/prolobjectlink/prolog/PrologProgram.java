/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
