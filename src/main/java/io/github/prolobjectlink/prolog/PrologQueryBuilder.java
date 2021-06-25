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
 * Prolog query builder to create prolog queries. The mechanism to create a new
 * query builder is using {@link PrologEngine#newQueryBuilder()}. The query
 * builder emulate the query creation process. After define all participant
 * terms with the {@link #begin(PrologTerm)} method, we specify the first term
 * in the query. If the query have more terms, they are created using
 * {@link #comma(PrologTerm)} for every one. Clause builder have a
 * {@link #getQueryString()} for string representation of the clause in
 * progress. After clause definition this builder have {@link #query()} method
 * that create the final query instance ready to be used.
 * 
 * <pre>
 * PrologVariable x = provider.newVariable("X", 0);
 * PrologStructure big = provider.newStructure("big", x);
 * PrologStructure dark = provider.newStructure("dark", x);
 * PrologQueryBuilder builder = engine.newQueryBuilder();
 * PrologQuery query = builder.begin(dark).comma(big).query();
 * </pre>
 * 
 * Prolog result
 * 
 * <pre>
 * ?- big(X), dark(X).
 * </pre>
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologQueryBuilder extends PrologBuilder {

	/**
	 * Append to the query builder the first term to be query. Return the current
	 * builder instance after append the term to be query.
	 * 
	 * @param term term to be query.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder begin(PrologTerm term);

	/**
	 * Append to the query builder the first term to be query. The term passed to
	 * the builder for this case is an structure created using the given functor and
	 * arguments array. Return the current builder instance after append the term to
	 * be query.
	 * 
	 * @param functor   string name for the structure term to be query.
	 * @param arguments prolog term arguments for the structure term to be query.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder begin(String functor, PrologTerm... arguments);

	/**
	 * Append to the query builder other term to be query in disjunctive mode. The
	 * term passed to the builder for this case is an expression created using the
	 * given operator and operands. Return the current builder instance after append
	 * the term to be query.
	 * 
	 * @param operator expression operator.
	 * @param left     left hand prolog term operand.
	 * @param right    right hand prolog term operand.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder semicolon(PrologTerm left, String operator, PrologTerm right);

	/**
	 * Append to the query builder other term to be query in disjunctive mode. The
	 * term passed to the builder for this case is an structure created using the
	 * given functor and arguments array. Return the current builder instance after
	 * append the term to be query.
	 * 
	 * @param functor   string name for the structure term to be query.
	 * @param arguments prolog term arguments for the structure term to be query.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder semicolon(String functor, PrologTerm... arguments);

	/**
	 * Append to the query builder other term to be query in disjunctive mode.
	 * Return the current builder instance after append the term to be query.
	 * 
	 * @param term term to be query.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder semicolon(PrologTerm term);

	/**
	 * Append to the query builder other term to be query in conjunctive mode. The
	 * term passed to the builder for this case is an expression created using the
	 * given operator and operands. Return the current builder instance after append
	 * the term to be query.
	 * 
	 * @param operator expression operator.
	 * @param left     left hand prolog term operand.
	 * @param right    right hand prolog term operand.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder comma(PrologTerm left, String operator, PrologTerm right);

	/**
	 * Append to the query builder other term to be query in conjunctive mode. The
	 * term passed to the builder for this case is an structure created using the
	 * given functor and arguments array. Return the current builder instance after
	 * append the term to be query.
	 * 
	 * @param functor   string name for the structure term to be query.
	 * @param arguments prolog term arguments for the structure term to be query.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder comma(String functor, PrologTerm... arguments);

	/**
	 * Append to the query builder other term to be query in conjunctive mode.
	 * Return the current builder instance after append the term to be query.
	 * 
	 * @param term term to be query.
	 * @return the current builder instance after append the term to be query.
	 * @since 1.0
	 */
	public PrologQueryBuilder comma(PrologTerm term);

	/**
	 * Get the query in string format.
	 * 
	 * @return string query.
	 * @since 1.0
	 */
	public String getQueryString();

	/**
	 * Create and return the result query.
	 * 
	 * @return the result query.
	 * @since 1.0
	 */
	public PrologQuery query();

}
