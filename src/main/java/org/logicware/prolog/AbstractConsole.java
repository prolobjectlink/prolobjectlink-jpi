/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2018 WorkLogic Project
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
package org.logicware.prolog;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.logicware.AbstractPlatform;
import org.logicware.logging.LoggerConstants;
import org.logicware.logging.LoggerUtils;

public abstract class AbstractConsole extends AbstractPlatform implements PrologConsole {

	// default input stream
	private final InputStreamReader reader = new InputStreamReader(System.in);

	// buffered reader for read from standard input stream
	private final BufferedReader stdin = new BufferedReader(reader);

	// standard output stream
	private final PrintWriter stdout = System.console().writer();

	//
	private final PrologEngine engine;

	public AbstractConsole(PrologProvider provider) {
		this.engine = provider.newEngine();
	}

	public final void run(String[] args) {

		String input;

		stdout.print("Prolobjectlink");
		stdout.print("WorkLogic (C).");
		stdout.print(engine.getName());
		stdout.print(" v");
		stdout.println(engine.getVersion());
		stdout.println(engine.getLicense());
		stdout.println(engine.getJavaName());
		stdout.println(engine.getJavaVendor());
		stdout.println(engine.getJavaVersion());
		stdout.println();

		try {

			if (args.length > 0) {
				engine.consult(args[0]);
			}

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

//					else if (engine.hasCause()) {
//						stdout.println(engine.getCause());
//					} 

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

		} catch (IOException e) {
			LoggerUtils.error(getClass(), LoggerConstants.IO, e);
		}
	}

}
