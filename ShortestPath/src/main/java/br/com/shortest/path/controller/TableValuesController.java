package br.com.shortest.path.controller;

import java.io.File;
import java.util.Queue;
import java.util.Stack;

import br.com.shortest.path.graph.Edge;
import br.com.shortest.path.graph.GraphDataTransform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableValuesController {

	@FXML
	private TableView<Edge> tableViewValues;

	@FXML
	private TableColumn<Edge, String> columnVerticeInicial;

	@FXML
	private TableColumn<Edge, String> columnVerticeFinal;

	@FXML
	private TableColumn<Edge, String> columnDistancia;
	
	public void initialize() {
		initalizeColumnsFactory();
	}
	
	public void initializeStack(File file) throws Exception {
		GraphDataTransform transform = new GraphDataTransform(file.getAbsolutePath());
		Stack<Edge> stack = transform.transformDataInStackedEdges();
		
		while(!stack.isEmpty()) {
			this.tableViewValues.getItems().add(stack.pop());
		}
	}
	
	public void initializeQueue(File file) throws Exception {
		GraphDataTransform transform = new GraphDataTransform(file.getAbsolutePath());
		Queue<Edge> queue = transform.transformDataInQueuedEdges();
		
		while(!queue.isEmpty()) {
			this.tableViewValues.getItems().add(queue.poll());
		}
	}

	private void initalizeColumnsFactory() {
		this.columnVerticeInicial.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStart().getValue()));
		this.columnVerticeFinal.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEnd().getValue()));
		this.columnDistancia.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getWeight().toString()));
	}

}
