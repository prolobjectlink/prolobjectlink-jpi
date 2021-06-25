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
