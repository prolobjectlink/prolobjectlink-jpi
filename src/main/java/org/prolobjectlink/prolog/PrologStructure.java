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
package org.prolobjectlink.prolog;

/**
 * <p>
 * Represent structured prolog compound term. Prolog structures consist in a
 * relation the functor (structure name) and arguments enclosed between
 * parenthesis.
 * </p>
 * <p>
 * The Prolog Provider is the mechanism to create a new Prolog structures
 * invoking {@link PrologProvider#newStructure(String, PrologTerm...)}.
 * </p>
 * <p>
 * Two structures are equals if and only if are structure and have equals
 * functor and arguments. Structures terms unify only with same functor and
 * arguments structures, with free variable or with with structures where your
 * arguments unify if they have the same functor and arity.
 * </p>
 * <p>
 * Structures have a special property named arity that means the number of
 * arguments present in the structure.
 * </p>
 * <p>
 * There are two special structures term. They are expressions (Two arguments
 * structure term with operator functor) and atoms (functor with zero
 * arguments). For the first special case must be used
 * {@link PrologProvider#newStructure(PrologTerm, String, PrologTerm)}
 * specifying operands like arguments and operator like functor. For the second
 * special case must be used {@link PrologProvider#newAtom(String)} specifying
 * functor only.
 * </p>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologStructure extends PrologTerm {

	/**
	 * Return the operator (structure functor) of the current term if the current
	 * term is an evalueble structure (expression). This method is equivalent to
	 * invoke #{@link #getFunctor()}
	 * 
	 * @return return the operator of the current term if the current term is an
	 *         (expression).
	 * @since 1.0
	 */
	public String getOperator();

	/**
	 * Return the left operand of the current term if the current term is an
	 * evalueble structure (expression). This method is equivalent to invoke
	 * {@link #getArgument(int)} with integer parameter equals to zero.
	 * 
	 * @return return the left operand of the current term if the current term is an
	 *         expression.
	 * @since 1.0
	 */
	public PrologTerm getLeft();

	/**
	 * Return the right operand of the current term if the current term is an
	 * evalueble structure (expression). This method is equivalent to invoke
	 * {@link #getArgument(int)} with integer parameter equals to one.
	 * 
	 * @return return the right operand of the current term if the current term is
	 *         an expression.
	 * @since 1.0
	 */
	public PrologTerm getRight();

}
