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

import java.util.List;

/**
 * Clause family list that join all clauses with same functor/arity based
 * indicator. Support all {@link List} operations to manage clauses in the
 * family list.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologClauses extends List<PrologClause> {

	/**
	 * True if this clause family list is a dynamic, false in other case
	 * 
	 * @deprecated Natives engine don't offer information about that.
	 * @return whether this clause family list is a dynamic
	 * @since 1.0
	 */
	@Deprecated
	public boolean isDynamic();

	/**
	 * True if this clause family list is a multifile, false in other case
	 * 
	 * @deprecated Natives engine don't offer information about that.
	 * @return whether this clause family list is a multifile
	 * @since 1.0
	 */
	@Deprecated
	public boolean isMultifile();

	/**
	 * True if this clause family list is a discontiguos, false in other case
	 * 
	 * @deprecated Natives engine don't offer information about that.
	 * @return whether this clause family list is a discontiguos.
	 * @since 1.0
	 */
	@Deprecated
	public boolean isDiscontiguous();

	/**
	 * Clause family functor/arity based indicator. The clause family indicator is
	 * the same indicator for all clauses head in the clause family.
	 * 
	 * @return functor/arity based indicator of the current clause family.
	 * @since 1.0
	 */
	public String getIndicator();

}
