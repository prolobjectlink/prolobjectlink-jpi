/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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
package io.github.prolobjectlink.prolog;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

/**
 * Partial implementation of {@link ScriptEngineFactory}
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
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
