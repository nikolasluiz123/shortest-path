package br.com.shortest.path.graph.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.shortest.path.graph.structure.Edge;
import br.com.shortest.path.graph.structure.Vertex;
import edu.uci.ics.jung.graph.SparseMultigraph;

/**
 * Classe responsável por calcular o menor caminho entre dois
 * vértices do grafo.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class GraphSmallerPathCalculator {

	private SparseMultigraph<Vertex, Edge> completeGraph;

	public GraphSmallerPathCalculator(SparseMultigraph<Vertex, Edge> completeGraph) {
		this.completeGraph = completeGraph;
	}

	/**
	 * Método que calcula o menor caminho entre os dois vértices
	 * e retorna um grafo contendo esse caminho calculado.
	 * 
	 * @param start Vértice de início
	 * @param end Vértice de fim
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
	public SparseMultigraph<Vertex, Edge> calculate(Vertex start, Vertex end) {
		Map<Vertex, Integer> distances = new HashMap<>();
		Map<Vertex, Vertex> previousVertices = new HashMap<>();
		Set<Vertex> visited = new HashSet<>();

		for (Vertex vertex : completeGraph.getVertices()) {
			distances.put(vertex, Integer.MAX_VALUE);
		}

		distances.put(start, 0);
		
		while (!visited.contains(end)) {
			Vertex currentVertex = getMinDistanceVertex(distances, visited);
			visited.add(currentVertex);

			List<Edge> edges = completeGraph.getEdges().stream()
													   .filter(e -> e.getStart().equals(currentVertex) || e.getEnd().equals(currentVertex))
													   .collect(Collectors.toList());
			
			for (Edge edge : edges) {
				Vertex neighbor = completeGraph.getOpposite(currentVertex, edge);
				
				if (!visited.contains(neighbor) && 
						distances.get(currentVertex) != Integer.MAX_VALUE && 
						distances.get(currentVertex) + edge.getWeight() < distances.get(neighbor)) {
					
					distances.put(neighbor, distances.get(currentVertex) + edge.getWeight());
					previousVertices.put(neighbor, currentVertex);
				}
			}
		}

		return buildShortestPathGraph(start, end, previousVertices);
	}

	/**
	 * Método que retorna o vértice com a menor distância
	 * que ainda não foi visitado.
	 * 
	 * @param distances Map com as distâncias
	 * @param visited Vértices já visitados
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
	private Vertex getMinDistanceVertex(Map<Vertex, Integer> distances, Set<Vertex> visited) {
	    return distances.entrySet().stream()
						           .filter(entry -> !visited.contains(entry.getKey()))
						           .min(Map.Entry.comparingByValue())
						           .map(Map.Entry::getKey)
						           .orElse(null);
	}

	/**
	 * Método que monta o grafo com o menor caminho para que ele possa
	 * ser desenhado depois.
	 * 
	 * @param start Vértice de início
	 * @param end Vértice de fim
	 * @param previousVertices Vértices anteriores
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
	private SparseMultigraph<Vertex, Edge> buildShortestPathGraph(Vertex start, 
															      Vertex end, 
															      Map<Vertex, Vertex> previousVertices) {
		SparseMultigraph<Vertex, Edge> shortestPathGraph = new SparseMultigraph<>();

		Vertex currentVertex = end;
		
		while (currentVertex != null) {
			Vertex previousVertex = previousVertices.get(currentVertex);
			
			if (previousVertex != null) {
				Edge edge = completeGraph.findEdge(previousVertex, currentVertex);
				shortestPathGraph.addEdge(edge, previousVertex, currentVertex);
			}

			currentVertex = previousVertex;
		}

		return shortestPathGraph;
	}

}
