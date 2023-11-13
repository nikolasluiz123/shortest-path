package br.com.shortest.path.reader;

import java.util.List;

import br.com.shortest.path.graph.Edge;
import br.com.shortest.path.graph.DataGraph;
import br.com.shortest.path.graph.Vertex;

public class GraphBuilder {

	private FileBufferedReader reader;

	public GraphBuilder(String fileName) {
		this.reader = new FileBufferedReader(fileName);
	}
	
	public DataGraph build() throws Exception {
		List<String> lines = reader.readFile();
		
		DataGraph graph = new DataGraph();
		
		for (String line : lines) {
			String[] values = line.split(";");
			
			Vertex a = new Vertex(values[0]);
			Vertex b = new Vertex(values[1]);
			
			Edge edge = new Edge(a, b, Integer.parseInt(values[2]));
			
			graph.addVertex(a);
			graph.addVertex(b);
			graph.addEdge(edge);
		}
		
		return graph;
	}
	
}
