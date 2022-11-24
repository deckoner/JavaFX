package controller;

import Utiles.AlertasUsuario;
import dao.ParticipacionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Participacion;

public class ParticipacionAnadirOModificarController {
	
	private Participacion p;
	
	private boolean modificar;
	
	@FXML
	private ObservableList<Deportista> listaDeportista = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Evento> listaEvento = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Equipo> listaEquipo = FXCollections.observableArrayList();

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<Deportista> cbDeportista;

    @FXML
    private ComboBox<Equipo> cbEquipo;

    @FXML
    private ComboBox<Evento> cbEvento;

    @FXML
    private Label lTitulo;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfMedalla;

    @FXML
    void aceptar(ActionEvent event) {
    	if (modificar == true) {
        	if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
	    		
    	    	String medalla;
    	    	
    	    	if(tfMedalla.getText().equals("")) {
    	    		medalla = "NA";
    	    	} else {
    	    		medalla = tfMedalla.getText();
    	    	}
    	    	
    	    	p.setIdEquipo(cbEquipo.getValue().getId());
    	    	p.setEdad(Integer.parseInt(tfEdad.getText()));
    	    	p.setMedalla(medalla);
    	    	
    			ParticipacionDAO.modificarParticipacionBD(p);
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha modificado correctamente la participacion", "Informacion");
    			
    	    	stage.close();
        	}
    	} else {
        	if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	    	
	    		String medalla;

    	    	if(tfMedalla.getText().equals("")) {
    	    		medalla = "NA";
    	    	} else {
    	    		medalla = tfMedalla.getText();
    	    	}
    	    	
    			ParticipacionDAO.anadirParticipacionBD(cbDeportista.getValue().getId(), cbEvento.getValue().getId(), cbEquipo.getValue().getId(), Integer.parseInt(tfEdad.getText()), medalla);
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha añadido correctamente la participacion", "Informacion");
    			
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
    	if (!(cbDeportista.getValue() == null && cbEquipo.getValue() == null && cbEvento.getValue() == null && tfEdad.getText().equals(""))) {
    		if(esNumero(tfEdad.getText())) {
    			return true;
    			
    		} else {
    			String txt = "El campo edad debe de ser un numero";
    			AlertasUsuario.crearAlertaError(btnAceptar.getScene().getWindow(), txt, "Error");

        		return false;

    		}
    		
    	} else {
    		String txt = "Porfavor completa todos los campos obligatorios";
			AlertasUsuario.crearAlertaError(btnAceptar.getScene().getWindow(), txt, "Error");

    		return false;
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
    
    public void setDatos(ObservableList<Deportista> listaDeportista, ObservableList<Evento> listaEvento, ObservableList<Equipo> listaEquipo, boolean modificar, Participacion p) {
    	this.listaDeportista = listaDeportista;
    	this.listaEvento = listaEvento;
    	this.listaEquipo = listaEquipo;
    	this.modificar = modificar;
    	
		// Establecemos los valores del combobox y dejamos como por defecto la primera opcion
    	cbDeportista.setItems(listaDeportista);
    	cbDeportista.getSelectionModel().selectFirst();
    	
    	cbEquipo.setItems(listaEquipo);
    	cbEquipo.getSelectionModel().selectFirst();

    	cbEvento.setItems(listaEvento);
    	cbEvento.getSelectionModel().selectFirst();
    	
    	if (modificar == true) {
			
			lTitulo.setText("Modificar Participacion");
			
			this.p = p;
			
			int idDeportista = 0;
			int idEquipo = 0;
			int idEvento = 0;
			
			for (int i = 0; i < listaDeportista.size(); i++) {
				if (p.getIdDeportista() == listaDeportista.get(i).getId()) {
					idDeportista = i;
					break;
				}
			}
			
			for (int i = 0; i < listaEquipo.size(); i++) {
				if (p.getIdEquipo() == listaEquipo.get(i).getId()) {
					idEquipo = i;
					break;
				}
			}
			
			for (int i = 0; i < listaEvento.size(); i++) {
				if (p.getIdEvento() == listaEvento.get(i).getId()) {
					idEvento = i;
					break;
				}
			}
			
			cbDeportista.getSelectionModel().select(idDeportista);
			cbEquipo.getSelectionModel().select(idEquipo);
			cbEvento.getSelectionModel().select(idEvento);
			
			tfEdad.setText(String.valueOf(p.getEdad()));
			tfMedalla.setText(p.getMedalla());
			
			cbDeportista.setDisable(true);
			cbEvento.setDisable(true);
    	}
    }
}