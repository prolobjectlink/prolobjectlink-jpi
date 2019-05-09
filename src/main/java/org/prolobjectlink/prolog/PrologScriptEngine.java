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

public final class PrologScriptEngine extends AbstractScriptEngine implements ScriptEngine {

	private final PrologEngine prolog;
	private final ScriptEngineFactory factory;

	public PrologScriptEngine(ScriptEngineFactory factory, PrologEngine prolog) {
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

}
