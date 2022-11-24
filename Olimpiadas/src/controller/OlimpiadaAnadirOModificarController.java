package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Utiles.AlertasUsuario;
import dao.OlimpiadaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Olimpiada;

public class OlimpiadaAnadirOModificarController implements Initializable {
	
	private boolean modificar;
	
	private Olimpiada o;
	
	@FXML
	private ObservableList<String> listaTemporada = FXCollections.observableArrayList();
	
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<String> cbTemporada;

    @FXML
    private Label lTitulo;

    @FXML
    private TextField tfAnio;

    @FXML
    private TextField tfCiudad;

    @FXML
    private TextField tfNombre;

    @FXML
    void aceptar(ActionEvent event) {
    	if (modificar == false) {
    		if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();

    			OlimpiadaDAO.anadirOlimpiadaBD(Integer.parseInt(tfAnio.getText()), tfNombre.getText(), cbTemporada.getValue(), tfCiudad.getText());
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha añadido correctamente la olimpiada", "Informacion");
    			
    	    	stage.close();
    		}
    		
    	} else {
    		if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	    	
    	    	o.setNombre(tfNombre.getText());
    	    	o.setCiudad(tfCiudad.getText());
    	    	o.setAnio(Integer.parseInt(tfAnio.getText()));
    	    	o.setTemporada(cbTemporada.getValue());
    	    	
    			OlimpiadaDAO.modificarOlimpiadaBD(o);
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha modificado correctamente la olimpiada", "Informacion");
    			
    	    	stage.close();
    		}
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    public void setDatos(boolean modificar, Olimpiada o) {
    	this.modificar = modificar;
    	this.o = o;
    	
    	if (modificar == true) {
    		lTitulo.setText("Modificar Olimpiada");
    	
	    	tfNombre.setText(o.getNombre());
	    	tfCiudad.setText(o.getCiudad());
	    	tfAnio.setText(String.valueOf(o.getAnio()));
	    	cbTemporada.getSelectionModel().select(o.getTemporada());
    	}
    }
    
    private boolean validar() {
    	if (!(tfNombre.getText().equals("") && tfCiudad.getText().equals("") && esNumero(tfAnio.getText()) && cbTemporada.getValue() == null)) {
    		return true;
    		
    	}
		String txt = "Porfavor introduzca todos los datos";
		AlertasUsuario.crearAlertaError(btnCancelar.getScene().getWindow(), txt, "Error");
    	
    	return false;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listaTemporada.addAll("Summer","Winter");
		cbTemporada.setItems(listaTemporada);
		cbTemporada.getSelectionModel().selectFirst();
	}
}
