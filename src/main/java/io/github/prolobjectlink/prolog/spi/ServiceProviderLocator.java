/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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
package io.github.prolobjectlink.prolog.spi;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import io.github.prolobjectlink.prolog.Prolog;
import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologScriptEngine;

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
	 * @since 1.1
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

	private ServiceProviderLocator() {
		// DO NOTHING
	}

}
