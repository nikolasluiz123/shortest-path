package br.com.shortest.path.graph;

import java.util.List;

import br.com.shortest.path.reader.FileBufferedReader;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class GraphDataTransform {

	private FileBufferedReader reader;

	public GraphDataTransform(String fileName) {
		this.reader = new FileBufferedReader(fileName);
	}

	public Graph<Vertex, Edge> transform() throws Exception {
		List<String> lines = reader.readFile();

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
}
