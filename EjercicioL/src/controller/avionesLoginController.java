package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AlertasUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.event.ActionEvent;

public class avionesLoginController {
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
			Properties p = new Properties();
			p.load(new FileInputStream(new File("configuration.properties")));

			String user = p.getProperty("usuario");
			String pwd = p.getProperty("contrasena");
									
			if ((userUsuario.equals(user)) && (pwdUsuario.equals(pwd))) {
				
			} else {
				
				AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), "El usuario y/o contraseña introducidas no son correctas", "Error al iniciar sesion");
			}
			
		} catch (IOException e) {
			
			AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), "El archivo de configuracion no existe", "Error al iniciar sesion");
		}
	}
}
