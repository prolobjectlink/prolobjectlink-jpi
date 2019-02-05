/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.prolobjectlink.prolog;

import static org.prolobjectlink.prolog.About.COPYRIHT;
import static org.prolobjectlink.prolog.About.PROLOBJECTLINK;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.prolobjectlink.AbstractPlatform;
import org.prolobjectlink.ArrayIterator;
import org.prolobjectlink.logging.LoggerConstants;
import org.prolobjectlink.logging.LoggerUtils;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractConsole extends AbstractPlatform implements PrologConsole {

	// default input stream
	private final InputStreamReader reader = new InputStreamReader(System.in);

	// buffered reader for read from standard input stream
	private final BufferedReader stdin = new BufferedReader(reader);

	// standard output stream
	private static final PrintStream stdout = System.out;

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
		stdout.println("Usage: prolog [-option] [file] to consult a file");
		stdout.println("options:");
		stdout.println("	-r	to consult/run a prolog file");
		stdout.println("	-v	print the prolog engine version");
		stdout.println("	-n	print the prolog engine name");
		stdout.println("	-l	print the prolog engine license");
		stdout.println("	-i	print the prolog engine information");
		stdout.println("	-a	print the prolog engine about");
		stdout.println("	-e	print the prolog engine enviroment paths");
		stdout.println("	-x	start the prolog engine execution");
		stdout.println("	-w	print the current work directory ");
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
				stdout.println(engine.getJavaName());
				stdout.println(engine.getJavaVendor());
				stdout.println(engine.getJavaVersion());
				stdout.println();
			} else if (m.containsKey("-w")) {
				try {
					stdout.println("Working directory");
					ProtectionDomain p = getClass().getProtectionDomain();
					URI d = p.getCodeSource().getLocation().toURI();
					stdout.println(d);
				} catch (URISyntaxException e) {
					LoggerUtils.error(getClass(), LoggerConstants.URI, e);
				}
			} else if (m.containsKey("-e")) {
				stdout.println("Enviroment");
				stdout.println("Class path");
				stdout.println(getClassPath());
				stdout.println("System path");
				stdout.println(getPath());
			} else if (m.containsKey("-a")) {
				stdout.print(PROLOBJECTLINK);
				stdout.print(COPYRIHT);
			} else if (m.containsKey("-r")) {
				String file = m.get("-r");
				stdout.print("Consult ");
				stdout.println(file);
				engine.consult(file);
			} else if (m.containsKey("-x")) {
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
							stdout.println("Yes.");
						}

//						else if (engine.hasCause()) {
//							stdout.println(engine.getCause());
//						} 

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
				checkJDKInstalation();
				stdout.println("You need prolog native engine and set this routes in your system class path:");
				if (runOnWindows()) {
					stdout.println(getJavaHome().replace(File.separator + "jre", File.separator) + "/jdk"
							+ getJavaVersion() + "/bin" + getPathSeparator());
					stdout.println(getJavaHome().replace(File.separator + "jre", File.separator) + "/jdk"
							+ getJavaVersion() + "/lib/tools.jar" + getPathSeparator());
					stdout.println(getJavaHome().replace(File.separator + "jre", File.separator) + "/jdk"
							+ getJavaVersion() + "/jre/lib/rt.jar;" + getPathSeparator());
					stdout.println("C:/Program Files/swipl/lib/jpl.jar" + getPathSeparator());
					stdout.println("C:/Program Files/swipl/bin");
				} else if (runOnOsX()) {
					// TODO environment routes for MacOSX
				} else if (runOnLinux()) {
					stdout.println("/usr/lib/jvm/java-" + getJavaVersion() + "-openjdk-" + getArch() + "/bin"
							+ getPathSeparator());
					stdout.println("/usr/lib/jvm/java-" + getJavaVersion() + "-openjdk-" + getArch() + "/lib/tools.jar"
							+ getPathSeparator());
					stdout.println("/usr/lib/jvm/java-" + getJavaVersion() + "-openjdk-" + getArch() + "/jre/lib/rt.jar"
							+ getPathSeparator());
					stdout.println("/usr/local/bin/swipl/lib/jpl.jar" + getPathSeparator());
					stdout.println("/usr/local/bin");
				}
			} catch (IOException e) {
				LoggerUtils.error(getClass(), LoggerConstants.IO, e);
				System.exit(0);
			}

		} else {
			printUsage();
			System.exit(1);
		}

	}

}
