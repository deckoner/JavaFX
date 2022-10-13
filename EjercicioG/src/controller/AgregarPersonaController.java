package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class AgregarPersonaController {
	
	private ObservableList<Persona> personasLista = FXCollections.observableArrayList();
	
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
    		
    		if (compararPersonas(p)) {
    			
        		txt = "La persona introducida ya existe";
        		
        		crearAlerta(btnAceptar, txt, true, "Error");
    		} else {
    			
        		personasLista.add(p);
        		
        		txt = "Se a introducido correctamente la persona a la tabla";
        		
        		crearAlerta(btnAceptar, txt, false, "Informacion");
    		}
    	
        //si no es correcta se le avisara al usuario con un panel de error
    	} else {
    		
    		//Creamos el txt para el mensaje de error
    		if (fldNombre.getText().equals("")) {
    			
    			txt += "El campo Nombre no puede estar vacio \n";
    		}
    		
    		if (fldApellidos.getText().equals("")) {
    			
    			txt += "El campo Apellidos no puede estar vacio \n";
    		}
    		
    		if (!esNumero(fldEdad.getText())) {
    			
    			txt += "La edad introducida no es un numero \n";
    		}
    		
    		crearAlerta(btnAceptar.getScene().getWindow(), txt, true, "Error");
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    public void setDatos(ObservableList<Persona> personasLista) {
    	this.personasLista = personasLista;
    }
    
    public boolean esNumero(String n) {
    	boolean resultado;

    	try {
    		
    		Integer.parseInt(n);
    		resultado = true;
    	} catch (NumberFormatException excepcion) {
    		
    		resultado = false;
    		}
    	
    	return resultado;	
    }
    
	private void crearAlerta(Window win, String txt, boolean error, String titulo) {
    	
    	if (error == true) {
    		
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.initOwner(win);
            alert.setHeaderText(null);
            alert.setContentText(txt);
            alert.showAndWait();
    	} else {
    		
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(titulo);
            alert.initOwner(win);
            alert.setHeaderText(null);
            alert.setContentText(txt);
            alert.showAndWait();
    	}
    }
}
