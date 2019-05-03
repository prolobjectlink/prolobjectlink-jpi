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
 * Prolog term that represent variable data type. Prolog variables are created
 * using {@link PrologProvider#newVariable(int)} for anonymous variables and
 * {@link PrologProvider#newVariable(String, int)} for named variables. The
 * Prolog variables can be used and reused because they remain in java heap. You
 * can instantiate a prolog variable and used it any times in the same clause
 * because refer to same variable every time.
 * 
 * <pre>
 * 
 * PrologVariable x = provider.newVariable(&quot;X&quot;);
 * PrologVariable y = provider.newVariable(&quot;Y&quot;);
 * PrologVariable z = provider.newVariable(&quot;Z&quot;);
 * engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
 * 		provider.newStructure(parent, y, z));
 * 
 * </pre>
 * 
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologVariable extends PrologTerm {

	/**
	 * Check that current variable be an anonymous variable
	 * 
	 * @return - true if the current variable is an anonymous variable, false in
	 *         other case
	 * @since 1.0
	 */
	public boolean isAnonymous();

	/**
	 * Name that identify this variable
	 * 
	 * @return variable name
	 * @since 1.0
	 */
	public String getName();

	/**
	 * Set the name for current prolog variable term. If the under-laying prolog
	 * variable term don't have rename option, a new under-laying variable is
	 * created with the given name.
	 * 
	 * @param name name to be set to the current variable.
	 * @since 1.0
	 */
	public void setName(String name);

	/**
	 * Non negative integer that represent the variable position of the Structure
	 * where the variable is first time declared.
	 * 
	 * @return the variable position
	 * @since 1.0
	 */
	public int getPosition();

}
