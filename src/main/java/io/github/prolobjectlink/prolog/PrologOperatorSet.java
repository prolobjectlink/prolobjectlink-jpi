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

import java.util.Set;

/**
 * A collection that contains no duplicate Prolog operators. Extends from
 * {@link Set} and add {@link #currentOp(String)} method to check if in the
 * operator set is defined some particular operator specified by your string
 * symbol.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologOperatorSet extends Set<PrologOperator> {

	/**
	 * Check if in the operator set is defined some particular operator specified by
	 * your string symbol. If the operator is defined in the operator set return
	 * true or false in other case.
	 * 
	 * @param operator operator to be checked
	 * @return true if the operator is defined in the operator set or false in other
	 *         case.
	 * @since 1.0
	 */
	public boolean currentOp(String operator);

}
