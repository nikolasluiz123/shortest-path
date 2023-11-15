package br.com.shortest.path.view;

import java.io.File;

import br.com.shortest.path.controller.TableValuesController;
import br.com.shortest.path.enums.EnumVisualizationType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableValuesVisualization extends Application {

	private EnumVisualizationType visualizationType;
	private File file;
	
	public TableValuesVisualization(EnumVisualizationType visualizationType, File file) {
		this.visualizationType = visualizationType;
		this.file = file;
	}

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/table-values-screen.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			
			TableValuesController controller = loader.getController();
			
			switch (this.visualizationType) {
			case STACK:
				stage.setTitle("Listagem dos Valores em Forma de Pilha");
				controller.initializeStack(this.file);
				break;

			case QUEUE:
				stage.setTitle("Listagem dos Valores em Forma de Fila");
				controller.initializeQueue(this.file);
				break;
			default:
				throw new IllegalArgumentException("Tipo de visualização inválida ou não tratada");
			}

			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
