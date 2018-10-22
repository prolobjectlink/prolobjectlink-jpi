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
package org.logicware;

public interface Graph<V, E> {

	public GraphEdge<E> addEdge(GraphVertex<V> from, GraphVertex<V> to, E edge, Direction direction);

	public GraphEdge<E> addEdge(GraphVertex<V> from, GraphVertex<V> to, E edge);

	public GraphEdge<E> addEdge(V from, V to, E edge, Direction direction);

	public GraphEdge<E> addEdge(V from, V to, E edge);

	public GraphVertex<V> addVertex(GraphVertex<V> vertex);

	public GraphVertex<V> addVertex(V vertex);

	/**
	 * Return the adjacent vertex from/out given vertex and incident edge.
	 * 
	 * @param vertex from/out vertex to find the adjacent vertex.
	 * @param edge   incident edge.
	 * @return adjacent vertex.
	 * @since 1.0
	 */
	public GraphVertex<V> getVertex(GraphVertex<V> vertex, GraphEdge<E> edge);

	public void removeEdge(GraphVertex<V> from, GraphVertex<V> to);

	public void removeVertex(GraphVertex<V> vertex);

	public Iterable<GraphEdge<E>> outEdges(GraphVertex<V> vertex);

	public Iterable<GraphEdge<E>> inEdges(GraphVertex<V> vertex);

	public int countOutEdges(GraphVertex<V> vertex);

	public int countInEdges(GraphVertex<V> vertex);

	public GraphEdge<E> getEdge(GraphVertex<V> from, GraphVertex<V> to);

	public GraphEdge<E> getEdge(V from, V to);

	public GraphEdge<E> getEdge(Object o);

	public GraphVertex<V> getVertex(Object o);

	public Iterable<GraphEdge<E>> getEdges();

	public Iterable<GraphVertex<V>> getVertices();

	public boolean equals(Object obj);

	public int countVertices();

	public int countEdges();

	public int hashCode();

	public void clear();

	boolean isEmpty();

}
