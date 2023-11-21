package br.com.shortest.path.graph.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import br.com.shortest.path.graph.structure.Edge;
import br.com.shortest.path.graph.structure.Vertex;
import br.com.shortest.path.reader.FileBufferedReader;
import edu.uci.ics.jung.graph.SparseMultigraph;

/**
 * Classe responsável por transformar os dados do arquivo
 * em estruturas específicas para realizar as operações.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class GraphDataTransform {

	private FileBufferedReader reader;

	public GraphDataTransform(String fileName) {
		this.reader = new FileBufferedReader(fileName);
	}

	/**
	 * Método que transforma os dados do arquivo em um grafo que
	 * pode ser exibido.
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
	public SparseMultigraph<Vertex, Edge> transformDataInGraph() throws Exception {
		List<String> lines = this.reader.readFile();

		SparseMultigraph<Vertex, Edge> viewGraph = new SparseMultigraph<>();

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
	
	/**
	 * Método que transforma transforma os dados do arquivo
	 * em uma pilha de {@link Edge}. 
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
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
	
	/**
	 * Método que transforma os dados do arquivo em uma 
	 * fila de {@link Edge}.
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
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
	
	/**
	 * Método que transforma os dados do arquivo em uma 
	 * lista de {@link Vertex}.
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
	public List<Vertex> transformDataInVertices() throws Exception {
		List<String> lines = this.reader.readFile();
		List<Vertex> vertices = new ArrayList<>();
		
		for (String line : lines) {
			String[] split = line.split(";");

			if (!vertices.stream().anyMatch(v -> v.getValue().equals(split[0]))) {
				vertices.add(new Vertex(split[0]));
			}
			
			if (!vertices.stream().anyMatch(v -> v.getValue().equals(split[1]))) {
				vertices.add(new Vertex(split[1]));
			}
		}
		
		return vertices;
	}
}
