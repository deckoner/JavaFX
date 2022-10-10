package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Persona;
import javafx.scene.control.TableColumn;

public class TablaPerosnasController implements Initializable {
	@FXML
	private ObservableList<Persona> personasLista = FXCollections.observableArrayList();
	@FXML
	private TableView<Persona> tablePersonas;
	@FXML
	private TableColumn<Persona, String> c1;
	@FXML
	private TableColumn<Persona, String> c2;
	@FXML
	private TableColumn<Persona, Integer> c3;
	@FXML
	private Button btnAgregarPersona;
	@FXML
	private Button btnModificarPersona;
	@FXML
	private Button btnEliminarPersona;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	    c1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	    c2.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
	    c3.setCellValueFactory(new PropertyValueFactory<>("edad"));
	    
	    Persona p1 = new Persona("paquito", "tree", 45);
		personasLista.add(p1);
	    Persona p2 = new Persona("pedro", "fre", 45);
		personasLista.add(p2);
	    Persona p3 = new Persona("David", "Lomano", 45);
		personasLista.add(p3);
	    Persona p4 = new Persona("Fernando", "De La Rosa", 45);
		personasLista.add(p4);
		tablePersonas.setItems(personasLista);
		System.out.println("Holi");
	}
}
