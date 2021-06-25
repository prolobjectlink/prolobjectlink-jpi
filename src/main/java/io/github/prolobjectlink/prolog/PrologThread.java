/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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
