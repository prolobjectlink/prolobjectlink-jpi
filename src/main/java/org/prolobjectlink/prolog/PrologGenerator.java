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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class PrologGenerator {

	private final PrintWriter stdout;
	private final PrologProvider provider;

	private final Set<String> ignoreClasses = new HashSet<String>();
	private final Set<String> ignorePackages = new HashSet<String>();

	public PrologGenerator(PrintWriter stdout, PrologProvider provider) {
		this.stdout = stdout;
		this.provider = provider;
	}

	public void generate() {
		JarFile jarFile = null;
		PrintWriter programmer = null;
		String javaHome = System.getProperty("java.home");
		File runtime = new File(javaHome + "/lib/rt.jar");
		try {
			jarFile = new JarFile(runtime);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry jarEntry = entries.nextElement();
				if (jarEntry.getName().startsWith("java") || jarEntry.getName().startsWith("javax")) {
					if (jarEntry.getName().endsWith(".class") && !jarEntry.getName().contains("$")) {
						String jarEntryName = jarEntry.getName();
						ClassLoader l = Thread.currentThread().getContextClassLoader();
						String className = jarEntryName.substring(0, jarEntryName.length() - 6);
						String fileName = toLowerCase(className) + ".pl";
						File runtimeFile = new File("prt/" + fileName.replace("java", "prolog"));
						stdout.println(runtimeFile);
						if (!runtimeFile.exists()) {
							runtimeFile.getParentFile().mkdirs();
							if (!runtimeFile.createNewFile()) {
								System.exit(1);
							}
						}

						programmer = new PrintWriter(new FileOutputStream(runtimeFile));

						// year of the generated copyright
						SimpleDateFormat f = new SimpleDateFormat("yyyy");
						Date resultdate = new Date(System.currentTimeMillis());
						String year = f.format(resultdate);

						// license section
						programmer.println("% Copyright (c) " + year + " Prolobjectlink Project");
						programmer.println();
						programmer.println(
								"% Permission is hereby granted, free of charge, to any person obtaining a copy");
						programmer.println(
								"% of this software and associated documentation files (the \"Software\"), to deal");
						programmer.println(
								"% in the Software without restriction, including without limitation the rights");
						programmer
								.println("% to use, copy, modify, merge, publish, distribute, sublicense, and/or sell");
						programmer.println("% copies of the Software, and to permit persons to whom the Software is");
						programmer.println("% furnished to do so, subject to the following conditions:");
						programmer.println();
						programmer.println(
								"% The above copyright notice and this permission notice shall be included in");
						programmer.println("% all copies or substantial portions of the Software.");
						programmer.println();
						programmer.println(
								"% THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR");
						programmer
								.println("% IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,");
						programmer.println(
								"% FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE");
						programmer.println("% AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER");
						programmer.println(
								"% LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,");
						programmer
								.println("% OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN");
						programmer.println("% THE SOFTWARE.");
						programmer.println();

						// author section
						programmer.println("% Author: Jose Zalacain");
						programmer.println();

						// include section
						programmer.println(":-" + provider.prologInclude("../../obj/prolobject.pl") + ".");
						programmer.println();

						// members section
						Class<?> runtimeClass = l.loadClass(className.replace('/', '.'));
						if (!ignoreClasses.contains(runtimeClass.getSimpleName())
								&& /* runtimeClass.getName().contains("class") && */runtimeClass
										.getModifiers() == Modifier.PUBLIC) {

							// constructor section
							Constructor<?>[] cs = runtimeClass.getConstructors();
							for (Constructor<?> constructor : cs) {
								String functor = toLowerCase(runtimeClass.getSimpleName());

								Parameter[] parameters = constructor.getParameters();
								PrologTerm[] arguments = new PrologTerm[parameters.length + 1];
								for (int i = 0; i < parameters.length; i++) {
									String arg = toUpperCase(parameters[i].getName());
									arguments[i] = provider.newVariable(arg, i);
								}
								arguments[parameters.length] = provider.newVariable("OUT", parameters.length);

								// procedure head
								PrologTerm head = provider.newStructure(functor, arguments);

								// procedure body
								arguments = new PrologTerm[parameters.length];
								for (int i = 0; i < parameters.length; i++) {
									String arg = toUpperCase(parameters[i].getName());
									arguments[i] = provider.newVariable(arg, i);
								}
								PrologTerm oclass = provider.newAtom(runtimeClass.getName());
								PrologTerm oargs = provider.newList(arguments);
								PrologTerm ooout = provider.newVariable("OUT", parameters.length);
								PrologTerm body = provider.newStructure("object_new", oclass, oargs, ooout);
								programmer.println(head + " :- \n\t" + body + ".");
								programmer.println();
							}

							// methods sections
							Method[] methods = runtimeClass.getMethods();
							for (Method method : methods) {
								String functor = toLowerCase(runtimeClass.getSimpleName()) + "_" + method.getName();

								Parameter[] parameters = method.getParameters();
								PrologTerm[] arguments = new PrologTerm[parameters.length + 2];
								arguments[0] = provider.newVariable("REF", 0);
								for (int i = 0; i < parameters.length; i++) {
									String arg = toUpperCase(parameters[i].getName());
									arguments[i + 1] = provider.newVariable(arg, i + 1);
								}
								arguments[parameters.length + 1] = provider.newVariable("OUT", parameters.length + 1);

								// procedure head
								PrologTerm head = provider.newStructure(functor, arguments);

								// procedure body
								arguments = new PrologTerm[parameters.length];
								for (int i = 0; i < parameters.length; i++) {
									String arg = toUpperCase(parameters[i].getName());
									arguments[i] = provider.newVariable(arg, i);
								}
								PrologTerm oref = provider.newVariable("REF", 0);
								PrologTerm omethod = provider.newAtom(method.getName());
								PrologTerm oargs = provider.newList(arguments);
								PrologTerm ooout = null;
								if (method.getReturnType() != Void.class) {
									ooout = provider.newVariable("OUT", parameters.length + 1);
								} else {
									ooout = provider.newVariable(parameters.length + 1);
								}
								PrologTerm body = provider.newStructure("object_call", oref, omethod, oargs, ooout);
								programmer.println(head + " :- \n\t" + body + ".");
								programmer.println();
							}
						}
						programmer.close();
					}
				}
			}
			jarFile.close();
		} catch (IOException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		} catch (ClassNotFoundException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
					Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		stdout.println("OK.");
	}

	public String toUpperCase(String target) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i);
			b.append(Character.toUpperCase(c));
		}
		return "" + b + "";
	}

	public String toLowerCase(String target) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i);
			b.append(Character.toLowerCase(c));
		}
		return "" + b + "";
	}

}
