package br.com.shortest.path.controller;

import java.io.File;

import br.com.shortest.path.enums.EnumVisualizationType;
import br.com.shortest.path.graph.handler.GraphDataTransform;
import br.com.shortest.path.graph.handler.GraphGenerator.GraphGeneratorConfigurator;
import br.com.shortest.path.graph.structure.Edge;
import br.com.shortest.path.graph.structure.Vertex;
import br.com.shortest.path.utils.AlertUtils;
import br.com.shortest.path.view.CalculateSmallerPathVisualization;
import br.com.shortest.path.view.TableValuesVisualization;
import br.com.shortest.path.view.TextFileChooser;
import edu.uci.ics.jung.graph.SparseMultigraph;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controller responsável pela tela inicial
 * 
 * @author Nikolas Luiz Schmitt
 */
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
		if(file == null) {
			AlertUtils.showErrorDialog("Utilize o atalho Control + I para importar um arquivo txt com as distâncias, dessa forma o grafo será gerado você poderá redesenha-lo.");
		} else {
			drawGraph();
		}
	}
	
	private void drawGraph() {
		try {
			GraphDataTransform graphBuilder = new GraphDataTransform(this.file.getAbsolutePath());
			SparseMultigraph<Vertex, Edge> graph = graphBuilder.transformDataInGraph();

			GraphGeneratorConfigurator configurator = new GraphGeneratorConfigurator();
			SwingNode swingNode = configurator.setDimension((int) graphContainer.getWidth(), (int) graphContainer.getHeight())
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
		CalculateSmallerPathVisualization calculateVisualization = new 	CalculateSmallerPathVisualization(this.file);
		calculateVisualization.start(new Stage());
	}

	private Stage getStageFromMenuItemActionEvent(ActionEvent event) {
		return ((Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
	}
}
