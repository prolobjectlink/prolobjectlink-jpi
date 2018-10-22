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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.logicware.platform.Graph;
import org.logicware.platform.GraphVertex;

public class GraphVertexTest extends BaseTest {

	@Test
	public void testGetIncomings() {

		Iterator<GraphVertex<String>> i = v3.getIncomingsVertices().iterator();
		assertEquals(v1, i.next());
		assertEquals(v2, i.next());

		i = v4.getIncomingsVertices().iterator();
		assertEquals(v2, i.next());

		i = v5.getIncomingsVertices().iterator();
		assertEquals(v1, i.next());
		assertEquals(v2, i.next());

	}

	@Test
	public void testGetOutgoings() {

		Iterator<GraphVertex<String>> i = v1.getOutgoingsVertices().iterator();
		assertEquals(v2, i.next());
		assertEquals(v3, i.next());
		assertEquals(v5, i.next());
		assertFalse(i.hasNext());

		i = v2.getOutgoingsVertices().iterator();
		assertEquals(v3, i.next());
		assertEquals(v4, i.next());
		assertEquals(v5, i.next());
		assertFalse(i.hasNext());

	}

	@Test
	public void testCountIncomings() {

		assertEquals(0, v1.countIncomings());
		assertEquals(1, v2.countIncomings());
		assertEquals(2, v3.countIncomings());
		assertEquals(1, v4.countIncomings());
		assertEquals(2, v5.countIncomings());
	}

	@Test
	public void testCountOutgoings() {

		assertEquals(3, v1.countOutgoings());
		assertEquals(3, v2.countOutgoings());

	}

	@Test
	public void testGetElement() {

		assertEquals("V1", v1.getElement());
		assertEquals("V2", v2.getElement());
		assertEquals("V3", v3.getElement());
		assertEquals("V4", v4.getElement());
		assertEquals("V5", v5.getElement());

	}

	@Test
	public void testGetElementClass() {

		assertEquals(String.class, v1.getElementClass());
		assertEquals(String.class, v2.getElementClass());
		assertEquals(String.class, v3.getElementClass());
		assertEquals(String.class, v4.getElementClass());
		assertEquals(String.class, v5.getElementClass());

	}

	@Test
	public void testUnwrapClassOfK() {
		assertEquals(null, v1.unwrap(String.class));
	}

	@Test
	public void testToString() {
		assertEquals("V1", v1.toString());
	}

	@Test
	public void testHashCode() {

		Graph<String, String> graph = new DirectedGraph<String, String>();
		assertEquals(v1.hashCode(), graph.addVertex("V1").hashCode());
		assertEquals(graph.addVertex((String) null).hashCode(), graph.addVertex((String) null).hashCode());

	}

	@Test
	public void testEquals() {

		Graph<String, String> graph = new DirectedGraph<String, String>();
		assertTrue(v1.equals(graph.addVertex("V1")));
		assertFalse(v1.equals(new Object()));
		assertFalse(v1.equals(null));
		assertFalse(v1.equals(v2));

		assertTrue(graph.addVertex((String) null).equals(graph.addVertex((String) null)));
		assertFalse(graph.addVertex((String) null).equals(new Object()));
		assertFalse(graph.addVertex((String) null).equals(v2));

	}

}
