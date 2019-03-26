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

import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class PrologScript extends AbstractScriptEngine implements ScriptEngine, Invocable {

	private final PrologScriptFactory factory;
	private static final String CTX = "context";

	public PrologScript(PrologScriptFactory factory) {
		this.factory = factory;
	}

	public Object eval(String script, ScriptContext context) throws ScriptException {
		context.getBindings(ScriptContext.ENGINE_SCOPE).put(CTX, context);
		return eval(script, context.getBindings(ScriptContext.ENGINE_SCOPE));
	}

	public Object eval(Reader reader, ScriptContext context) throws ScriptException {
		context.getBindings(ScriptContext.ENGINE_SCOPE).put(CTX, context);
		return eval(reader, context.getBindings(ScriptContext.ENGINE_SCOPE));
	}

	public Bindings createBindings() {
		return new SimpleBindings();
	}

	public ScriptEngineFactory getFactory() {
		return factory;
	}

	@Override
	public void setContext(ScriptContext ctxt) {
		// TODO Auto-generated method stub
		super.setContext(ctxt);
	}

	@Override
	public ScriptContext getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public Bindings getBindings(int scope) {
		// TODO Auto-generated method stub
		return super.getBindings(scope);
	}

	@Override
	public void setBindings(Bindings bindings, int scope) {
		// TODO Auto-generated method stub
		super.setBindings(bindings, scope);
	}

	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub
		super.put(key, value);
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}

	@Override
	public Object eval(Reader reader, Bindings bindings) throws ScriptException {
		// TODO Auto-generated method stub
		return super.eval(reader, bindings);
	}

	@Override
	public Object eval(String script, Bindings bindings) throws ScriptException {
		// TODO Auto-generated method stub
		return super.eval(script, bindings);
	}

	@Override
	public Object eval(Reader reader) throws ScriptException {
		// TODO Auto-generated method stub
		return super.eval(reader);
	}

	@Override
	public Object eval(String script) throws ScriptException {
		// TODO Auto-generated method stub
		return super.eval(script);
	}

	@Override
	protected ScriptContext getScriptContext(Bindings nn) {
		// TODO Auto-generated method stub
		return super.getScriptContext(nn);
	}

	public Object invokeMethod(Object thiz, String name, Object... args) throws ScriptException, NoSuchMethodException {
		// TODO we need object prolog converter
		return null;
	}

	public Object invokeFunction(String name, Object... args) throws ScriptException, NoSuchMethodException {
		return invokeMethod(null, name, args);
	}

	public <T> T getInterface(Class<T> clasz) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getInterface(Object thiz, Class<T> clasz) {
		// TODO Auto-generated method stub
		return null;
	}

}
