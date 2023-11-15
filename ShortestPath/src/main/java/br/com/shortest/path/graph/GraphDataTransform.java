package br.com.shortest.path.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import br.com.shortest.path.reader.FileBufferedReader;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class GraphDataTransform {

	private FileBufferedReader reader;

	public GraphDataTransform(String fileName) {
		this.reader = new FileBufferedReader(fileName);
	}

	public Graph<Vertex, Edge> transformDataInGraph() throws Exception {
		List<String> lines = this.reader.readFile();

		Graph<Vertex, Edge> viewGraph = new SparseGraph<>();

		for (String line : lines) {
			String[] split = line.split(";");

			if (!viewGraph.getVertices().stream().anyMatch(v -> v.getValue().equals(split[0]))) {
				viewGraph.addVertex(new Vertex(split[0]));
			}
			
			if (!viewGraph.getVertices().stream().anyMatch(v -> v.getValue().equals(split[1]))) {
				viewGraph.addVertex(new Vertex(split[1]));
			}
		}
		
		for (String line : lines) {
			String[] split = line.split(";");

			Vertex vertex1 = viewGraph.getVertices().stream().filter(v -> v.getValue().equals(split[0])).findFirst().get();
			Vertex vertex2 = viewGraph.getVertices().stream().filter(v -> v.getValue().equals(split[1])).findFirst().get();
			Integer weight = Integer.parseInt(split[2]);

			Edge edge = new Edge(vertex1, vertex2, weight);
			viewGraph.addEdge(edge, edge.getStart(), edge.getEnd());
		}
		
		return viewGraph;
	}
	
	public Stack<Edge> transformDataInStackedEdges() throws Exception {
		List<String> lines = this.reader.readFile();
		Stack<Edge> edges = new Stack<>();
		
		for (String line : lines) {
			String[] split = line.split(";");

			Edge edge = new Edge(new Vertex(split[0]), new Vertex(split[1]), Integer.parseInt(split[2]));
			edges.push(edge);
		}
		
		return edges;
	}
	
	public Queue<Edge> transformDataInQueuedEdges() throws Exception {
		List<String> lines = this.reader.readFile();
		Queue<Edge> edges = new LinkedList<>();
		
		for (String line : lines) {
			String[] split = line.split(";");

			Edge edge = new Edge(new Vertex(split[0]), new Vertex(split[1]), Integer.parseInt(split[2]));
			edges.add(edge);
		}
		
		return edges;
	}
}
