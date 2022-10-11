package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Persona;

public class AgregarPersonaController {
	@FXML
	private ObservableList<Persona> personasLista = FXCollections.observableArrayList();
	@FXML
	private TextField fldNombre;
	@FXML
	private TextField fldApellidos;
	@FXML
	private TextField fldEdad;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	
    @FXML
    void aceptar(ActionEvent event) {
    	
    	Persona p = new Persona("sadasd", "sadasd", 32);
    	
    	personasLista.add(p);
    }

    @FXML
    void cancelar(ActionEvent event) {

    }
    
    public void cogerLista(ObservableList<Persona> personasLista) {
    	this.personasLista = personasLista;
    }
}
