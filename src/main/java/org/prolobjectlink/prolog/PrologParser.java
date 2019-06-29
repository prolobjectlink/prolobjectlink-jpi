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

import java.io.File;
import java.util.Set;

/**
 * Prolog parser interface used for build terms from string with Prolog syntax.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
interface PrologParser extends PrologMapper {

	/**
	 * Parse the string with Prolog syntax and create an equivalent Prolog term
	 * instance.
	 * 
	 * @param term prolog term in string format.
	 * @return a Prolog term from prolog text
	 * @since 1.0
	 */
	public PrologTerm parseTerm(String term);

	/**
	 * Parse the comma separate terms in the given string with prolog syntax and
	 * return an array of terms formed by the comma separate terms.
	 * 
	 * @param stringTerms comma separate terms with prolog syntax
	 * @return an array of terms formed by the comma separate terms.
	 * @since 1.0
	 */
	public PrologTerm[] parseTerms(String stringTerms);

	/**
	 * Parse the string with Prolog syntax and create an equivalent Prolog list term
	 * instance.
	 * 
	 * @param stringList prolog term in string format.
	 * @return a Prolog term from prolog text.
	 * @since 1.0
	 */
	public PrologList parseList(String stringList);

	/**
	 * Parse the string with Prolog syntax and create an equivalent Prolog structure
	 * term instance.
	 * 
	 * @param stringStructure prolog term in string format.
	 * @return a Prolog term from prolog text.
	 * @since 1.0
	 */
	public PrologStructure parseStructure(String stringStructure);

	/**
	 * Parse the string with Prolog syntax and create an equivalent Prolog clause
	 * instance.
	 * 
	 * @param clause prolog clause in string format.
	 * @return a Prolog clause from prolog text.
	 * @since 1.0
	 */
	public PrologClause parseClause(String clause);

	/**
	 * Parse the Prolog text contained at specific file path and return a Prolog
	 * clause set found in the file.
	 * 
	 * @param file file path to be parsed.
	 * @return a Prolog clause set found in the file.
	 * @since 1.0
	 */
	public Set<PrologClause> parseProgram(String file);

	/**
	 * Parse the Prolog text contained at specific file and return a Prolog clause
	 * set found in the file.
	 * 
	 * @param in file to be parsed.
	 * @return a Prolog clause set found in the file.
	 * @since 1.0
	 */
	public Set<PrologClause> parseProgram(File in);

}
