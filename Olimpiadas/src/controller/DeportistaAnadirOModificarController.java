package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Utiles.AlertasUsuario;
import dao.DeportistaDAO;
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
import model.Deportista;

public class DeportistaAnadirOModificarController implements Initializable {
	
	private ObservableList<String> listaGenero = FXCollections.observableArrayList();
	
	private boolean modificar;
	
	private Deportista d;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<String> cbGenero;

    @FXML
    private TextField tfAltura;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPeso;
    
    @FXML
    private Label  tfTitulo;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Establecemos los valores del combobox y dejamos como por defecto la primera opcion
		listaGenero.addAll("M","F");
		cbGenero.setItems(listaGenero);
		cbGenero.getSelectionModel().selectFirst();
	}
	
	private boolean validar() {
		
		boolean peso = true;
		boolean altura = true;
		
		if (!(tfNombre.getText().equals("") && cbGenero.getValue().equals(""))) {			
			if (!tfAltura.getText().equals("")) {
				if(esNumero(tfAltura.getText())) {
					altura = false;

				}
				
			} else {
				altura = false;
				
			}
			
			if (!tfPeso.getText().equals("")) {
				if (esNumero(tfPeso.getText())) {
					peso = false;

				}
				
			} else {
				peso = false;
				
			}
			
			if (peso == true || altura == true) {
				
				String txt = "Los campos alturay peso deven de ser numeros";
				AlertasUsuario.crearAlertaError(btnCancelar.getScene().getWindow(), txt, "Error");
				
				return false;
				
			} else {
				return true;
			}
			
		} else {
			String txt = "Porfavor introduzca los datos en los campos obligatorios";
			AlertasUsuario.crearAlertaError(btnCancelar.getScene().getWindow(), txt, "Error");
			
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
	
	public void setDatos(boolean modificar, Deportista d) {
		
		// Establecemos si se va a editar (verdadero) o añadir (false)
		if (modificar == true) {
			this.modificar = modificar;
			
			tfNombre.setText(d.getNombre());
			tfAltura.setText(d.getAltura()+"");
			tfPeso.setText(d.getPeso()+"");
			cbGenero.getSelectionModel().select(d.getGenero());
			tfTitulo.setText("Modificar Deportista");
			this.d = d;

			
		} else {
			
			this.modificar = false;
		}
	}

    @FXML
    void aceptar(ActionEvent event) {
    	
    	// El primer bloque sera si se ha abierto para añadir Deportista el segundo para modificar
    	if (modificar == false) {
    		if (validar()) {
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();

    			DeportistaDAO.anadirDeportistaBD(tfNombre.getText(), cbGenero.getValue(), tfPeso.getText(), tfAltura.getText());
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha añadido correctamente el deportista", "Informacion");
    			
    	    	stage.close();
    		}
    		
    	} else {
    		if (validar()) {
    		
    	    	Stage stage = (Stage) btnAceptar.getScene().getWindow();

    			DeportistaDAO.modificarDeportistaBD(d);
    			AlertasUsuario.crearAlertaInformacion(stage, "Se ha actualizado correctamente el deportista", "Informacion");
    			
    	    	stage.close();
    		}
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    	
    }
}