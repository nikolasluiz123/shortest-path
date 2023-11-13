package br.com.shortest.path.graph;

import java.util.ArrayList;
import java.util.List;

public class DataGraph {
	private List<Vertex> vertices;
	private List<Edge> edges;

	public DataGraph() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		vertices.add(vertex);
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}
}