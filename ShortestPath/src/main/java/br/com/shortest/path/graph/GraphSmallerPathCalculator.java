package br.com.shortest.path.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import edu.uci.ics.jung.graph.SparseMultigraph;

public class GraphSmallerPathCalculator {

	private SparseMultigraph<Vertex, Edge> completeGraph;

	public GraphSmallerPathCalculator(SparseMultigraph<Vertex, Edge> completeGraph) {
		this.completeGraph = completeGraph;
	}

	public SparseMultigraph<Vertex, Edge> calculate(Vertex start, Vertex end) {
		Map<Vertex, Integer> distances = new HashMap<>();
		Map<Vertex, Vertex> previousVertices = new HashMap<>();
		Set<Vertex> visited = new HashSet<>();

		for (Vertex vertex : completeGraph.getVertices()) {
			distances.put(vertex, Integer.MAX_VALUE);
		}

		distances.put(start, 0);
		while (!visited.stream().anyMatch(v -> v.equals(end))) {
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

		return buildShortestPathGraph(completeGraph, start, end, previousVertices);
	}

	private Vertex getMinDistanceVertex(Map<Vertex, Integer> distances, Set<Vertex> visited) {
		Vertex minVertex = null;
		int minDistance = Integer.MAX_VALUE;

		for (Map.Entry<Vertex, Integer> entry : distances.entrySet()) {
			Vertex vertex = entry.getKey();
			int distance = entry.getValue();

			if (!visited.contains(vertex) && distance < minDistance) {
				minVertex = vertex;
				minDistance = distance;
			}
		}

		return minVertex;
	}

	private SparseMultigraph<Vertex, Edge> buildShortestPathGraph(SparseMultigraph<Vertex, Edge> originalGraph, 
													   Vertex start, 
													   Vertex end, 
													   Map<Vertex, Vertex> previousVertices) {
		SparseMultigraph<Vertex, Edge> shortestPathGraph = new SparseMultigraph<>();

		Vertex currentVertex = end;
		
		while (currentVertex != null) {
			Vertex previousVertex = previousVertices.get(currentVertex);
			
			if (previousVertex != null) {
				Edge edge = originalGraph.findEdge(previousVertex, currentVertex);
				shortestPathGraph.addEdge(edge, previousVertex, currentVertex);
			}

			currentVertex = previousVertex;
		}

		return shortestPathGraph;
	}

}
