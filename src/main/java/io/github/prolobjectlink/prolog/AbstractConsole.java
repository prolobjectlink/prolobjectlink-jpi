/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
 * Partial implementation of {@link PrologConsole} interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractConsole implements PrologConsole {

	private static final String PROLOBJECTLINK = "Prolobjectlink";
	private static final String COPYRIHT = " (C)";

	private static final InputStream STDIN = System.in;
//	private static final OutputStream STDOUT = System.out

	// default input stream
	private final InputStreamReader input = new InputStreamReader(STDIN);

	// buffered reader for read from standard input stream
	private final BufferedReader reader = new BufferedReader(input);

	// standard output stream
//	private final PrintWriter output = System.console().writer()
	private final PrintWriter output = new PrintWriter(System.out, true);

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
		output.println("Usage: pllink option [file]");
		output.println("options:");
		output.println("	-r	consult/run a prolog file");
		output.println("	-v	print the prolog engine version");
		output.println("	-n	print the prolog engine name");
		output.println("	-l	print the prolog engine license");
		output.println("	-i	print the prolog engine information");
		output.println("	-a	print the prolog engine about");
		output.println("	-e	print the prolog engine enviroment paths");
		output.println("	-x	start the prolog engine execution");
		output.println("	-w	print the current work directory ");
		output.println("	-f	consult a prolog file and save formatted code");
		output.println("	-t	test and report integration conditions");
		output.println("	-p	print in a file a snapshot of currents predicates");
		output.println("	-s	generate .project file for Prolog Development Tool");

	}

	public final void run(String[] args) {

		Map<String, String> m = getArguments(args);
		if (!m.isEmpty()) {
			if (m.containsKey("-v")) {
				output.println(engine.getVersion());
			} else if (m.containsKey("-n")) {
				output.println(engine.getName());
			} else if (m.containsKey("-l")) {
				output.println(engine.getLicense());
			} else if (m.containsKey("-i")) {
				output.print(PROLOBJECTLINK);
				output.print(COPYRIHT);
				output.print(" ");
				output.print(engine.getName());
				output.print(" v");
				output.println(engine.getVersion());
				output.println(engine.getLicense());
				output.println(System.getProperty("java.vm.name"));
				output.println(System.getProperty("java.vendor"));
				output.println(System.getProperty("java.version"));
				output.println();
			} else if (m.containsKey("-w")) {
				try {
					output.println("Working directory");
					ProtectionDomain p = getClass().getProtectionDomain();
					URI d = p.getCodeSource().getLocation().toURI();
					output.println(d);
				} catch (URISyntaxException e) {
					Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
				}
			} else if (m.containsKey("-e")) {
				output.println("Enviroment");
				output.println("Class path");
				output.println(System.getenv("java.class.path"));
				output.println("System path");
				output.println(System.getenv("Path"));
			} else if (m.containsKey("-a")) {
				output.print(PROLOBJECTLINK);
				output.print(COPYRIHT);
			} else if (m.containsKey("-r")) {
				String file = m.get("-r");
				output.print("Consult ");
				output.println(file);
				engine.consult(file);
			} else if (m.containsKey("-x")) {
				// do nothing silently execution
			} else if (m.containsKey("-f")) {
				String file = m.get("-r");
				output.print("Format ");
				output.println(file);
				engine.consult(file);
				engine.persist(file);
			} else if (m.containsKey("-t")) {
				List<String> status = engine.verify();
				for (String string : status) {
					output.println(string);
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
			} else if (m.containsKey("-h")) {
				System.exit(1);
				printUsage();
			} else {
				printUsage();
				System.exit(1);
			}

			try {

				String queryString;
				output.print("?- ");
				output.flush();
				queryString = reader.readLine();

				while (true) {

					if (!queryString.equals("")) {
						output.println();

						if (queryString.lastIndexOf('.') == queryString.length() - 1) {
							queryString = queryString.substring(0, queryString.length() - 1);
						}

						PrologQuery query = engine.query(queryString);
						if (query.hasSolution()) {
							Map<String, PrologTerm> s = query.oneVariablesSolution();
							for (Entry<String, PrologTerm> e : s.entrySet()) {
								output.println(e.getKey() + " = " + e.getValue());
							}
							output.println();
							output.println("Yes.");
						}

						else {
							output.println("No.");
						}

						output.println();
						output.println();

					} else {
						output.println("Emty query");
						output.println();
					}

					output.print("?- ");
					output.flush();
					queryString = reader.readLine();

				}

			} catch (UnsatisfiedLinkError e) {
				output.println("Prolog engine link conditions:");
				List<String> status = engine.verify();
				for (String string : status) {
					output.println(string);
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
