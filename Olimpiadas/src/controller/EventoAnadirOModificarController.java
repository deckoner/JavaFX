package controller;

import Utiles.AlertasUsuario;
import dao.EventoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deporte;
import model.Evento;
import model.Olimpiada;

public class EventoAnadirOModificarController {
	
	private boolean modificar;
	
	private Evento e;
	
	@FXML
	private ObservableList<Deporte> listaDeporte = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Olimpiada> listaOlimpiada = FXCollections.observableArrayList();

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<Deporte> cbDeporte;

    @FXML
    private ComboBox<Olimpiada> cbOlimpiada;

    @FXML
    private Label lTitulo;

    @FXML
    private TextField tfNombre;

    @FXML
    void aceptar(ActionEvent event) {
    	if (modificar == false) {
        	if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();

    			EventoDAO.anadirEventoBD(tfNombre.getText(), cbDeporte.getValue().getId(), cbOlimpiada.getValue().getId());
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha añadido correctamente el evento", "Informacion");
    			
    	    	stage.close();
        	}
    	} else {
        	if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	    	
    	    	e.setNombre(tfNombre.getText());
    	    	e.setIdDeporte(cbDeporte.getValue().getId());
    	    	e.setIdOlimpiada(cbOlimpiada.getValue().getId());

    	    	EventoDAO.modificarEventoBD(e);
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha modificado correctamente el evento", "Informacion");
    			
    	    	stage.close();
        	}
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	stage.close();
    }
    
    private boolean validar() {
    	
    	if (!(tfNombre.getText().equals("") && cbDeporte.getValue() == null && cbOlimpiada.getValue() == null)) {
    		return true;
    		
    	}
   
		String txt = "Porfavor rellene todos los campos";
		AlertasUsuario.crearAlertaError(btnCancelar.getScene().getWindow(), txt, "Error");
		
		return false;    
	}
    
	public void setDatos(ObservableList<Deporte> listaDeporte, ObservableList<Olimpiada> listaOlimpiada, Evento e, boolean modificar) {				
		this.listaDeporte = listaDeporte;
		this.listaOlimpiada = listaOlimpiada;
		
		// Establecemos los valores del combobox y dejamos como por defecto la primera opcion
		cbDeporte.setItems(this.listaDeporte);
		cbDeporte.getSelectionModel().selectFirst();
		
		cbOlimpiada.setItems(this.listaOlimpiada);
		cbOlimpiada.getSelectionModel().selectFirst();
		
		if (modificar == true) {
			this.modificar = true;
			
			lTitulo.setText("Modificar Evento");
			
			this.e = e;
			
			int idDeporte = 0;
			int idOlimpiada = 0;
			
			for (int i = 0; i < listaDeporte.size(); i++) {
				if (e.getDeporte().equals(listaDeporte.get(i).getNombre())) {
					idDeporte = i;
					
				}
			}
			
			for (int i = 0; i < listaOlimpiada.size(); i++) {
				if (e.getOlimpiada().equals(listaOlimpiada.get(i).getNombre())) {
					idOlimpiada = i;
					
				}
			}
			
			cbDeporte.getSelectionModel().select(idDeporte);
			cbOlimpiada.getSelectionModel().select(idOlimpiada);
			tfNombre.setText(e.getNombre());
			
		} else {
			this.modificar = false;
			
		}
	}
}
