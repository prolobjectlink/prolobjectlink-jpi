/*
 * #%L
 * prolobjectlink-jpi
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
import org.logicware.Graph;
import org.logicware.GraphEdge;
import org.logicware.GraphVertex;

public class DirectedGraphTest extends BaseTest {

	@Test
	public void AbstractGraph() {

		Graph<String, String> graph = new DirectedGraph<String, String>(g);
		assertEquals(5, graph.countVertices());
		assertEquals(6, graph.countEdges());
		assertFalse(graph.isEmpty());

	}

	@Test
	public void testGetAdjacentVertex() {

		assertEquals(v2, g.getVertex(v1, e1));
		assertEquals(v3, g.getVertex(v1, e2));
		assertEquals(v5, g.getVertex(v1, e3));
		assertEquals(v3, g.getVertex(v2, e4));
		assertEquals(null, g.getVertex(v3, e5));
		assertEquals(null, g.getVertex(v1, e6));

		assertEquals(v1, g.getVertex(v2, e1));
		assertEquals(v1, g.getVertex(v3, e2));
		assertEquals(v1, g.getVertex(v5, e3));
		assertEquals(v2, g.getVertex(v3, e4));
		assertEquals(null, g.getVertex(v3, e5));
		assertEquals(null, g.getVertex(v1, e6));

		assertEquals(null, g.getVertex(null, e6));
		assertEquals(null, g.getVertex(v1, null));

		assertEquals(null, g.getVertex(null, null));

	}

	@Test
	public void testAddEdgeGraphVertexOfObjectGraphVertexOfObjectObject() {

		Graph<String, String> graph = new DirectedGraph<String, String>();

		assertTrue(graph.isEmpty());
		assertEquals(0, graph.countEdges());
		assertEquals(0, graph.countVertices());

		GraphVertex<String> v1 = graph.addVertex("V1");
		GraphVertex<String> v2 = graph.addVertex("V2");
		GraphVertex<String> v3 = graph.addVertex("V3");
		GraphVertex<String> v4 = graph.addVertex("V4");
		GraphVertex<String> v5 = graph.addVertex("V5");

		graph.addEdge(v1, v2, "E1");
		graph.addEdge(v1, v3, "E2");
		graph.addEdge(v1, v5, "E3");
		graph.addEdge(v2, v3, "E4");
		graph.addEdge(v2, v4, "E5");
		graph.addEdge(v2, v5, "E6");

		assertEquals(5, graph.countVertices());
		assertEquals(6, graph.countEdges());
		assertFalse(graph.isEmpty());

		// graph.addEdge(null, null, "E1");
		graph.addEdge(v1, v2, null);
		graph.addEdge(v1, null, null);
		graph.addEdge(v1, null, "E1");
		graph.addEdge(null, v2, null);
		graph.addEdge(null, v2, "E1");
		// graph.addEdge(null, null, null);
		assertEquals(5, graph.countVertices());
		assertEquals(6, graph.countEdges());
		assertFalse(graph.isEmpty());

	}

	@Test
	public void testAddVertexObject() {

		Graph<String, String> graph = new DirectedGraph<String, String>();
		graph.addVertex("V1");
		graph.addVertex("V2");
		graph.addVertex("V3");
		graph.addVertex("V4");
		graph.addVertex("V5");
		assertEquals(5, graph.countVertices());
		assertFalse(graph.isEmpty());
	}

	@Test
	public void testRemoveEdge() {

		assertEquals(6, g.countEdges());
		g.removeEdge(null, null);
		g.removeEdge(v1, v2);
		g.removeEdge(v1, v3);
		g.removeEdge(v1, v5);
		g.removeEdge(v2, v3);
		g.removeEdge(v2, v4);
		g.removeEdge(v2, v5);
		assertEquals(0, g.countEdges());

	}

	@Test
	public void testRemoveVertex() {

		assertEquals(5, g.countVertices());
		g.removeVertex(v1);
		g.removeVertex(v2);
		g.removeVertex(v3);
		g.removeVertex(v4);
		g.removeVertex(v5);
		assertEquals(0, g.countVertices());

	}

	@Test
	public void testOutEdges() {

		Iterator<GraphEdge<String>> i = g.outEdges(v1).iterator();
		assertEquals(e1, i.next());
		assertEquals(e2, i.next());
		assertEquals(e3, i.next());
		assertFalse(i.hasNext());

		i = g.outEdges(v2).iterator();
		assertEquals(e4, i.next());
		assertEquals(e5, i.next());
		assertEquals(e6, i.next());
		assertFalse(i.hasNext());

		i = g.outEdges(null).iterator();
		assertFalse(i.hasNext());

	}

	@Test
	public void testInEdges() {

		Iterator<GraphEdge<String>> i = g.inEdges(v3).iterator();
		assertEquals(e2, i.next());
		assertEquals(e4, i.next());
		assertFalse(i.hasNext());

		i = g.inEdges(v4).iterator();
		assertEquals(e5, i.next());
		assertFalse(i.hasNext());

		i = g.inEdges(v5).iterator();
		assertEquals(e3, i.next());
		assertEquals(e6, i.next());
		assertFalse(i.hasNext());

		i = g.inEdges(null).iterator();
		assertFalse(i.hasNext());

	}

	@Test
	public void testGetEdgeGraphVertexOfObjectGraphVertexOfObject() {

		assertEquals(e1, g.getEdge(v1, v2));
		assertEquals(e2, g.getEdge(v1, v3));
		assertEquals(e3, g.getEdge(v1, v5));
		assertEquals(e4, g.getEdge(v2, v3));
		assertEquals(e5, g.getEdge(v2, v4));
		assertEquals(e6, g.getEdge(v2, v5));

		assertEquals(null, g.getEdge(null, v5));
		assertEquals(null, g.getEdge(v2, null));
		// assertEquals(null, g.getEdge(null, null));

	}

	@Test
	public void testAddVertexGraphVertexOfV() {

		Graph<String, String> graph = new DirectedGraph<String, String>();
		assertTrue(graph.isEmpty());
		assertEquals(0, graph.countVertices());
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		assertEquals(5, graph.countVertices());
		assertFalse(graph.isEmpty());

	}

	@Test
	public void testGetEdgeObject() {

		Graph<String, String> graph = new DirectedGraph<String, String>();
		assertEquals(null, graph.getEdge(""));

		assertEquals(e1, g.getEdge("E1"));
		assertEquals(e2, g.getEdge("E2"));
		assertEquals(e3, g.getEdge("E3"));
		assertEquals(e4, g.getEdge("E4"));
		assertEquals(e5, g.getEdge("E5"));
		assertEquals(e6, g.getEdge("E6"));

	}

	@Test
	public void testCountOutEdges() {

		assertEquals(3, g.countOutEdges(v1));
		assertEquals(3, g.countOutEdges(v2));
		assertEquals(0, g.countOutEdges(v3));
		assertEquals(0, g.countOutEdges(v4));
		assertEquals(0, g.countOutEdges(v5));

	}

	@Test
	public void testCountInEdges() {

		assertEquals(0, g.countInEdges(v1));
		assertEquals(1, g.countInEdges(v2));
		assertEquals(2, g.countInEdges(v3));
		assertEquals(1, g.countInEdges(v4));
		assertEquals(2, g.countInEdges(v5));

	}

	@Test
	public void testGetVertex() {

		Graph<String, String> graph = new DirectedGraph<String, String>();
		assertEquals(null, graph.getVertex(""));

		assertEquals(v1, g.getVertex("V1"));
		assertEquals(v2, g.getVertex("V2"));
		assertEquals(v3, g.getVertex("V3"));
		assertEquals(v4, g.getVertex("V4"));
		assertEquals(v5, g.getVertex("V5"));

	}

	@Test
	public void testGetEdges() {

		Iterator<GraphEdge<String>> i = g.getEdges().iterator();
		assertEquals(e1, i.next());
		assertEquals(e2, i.next());
		assertEquals(e3, i.next());
		assertEquals(e4, i.next());
		assertEquals(e5, i.next());
		assertEquals(e6, i.next());
		assertFalse(i.hasNext());

	}

	@Test
	public void testGetVertices() {

		Iterator<GraphVertex<String>> i = g.getVertices().iterator();
		assertEquals(v1, i.next());
		assertEquals(v2, i.next());
		assertEquals(v3, i.next());
		assertEquals(v4, i.next());
		assertEquals(v5, i.next());
		assertFalse(i.hasNext());

	}

	@Test
	public void testCountEdges() {

		assertEquals(6, g.countEdges());

	}

	@Test
	public void testCountVertices() {

		assertEquals(5, g.countVertices());

	}

	@Test
	public void testIsEmpty() {

		assertFalse(g.isEmpty());
		assertEquals(5, g.countVertices());
		g.clear();
		assertEquals(0, g.countVertices());
		assertTrue(g.isEmpty());

	}

	@Test
	public void testClear() {

		assertEquals(6, g.countEdges());
		assertEquals(5, g.countVertices());
		g.clear();
		assertEquals(0, g.countEdges());
		assertEquals(0, g.countVertices());

	}

	@Test
	public void testHashCode() {

		Graph<String, String> graph = new DirectedGraph<String, String>(g);
		assertEquals(new DirectedGraph<String, String>(null).hashCode(),
				new DirectedGraph<String, String>(null).hashCode());
		assertEquals(g.hashCode(), graph.hashCode());

	}

	@Test
	public void testEquals() {

		Graph<String, String> graph = new DirectedGraph<String, String>(g);
		assertFalse(g.equals(new DirectedGraph<String, String>()));
		assertFalse(v1.equals(new Object()));
		assertFalse(v1.equals(null));
		assertTrue(g.equals(graph));

	}

}
