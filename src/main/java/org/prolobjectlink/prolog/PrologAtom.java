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

/**
 * Represent the Prolog atom data type. Prolog atoms are can be of two kinds
 * simple or complex. Simple atoms are defined like a single alpha numeric word
 * that begin like initial lower case character. The complex atom are define
 * like any character sequence that begin and end with simple quotes. The string
 * passed to build a simple atoms should be match with [a-z][A-Za-z0-9_]*
 * regular expression. If the string passed to build an atom don't match with
 * the before mentioned regular expression the atom constructor can be capable
 * of create a complex atom automatically. For complex atom the string value can
 * have the quotes or just can be absent. The printed string representation of
 * the complex atom implementation set the quotes if they are needed.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologAtom extends PrologTerm {

	/**
	 * String value for atom term.
	 * 
	 * @return value for atom term.
	 * @since 1.0
	 */
	public String getStringValue();

	/**
	 * Set the string value for this atom instance. The string value should be match
	 * with [a-z][A-Za-z0-9_]* regular expression. If the string passed to build an
	 * atom don't match with the before mentioned regular expression the atom
	 * constructor can be capable of create a complex atom automatically.
	 * 
	 * @param value string value for this atom
	 * @since 1.0
	 */
	public void setStringValue(String value);

}
