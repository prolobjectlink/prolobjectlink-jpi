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
