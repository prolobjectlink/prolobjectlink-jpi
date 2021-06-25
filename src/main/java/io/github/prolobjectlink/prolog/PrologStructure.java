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
