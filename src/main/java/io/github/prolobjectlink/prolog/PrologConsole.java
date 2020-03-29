/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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

import java.util.Map;

/**
 * Represent the prolog console of the system.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public interface PrologConsole {

	/**
	 * Create a arguments map from a given string arguments array. Used for convert
	 * command line interface program arguments array to argument map.
	 * 
	 * @param args string arguments array
	 * @return arguments map
	 * @since 1.0
	 */
	public Map<String, String> getArguments(String[] args);

	/**
	 * <p>
	 * Command line interface program run method for this platform. Take the program
	 * arguments from main entry point and execute the job. Used like:
	 * </p>
	 * 
	 * <pre>
	 * public class ANYPrologConsole extends AbstractConsole implements PrologConsole {
	 * 	public static void main(String[] args) {
	 * 		new ANYPrologConsole().run(args);
	 * 	}
	 *
	 * }
	 * </pre>
	 * 
	 * @param args command line interface program arguments array
	 * @since 1.0
	 */
	public void run(String[] args);

	/**
	 * Used to print console usage.
	 * 
	 * @since 1.0
	 */
	public void printUsage();

}
