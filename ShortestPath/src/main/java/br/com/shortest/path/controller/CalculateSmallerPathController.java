package br.com.shortest.path.controller;

import java.io.File;
import java.util.List;

import br.com.shortest.path.graph.handler.GraphDataTransform;
import br.com.shortest.path.graph.handler.GraphSmallerPathCalculator;
import br.com.shortest.path.graph.handler.GraphGenerator.GraphGeneratorConfigurator;
import br.com.shortest.path.graph.structure.Edge;
import br.com.shortest.path.graph.structure.Vertex;
import br.com.shortest.path.utils.AlertUtils;
import edu.uci.ics.jung.graph.SparseMultigraph;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;

/**
 * Controller responsável pela tela de cálculo do menor caminho
 * de um vértice até outro.
 * 
 * @author Nikolas Luiz Schmitt
 */
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
		Boolean isValid = true;
		
		if(this.comboboxVerticeInicial.getValue() == null || this.comboboxVerticeFinal.getValue() == null) {
			isValid = false;
			AlertUtils.showErrorDialog("Você deve selecionar um vértice inicial e um final.");
		} else if (this.comboboxVerticeInicial.getValue().equals(this.comboboxVerticeFinal.getValue())) {
			isValid = false;
			AlertUtils.showErrorDialog("Os vértices selecionados devem ser diferentes.");
		}
		
		if (isValid) {
			GraphSmallerPathCalculator calculator = new GraphSmallerPathCalculator(this.completeGraph);
			SparseMultigraph<Vertex, Edge> graphShortestPath = calculator.calculate(this.comboboxVerticeInicial.getValue(), 
																					this.comboboxVerticeFinal.getValue());
			
			GraphGeneratorConfigurator configurator = new GraphGeneratorConfigurator();
			SwingNode swingNode = configurator.setDimension((int) containerGraphShortestPath.getWidth(), (int) containerGraphShortestPath.getHeight())
											  .setEdgeStroke(2.0f)
											  .generateWithConfigs(graphShortestPath);
			
			this.containerGraphShortestPath.getChildren().clear();
			this.containerGraphShortestPath.getChildren().add(swingNode);
		}
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
