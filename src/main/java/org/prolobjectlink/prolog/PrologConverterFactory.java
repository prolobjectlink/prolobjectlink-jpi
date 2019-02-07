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

import static org.prolobjectlink.logging.LoggerConstants.CLASS_NOT_FOUND;
import static org.prolobjectlink.logging.LoggerConstants.ILLEGAL_ACCESS;
import static org.prolobjectlink.logging.LoggerConstants.INSTANTIATION;
import static org.prolobjectlink.logging.LoggerConstants.NO_SUCH_METHOD;
import static org.prolobjectlink.logging.LoggerConstants.SECURITY;

import java.lang.reflect.Constructor;

import org.prolobjectlink.logging.LoggerUtils;

public final class PrologConverterFactory {

	private PrologConverterFactory() {
	}

	public static <T> PrologConverter<T> createPrologConverter(String converterClassName) {
		PrologConverter<T> provider = null;
		try {
			provider = createPrologConverter(Class.forName(converterClassName));
		} catch (ClassNotFoundException e) {
			LoggerUtils.error(PrologConverterFactory.class, CLASS_NOT_FOUND, e);
		}
		return provider;
	}

	public static <T> PrologConverter<T> createPrologConverter(Class<?> converterClass) {
		PrologConverter<T> provider = null;
		try {
			Constructor<?> constructor = converterClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			provider = (PrologConverter<T>) converterClass.newInstance();
			constructor.setAccessible(false);
		} catch (InstantiationException e) {
			LoggerUtils.error(PrologConverterFactory.class, INSTANTIATION, e);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(PrologConverterFactory.class, ILLEGAL_ACCESS, e);
		} catch (NoSuchMethodException e) {
			LoggerUtils.error(PrologConverterFactory.class, NO_SUCH_METHOD, e);
		} catch (SecurityException e) {
			LoggerUtils.error(PrologConverterFactory.class, SECURITY, e);
		}
		return provider;
	}

}
