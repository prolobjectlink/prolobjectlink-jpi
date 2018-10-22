/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2018 Logicware Project
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
package org.logicware.logging;

import java.util.logging.Level;

public class LoggerUtils {

	private static final PlatformLogger LOGGER = PlatformLogger.getInstance(Level.ALL);

	public static final void trace(Object sender, Object message) {
		LOGGER.log(sender, Level.FINEST, message);
	}

	public static final void trace(Object sender, Object message, Throwable throwable) {
		LOGGER.log(sender, Level.FINEST, message, throwable);
	}

	public static final void debug(Object sender, Object message) {
		LOGGER.log(sender, Level.FINE, message);
	}

	public static final void debug(Object sender, Object message, Throwable throwable) {
		LOGGER.log(sender, Level.FINE, message, throwable);
	}

	public static final void info(Object sender, Object message) {
		LOGGER.log(sender, Level.INFO, message);
	}

	public static final void info(Object sender, Object message, Throwable throwable) {
		LOGGER.log(sender, Level.INFO, message, throwable);
	}

	public static final void warn(Object sender, Object message) {
		LOGGER.log(sender, Level.WARNING, message);
	}

	public static final void warn(Object sender, Object message, Throwable throwable) {
		LOGGER.log(sender, Level.WARNING, message, throwable);
	}

	public static final void error(Object sender, Object message) {
		LOGGER.log(sender, Level.SEVERE, message);
	}

	public static final void error(Object sender, Object message, Throwable throwable) {
		LOGGER.log(sender, Level.SEVERE, message, throwable);
	}

	private LoggerUtils() {
	}

}
