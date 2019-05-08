/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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
package org.prolobjectlink.prolog;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public abstract class PrologScriptEngineFactory implements ScriptEngineFactory {

	private final PrologEngine engine;

	public PrologScriptEngineFactory(PrologEngine engine) {
		this.engine = engine;
	}

	public final String getEngineName() {
		return engine.getName();
	}

	public final String getEngineVersion() {
		return engine.getVersion();
	}

	public final List<String> getExtensions() {
		return Arrays.asList("pro", "pl");
	}

	public final List<String> getMimeTypes() {
		return Arrays.asList("text/plain");
	}

	public final List<String> getNames() {
		return Arrays.asList(getEngineName(), "Prolog", "prolog");
	}

	public final String getLanguageName() {
		return "Prolog";
	}

	public final String getLanguageVersion() {
		return engine.getVersion();
	}

	public final Object getParameter(String key) {
		if (key.equals(ScriptEngine.ENGINE)) {
			return getEngineName();
		} else if (key.equals(ScriptEngine.ENGINE_VERSION)) {
			return getEngineVersion();
		} else if (key.equals(ScriptEngine.LANGUAGE)) {
			return getLanguageName();
		} else if (key.equals(ScriptEngine.LANGUAGE_VERSION)) {
			return getLanguageVersion();
		} else if (key.equals(ScriptEngine.NAME)) {
			return getEngineName();
		}
		return null;
	}

	public final String getOutputStatement(String toDisplay) {
		return "write('" + toDisplay + "')";
	}

	public final String getProgram(String... statements) {
		StringBuilder b = new StringBuilder();
		Iterator<String> i = new ArrayIterator<String>(statements);
		if (i.hasNext()) {
			while (i.hasNext()) {
				b.append(i.next());
				if (i.hasNext()) {
					b.append(".\n");
				}
			}
			b.append('.');
		}
		return "" + b + "";
	}

	public final ScriptEngine getScriptEngine() {
		return new PrologScriptEngine(this, engine);
	}

	@Override
	public String toString() {
		return "PrologScriptEngineFactory [engine=" + engine + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engine == null) ? 0 : engine.getName().hashCode());
		result = prime * result + ((engine == null) ? 0 : engine.getVersion().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		PrologScriptEngineFactory other = (PrologScriptEngineFactory) object;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.getName().equals(other.engine.getName())) {
			return false;
		} else if (!engine.getVersion().equals(other.engine.getVersion())) {
			return false;
		}
		return true;
	}

}
