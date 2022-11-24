package controller;

import Utiles.AlertasUsuario;
import dao.EquipoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Equipo;

public class EquipoAnadirOModificarController {
	
	private boolean modificar;
	
	private Equipo e;
	
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField tfIniciales;

    @FXML
    private TextField tfNombre;

    @FXML
    private Label tfTitulo;

    @FXML
    void aceptar(ActionEvent event) {
    	
    	if (modificar == true) {
        	if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();

    			EquipoDAO.modificarEquipoBD(e.getId(), tfNombre.getText(), tfIniciales.getText());
    			        		
    			String txt = "Se ha modificado el equipo correctamente";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");
    			
    	    	stage.close();
        		
        		
        	} else {
    			String txt = "Porfavor introduzca los datos en los campos obligatorios";
    			AlertasUsuario.crearAlertaError(btnAceptar.getScene().getWindow(), txt, "Error");
    			
        	}
    		
    	} else {
        	if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();

    			EquipoDAO.anadirEquipoBD(tfNombre.getText(), tfIniciales.getText());
    			        		
    			String txt = "Se ha añadido el equipo correctamente";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");
    			
    	    	stage.close();
        		
        		
        	} else {
    			String txt = "Porfavor introduzca los datos en los campos obligatorios";
    			AlertasUsuario.crearAlertaError(btnAceptar.getScene().getWindow(), txt, "Error");
    			
        	}	
    		
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    public void setDatos(boolean modificar, Equipo e) {
    	this.modificar = modificar;
    	
    	if (modificar == true) {
    		tfTitulo.setText("Modificar Equipo");
    		tfNombre.setText(e.getNombre());
    		tfIniciales.setText(e.getIniciales());
    		this.e = e;
    	}
    }
    
    private boolean validar() {
    	
    	if (!(tfNombre.getText().equals("") && tfIniciales.getText().equals(""))) {
    		return true;
    		
    	}
    	
    	return false;
    }
}