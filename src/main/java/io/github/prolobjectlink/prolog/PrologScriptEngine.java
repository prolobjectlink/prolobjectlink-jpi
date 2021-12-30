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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * Partial implementation of {@link ScriptEngine}
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class PrologScriptEngine extends AbstractScriptEngine implements ScriptEngine {

	private final PrologEngine prolog;
	private final ScriptEngineFactory factory;

	PrologScriptEngine(ScriptEngineFactory factory, PrologEngine prolog) {
		this.factory = factory;
		this.prolog = prolog;
	}

	public Bindings createBindings() {
		return new SimpleBindings();
	}

	public ScriptEngineFactory getFactory() {
		return factory;
	}

	public Object eval(String script, ScriptContext context) throws ScriptException {
		return eval(script, context.getBindings(ScriptContext.ENGINE_SCOPE));
	}

	public Object eval(Reader reader, ScriptContext context) throws ScriptException {
		return eval(reader, context.getBindings(ScriptContext.ENGINE_SCOPE));
	}

	@Override
	public Object eval(Reader reader, Bindings bindings) throws ScriptException {
		BufferedReader bfr = new BufferedReader(reader);
		StringBuilder script = new StringBuilder();
		try {
			String line = bfr.readLine();
			while (line != null) {
				script.append(line);
				script.append("\n");
				line = bfr.readLine();
			}
		} catch (IOException ex) {
			throw new ScriptException(ex);
		}
		return eval("" + script + "", bindings);
	}

	@Override
	public Object eval(String script, Bindings bindings) throws ScriptException {

		String code = script;

		// check code goal to query
		if (code.startsWith("?-")) {

			// replace all bindings
			for (Entry<String, Object> entry : bindings.entrySet()) {
				code = code.replace(entry.getKey(), "" + entry.getValue() + "");
			}

			code = code.substring(2).trim();

			// remove dot at the end if needed
			if (code.endsWith(".")) {
				code = code.substring(0, code.length() - 1);
			}

			PrologQuery query = prolog.query(code);
			if (!query.hasSolution()) {
				return false;
			}

			// set variables result in the binding map
			Map<String, Object> map = query.oneVariablesResult();
			for (Entry<String, Object> entry : map.entrySet()) {
				put(entry.getKey(), entry.getValue());
			}

		}

		// code is prolog program
		// code need ensure_loaded
		else {
			prolog.include(new StringReader(code));
		}

		return true;

	}

	@Override
	public Object eval(String string) throws ScriptException {
		return eval(string, getContext());
	}

	@Override
	public Object eval(Reader reader) throws ScriptException {
		return eval(reader, getContext());
	}

	public PrologProvider getProvider() {
		return prolog.getProvider();
	}

}
