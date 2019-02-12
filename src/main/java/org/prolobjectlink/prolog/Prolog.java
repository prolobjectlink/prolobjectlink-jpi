/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.prolobjectlink.prolog;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Prolog {

	private Prolog() {
	}

	public static PrologProvider newProvider(String providerClassName) {
		PrologProvider provider = null;
		try {
			provider = newProvider(Class.forName(providerClassName));
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Prolog.class.getName()).log(Level.FINEST, null, e);
		}
		return provider;
	}

	public static PrologProvider newProvider(Class<?> providerClass) {
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
