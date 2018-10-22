/*
 * #%L
 * prolobjectlink-jpe
 * %%
 * Copyright (C) 2012 - 2018 Logicware Project
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
package org.logicware.graph;

import org.junit.After;
import org.junit.Before;
import org.logicware.platform.GraphEdge;
import org.logicware.platform.GraphVertex;

public abstract class BaseTest {

	protected final DirectedGraph<String, String> g = new DirectedGraph<String, String>();

	protected final GraphVertex<String> v1 = g.addVertex("V1");
	protected final GraphVertex<String> v2 = g.addVertex("V2");
	protected final GraphVertex<String> v3 = g.addVertex("V3");
	protected final GraphVertex<String> v4 = g.addVertex("V4");
	protected final GraphVertex<String> v5 = g.addVertex("V5");

	protected final GraphEdge<String> e1 = g.addEdge(v1, v2, "E1");
	protected final GraphEdge<String> e2 = g.addEdge(v1, v3, "E2");
	protected final GraphEdge<String> e3 = g.addEdge(v1, v5, "E3");
	protected final GraphEdge<String> e4 = g.addEdge(v2, v3, "E4");
	protected final GraphEdge<String> e5 = g.addEdge(v2, v4, "E5");
	protected final GraphEdge<String> e6 = g.addEdge(v2, v5, "E6");

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

}
