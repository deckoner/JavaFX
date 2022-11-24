package controller;

import Utiles.AlertasUsuario;
import dao.DeporteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deporte;

public class DeporteAnadirOModificarController {
	
	private Deporte d;
	
	private boolean modificar;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lTitulo;

    @FXML
    private TextField tfNombre;

    @FXML
    void aceptar(ActionEvent event) {
    	
    	if (modificar == true) {
    		if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();

    			DeporteDAO.modificarDeporteBD(tfNombre.getText(), d.getId());
    			        		
    			String txt = "Se ha modificado el deporte correctamente";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");
    			
    	    	stage.close();
    			
    		} else {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    			        		
    			String txt = "El campo nombre no tiene que estar vacio";
    			AlertasUsuario.crearAlertaError(stage, txt, "Error");    			
    		}
    		
    	} else {
    		if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	    	
    			DeporteDAO.anadirDeporteBD(tfNombre.getText());
    			        		
    			String txt = "Se ha añadido el deporte correctamente";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");
    			
    	    	stage.close();
    			
    		} else {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    			        		
    			String txt = "El campo nombre no tiene que estar vacio";
    			AlertasUsuario.crearAlertaError(stage, txt, "Error");    			
    		}	
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	stage.close();
    }
    
    public void setDatos(boolean modificar, Deporte d) {
    	this.modificar = modificar;
    	this.d = d;
    	
    	if (modificar == true) {
    		tfNombre.setText(d.getNombre());
    		lTitulo.setText("Modificar Deporte");
    	}
    }
    
    private boolean validar() {
    	
    	if (tfNombre.getText().equals("")) {
        	return false;

    	} else {
        	return true;

    	}
    }
}