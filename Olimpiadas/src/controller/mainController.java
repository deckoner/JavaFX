package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ConecxionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Deportista;
import model.Evento;
import model.Olimpiada;

public class mainController implements Initializable {
	
	@FXML
	private ObservableList<Olimpiada> ListaOlimpiada = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Deportista> Listadeportista = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Evento> ListaEvento = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Olimpiada, Integer> anioOlimpiada;

    @FXML
    private TableColumn<Olimpiada, String> ciudadOlimpiada;

    @FXML
    private TableColumn<Deportista, String> equipoDeportsta;

    @FXML
    private TableColumn<Deportista, String> generoDeportsta;

    @FXML
    private TableColumn<Evento, Integer> nombreDeporteEvento;

    @FXML
    private TableColumn<Deportista, Integer> idDeportista;

    @FXML
    private TableColumn<Evento, Integer> idEvento;

    @FXML
    private TableColumn<Evento, Integer> nombreOlimEvento;

    @FXML
    private TableColumn<Olimpiada, Integer> idOlimpiada;

    @FXML
    private TableColumn<Deportista, String> nombreDeportista;

    @FXML
    private TableColumn<Evento, String> nombreEvento;

    @FXML
    private TableColumn<Olimpiada, String> nombreOlimpiada;

    @FXML
    private TableColumn<Deportista, Integer> pesoDeportsta;

    @FXML
    private TableColumn<Olimpiada, String> temporadaOlimpiada;
    
    @FXML
    private TableView<Deportista> tablaDeportista;

    @FXML
    private TableView<Evento> tablaEvento;
    
    @FXML
    private TableView<Olimpiada> tablaOlimpiada;

    @FXML
    private Tab tablaEventos;
    
    @FXML
    private Tab tablaOlimpiadas;

    @FXML
    private TextField tfFiltro;

    @FXML
    private MenuItem deportistaAnadir;

    @FXML
    private MenuItem deportistaMasInfo;

    @FXML
    private MenuItem deportistaModificar;
    
    @FXML
    private MenuItem equipoAnadir;
    
    @FXML
    private MenuItem equipoMasInfo;

    @FXML
    private MenuItem equipoModificar;

    @FXML
    private MenuItem eventoAnadir;

    @FXML
    private MenuItem eventoMasInfo;

    @FXML
    private MenuItem eventoModificar;
    
    @FXML
    private MenuItem olimMasInfo;

    @FXML
    private MenuItem olimModificar;

    @FXML
    private MenuItem olimpiadaAnadir;

    @FXML
    private MenuItem participaMasInfo;

    @FXML
    private MenuItem participacionAnadir;

    @FXML
    private MenuItem participacionModificar;
    
    @FXML
    private MenuItem cmDeportistaMasInfo;
    
    @FXML
    private MenuItem cmDeportistaAnadir;

    @FXML
    private MenuItem cmDeportistaModificar;

    @FXML
    private MenuItem cmEventoAnadir;

    @FXML
    private MenuItem cmEventoMasInfo;

    @FXML
    private MenuItem cmEventoModificar;

    @FXML
    private MenuItem cmOlimMasInfo;

    @FXML
    private MenuItem cmOlimModificar;

    @FXML
    private MenuItem cmOlimpiadaAnadir;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Hacemos las consultas a la base de datos para optener la informacion de las tablas principales
		ConecxionBD.rellenarTablaOlimpiada(ListaOlimpiada);
		ConecxionBD.rellenarTablaDeportista(Listadeportista);
		ConecxionBD.rellenarTablaEvento(ListaEvento);
		
		// Tabla Olimpiada
		idOlimpiada.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreOlimpiada.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		anioOlimpiada.setCellValueFactory(new PropertyValueFactory<>("anio"));
		temporadaOlimpiada.setCellValueFactory(new PropertyValueFactory<>("temporada"));
		ciudadOlimpiada.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
	    
		tablaOlimpiada.setItems(ListaOlimpiada);
		
		// Tabla Deportista
		idDeportista.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreDeportista.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		generoDeportsta.setCellValueFactory(new PropertyValueFactory<>("genero"));
		pesoDeportsta.setCellValueFactory(new PropertyValueFactory<>("peso"));
		equipoDeportsta.setCellValueFactory(new PropertyValueFactory<>("equipo"));
	    
		tablaDeportista.setItems(Listadeportista);
		
		// Tabla Evento
		idEvento.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreEvento.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombreOlimEvento.setCellValueFactory(new PropertyValueFactory<>("olimpiada"));
		nombreDeporteEvento.setCellValueFactory(new PropertyValueFactory<>("deporte"));
	    
		tablaEvento.setItems(ListaEvento);
	}
	
    //@FXML
    //void cambioDeportista(ActionEvent event) {

    //}

    //@FXML
    //void cambioEventos(ActionEvent event) {

    //}

    //@FXML
    //void cambioOlimpiadas(ActionEvent event) {

    //}

    @FXML
    void anadirEquipo(ActionEvent event) {

    }

    @FXML
    void anadirEvento(ActionEvent event) {

    }

    @FXML
    void anadirOlim(ActionEvent event) {

    }

    @FXML
    void andirDeportista(ActionEvent event) {
    	
    }

    @FXML
    void andirParticipacion(ActionEvent event) {

    }

    @FXML
    void filtrarAccion(ActionEvent event) {

    }

    @FXML
    void filtrarTecla(KeyEvent event) {

    }

    @FXML
    void masInfoEquipo(ActionEvent event) {

    }

    @FXML
    void masInfoEvento(ActionEvent event) {

    }

    @FXML
    void masInfoOlim(ActionEvent event) {

    }

    @FXML
    void masInfoParticipacion(ActionEvent event) {

    }

    @FXML
    void masInforDeportista(ActionEvent event) {

    }

    @FXML
    void modificarDeportista(ActionEvent event) {

    }

    @FXML
    void modificarEquipo(ActionEvent event) {

    }

    @FXML
    void modificarEvento(ActionEvent event) {

    }

    @FXML
    void modificarOlim(ActionEvent event) {

    }

    @FXML
    void modificarParticipacion(ActionEvent event) {

    }
}