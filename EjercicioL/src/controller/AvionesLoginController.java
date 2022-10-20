package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertasUsuario;
import java.io.IOException;
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
		try {			
			
			System.out.println(tfUsuario.getText());
			System.out.println(tfUsuario.getText().toString());
			
			//Comparamos las credenciales de acceso
			if (ConecxionBD.verificarUsuario(tfUsuario.getText().toString(), pfContrasena.getText().toString())) {
				
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
			} else {
				
				String txt = "El usuario y/o contraseña introducidas no son correctas";
				AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), txt, "Error al iniciar sesion");	
			}
			
		} catch (IOException e) {
			
			AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), "El archivo de configuracion no existe", "Error al iniciar sesion");
		}
	}
}
