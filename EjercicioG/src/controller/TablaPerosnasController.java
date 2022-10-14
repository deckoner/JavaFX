package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AlertasUsuario;
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
	    
		tablePersonas.setItems(personasLista);
	}
	
	@FXML
    void agregarPersona(ActionEvent event) {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NuevaModificarPersona.fxml"));
			Parent root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.initOwner(this.btnAgregarPersona.getScene().getWindow());
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Persona");
			
	        // Pasamos la lista al controlador usando el método implementado
			AgregarPersonaController controlador = (AgregarPersonaController) loader.getController();
			controlador.setDatos(personasLista, false, null);
			
			newStage.showAndWait();
			this.tablePersonas.refresh();
			
	    } catch (IOException e) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText(null);
	        alert.setTitle("Error");
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
	    }
	}

    @FXML
    void eliminarPersona(ActionEvent event) {
    	Persona p = tablePersonas.getSelectionModel().getSelectedItem();    
    	
    	if (p == null) {
    		AlertasUsuario.crearAlertaError(btnEliminarPersona.getScene().getWindow(), "Porfavor Selecione un elemento a borrar", "Error");
    		
    	} else {
            personasLista.remove(p);
            
    	}
    }

    @FXML
    void modificarPersona(ActionEvent event) {
    	
    	Persona p = tablePersonas.getSelectionModel().getSelectedItem();    
    	
    	if (p == null) {
    		AlertasUsuario.crearAlertaError(btnEliminarPersona.getScene().getWindow(), "Porfavor Selecione un elemento a modificar", "Error");
    		
    	} else {
    		
    		try{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NuevaModificarPersona.fxml"));
    			Parent root = loader.load();
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.initOwner(this.btnModificarPersona.getScene().getWindow());
    			newStage.setScene(newScene);
    			newStage.setTitle("Modificar Persona");
    			
    	        // Pasamos la lista al controlador usando el método implementado
    			AgregarPersonaController controlador = (AgregarPersonaController) loader.getController();
    			controlador.setDatos(personasLista, true, p );
    			
    			newStage.showAndWait();
    			this.tablePersonas.refresh();
    			
    	    } catch (IOException e) {
    	        Alert alert = new Alert(Alert.AlertType.ERROR);
    	        alert.setHeaderText(null);
    	        alert.setTitle("Error");
    	        alert.setContentText(e.getMessage());
    	        alert.showAndWait();
    	   }
    	}
    }
    
    @FXML
    void filtroTiempoReal(KeyEvent event) {

    }
    
}
