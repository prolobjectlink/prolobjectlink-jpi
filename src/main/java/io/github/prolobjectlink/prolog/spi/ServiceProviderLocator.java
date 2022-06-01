/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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
package io.github.prolobjectlink.prolog.spi;

import io.github.prolobjectlink.prolog.Prolog;
import io.github.prolobjectlink.prolog.PrologProvider;

/**
 * Bootstrap platform class. Contains {@link #getProvider(Class)} method that
 * return an instance of Prolog Provider from the given class. Alternatively can
 * be used {@link #getProvider()} for create and return a {@link PrologProvider}
 * using Java Platform discovery pattern.
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public final class ServiceProviderLocator {

	/**
	 * Create and return an instance of Prolog Provider using Java Platform
	 * discovery pattern.
	 * 
	 * @return an instance of Prolog Provider from the given class.
	 * @since 1.1
	 */
	public static PrologProvider getProvider() {
		return Prolog.getProvider();
	}

	/**
	 * Create and return an instance of Prolog Provider using the Prolog Provider
	 * class name.
	 * 
	 * @return an instance of Prolog Provider from the given class.
	 * @since 1.1
	 */
	public static PrologProvider getProvider(String className) {
		return Prolog.getProvider(className);
	}

	/**
	 * Create and return an instance of Prolog Provider from the given class.
	 * 
	 * @param providerClass Prolog Provider class used to create a new instance.
	 * @return an instance of Prolog Provider from the given class.
	 * @since 1.1
	 */
	public static PrologProvider getProvider(Class<?> providerClass) {
		return Prolog.getProvider(providerClass);
	}

	private ServiceProviderLocator() {
		// DO NOTHING
	}

}
