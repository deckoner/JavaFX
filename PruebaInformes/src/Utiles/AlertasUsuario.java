package Utiles;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertasUsuario {
	
	public static void crearAlertaInformacion(Window win, String txt, String titulo) {
    	
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.initOwner(win);
		alert.setHeaderText(null);
		alert.setContentText(txt);
		alert.showAndWait();
    } 
	
	public static void crearAlertaError(Window win, String txt, String titulo) {
    	
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titulo);
		alert.initOwner(win);
		alert.setHeaderText(null);
		alert.setContentText(txt);
		alert.showAndWait();
    } 

}
