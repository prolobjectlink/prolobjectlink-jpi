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

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Bootstrap platform class. Contains {@link #getProvider(Class)} method that
 * return an instance of Prolog Provider from the given class. Alternatively can
 * be used {@link #getProvider()} for create and return a {@link PrologProvider}
 * using Java Platform discovery pattern.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class Prolog {

	private Prolog() {
	}

	/**
	 * Create and return an instance of Prolog Provider using Java Platform
	 * discovery pattern.
	 * 
	 * @return an instance of Prolog Provider from the given class.
	 * @since 1.1
	 */
	public static PrologProvider getProvider() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("prolog");
		return ((PrologScriptEngine) engine).getProvider();
	}

	/**
	 * Create and return an instance of Prolog Provider using the Prolog Provider
	 * class name.
	 * 
	 * @return an instance of Prolog Provider from the given class.
	 * @since 1.1
	 */
	public static PrologProvider getProvider(String className) {
		PrologProvider provider = null;
		try {
			return getProvider(Class.forName(className));
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Prolog.class.getName()).log(Level.FINEST, null, e);
		}
		return provider;
	}

	/**
	 * Create and return an instance of Prolog Provider from the given class.
	 * 
	 * @param providerClass Prolog Provider class used to create a new instance.
	 * @return an instance of Prolog Provider from the given class.
	 * @since 1.0
	 */
	public static PrologProvider getProvider(Class<?> providerClass) {
		PrologProvider provider = null;
		try {
			Constructor<?> constructor = providerClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			provider = (PrologProvider) providerClass.newInstance();
			constructor.setAccessible(false);
		} catch (InstantiationException e) {
			Logger.getLogger(Prolog.class.getName()).log(Level.FINEST, null, e);
		} catch (IllegalAccessException e) {
			Logger.getLogger(Prolog.class.getName()).log(Level.FINEST, null, e);
		} catch (NoSuchMethodException e) {
			Logger.getLogger(Prolog.class.getName()).log(Level.FINEST, null, e);
		} catch (SecurityException e) {
			Logger.getLogger(Prolog.class.getName()).log(Level.FINEST, null, e);
		}
		return provider;
	}

}
