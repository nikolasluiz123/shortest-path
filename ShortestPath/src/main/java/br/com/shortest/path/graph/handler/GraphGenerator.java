package br.com.shortest.path.graph.handler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import br.com.shortest.path.graph.structure.Edge;
import br.com.shortest.path.graph.structure.Vertex;
import br.com.shortest.path.utils.NumberUtils;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import javafx.embed.swing.SwingNode;

/**
 * Classe responsável por desenhar o grafo.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class GraphGenerator {

	private SparseMultigraph<Vertex, Edge> graph;
	private Dimension dimension;
	private BasicStroke edgeStroke;
	
	private GraphGenerator(SparseMultigraph<Vertex, Edge> graph, Dimension dimension, BasicStroke edgeStroke) {
		this.graph = graph;
		this.dimension = dimension;
		this.edgeStroke = edgeStroke;
	}

	/**
	 * Método que gera o grafo e retorna um {@link SwingNode}
	 * contendo ele, dessa forma é possível adicionar como
	 * filho de algum componente de layout.
	 * 
	 * @author Nikolas Luiz Schmitt
	 */
	public SwingNode generateGraph() {
		StaticLayout<Vertex, Edge> layout = new StaticLayout<>(graph);

		List<Integer> heightRandomNumbers = NumberUtils.generateRandomNumbers(graph.getVertices().size(), (int) this.dimension.getHeight() - 50);
		List<Integer> widthRandomNumbers = NumberUtils.generateRandomNumbers(graph.getVertices().size(), (int) this.dimension.getWidth() - 50);
		
		List<Vertex> vertices = new ArrayList<>(graph.getVertices());
		for (int i = 0; i < vertices.size(); i++) {
			layout.setLocation(vertices.get(i), new Point2D.Double(widthRandomNumbers.get(i), heightRandomNumbers.get(i)));
		}

		BasicVisualizationServer<Vertex, Edge> vv = new BasicVisualizationServer<>(layout);
		vv.setPreferredSize(this.dimension);

		vv.getRenderContext().setVertexFillPaintTransformer(i -> Color.GREEN);
		vv.getRenderContext().setEdgeStrokeTransformer(i -> this.edgeStroke);

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(edge -> String.valueOf(edge.getWeight()));

		SwingNode swingNode = new SwingNode();
		swingNode.setContent(vv);
		
		return swingNode;
	}
	
	/**
	 * Classe utilizada para definir as configurações
	 * do gerador de grafos.
	 * 
	 * @author Nikolas Luiz Schmitt
	 */
	public static class GraphGeneratorConfigurator {
		
		private Dimension dimension;
		private BasicStroke edgeStroke;
		
		public GraphGeneratorConfigurator setDimension(int width, int height) {
			this.dimension = new Dimension(width, height);
			return this;
		}
		
		public GraphGeneratorConfigurator setEdgeStroke(float width) {
			this.edgeStroke = new BasicStroke(width);
			return this;
		}
		
		public SwingNode generateWithConfigs(SparseMultigraph<Vertex, Edge> graph) {
			GraphGenerator generator = new GraphGenerator(graph, this.dimension, this.edgeStroke);
			return generator.generateGraph();
		}
	}
	
}
