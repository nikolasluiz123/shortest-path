package br.com.shortest.path.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Tela principal onde pode-se ver o grafo gerado e realizar
 * todas as operações pelo menu.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class GraphVisualization extends Application {

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main-screen.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			stage.setMaximized(true);
			stage.setResizable(false);
			stage.setTitle("Renderize um Grafo a Partir de um TXT");
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
