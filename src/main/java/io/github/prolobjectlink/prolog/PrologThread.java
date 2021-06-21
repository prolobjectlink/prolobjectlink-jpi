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

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Prolog thread is a thread of execution in a program. The Java Virtual Machine
 * allows an application to have multiple threads of execution running
 * concurrently.
 * 
 * Every thread has a priority. Threads with higher priority are executed in
 * preference to threads with lower priority. Each thread may or may not also be
 * marked as a daemon. When code running in some thread creates a new Thread
 * object, the new thread has its priority initially set equal to the priority
 * of the creating thread, and is a daemon thread if and only if the creating
 * thread is a daemon.
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public class PrologThread extends Thread implements RunnableFuture<List<List<Object>>>, Callable<List<List<Object>>> {

	private final PrologRunnable runnable;

	private PrologThread(PrologRunnable target) {
		super(target);
		runnable = target;
	}

	private PrologThread(PrologRunnable target, String name) {
		super(target, name);
		runnable = target;
	}

	PrologThread(PrologProvider provider, PrologTerm... goals) {
		this(new PrologRunnable(provider, goals));
	}

	PrologThread(PrologProvider provider, String name, PrologTerm... goals) {
		this(new PrologRunnable(provider, goals), name);
	}

	PrologThread(PrologProvider provider, Thread thread, PrologTerm[] goals) {
		this(new PrologRunnable(provider, goals));
		// TODO Join this thread with the given thread
	}

	public boolean cancel(boolean mayInterruptIfRunning) {
		return runnable.cancel(mayInterruptIfRunning);
	}

	public boolean isCancelled() {
		return runnable.isCancelled();
	}

	public boolean isDone() {
		return runnable.isDone();
	}

	public List<List<Object>> get() throws InterruptedException, ExecutionException {
		return runnable.get();
	}

	public List<List<Object>> get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return runnable.get(timeout, unit);
	}

	public List<List<Object>> call() throws Exception {
		return runnable.get();
	}

}
