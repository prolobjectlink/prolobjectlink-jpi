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

/**
 * 
 * @author Jose Zalacain
 * @since 1.1
 */
public interface PrologDocumentable {

	/**
	 * Check if exist at least one tag like documentation comment
	 * 
	 * @since 1.2
	 */
	public boolean hasDocumentation();

	/**
	 * Check if exist description tag in doc comment
	 * 
	 * @since 1.2
	 */
	public boolean hasDescription();

	/**
	 * Check if exist version tag in doc comment
	 * 
	 * @since 1.2
	 */
	public boolean hasVersion();

	/**
	 * Check if exist author tag in doc comment
	 * 
	 * @since 1.2
	 */
	public boolean hasAuthor();

	/**
	 * Check if exist since tag in doc comment
	 * 
	 * @since 1.2
	 */
	public boolean hasSince();

	/**
	 * Check if exist see tag in doc comment
	 * 
	 * @since 1.2
	 */
	public boolean hasSee();

	/**
	 * Set the description tag in doc comment
	 * 
	 * @param description description for this doc object
	 * @since 1.2
	 */
	public void setDescription(String description);

	/**
	 * Set the version tag in doc comment
	 * 
	 * @param version version for this doc object
	 * @since 1.2
	 */
	public void setVersion(String version);

	/**
	 * Set the author tag in doc comment
	 * 
	 * @param author author for this doc object
	 * @since 1.2
	 */
	public void setAuthor(String author);

	/**
	 * Set the since tag in doc comment
	 * 
	 * @param since since for this doc object
	 * @since 1.2
	 */
	public void setSince(String since);

	/**
	 * Set the see tag in doc comment
	 * 
	 * @param see see for this doc object
	 * @since 1.2
	 */
	public void setSee(String see);

	/**
	 * Get the description tag in doc comment
	 * 
	 * @since 1.2
	 */
	public String getDescription();

	/**
	 * Get the version tag in doc comment
	 * 
	 * @since 1.2
	 */
	public String getVersion();

	/**
	 * Get the author tag in doc comment
	 * 
	 * @since 1.2
	 */
	public String getAuthor();

	/**
	 * Get the since tag in doc comment
	 * 
	 * @since 1.2
	 */
	public String getSince();

	/**
	 * Get the see tag in doc comment
	 * 
	 * @since 1.2
	 */
	public String getSee();

}
