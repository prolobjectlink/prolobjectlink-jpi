/*
 * #%L
 * prolobjectlink-jpe
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
package org.logicware.platform.logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logicware.platform.AbstractPlatform;
import org.logicware.platform.Platform;

public final class PlatformLogger extends AbstractPlatform implements Platform {

	private static final String MESSAGE = "Logger File Handler can't be created";
	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	static PlatformLogger getInstance(Level level) {
		LOGGER.setLevel(level);
		Logger rootlogger = LOGGER.getParent();
		SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");
		String date = f.format(new Date());
		Formatter formatter = new LoggerFormatter();
		for (Handler h : rootlogger.getHandlers()) {
			h.setFormatter(formatter);
			h.setLevel(level);
		}
		FileHandler file = null;
		try {
			file = new FileHandler("%t/prolobjectlink-" + date + ".log", true);
		} catch (SecurityException e) {
			rootlogger.log(Level.SEVERE, MESSAGE, e);
		} catch (IOException e) {
			rootlogger.log(Level.SEVERE, MESSAGE, e);
		}
		assert file != null;
		file.setFormatter(formatter);
		LOGGER.addHandler(file);
		return new PlatformLogger();
	}

	public final void log(Object sender, Level level, Object message) {
		LOGGER.log(level, sender + "\n" + message, (Throwable) null);
	}

	public final void log(Object sender, Level level, Object message, Throwable throwable) {
		LOGGER.log(level, sender + "\n" + message, throwable);
	}

	public final void trace(Object sender, Object message) {
		log(sender, Level.FINEST, message);
	}

	public final void trace(Object sender, Object message, Throwable throwable) {
		log(sender, Level.FINEST, message, throwable);
	}

	public final void debug(Object sender, Object message) {
		log(sender, Level.FINE, message);
	}

	public final void debug(Object sender, Object message, Throwable throwable) {
		log(sender, Level.FINE, message, throwable);
	}

	public final void info(Object sender, Object message) {
		log(sender, Level.INFO, message);
	}

	public final void info(Object sender, Object message, Throwable throwable) {
		log(sender, Level.INFO, message, throwable);
	}

	public final void warn(Object sender, Object message) {
		log(sender, Level.WARNING, message);
	}

	public final void warn(Object sender, Object message, Throwable throwable) {
		log(sender, Level.WARNING, message, throwable);
	}

	public final void error(Object sender, Object message) {
		log(sender, Level.SEVERE, message);
	}

	public final void error(Object sender, Object message, Throwable throwable) {
		log(sender, Level.SEVERE, message, throwable);
	}

	private PlatformLogger() {
	}

}
