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
