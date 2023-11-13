package br.com.shortest.path.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.shortest.path.graph.DataGraph;
import br.com.shortest.path.graph.Edge;
import br.com.shortest.path.graph.Vertex;
import br.com.shortest.path.reader.GraphBuilder;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private StackPane graphContainer;

	@FXML
	private void onMenuItemImportarClick(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecione um arquivo de texto");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos de Texto (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		Stage stage = getStageFromMenuItemActionEvent(event);

		File selectedFile = fileChooser.showOpenDialog(stage);

		if (selectedFile != null) {
			try {
				GraphBuilder graphBuilder = new GraphBuilder(selectedFile.getAbsolutePath());
				DataGraph dataGraph = graphBuilder.build();

				Graph<Vertex, Edge> viewGraph = new SparseMultigraph<>();

				for (Vertex v : dataGraph.getVertices()) {
					viewGraph.addVertex(v);
				}

				for (Edge e : dataGraph.getEdges()) {
					viewGraph.addEdge(e, e.getStart(), e.getEnd());
				}

				StaticLayout<Vertex, Edge> layout = new StaticLayout<>(viewGraph);

				List<Integer> randomNumbers = generateRandomNumbers(dataGraph.getVertices().size() * 2);
				List<Vertex> vertices = dataGraph.getVertices();
				for (int i = 0; i < vertices.size(); i++) {
					layout.setLocation(vertices.get(i), new Point2D.Double(randomNumbers.get(i), randomNumbers.get(i + 1)));
				}

				BasicVisualizationServer<Vertex, Edge> vv = new BasicVisualizationServer<>(layout);
				vv.setPreferredSize(new Dimension(600, 600));

				vv.getRenderContext().setVertexFillPaintTransformer(i -> Color.GREEN);
				vv.getRenderContext().setEdgeStrokeTransformer(i -> new BasicStroke(2.0f));

				vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
				vv.getRenderContext().setEdgeLabelTransformer(edge -> String.valueOf(edge.getWeight()));

				SwingNode swingNode = new SwingNode();
				swingNode.setContent(vv);

				this.graphContainer.getChildren().clear();
				this.graphContainer.getChildren().add(swingNode);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private List<Integer> generateRandomNumbers(Integer vertexCount) {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random(50);

        while (numbers.size() < vertexCount) {
            int numeroAleatorio = random.nextInt(600); // Altere o limite conforme necessário
            numbers.add(numeroAleatorio);
        }

        return new ArrayList<>(numbers);
	}

	private Stage getStageFromMenuItemActionEvent(ActionEvent event) {
		return ((Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
	}
}
