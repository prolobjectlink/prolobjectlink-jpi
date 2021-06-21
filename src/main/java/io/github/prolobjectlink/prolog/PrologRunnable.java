/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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
package io.github.prolobjectlink.prolog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

class PrologRunnable extends FutureTask<List<List<Object>>> implements RunnableFuture<List<List<Object>>> {

	private final PrologCallable callable;

	PrologRunnable(PrologCallable callable) {
		super(callable);
		this.callable = callable;
	}

	PrologRunnable(PrologProvider provider, PrologTerm... goals) {
		this(new PrologCallable(provider, goals));
	}

	public PrologProvider getProvider() {
		return callable.getProvider();
	}

	public List<List<Object>> get() throws InterruptedException, ExecutionException {
		List<List<Object>> list = new ArrayList<List<Object>>();
		try {
			list = callable.call();
		} catch (Exception e) {
			Logger.getLogger(Prolog.class.getName()).log(Level.FINEST, null, e);
		}
		return list;
	}

}
