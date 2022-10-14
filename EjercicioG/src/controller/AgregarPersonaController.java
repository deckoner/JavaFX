package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.AlertasUsuario;
import model.Persona;

public class AgregarPersonaController {
	
	private ObservableList<Persona> personasLista = FXCollections.observableArrayList();
	
    private boolean modficarPersona;
    
    private Persona personaModificar;
	
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField fldApellidos;

    @FXML
    private TextField fldEdad;

    @FXML
    private TextField fldNombre;

    @FXML
    private FlowPane stage;
    	
    @FXML
    void aceptar(ActionEvent event) {
    	String txt = "";	
    	//Comprobamos si la informacion intreoducida es correcta
    	if (esNumero(fldEdad.getText()) && (!fldNombre.getText().equals("")) && (!fldApellidos.getText().equals(""))) {
        		
    		//Creamos un objeto Persona con los datos introducidos por el usuario
    		Persona p = new Persona(fldNombre.getText(), fldApellidos.getText(), Integer.parseInt(fldEdad.getText()));
    		
    		if (personasLista.contains(p)) {
    			txt = "Ya existe una persona igual";

    			AlertasUsuario.crearAlertaError(btnAceptar.getScene().getWindow(), txt, "Error");
    		} else {
    			
    			if (modficarPersona) {
     				personasLista.remove(personaModificar);
     				personasLista.add(p);
             			
     				txt = "Se a modificado correctamente la persona";
                 		
     				AlertasUsuario.crearAlertaInformacion(btnAceptar.getScene().getWindow(), txt, "Informacion");
    			} else {		
	        			
					personasLista.add(p);
	            		
					txt = "Se a introducido correctamente la persona a la tabla";
	            		
					AlertasUsuario.crearAlertaInformacion(btnAceptar.getScene().getWindow(), txt, "Informacion");
    			}
				Stage stage = (Stage) btnAceptar.getScene().getWindow();
				stage.close();
    		}
    	
    	} else {
    			            	
    		if (fldNombre.getText().equals("")) {
        			
    			txt += "El campo Nombre no puede estar vacio \n";
    		}
        		
    		if (fldApellidos.getText().equals("")) {
        			
    			txt += "El campo Apellidos no puede estar vacio \n";
    		}
        		
    		if (!esNumero(fldEdad.getText())) {
        			
    			txt += "La edad introducida no es un numero \n";
    		}
        		
    		AlertasUsuario.crearAlertaError(btnAceptar.getScene().getWindow(), txt, "Error");
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    public void setDatos(ObservableList<Persona> personasLista, boolean modficarPersona, Persona personaModificar) {
    	this.personasLista = personasLista;
    	this.modficarPersona = modficarPersona;
    	this.personaModificar = personaModificar;
    	
    	
    	if (modficarPersona) {
    		fldNombre.setText(personaModificar.getNombre());
    		fldApellidos.setText(personaModificar.getApellidos());
    		fldEdad.setText(String.valueOf(personaModificar.getEdad()));
    	}
    }
    
    private boolean esNumero(String n) {
    	boolean resultado;

    	try {
    		
    		Integer.parseInt(n);
    		resultado = true;
    	} catch (NumberFormatException excepcion) {
    		
    		resultado = false;
    		}
    	
    	return resultado;	
    }
}
