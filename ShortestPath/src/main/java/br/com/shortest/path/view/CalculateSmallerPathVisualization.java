package br.com.shortest.path.view;

import java.io.File;

import br.com.shortest.path.controller.CalculateSmallerPathController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculateSmallerPathVisualization extends Application {

	private File file;
	
	public CalculateSmallerPathVisualization(File file) {
		this.file = file;
	}

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/calculate-smaller-path-screen.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			
			CalculateSmallerPathController controller = loader.getController();
			controller.loadScreen(this.file);
			
			stage.setTitle("Calcule o Menor Caminho");
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
