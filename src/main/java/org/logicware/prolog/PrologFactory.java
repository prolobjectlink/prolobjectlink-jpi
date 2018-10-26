/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
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

import java.lang.reflect.Constructor;

import org.logicware.logging.LoggerConstants;
import org.logicware.logging.LoggerUtils;

public final class PrologFactory {

	private PrologFactory() {
	}

	public static PrologProvider newProvider(String providerClassName) {
		PrologProvider provider = null;
		try {
			provider = newProvider(Class.forName(providerClassName));
		} catch (ClassNotFoundException e) {
			LoggerUtils.error(PrologFactory.class, LoggerConstants.CLASS_NOT_FOUND, e);
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
			LoggerUtils.error(PrologFactory.class, LoggerConstants.INSTANTIATION, e);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(PrologFactory.class, LoggerConstants.ILLEGAL_ACCESS, e);
		} catch (NoSuchMethodException e) {
			LoggerUtils.error(PrologFactory.class, LoggerConstants.NO_SUCH_METHOD, e);
		} catch (SecurityException e) {
			LoggerUtils.error(PrologFactory.class, LoggerConstants.SECURITY, e);
		}
		return provider;
	}

}
