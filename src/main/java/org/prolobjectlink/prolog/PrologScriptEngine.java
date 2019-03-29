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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public final class PrologScriptEngine extends AbstractScriptEngine implements ScriptEngine {

	private final ScriptEngineFactory factory;
	private static final String CTX = "context";

	public PrologScriptEngine(ScriptEngineFactory factory) {
		this.factory = factory;
	}

	public Bindings createBindings() {
		return new SimpleBindings();
	}

	public ScriptEngineFactory getFactory() {
		return factory;
	}

	public Object eval(String script, ScriptContext context) throws ScriptException {
		context.getBindings(ScriptContext.ENGINE_SCOPE).put(CTX, context);
		return eval(script, context.getBindings(ScriptContext.ENGINE_SCOPE));
	}

	public Object eval(Reader reader, ScriptContext context) throws ScriptException {
		context.getBindings(ScriptContext.ENGINE_SCOPE).put(CTX, context);
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
				line = bfr.readLine();
			}
		} catch (IOException ex) {
			throw new ScriptException(ex);
		}
		return eval("" + script + "", bindings);
	}

	@Override
	public Object eval(String script, Bindings bindings) throws ScriptException {
		// TODO Auto-generated method stub
		// TODO this is the main eval
		return super.eval(script, bindings);
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
