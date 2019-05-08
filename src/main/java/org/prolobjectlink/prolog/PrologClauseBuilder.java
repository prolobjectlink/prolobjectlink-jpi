/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.prolobjectlink.prolog;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologClauseBuilder extends PrologBuilder {

	public PrologClauseBuilder comma(PrologTerm left, String operator, PrologTerm right);

	public PrologClauseBuilder comma(String functor, PrologTerm... arguments);

	public PrologClauseBuilder comma(PrologTerm body);

	public PrologClauseBuilder begin(String functor, PrologTerm... arguments);

	public PrologClauseBuilder neck(PrologTerm left, String operator, int right);

	public PrologClauseBuilder neck(PrologTerm left, String operator, PrologTerm right);

	public PrologClauseBuilder neck(String functor, PrologTerm... arguments);

	public PrologClauseBuilder neck(PrologTerm body);

	public PrologClauseBuilder begin(PrologTerm term);

	public String getClauseString();

	public boolean clause();

	public void assertz();

	public void asserta();

	public void retract();

}
