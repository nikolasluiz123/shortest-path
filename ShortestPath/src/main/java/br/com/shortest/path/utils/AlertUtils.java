package br.com.shortest.path.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Classe utilit�ria par exibir dialogs.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class AlertUtils {

	/**
	 * M�todo para exibir uma dialog de erro.
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
	public static void showErrorDialog(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ocorreu um Erro!");
		alert.setContentText(message);
		alert.showAndWait();
	}
}
