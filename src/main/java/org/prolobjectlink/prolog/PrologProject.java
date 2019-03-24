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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to generate IDE(s) project information files
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
final class PrologProject {

	/**
	 * Generate .project file at the given folder
	 * 
	 * @param folder
	 * @since 1.0
	 */
	static void dotProject(String folder) {
		File plk = new File(folder);
		File pdk = plk.getParentFile();
		File projectFolder = pdk.getParentFile();
		String projectName = projectFolder.getName();
		File projectFile = new File(projectFolder + "/.project");
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(projectFile));
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.println("<projectDescription>");
			writer.println("\t<name>" + projectName + "</name>");
			writer.println("\t<comment></comment>");
			writer.println("\t<projects>");
			writer.println("\t</projects>");
			writer.println("\t<buildSpec>");
			writer.println("\t</buildSpec>");
			writer.println("\t<natures>");
			writer.println("\t</natures>");
			writer.println("</projectDescription>");
			writer.close();
		} catch (FileNotFoundException e) {
			Logger.getLogger(PrologProject.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Generate .project file at distribution folder
	 * 
	 * @since 1.0
	 */
	static void dotProject() {
		Class<?> c = PrologProject.class;
		ProtectionDomain d = c.getProtectionDomain();
		CodeSource s = d.getCodeSource();
		String prolobjectlink = s.getLocation().getPath();
		dotProject(prolobjectlink);
	}

	private PrologProject() {
	}

}
