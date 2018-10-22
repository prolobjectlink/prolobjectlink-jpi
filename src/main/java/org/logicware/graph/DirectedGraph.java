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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.logicware.Direction;
import org.logicware.Graph;
import org.logicware.GraphEdge;
import org.logicware.GraphVertex;

/**
 * Directed graph implementation
 * 
 * @author Jose Zalacain
 *
 * @param <V> generic type for vertices
 * @param <E> generic type for edges
 * @since 1.0
 * @see AbstractGraph
 * @see Graph
 */
public class DirectedGraph<V, E> extends AbstractGraph<V, E> implements Graph<V, E> {

	public DirectedGraph() {
	}

	public DirectedGraph(Graph<V, E> graph) {
		super(graph);
	}

	public GraphEdge<E> addEdge(GraphVertex<V> from, GraphVertex<V> to, E edge, Direction direction) {
		GraphEdge<E> e = getEdge(from, to);
		if (e == null && from != null && to != null) {
			e = new GenericGraphEdge(edge, from, to, direction);
			switch (direction) {
			case IN:
				to.unwrap(GenericGraphVertex.class).incoming.put(from, e);
				break;
			case OUT:
				from.unwrap(GenericGraphVertex.class).outgoing.put(to, e);
				break;
			default:
				from.unwrap(GenericGraphVertex.class).outgoing.put(to, e);
				to.unwrap(GenericGraphVertex.class).incoming.put(from, e);
				break;
			}
			edges.add(e);
		}
		return e;
	}

	public final GraphVertex<V> addVertex(V vertex) {
		return addVertex(new GenericGraphVertex(vertex));
	}

	public void removeEdge(GraphVertex<V> from, GraphVertex<V> to) {
		GraphEdge<E> edge = getEdge(from, to);
		if (edge != null) {
			edges.remove(edge);
			from.unwrap(GenericGraphVertex.class).outgoing.remove(to);
			to.unwrap(GenericGraphVertex.class).incoming.remove(from);
		}
	}

	public final void removeVertex(GraphVertex<V> vertex) {
		vertices.remove(vertex);
		if (vertex.isWrappedFor(GenericGraphVertex.class)) {
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
	}

	public final Iterable<GraphEdge<E>> outEdges(GraphVertex<V> vertex) {
		if (vertex != null) {
			GenericGraphVertex v = vertex.unwrap(GenericGraphVertex.class);
			return v.outgoing.values();
		}
		return new ArrayList<GraphEdge<E>>();
	}

	public final Iterable<GraphEdge<E>> inEdges(GraphVertex<V> vertex) {
		if (vertex != null) {
			GenericGraphVertex v = vertex.unwrap(GenericGraphVertex.class);
			return new ArrayList<GraphEdge<E>>(v.incoming.values());
		}
		return new ArrayList<GraphEdge<E>>();
	}

	public final GraphEdge<E> getEdge(GraphVertex<V> from, GraphVertex<V> to) {
		if (from != null) {
			GenericGraphVertex v = from.unwrap(GenericGraphVertex.class);
			for (GraphEdge<E> edge : v.outgoing.values()) {
				if (edge.getTo().equals(to)) {
					return edge;
				}
			}
		}
		return null;
	}

	public GraphEdge<E> getEdge(V from, V to) {
		GraphVertex<V> f = new GenericGraphVertex(from);
		GraphVertex<V> t = new GenericGraphVertex(to);
		return getEdge(f, t);
	}

	private class GenericGraphEdge extends AbstractGraphEdge<E> implements GraphEdge<E> {

		private final GraphVertex<V> from;
		private final GraphVertex<V> to;

		private GenericGraphEdge(E element, GraphVertex<V> from, GraphVertex<V> to, Direction direction) {
			super(element, direction);
			this.from = from;
			this.to = to;

		}

		public GraphVertex<V> getFrom() {
			return from;
		}

		public GraphVertex<V> getTo() {
			return to;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

	}

	protected class GenericGraphVertex extends AbstractGraphVertex<V> implements GraphVertex<V> {

		public Map<GraphVertex<V>, GraphEdge<E>> incoming;
		public Map<GraphVertex<V>, GraphEdge<E>> outgoing;

		protected GenericGraphVertex(V element) {
			super(element);
			incoming = new LinkedHashMap<GraphVertex<V>, GraphEdge<E>>();
			outgoing = new LinkedHashMap<GraphVertex<V>, GraphEdge<E>>();
		}

		public Iterable<GraphVertex<V>> getIncomingsVertices() {
			return new ArrayList<GraphVertex<V>>(incoming.keySet());
		}

		public Iterable<GraphVertex<V>> getOutgoingsVertices() {
			return new ArrayList<GraphVertex<V>>(outgoing.keySet());
		}

		public int countIncomings() {
			return incoming.size();
		}

		public int countOutgoings() {
			return outgoing.size();
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

	}

}
