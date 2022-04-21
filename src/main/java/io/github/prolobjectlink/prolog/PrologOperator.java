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
 * This class defines a Prolog operator. Prolog operators are composed by a
 * string operator name, string operator specifier or type and a operator
 * priority. Extends from {@link Comparable} to compare with others operators
 * instance over priority property.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologOperator extends Comparable<PrologOperator> {

	/**
	 * String symbol that represent the Prolog operator.
	 * 
	 * @return the Prolog operator symbol.
	 * @since 1.0
	 */
	public String getOperator();

	/**
	 * String symbol that specify the associativity and position of the Prolog
	 * operator.
	 * 
	 * @return symbol that specify associativity and position of the Prolog
	 *         operator.
	 * @since 1.0
	 */
	public String getSpecifier();

	/**
	 * Integer number between 0 and 1200 that represent the operator priority.
	 * 
	 * @return the operator priority.
	 * @since 1.0
	 */
	public int getPriority();

}
