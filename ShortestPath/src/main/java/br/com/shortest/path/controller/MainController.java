package br.com.shortest.path.controller;

import java.io.File;

import br.com.shortest.path.TextFileChooser;
import br.com.shortest.path.enums.EnumVisualizationType;
import br.com.shortest.path.graph.Edge;
import br.com.shortest.path.graph.GraphDataTransform;
import br.com.shortest.path.graph.GraphGenerator.GraphGeneratorConfigurator;
import br.com.shortest.path.graph.Vertex;
import br.com.shortest.path.view.TableValuesVisualization;
import edu.uci.ics.jung.graph.Graph;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private StackPane graphContainer;
	
	private File file;

	@FXML
	private void onMenuItemImportarClick(ActionEvent event) {
		Stage stage = getStageFromMenuItemActionEvent(event);
		
		TextFileChooser fileChooser = new TextFileChooser();
		this.file = fileChooser.show(stage);

		if (this.file != null) {
			drawGraph();
		}
	}

	@FXML
	private void onMenuItemRedesenharClick(ActionEvent event) {
		if (this.file != null) {
			drawGraph();
		}
	}
	
	private void drawGraph() {
		try {
			GraphDataTransform graphBuilder = new GraphDataTransform(this.file.getAbsolutePath());
			Graph<Vertex, Edge> graph = graphBuilder.transformDataInGraph();

			GraphGeneratorConfigurator configurator = new GraphGeneratorConfigurator();
			SwingNode swingNode = configurator.setDimension(700, 700)
											  .setEdgeStroke(2.0f)
											  .generateWithConfigs(graph);
			
			this.graphContainer.getChildren().clear();
			this.graphContainer.getChildren().add(swingNode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onMenuItemVisualizarPilhaClick(ActionEvent event) {
		TableValuesVisualization tableVisualization = new TableValuesVisualization(EnumVisualizationType.STACK, this.file);
		tableVisualization.start(new Stage());
	}
	
	@FXML
	private void onMenuItemVisualizarFilaClick(ActionEvent event) {
		TableValuesVisualization tableVisualization = new TableValuesVisualization(EnumVisualizationType.QUEUE, this.file);
		tableVisualization.start(new Stage());
	}
	
	@FXML
	private void onMenuItemCalcularMenorCaminhoClick(ActionEvent event) {
		
	}

	private Stage getStageFromMenuItemActionEvent(ActionEvent event) {
		return ((Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
	}
}
