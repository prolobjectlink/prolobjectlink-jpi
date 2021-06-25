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
 * Prolog clause builder to create prolog clauses. The mechanism to create a new
 * clause builder is using {@link PrologEngine#newClauseBuilder()}. The clause
 * builder emulate the clause creation process. After define all participant
 * terms with the {@link #begin(PrologTerm)} method, we specify the head of the
 * clause.
 * </p>
 * <p>
 * If the clause is a rule, after head definition, the clause body is created
 * with {@link #neck(PrologTerm)} for the first term in the clause body. If the
 * clause body have more terms, they are created using
 * {@link #comma(PrologTerm)} for every one.
 * </p>
 * <p>
 * Clause builder have a {@link #getClauseString()} for string representation of
 * the clause in progress. After clause definition this builder have
 * {@link #asserta()},{@link #assertz()},{@link #clause()},{@link #retract()}
 * that use the wrapped engine invoking the correspondent methods for check,
 * insert or remove clause respectively.
 * </p>
 * 
 * <pre>
 * PrologStructure blackZ = provider.newStructure("black", z);
 * PrologStructure brownZ = provider.newStructure("brown", z);
 * PrologClauseBuilder builder = engine.newClauseBuilder();
 * builder.begin(darkZ).neck(blackZ).assertz();
 * builder.begin(darkZ).neck(brownZ).assertz();
 * </pre>
 * 
 * Prolog result.
 * 
 * <pre>
 * dark(Z) :- 
 *	black(Z).
 * dark(Z) :- 
 *	brown(Z).
 * </pre>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologClauseBuilder extends PrologBuilder {

	/**
	 * Append to the clause builder the head term in the clause. The term passed to
	 * the builder for this case is an structure created using the given functor and
	 * arguments array. Return the current builder instance after append the term in
	 * the clause.
	 * 
	 * @param functor   string name for the structure term.
	 * @param arguments prolog term arguments for the structure.
	 * @return the current builder instance after append the term in the clause.
	 * @since 1.0
	 */
	public PrologClauseBuilder begin(String functor, PrologTerm... arguments);

	/**
	 * Append to the clause builder the head term in the clause. Return the current
	 * builder instance after append the term in the clause.
	 * 
	 * @param term term to be the head in the clause.
	 * @return the current builder instance after append the term in the clause.
	 * @since 1.0
	 */
	public PrologClauseBuilder begin(PrologTerm term);

	/**
	 * Append to the clause builder the first term in the clause body. The term
	 * passed to the builder for this case is an expression created using the given
	 * operator and operands. Return the current builder instance after append the
	 * term in the clause body
	 * 
	 * @param operator expression operator.
	 * @param left     left hand prolog term operand.
	 * @param right    right hand prolog term operand.
	 * @return the current builder instance after append the term in the clause
	 *         body.
	 * @since 1.0
	 */
	public PrologClauseBuilder neck(PrologTerm left, String operator, PrologTerm right);

	/**
	 * Append to the clause builder the first term in the clause body. The term
	 * passed to the builder for this case is an structure created using the given
	 * functor and arguments array. Return the current builder instance after append
	 * the term in the clause body
	 * 
	 * @param functor   string name for the structure term.
	 * @param arguments prolog term arguments for the structure term.
	 * @return the current builder instance after append the term in the clause
	 *         body.
	 * @since 1.0
	 */
	public PrologClauseBuilder neck(String functor, PrologTerm... arguments);

	/**
	 * Append to the clause builder the first term in the clause body. Return the
	 * current builder instance after append the term in the clause body.
	 * 
	 * @param term term to be append in the clause body.
	 * @return the current builder instance after append the term in the clause
	 *         body.
	 * @since 1.0
	 */
	public PrologClauseBuilder neck(PrologTerm term);

	/**
	 * Append to the clause builder other term in the clause body in conjunctive
	 * mode. The term passed to the builder for this case is an expression created
	 * using the given operator and operands. Return the current builder instance
	 * after append the term in the clause body.
	 * 
	 * @param operator expression operator.
	 * @param left     left hand prolog term operand.
	 * @param right    right hand prolog term operand.
	 * @return the current builder instance after append the term in the clause
	 *         body.
	 * @since 1.0
	 */
	public PrologClauseBuilder comma(PrologTerm left, String operator, PrologTerm right);

	/**
	 * Append to the clause builder other term in the clause body in conjunctive
	 * mode. The term passed to the builder for this case is an structure created
	 * using the given functor and arguments array. Return the current builder
	 * instance after append the term in the clause body.
	 * 
	 * @param functor   string name for the structure term.
	 * @param arguments prolog term arguments for the structure term.
	 * @return the current builder instance after append the term in the clause
	 *         body.
	 * @since 1.0
	 */
	public PrologClauseBuilder comma(String functor, PrologTerm... arguments);

	/**
	 * Append to the clause builder other term in the clause body in conjunctive
	 * mode. Return the current builder instance after append the term in the clause
	 * body.
	 * 
	 * @param term term to be query.
	 * @return the current builder instance after append the term in the clause
	 *         body.
	 * @since 1.0
	 */
	public PrologClauseBuilder comma(PrologTerm term);

	/**
	 * Get the clause in string format.
	 * 
	 * @return string clause.
	 * @since 1.0
	 */
	public String getClauseString();

	/**
	 * Check if the clause in the main memory program unify with the current clause
	 * and return true. If the clause not exist in main memory program or exist but
	 * not unify with the given clause false value is returned.
	 * 
	 * @return true if the clause in the main memory program unify with the current
	 *         clause, false otherwise.
	 * @since 1.0
	 */
	public boolean clause();

	/**
	 * Add the current clause in the main memory program if the clause non exist. If
	 * the clause exist, will not overwritten and the clause will not added. The
	 * added clause will be the first clause for a clause lot with the same
	 * predicate indicator (PI).
	 * 
	 * @since 1.0
	 */
	public void asserta();

	/**
	 * Add the clause in the main memory program if the clause non exist. If the
	 * clause exist, will not overwritten and the clause will not added. The added
	 * clause will be the last clause for a clause lot with the same predicate
	 * indicator (PI).
	 * 
	 * @since 1.0
	 */
	public void assertz();

	/**
	 * Remove the clause in the main memory program if the clause exist.
	 * 
	 * @since 1.0
	 */
	public void retract();

}
