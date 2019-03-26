/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public class PrologScriptFactory implements ScriptEngineFactory {

	final PrologEngine engine;

	public PrologScriptFactory(PrologEngine engine) {
		this.engine = engine;
	}

	public String getEngineName() {
		return engine.getName();
	}

	public String getEngineVersion() {
		return engine.getVersion();
	}

	public List<String> getExtensions() {
		return Arrays.asList("pro", "pl");
	}

	public List<String> getMimeTypes() {
		return Arrays.asList("text/plain");
	}

	public List<String> getNames() {
		return Arrays.asList("tuProlog", "Prolog", "prolog");
	}

	public String getLanguageName() {
		return "Prolog";
	}

	public String getLanguageVersion() {
		return engine.getVersion();
	}

	public Object getParameter(String key) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ENGINE", getEngineName());
		parameters.put("ENGINE_VERSION", getEngineVersion());
		parameters.put("NAME", getNames().get(0));
		parameters.put("LANGUAGE", getLanguageName());
		parameters.put("LANGUAGE_VERSION", getLanguageVersion());
		return parameters.get(key);
	}

	public String getMethodCallSyntax(String obj, String m, String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOutputStatement(String toDisplay) {
		return "write('" + toDisplay + "')";
	}

	public String getProgram(String... statements) {
		StringBuilder b = new StringBuilder();
		Iterator<String> i = new ArrayIterator<String>(statements);
		if (i.hasNext()) {
			while (i.hasNext()) {
				b.append(i.next());
				if (i.hasNext()) {
					b.append(",\n\t");
				}
			}
			b.append('.');
		}
		return "" + b + "";
	}

	public ScriptEngine getScriptEngine() {
		return new PrologScript(this);
	}

}
