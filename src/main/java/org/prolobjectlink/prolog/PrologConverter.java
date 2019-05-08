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

import java.util.Map;

/**
 * Converter for convert {@code PrologTerm} to the equivalent driver {@code T}
 * term representation.
 * 
 * @author Jose Zalacain
 * @since 1.0
 * @param <T> Driver Term Representation
 */
public interface PrologConverter<T> extends PrologWrapper {

	public Map<String, PrologTerm>[] toTermMapArray(Map<String, T>[] map);

	public Map<String, PrologTerm> toTermMap(Map<String, T> map);

	public PrologTerm[][] toTermMatrix(T[][] terms);

	public PrologTerm[] toTermArray(T[] terms);

	public PrologTerm toTerm(T prologTerm);

	public T fromTerm(PrologTerm term);

	public T[] fromTermArray(PrologTerm[] terms);

	public T fromTerm(PrologTerm head, PrologTerm[] body);

	public Class<T> getGenericClass();

	public PrologProvider createProvider();

	public int hashCode();

	public boolean equals(Object object);

	public String toString();

}
