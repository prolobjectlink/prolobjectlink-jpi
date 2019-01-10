/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 WorkLogic Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.prolog;

import static org.worklogic.logging.LoggerConstants.CLASS_NOT_FOUND;
import static org.worklogic.logging.LoggerConstants.ILLEGAL_ACCESS;
import static org.worklogic.logging.LoggerConstants.INSTANTIATION;
import static org.worklogic.logging.LoggerConstants.NO_SUCH_METHOD;
import static org.worklogic.logging.LoggerConstants.SECURITY;

import java.lang.reflect.Constructor;

import org.worklogic.logging.LoggerUtils;

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
