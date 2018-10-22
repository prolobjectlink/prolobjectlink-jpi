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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.logicware.Direction;
import org.logicware.Graph;
import org.logicware.GraphEdge;
import org.logicware.GraphVertex;
import org.logicware.graph.DirectedGraph.GenericGraphVertex;

/**
 * Partial implementation of {@link Graph} interface.
 * 
 * @author Jose Zalacain
 *
 * @param <V> vertex element type
 * @param <E> edge element type
 * 
 * @since 1.0
 * @see DirectedGraph
 * @see Graph
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {

	protected Collection<GraphEdge<E>> edges;
	protected Collection<GraphVertex<V>> vertices;

	public AbstractGraph() {
		edges = new LinkedList<GraphEdge<E>>();
		vertices = new LinkedList<GraphVertex<V>>();
	}

	public AbstractGraph(Graph<V, E> graph) {
		edges = new LinkedList<GraphEdge<E>>();
		vertices = new LinkedList<GraphVertex<V>>();
		if (graph != null) {
			for (GraphEdge<E> edge : graph.getEdges()) {
				edges.add(edge);
			}
			for (GraphVertex<V> vertex : graph.getVertices()) {
				vertices.add(vertex);
			}
		}
	}

	public final GraphEdge<E> addEdge(V from, V to, E edge) {
		return addEdge(getVertex(from), getVertex(to), edge);
	}

	public final GraphEdge<E> addEdge(V from, V to, E edge, Direction direction) {
		return addEdge(getVertex(from), getVertex(to), edge, direction);
	}

	public final GraphEdge<E> addEdge(GraphVertex<V> from, GraphVertex<V> to, E edge) {
		return addEdge(from, to, edge, Direction.BOTH);
	}

	public final GraphVertex<V> addVertex(GraphVertex<V> vertex) {
		vertices.add(vertex);
		return vertex;
	}

	public final GraphEdge<E> getEdge(Object o) {
		for (GraphEdge<E> edge : edges) {
			if (edge.getElement().equals(o)) {
				return edge;
			}
		}
		return null;
	}

	public final GraphVertex<V> getVertex(Object o) {
		for (GraphVertex<V> vertex : vertices) {
			if (vertex.getElement().equals(o)) {
				return vertex;
			}
		}
		return null;
	}

	public final GraphVertex<V> getVertex(GraphVertex<V> vertex, GraphEdge<E> edge) {
		if (edge != null) {
			GraphVertex<V> from = edge.getFrom();
			GraphVertex<V> to = edge.getTo();
			if (from != null && from.equals(vertex)) {
				return to;
			} else if (to != null && to.equals(vertex)) {
				return from;
			}
		}
		return null;
	}

	public final int countOutEdges(GraphVertex<V> vertex) {
		return vertex.countOutgoings();
	}

	public final int countInEdges(GraphVertex<V> vertex) {
		return vertex.countIncomings();
	}

	public final Iterable<GraphEdge<E>> getEdges() {
		return edges;
	}

	public final Iterable<GraphVertex<V>> getVertices() {
		return vertices;
	}

	public final int countEdges() {
		return edges.size();
	}

	public final int countVertices() {
		return vertices.size();
	}

	public final boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<GraphEdge<E>> ei = edges.iterator();
		Iterator<GraphVertex<V>> vi = vertices.iterator();
		sb.append('[');

		// vertices
		sb.append('[');
		if (vi.hasNext()) {
			while (vi.hasNext()) {
				GraphVertex<V> v = vi.next();
				sb.append(v);
				if (vi.hasNext()) {
					sb.append(',');
					sb.append(' ');
				}
			}
		}

		sb.append(']');
		sb.append(',');

		// edges
		sb.append('[');
		if (ei.hasNext()) {
			while (ei.hasNext()) {
				GraphEdge<E> e = ei.next();
				sb.append(e);
				if (vi.hasNext()) {
					sb.append(',');
					sb.append(' ');
				}
			}
		}
		sb.append(']');
		sb.append(']');
		return "" + sb + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractGraph<?, ?> other = (AbstractGraph<?, ?>) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges)) {
			return false;
		}
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!vertices.equals(other.vertices)) {
			return false;
		}
		return true;
	}

	public final void clear() {
		for (GraphVertex<V> vertex : new ArrayList<GraphVertex<V>>(vertices)) {
			removeVertex(vertex);
			GenericGraphVertex v = vertex.unwrap(GenericGraphVertex.class);
			for (GraphEdge<E> e : new ArrayList<GraphEdge<E>>(v.outgoing.values())) {
				if (e.getFrom() == v) {
					v.outgoing.remove(e.getTo());
				}
			}
			for (GraphEdge<E> e : new ArrayList<GraphEdge<E>>(v.incoming.values())) {
				if (e.getTo() == v) {
					v.incoming.remove(e.getFrom());
				}
			}
		}
		vertices.clear();
		edges.clear();
	}

}
