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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LoggerFormatter extends Formatter {

	private static final String ERROR = "ERROR";
	private static final String WARN = "WARN";
	private static final String INFO = "INFO";
	private static final String DEBUG = "DEBUG";
	private static final String TRACE = "TRACE";

	@Override
	public String format(LogRecord record) {
		StringBuilder buffer = new StringBuilder();

		// retrieve record data
		String levelString = "";
		Level level = record.getLevel();
		if (level.intValue() == Level.SEVERE.intValue()) {
			levelString = ERROR;
		} else if (level.intValue() == Level.WARNING.intValue()) {
			levelString = WARN;
		} else if (level.intValue() == Level.INFO.intValue()) {
			levelString = INFO;
		} else if (level.intValue() == Level.CONFIG.intValue()) {
			levelString = DEBUG;
		} else if (level.intValue() == Level.FINE.intValue()) {
			levelString = DEBUG;
		} else if (level.intValue() == Level.FINER.intValue()) {
			levelString = DEBUG;
		} else if (level.intValue() == Level.FINEST.intValue()) {
			levelString = TRACE;
		}

		long millisecs = record.getMillis();
		String name = record.getLoggerName();
		String message = record.getMessage();
		Throwable throwable = record.getThrown();
		SimpleDateFormat f = new SimpleDateFormat("MMM-dd-yyyy HH:mm");
		Date resultdate = new Date(millisecs);
		String date = f.format(resultdate);

		// create log line
		buffer.append('[');
		buffer.append(name);
		buffer.append(']');
		buffer.append(' ');
		buffer.append(levelString);
		buffer.append(' ');
		buffer.append(date);
		buffer.append(' ');
		buffer.append(message);
		if (throwable != null) {
			buffer.append('\n');
			buffer.append(throwable);
			buffer.append('\n');
			StackTraceElement[] s = throwable.getStackTrace();
			for (StackTraceElement stackTraceElement : s) {
				buffer.append(stackTraceElement);
				buffer.append('\n');
			}
		}
		buffer.append('\n');
		return "" + buffer + "";
	}

}
