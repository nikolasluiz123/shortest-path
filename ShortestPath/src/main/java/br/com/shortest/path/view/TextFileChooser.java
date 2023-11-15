package br.com.shortest.path.view;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Classe responsável por abrir o windows explorer para
 * selecionar arquivos.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class TextFileChooser {
	
	private FileChooser fileChooser;

	public TextFileChooser() {
		this.fileChooser = new FileChooser();
	}
	
	public File show(Stage stage) {
		this.fileChooser.setTitle("Selecione um arquivo de texto");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos de Texto (*.txt)", "*.txt");
		this.fileChooser.getExtensionFilters().add(extFilter);
		
		return this.fileChooser.showOpenDialog(stage);
	}
	

}
