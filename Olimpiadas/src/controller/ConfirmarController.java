package controller;

import Utiles.AlertasUsuario;
import dao.DeporteDAO;
import dao.DeportistaDAO;
import dao.EquipoDAO;
import dao.EventoDAO;
import dao.OlimpiadaDAO;
import dao.ParticipacionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

public class ConfirmarController {
	
	private Object o;
	
	private String nombre;
	
	private int elecion;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Text tTextoInformativo;

    @FXML
    void aceptar(ActionEvent event) {
    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	
    	switch (elecion) {
    	
		case 1:
			if (DeportistaDAO.eliminarDeportistaBD((Deportista) o)) {
    			String txt = "Se a borrado correctamente el deportista";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");
    	    	stage.close();

			} else {
    			String txt = "No se puede eliminar el registro ya que tiene dependencias \nBorre antes todas las participaciones que contengan este deportista";
    			AlertasUsuario.crearAlertaError(stage, txt, "Error");  
    	    	stage.close();

			}
			break;
			
		case 2:
			tTextoInformativo.setText("Esto eliminara la olimpiada: " + nombre + " De nuestra base de datos");
			if (OlimpiadaDAO.eliminarOlimpiadaBD((Olimpiada) o)) {
    			String txt = "Se a borrado correctamente la olimpiada";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");  
    	    	stage.close();

			} else {
    			String txt = "No se puede eliminar el registro ya que tiene dependencias \nBorre antes todos los eventos que contengan esta olimpiada";
    			AlertasUsuario.crearAlertaError(stage, txt, "Error");  
    	    	stage.close();

			}
			break;
			
		case 3:
			tTextoInformativo.setText("Esto eliminara la participacion de: " + nombre + " De nuestra base de datos");
			if (ParticipacionDAO.eliminarParticipacionBD((Participacion) o)) {
    			String txt = "Se a borrado correctamente la participacion";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion"); 
    	    	stage.close();

			}
			break;
			
		case 4:
			tTextoInformativo.setText("Esto eliminara el evento: " + nombre + " De nuestra base de datos");
			if (EventoDAO.eliminarEventoBD((Evento) o)) {
    			String txt = "Se a borrado correctamente el evento";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");  
    	    	stage.close();

			} else {
    			String txt = "No se puede eliminar el registro ya que tiene dependencias \nBorre antes todas las participaciones que contengan este evento";
    			AlertasUsuario.crearAlertaError(stage, txt, "Error");  
    	    	stage.close();

			}
			break;
			
		case 5:
			tTextoInformativo.setText("Esto eliminara la equipo: " + nombre + " De nuestra base de datos");
			if (EquipoDAO.eliminarEquipoBD((Equipo) o)) {
    			String txt = "Se a borrado correctamente la equipo";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");  
    	    	stage.close();

			} else {
    			String txt = "No se puede eliminar el registro ya que tiene dependencias \nBorre antes todos las participaciones que contengan este equipo";
    			AlertasUsuario.crearAlertaError(stage, txt, "Error"); 
    	    	stage.close();

			}
			break;
			
		case 6:
			tTextoInformativo.setText("Esto eliminara el deporte: " + nombre + " De nuestra base de datos");
			if (DeporteDAO.eliminarDeporteBD((Deporte) o)) {
    			String txt = "Se a borrado correctamente el deporte";
    			AlertasUsuario.crearAlertaInformacion(stage, txt, "Informacion");  
    	    	stage.close();

			} else {
    			String txt = "No se puede eliminar el registro ya que tiene dependencias \nBorre antes todos los eventos que contengan este deporte";
    			AlertasUsuario.crearAlertaError(stage, txt, "Error");  
    	    	stage.close();

			}
			break;
			
		default:
			break;
		}
    	
    }

    @FXML
    void cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnAceptar.getScene().getWindow();
    	stage.close();
    	
    }
    
    public void setDatos(int elecion, String nombre, Object o) {
    	this.elecion = elecion;
    	this.nombre = nombre;
    	this.o = o;
    	
		tTextoInformativo.setText(nombre);
    	
    }
}
