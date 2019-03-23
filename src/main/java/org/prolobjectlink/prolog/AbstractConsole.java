/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractConsole implements PrologConsole {

	private static final String PROLOBJECTLINK = "Prolobjectlink";
	private static final String COPYRIHT = " (C)";

	// default input stream
	private final InputStreamReader reader = new InputStreamReader(System.in);

	// buffered reader for read from standard input stream
	private final BufferedReader stdin = new BufferedReader(reader);

	// standard output stream
	// private final PrintWriter stdout = System.console().writer()
	private static final PrintWriter stdout = new PrintWriter(System.out, true);

	//
	private final PrologEngine engine;

	public AbstractConsole(PrologProvider provider) {
		this.engine = provider.newEngine();
	}

	public final Map<String, String> getArguments(String[] args) {
		final Map<String, String> map = new HashMap<String, String>();
		if (args.length > 0) {
			Iterator<String> i = new ArrayIterator<String>(args);
			String name = i.next();
			if (i.hasNext()) {
				String value = i.next();
				map.put(name, value);
			} else {
				map.put(name, "");
			}
		}
		return map;
	}

	public final void printUsage() {
		stdout.println("Usage: pllink option [file] to consult a file");
		stdout.println("options:");
		stdout.println("	-r	consult/run a prolog file");
		stdout.println("	-v	print the prolog engine version");
		stdout.println("	-n	print the prolog engine name");
		stdout.println("	-l	print the prolog engine license");
		stdout.println("	-i	print the prolog engine information");
		stdout.println("	-a	print the prolog engine about");
		stdout.println("	-e	print the prolog engine enviroment paths");
		stdout.println("	-x	start the prolog engine execution");
		stdout.println("	-w	print the current work directory ");
		stdout.println("	-f	consult a prolog file and save formatted code");
		stdout.println("	-t	test and report integration conditions");
		stdout.println("	-p	print in a file a snapshot of currents predicates");
		stdout.println("	-g	generate all java class path wrapper procedures");
	}

	public final void run(String[] args) {

		Map<String, String> m = getArguments(args);
		if (!m.isEmpty()) {
			if (m.containsKey("-v")) {
				stdout.println(engine.getVersion());
			} else if (m.containsKey("-n")) {
				stdout.println(engine.getName());
			} else if (m.containsKey("-l")) {
				stdout.println(engine.getLicense());
			} else if (m.containsKey("-i")) {
				stdout.print(PROLOBJECTLINK);
				stdout.print(COPYRIHT);
				stdout.print(" ");
				stdout.print(engine.getName());
				stdout.print(" v");
				stdout.println(engine.getVersion());
				stdout.println(engine.getLicense());
				stdout.println(System.getProperty("java.vm.name"));
				stdout.println(System.getProperty("java.vendor"));
				stdout.println(System.getProperty("java.version"));
				stdout.println();
			} else if (m.containsKey("-w")) {
				try {
					stdout.println("Working directory");
					ProtectionDomain p = getClass().getProtectionDomain();
					URI d = p.getCodeSource().getLocation().toURI();
					stdout.println(d);
				} catch (URISyntaxException e) {
					Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
				}
			} else if (m.containsKey("-e")) {
				stdout.println("Enviroment");
				stdout.println("Class path");
				stdout.println(System.getenv("java.class.path"));
				stdout.println("System path");
				stdout.println(System.getenv("Path"));
			} else if (m.containsKey("-a")) {
				stdout.print(PROLOBJECTLINK);
				stdout.print(COPYRIHT);
			} else if (m.containsKey("-r")) {
				String file = m.get("-r");
				stdout.print("Consult ");
				stdout.println(file);
				engine.consult(file);
			} else if (m.containsKey("-x")) {
				// do nothing silently execution
			} else if (m.containsKey("-f")) {
				String file = m.get("-r");
				stdout.print("Format ");
				stdout.println(file);
				engine.consult(file);
				engine.persist(file);
			} else if (m.containsKey("-t")) {
				List<String> status = engine.verify();
				for (String string : status) {
					stdout.println(string);
				}
			} else if (m.containsKey("-p")) {
				String file = m.get("-p");
				try {
					PrintWriter writter = new PrintWriter(file);
					Set<PrologIndicator> set = engine.currentPredicates();
					for (PrologIndicator prologIndicator : set) {
						writter.println(prologIndicator);
					}
					writter.close();
				} catch (FileNotFoundException e) {
					Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
					System.exit(1);
				}
			} else if (m.containsKey("-g")) {
				engine.getProgrammer().codingRuntime(stdout);
				System.exit(0);
			} else {
				printUsage();
				System.exit(1);
			}

			try {

				String input;
				stdout.print("?- ");
				stdout.flush();
				input = stdin.readLine();

				while (true) {

					if (!input.equals("")) {
						stdout.println();

						if (input.lastIndexOf('.') == input.length() - 1) {
							input = input.substring(0, input.length() - 1);
						}

						PrologQuery query = engine.query(input);
						if (query.hasSolution()) {
							Map<String, PrologTerm> s = query.oneVariablesSolution();
							for (Entry<String, PrologTerm> e : s.entrySet()) {
								stdout.println(e.getKey() + " = " + e.getValue());
							}
							stdout.println();
							stdout.println("Yes.");
						}

						else {
							stdout.println("No.");
						}

						stdout.println();
						stdout.println();

					} else {
						stdout.println("Emty query");
						stdout.println();
					}

					stdout.print("?- ");
					stdout.flush();
					input = stdin.readLine();

				}

			} catch (UnsatisfiedLinkError e) {
				stdout.println("Prolog engine link conditions:");
				List<String> status = engine.verify();
				for (String string : status) {
					stdout.println(string);
				}
			} catch (IOException e) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
				System.exit(1);
			}

		} else {
			printUsage();
			System.exit(1);
		}

	}

}
