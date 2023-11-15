package br.com.shortest.path.controller;

import java.io.File;
import java.util.List;

import br.com.shortest.path.graph.Edge;
import br.com.shortest.path.graph.GraphDataTransform;
import br.com.shortest.path.graph.GraphGenerator.GraphGeneratorConfigurator;
import br.com.shortest.path.graph.GraphSmallerPathCalculator;
import br.com.shortest.path.graph.Vertex;
import edu.uci.ics.jung.graph.SparseMultigraph;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;

public class CalculateSmallerPathController {

	@FXML
	private ComboBox<Vertex> comboboxVerticeInicial;
	
	@FXML
	private ComboBox<Vertex> comboboxVerticeFinal;
	
	@FXML
	private Button buttonCalcularMenorCaminho;
	
	@FXML
	private StackPane containerGraphShortestPath;

	private SparseMultigraph<Vertex, Edge> completeGraph;
	
	@FXML
	private void onButtonCalcularClick(ActionEvent event) {
		GraphSmallerPathCalculator calculator = new GraphSmallerPathCalculator(this.completeGraph);
		SparseMultigraph<Vertex, Edge> graphShortestPath = calculator.calculate(this.comboboxVerticeInicial.getValue(), 
																	 			this.comboboxVerticeFinal.getValue());
		
		GraphGeneratorConfigurator configurator = new GraphGeneratorConfigurator();
		SwingNode swingNode = configurator.setDimension(600, 600)
										  .setEdgeStroke(2.0f)
										  .generateWithConfigs(graphShortestPath);
		
		this.containerGraphShortestPath.getChildren().clear();
		this.containerGraphShortestPath.getChildren().add(swingNode);
	}
	
	public void loadScreen(File file) throws Exception {
		GraphDataTransform transformer = new GraphDataTransform(file.getAbsolutePath());
		this.completeGraph = transformer.transformDataInGraph();
		
		List<Vertex> transformDataInVertices = transformer.transformDataInVertices();
		
		for (Vertex vertex : transformDataInVertices) {
			this.comboboxVerticeInicial.getItems().add(vertex);
			this.comboboxVerticeFinal.getItems().add(vertex);
		}
	}
}
