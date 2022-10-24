package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

	@FXML
	public void logearse(ActionEvent event) {		
		login();
	}
	
    @FXML
    void pulsaIntroPwd(KeyEvent event) {

    }

    @FXML
    void pulsaIntroUser(KeyEvent event) {
    	
    }
    
    private void login() {
    				
		//Comparamos las credenciales de acceso
		if (ConecxionBD.verificarUsuario(tfUsuario.getText().toString(), pfContrasena.getText().toString())) {
			
			try {	
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Aeropuertos.fxml"));
				Parent root;
				root = loader.load();
				Scene newScene = new Scene(root);
				Stage newStage = new Stage();
				newStage.setResizable(false);
				newStage.setScene(newScene);
				newStage.setTitle("AVIONES - AEROPUERTOS");
				newStage.show();
				
				//cerramos la ventana de login
				((Stage) btnLogin.getScene().getWindow()).close();
			} catch (IOException e) {
				
				e.printStackTrace();
				
				String txt = "Ocurrio un error y la aplicacion no se pudo cargar";
				AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), txt, "Error fatal");	
			}
		} else {
				
			String txt = "El usuario y/o contraseña introducidas no son correctas";
			AlertasUsuario.crearAlertaError(btnLogin.getScene().getWindow(), txt, "Error al iniciar sesion");	
		}	
    }
}
