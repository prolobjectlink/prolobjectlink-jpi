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
