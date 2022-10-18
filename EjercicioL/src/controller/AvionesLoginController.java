package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AlertasUsuario;
import java.io.IOException;
import java.sql.Connection;
import dao.ConecxionBD;
import javafx.event.ActionEvent;

public class AvionesLoginController {
	@FXML
	private TextField tfUsuario;
	@FXML
	private PasswordField pfContrasena;
	@FXML
	private Button btnLogin;

	// Event Listener on Button[#btnLogin].onAction
	@FXML
	public void logearse(ActionEvent event) {
		String userUsuario = tfUsuario.getText();
		String pwdUsuario = pfContrasena.getText();
		
		try {
			
			Connection con = ConecxionBD.conectar("admin", "admin");
			
			//Comparamos las credenciales de acceso
			if (con == null) {
				
				String txt = "El usuario y/o contraseña introducidas no son correctas";
				AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), txt, "Error al iniciar sesion");
			} else {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Aeropuertos.fxml"));
				Parent root = loader.load();
				Scene newScene = new Scene(root);
				Stage newStage = new Stage();
				newStage.setResizable(false);
				newStage.setScene(newScene);
				newStage.setTitle("AVIONES - AEROPUERTOS");
				newStage.show();
				
				//cerramos la ventana de login
				((Stage) btnLogin.getScene().getWindow()).close();			
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), "El archivo de configuracion no existe", "Error al iniciar sesion");
		}
	}
}
