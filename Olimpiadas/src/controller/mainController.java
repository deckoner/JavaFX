package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Utiles.AlertasUsuario;
import dao.DeporteDAO;
import dao.DeportistaDAO;
import dao.EquipoDAO;
import dao.EventoDAO;
import dao.OlimpiadaDAO;
import dao.ParticipacionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;


public class mainController implements Initializable {
	
	@FXML
	private ObservableList<Olimpiada> listaOlimpiada = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Deportista> listaDeportista = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Evento> listaEvento = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Equipo> listaEquipo = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Deporte> listaDeporte = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<Participacion> listaParticipacion = FXCollections.observableArrayList();

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
    private TableColumn<Evento, Integer> nombreOlimEvento;

    @FXML
    private TableColumn<Deportista, String> nombreDeportista;

    @FXML
    private TableColumn<Evento, String> nombreEvento;

    @FXML
    private TableColumn<Olimpiada, String> nombreOlimpiada;

    @FXML
    private TableColumn<Deportista, Integer> pesoDeportsta;
    
    @FXML
    private TableColumn<Deportista, Integer> alturaDeportista;

    @FXML
    private TableColumn<Olimpiada, String> temporadaOlimpiada;
    
    @FXML
    private TableColumn<Equipo, String> nombreEquipo;
    
    @FXML
    private TableColumn<Equipo, String> inicialesEquipo;
    
    @FXML
    private TableColumn<Deporte, String> nombreDeporte;
    
    @FXML
    private TableColumn<Participacion, String> nomDeportistaParticipacion;
    
    @FXML
    private TableColumn<Participacion, String> nomEventoParticipacion;
    
    @FXML
    private TableColumn<Participacion, String> nomEquipoParticipacion;
    
    @FXML
    private TableColumn<Participacion, String> edadParticipacion;
    
    @FXML
    private TableColumn<Participacion, String> medallaParticipacion;
    
    @FXML
    private TableView<Deportista> tablaDeportista;

    @FXML
    private TableView<Evento> tablaEvento;
    
    @FXML
    private TableView<Olimpiada> tablaOlimpiada;
    
    @FXML
    private TableView<Equipo> tablaEquipo;
    
    @FXML
    private TableView<Deporte> tablaDeporte;
    
    @FXML
    private TableView<Participacion> tablaParticipacion;

    @FXML
    private Tab tablaEventos;
    
    @FXML
    private Tab tablaOlimpiadas;

    @FXML
    private TextField tfFiltro;
    
    @FXML
    private TabPane tpTablas;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Hacemos las consultas a la base de datos para optener la informacion de las tablas principales
		OlimpiadaDAO.rellenarTablaOlimpiada(listaOlimpiada);
		DeportistaDAO.rellenarTablaDeportista(listaDeportista);
		EventoDAO.rellenarTablaEvento(listaEvento);
		EquipoDAO.rellenarTablaEquipo(listaEquipo);
		DeporteDAO.rellenarTablaDeporte(listaDeporte);
		ParticipacionDAO.rellenarTablaParticipacion(listaParticipacion);
		
		// Tabla Olimpiada
		nombreOlimpiada.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		anioOlimpiada.setCellValueFactory(new PropertyValueFactory<>("anio"));
		temporadaOlimpiada.setCellValueFactory(new PropertyValueFactory<>("temporada"));
		ciudadOlimpiada.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
	    
		tablaOlimpiada.setItems(listaOlimpiada);
		
		// Tabla Deportista
		nombreDeportista.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		generoDeportsta.setCellValueFactory(new PropertyValueFactory<>("genero"));
		alturaDeportista.setCellValueFactory(new PropertyValueFactory<>("altura"));
		pesoDeportsta.setCellValueFactory(new PropertyValueFactory<>("peso"));
		equipoDeportsta.setCellValueFactory(new PropertyValueFactory<>("equipo"));
	    
		tablaDeportista.setItems(listaDeportista);
		
		// Tabla Evento
		nombreEvento.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombreOlimEvento.setCellValueFactory(new PropertyValueFactory<>("olimpiada"));
		nombreDeporteEvento.setCellValueFactory(new PropertyValueFactory<>("deporte"));
	    
		tablaEvento.setItems(listaEvento);
		
		// Tabla Equipos
		nombreEquipo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		inicialesEquipo.setCellValueFactory(new PropertyValueFactory<>("iniciales"));
	    
		tablaEquipo.setItems(listaEquipo);
		
		// Tabla Deporte
		nombreDeporte.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	    
		tablaDeporte.setItems(listaDeporte);
		
		// Tabla Participacion
	    nomDeportistaParticipacion.setCellValueFactory(new PropertyValueFactory<>("nombreDeportista"));
	    nomEventoParticipacion.setCellValueFactory(new PropertyValueFactory<>("nombreEvento"));
	    nomEquipoParticipacion.setCellValueFactory(new PropertyValueFactory<>("nombreEquipo"));
	    edadParticipacion.setCellValueFactory(new PropertyValueFactory<>("edad"));
	    medallaParticipacion.setCellValueFactory(new PropertyValueFactory<>("medalla"));

		tablaParticipacion.setItems(listaParticipacion);
	}
	
    @FXML
    void anadirEquipo(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(4);
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EquipoAnadirOModificar.fxml"));
			Parent root;
			root = loader.load();
			
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Equipo");
			newStage.setResizable(false);
			
	        // Pasamos la lista al controlador usando el método implementado
			EquipoAnadirOModificarController controlador = (EquipoAnadirOModificarController) loader.getController();
			
			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
			Equipo e = null;
			controlador.setDatos(false, e);
			
			newStage.showAndWait();
			
			// Volvemos a cargar la informacion de la tabla equipo
			EquipoDAO.rellenarTablaEquipo(listaEquipo);
	    } catch (IOException e) {
	    	
			e.printStackTrace();
		}
    }
    
    @FXML
    void anadirEvento(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(3);
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EventoAnadirOModificar.fxml"));
			Parent root;
			root = loader.load();
			
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Evento");
			newStage.setResizable(false);
			
	        // Pasamos la lista al controlador usando el método implementado
			EventoAnadirOModificarController controlador = (EventoAnadirOModificarController) loader.getController();
			
			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
			Evento e = null;
			controlador.setDatos(listaDeporte, listaOlimpiada, e, false);
			
			newStage.showAndWait();
			
			// Volvemos a cargar la informacion de la tabla equipo
			EventoDAO.rellenarTablaEvento(listaEvento);
	    } catch (IOException e) {
	    	
			e.printStackTrace();
		}
    }

    @FXML
    void anadirOlim(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(1);
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OlimpiadaAnadirOModificar.fxml"));
			Parent root;
			root = loader.load();
			
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Olimpiada");
			newStage.setResizable(false);
			
	        // Pasamos la lista al controlador usando el método implementado
			OlimpiadaAnadirOModificarController controlador = (OlimpiadaAnadirOModificarController) loader.getController();
			
			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
			Olimpiada o = null;
			controlador.setDatos(false, o);
			
			newStage.showAndWait();
			
			// Volvemos a cargar la informacion de la tabla equipo
			OlimpiadaDAO.rellenarTablaOlimpiada(listaOlimpiada);
	    } catch (IOException e) {
	    	
			e.printStackTrace();
		}
    }

    @FXML
    void andirDeportista(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(0);
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeportistaAnadirOModificar.fxml"));
			Parent root;
			root = loader.load();
			
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Deportista");
			newStage.setResizable(false);
			
	        // Pasamos la lista al controlador usando el método implementado
			DeportistaAnadirOModificarController controlador = (DeportistaAnadirOModificarController) loader.getController();
			
			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
			Deportista d = new Deportista(0, 0, 0, "", "", "");
			controlador.setDatos(false, d);
			
			newStage.showAndWait();
			
			// Volvemos a cargar la informacion de la tabla deportistas
			DeportistaDAO.rellenarTablaDeportista(listaDeportista);
	    } catch (IOException e) {
	    	
			e.printStackTrace();
		}
    }

    @FXML
    void anadirParticipacion(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(2);
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ParticipacionAnadirOModificar.fxml"));
			Parent root;
			root = loader.load();
			
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Participacion");
			newStage.setResizable(false);
			
	        // Pasamos la lista al controlador usando el método implementado
			ParticipacionAnadirOModificarController controlador = (ParticipacionAnadirOModificarController) loader.getController();
			
			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
			Participacion p = null;
			controlador.setDatos(listaDeportista, listaEvento, listaEquipo, false, p);
			
			newStage.showAndWait();
			
			// Volvemos a cargar la informacion de la tabla
			ParticipacionDAO.rellenarTablaParticipacion(listaParticipacion);
	    } catch (IOException e) {
	    	
			e.printStackTrace();
		}
    }
    
    @FXML
    void andirDeporte(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(5);
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeporteAnadirOModificar.fxml"));
			Parent root;
			root = loader.load();
			
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Deporte");
			newStage.setResizable(false);
			
	        // Pasamos la lista al controlador usando el método implementado
			DeporteAnadirOModificarController controlador = (DeporteAnadirOModificarController) loader.getController();
			
			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
			Deporte d = null;
			controlador.setDatos(false, d);
			
			newStage.showAndWait();
			
			// Volvemos a cargar la informacion de la tabla equipo
			DeporteDAO.rellenarTablaDeporte(listaDeporte);
	    } catch (IOException e) {
	    	
			e.printStackTrace();
		}
    }

    @FXML
    void masInfoEvento(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(3);
    	
    	if (tablaEvento.getSelectionModel().getSelectedItem() != null) {
    		
        	Evento o = tablaEvento.getSelectionModel().getSelectedItem();
    		        	
        	String txt = "INFORMACION DEL EVENTO" 
                	+ "ID del evento: " + o.getId() + "\n" 
        			+ "Nombre del evento: " + o.getNombre() + "\n" 
                	+ "ID de olimpiada: " + o.getIdOlimpiada() + "\n" 
                	+ "Nombre de olimpiada: " + o.getOlimpiada() + "\n" 
                	+ "ID de deporte: " + o.getIdDeporte() + "\n"
        			+ "Nombre de deporte: " + o.getDeporte();

        	
    		AlertasUsuario.crearAlertaInformacion(tablaEvento.getScene().getWindow(), txt, "Informacion de evento");
    		
    	} else {
    		String txt = "Porfavor seleciona un evento de la tabla";
    		AlertasUsuario.crearAlertaError(tablaEvento.getScene().getWindow(), txt, "Error al selecionar evento");
    	}
    }

    @FXML
    void masInfoOlim(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(1);
    	
    	if (tablaOlimpiada.getSelectionModel().getSelectedItem() != null) {
    		
        	Olimpiada o = tablaOlimpiada.getSelectionModel().getSelectedItem();
    		        	
        	String txt = "INFORMACION DEL OLIMPIADA" 
                	+ "ID de olimpiada: " + o.getId() + "\n" 
        			+ "Nombre olimpiada: " + o.getNombre() + "\n" 
                	+ "Temporada: " + o.getTemporada() + "\n" 
                	+ "Año de la olimpiada: " + o.getTemporada() + "\n" 
                	+ "Ciudad de la olimpiada: " + o.getCiudad();
        	
    		AlertasUsuario.crearAlertaInformacion(tablaOlimpiada.getScene().getWindow(), txt, "Informacion de olimpiada");
    		
    	} else {
    		String txt = "Porfavor seleciona una olimpiada de la tabla";
    		AlertasUsuario.crearAlertaError(tablaOlimpiada.getScene().getWindow(), txt, "Error al selecionar olimpiada");
    	}
    }

    @FXML
    void masInfoParticipacion(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(2);
    	
    	if (tablaParticipacion.getSelectionModel().getSelectedItem() != null) {
    		
        	Participacion p = tablaParticipacion.getSelectionModel().getSelectedItem();
    		        	
        	String txt = "INFORMACION DEL PARTICIPACION" 
        			+ "ID de deportista: " + p.getIdDeportista() + "\n" 
                	+ "Nombre de deportista: " + p.getNombreDeportista() + "\n" 
                	+ "Edad del deportista:: " + p.getEdad() + "\n" 
                	+ "Medalla conseguida: " + p.getMedalla() + "\n" 
        			+ "ID de evento: " + p.getIdEvento() + "\n"
        			+ "Nombre de evento: " + p.getNombreEvento() + "\n" 
                	+ "ID de equipo: " + p.getIdDeportista() + "\n"
                	+ "Nombre de equipo: " + p.getNombreEquipo();
        	
    		AlertasUsuario.crearAlertaInformacion(tablaParticipacion.getScene().getWindow(), txt, "Informacion de participacion");
    		
    	} else {
    		String txt = "Porfavor seleciona una participacion de la tabla";
    		AlertasUsuario.crearAlertaError(tablaParticipacion.getScene().getWindow(), txt, "Error al selecionar participacion");
    	}
    }

    @FXML
    void masInfoDeportista(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(0);
    	
    	if (tablaDeportista.getSelectionModel().getSelectedItem() != null) {
    		
        	Deportista d = tablaDeportista.getSelectionModel().getSelectedItem();
        	
        	int idEquipo =0;
    		
    		for (int i = 0; i < listaEquipo.size(); i++) {
    			if(d.getEquipo().equals(listaEquipo.get(i).getNombre())) {
    				idEquipo = listaEquipo.get(i).getId();
    				break;
    			}	
			}
    		        	
        	String txt = "INFORMACION DEL DEPORTISTA" 
        			+ "ID: " + d.getId() + "\n" 
        			+ "Nombre: " + d.getNombre() + "\n"
                	+ "Genero: " + d.getGenero() + "\n"
        			+ "Altura: " + d.getAltura() + "\n"
                	+ "Peso: " + d.getPeso() + "\n"
                    + "Equipo: " + d.getEquipo() + "\n"
        			+ "ID de equipo: " + idEquipo;
        	
    		AlertasUsuario.crearAlertaInformacion(tablaDeportista.getScene().getWindow(), txt, "Informacion de deportista");
    		
    	} else {
    		String txt = "Porfavor seleciona un deportista de la tabla";
    		AlertasUsuario.crearAlertaError(tablaDeportista.getScene().getWindow(), txt, "Error al selecionar deportista");
    	}
    }

    @FXML
    void modificarDeportista(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(0);
    	
    	if (tablaDeportista.getSelectionModel().getSelectedItem() != null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeportistaAnadirOModificar.fxml"));
    			Parent root;
    			root = loader.load();
    			
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.setScene(newScene);
    			newStage.setTitle("Modificar Deportista");
    			newStage.setResizable(false);
    			
    	        // Pasamos la lista al controlador usando el método implementado
    			DeportistaAnadirOModificarController controlador = (DeportistaAnadirOModificarController) loader.getController();
    			Deportista d = tablaDeportista.getSelectionModel().getSelectedItem();
    			controlador.setDatos(true, d);
    			
    			newStage.showAndWait();
    			
    			// Volvemos a cargar la informacion de la tabla equipo
    			DeportistaDAO.rellenarTablaDeportista(listaDeportista);
    	    } catch (IOException e) {
    	    	
    			e.printStackTrace();
    		}
    	} else {
    		String txt = "Porfavor seleciona un deportista de la tabla";
    		AlertasUsuario.crearAlertaError(tablaDeportista.getScene().getWindow(), txt, "Error al selecionar deportista");
    		
    	}
    }
    
    @FXML
    void modificarEquipo(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(4);
    	
    	if (tablaEquipo.getSelectionModel().getSelectedItem() != null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EquipoAnadirOModificar.fxml"));
    			Parent root;
    			root = loader.load();
    			
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.setScene(newScene);
    			newStage.setTitle("Modificar Equipo");
    			newStage.setResizable(false);

    	        // Pasamos la lista al controlador usando el método implementado
    			EquipoAnadirOModificarController controlador = (EquipoAnadirOModificarController) loader.getController();
    			Equipo e = tablaEquipo.getSelectionModel().getSelectedItem();
    			controlador.setDatos(true, e);
    			
    			newStage.showAndWait();
    			
    			// Volvemos a cargar la informacion de la tabla equipo
    			EquipoDAO.rellenarTablaEquipo(listaEquipo);
    	    } catch (IOException e) {
    	    	
    			e.printStackTrace();
    		}
    	} else {
    		String txt = "Porfavor seleciona un equipo de la tabla";
    		AlertasUsuario.crearAlertaError(tablaDeportista.getScene().getWindow(), txt, "Error al selecionar deportista");
    		
    	}
    	
    }
    
    @FXML
    void modificarDeporte(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(5);
    	
    	if (tablaDeporte.getSelectionModel().getSelectedItem() != null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeporteAnadirOModificar.fxml"));
    			Parent root;
    			root = loader.load();
    			
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.setScene(newScene);
    			newStage.setTitle("Modificar Deporte");
    			newStage.setResizable(false);
    			
    	        // Pasamos la lista al controlador usando el método implementado
    			DeporteAnadirOModificarController controlador = (DeporteAnadirOModificarController) loader.getController();
    			
    			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
    			Deporte d = tablaDeporte.getSelectionModel().getSelectedItem();
    			controlador.setDatos(true, d);
    			
    			newStage.showAndWait();
    			
    			// Volvemos a cargar la informacion de la tabla equipo
    			DeporteDAO.rellenarTablaDeporte(listaDeporte);
    	    } catch (IOException e) {
    	    	
    			e.printStackTrace();
    		}
    		
    	} else {
    		String txt = "Porfavor seleciona un deporte de la tabla";
    		AlertasUsuario.crearAlertaError(tablaDeportista.getScene().getWindow(), txt, "Error al selecionar el deporte");
    		
    	}
    }
    
    @FXML
    void modificarOlim(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(1);
    	
    	if (tablaOlimpiada.getSelectionModel().getSelectedItem() != null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OlimpiadaAnadirOModificar.fxml"));
    			Parent root;
    			root = loader.load();
    			
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.setScene(newScene);
    			newStage.setTitle("Modificar Olimpiada");
    			newStage.setResizable(false);
    			
    	        // Pasamos la lista al controlador usando el método implementado
    			OlimpiadaAnadirOModificarController controlador = (OlimpiadaAnadirOModificarController) loader.getController();
    			
    			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
    			Olimpiada o = tablaOlimpiada.getSelectionModel().getSelectedItem();
    			controlador.setDatos(true, o);
    			
    			newStage.showAndWait();
    			
    			// Volvemos a cargar la informacion de la tabla equipo
    			OlimpiadaDAO.rellenarTablaOlimpiada(listaOlimpiada);
    	    } catch (IOException e) {
    	    	
    			e.printStackTrace();
    		}
    		
    	} else {
    		String txt = "Porfavor seleciona una olimpiada de la tabla";
    		AlertasUsuario.crearAlertaError(tablaDeportista.getScene().getWindow(), txt, "Error al selecionar al olimpiada");
    		
    	}
    }
    
    @FXML
    void modificarParticipacion(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(2);
    	
    	if (tablaParticipacion.getSelectionModel().getSelectedItem() != null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ParticipacionAnadirOModificar.fxml"));
    			Parent root;
    			root = loader.load();
    			
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.setScene(newScene);
    			newStage.setTitle("Modificar Participacion");
    			newStage.setResizable(false);
    			
    	        // Pasamos la lista al controlador usando el método implementado
    			ParticipacionAnadirOModificarController controlador = (ParticipacionAnadirOModificarController) loader.getController();
    			
    			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
    			Participacion p = tablaParticipacion.getSelectionModel().getSelectedItem();
    			controlador.setDatos(listaDeportista, listaEvento, listaEquipo, true, p);
    			
    			newStage.showAndWait();
    			
    			// Volvemos a cargar la informacion de la tabla equipo
    			ParticipacionDAO.rellenarTablaParticipacion(listaParticipacion);
    	    } catch (IOException e) {
    	    	
    			e.printStackTrace();
    		}
    		
    	} else {
    		String txt = "Porfavor seleciona una olimpiada de la tabla";
    		AlertasUsuario.crearAlertaError(tablaParticipacion.getScene().getWindow(), txt, "Error al selecionar al olimpiada");
    		
    	}
    }
    
    @FXML
    void modificarEvento(ActionEvent event) {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(3);
    	
    	if (tablaEvento.getSelectionModel().getSelectedItem() != null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EventoAnadirOModificar.fxml"));
    			Parent root;
    			root = loader.load();
    			
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.setScene(newScene);
    			newStage.setTitle("Modificar Evento");
    			newStage.setResizable(false);
    			
    	        // Pasamos la lista al controlador usando el método implementado
    			EventoAnadirOModificarController controlador = (EventoAnadirOModificarController) loader.getController();
    			
    			//Creamos un deportista vacio ya que para añadir uno a la base de datos no vamos a utilizar el objeto
    			Evento e = tablaEvento.getSelectionModel().getSelectedItem();
    			controlador.setDatos(listaDeporte, listaOlimpiada, e, true);
    			
    			newStage.showAndWait();
    			
    			// Volvemos a cargar la informacion de la tabla equipo
    			ParticipacionDAO.rellenarTablaParticipacion(listaParticipacion);
    	    } catch (IOException e) {
    	    	
    			e.printStackTrace();
    		}
    		
    	} else {
    		String txt = "Porfavor seleciona un evento de la tabla";
    		AlertasUsuario.crearAlertaError(tablaEvento.getScene().getWindow(), txt, "Error al selecionar el evento");
    		
    	}
    }
    
    @FXML
    void eliminarDeportista() {
    	// Movemos al usuario al tab de la accion
    	tpTablas.getSelectionModel().select(0);
    	
    	if (tablaDeportista.getSelectionModel().getSelectedItem() != null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Confirmacion.fxml"));
    			Parent root;
    			root = loader.load();
    			
    			Scene newScene = new Scene(root);
    			Stage newStage = new Stage();
    			newStage.initModality(Modality.APPLICATION_MODAL);
    			newStage.setScene(newScene);
    			newStage.setTitle("Eliminar Deportista");
    			newStage.setResizable(false);
    			
    	        // Pasamos la lista al controlador usando el método implementado
    			ConfirmarController controlador = (ConfirmarController) loader.getController();
    			Deportista d = tablaDeportista.getSelectionModel().getSelectedItem();
    			String txt = "Esto eliminara el deportista: " + d.getNombre() + " De nuestra base de datos";
    			controlador.setDatos(1, txt, d);
    			
    			newStage.showAndWait();
    			
    			// Volvemos a cargar la informacion de la tabla equipo
    			DeportistaDAO.rellenarTablaDeportista(listaDeportista);
    	    } catch (IOException e) {
    	    	
    			e.printStackTrace();
    		}
    	} else {
    		String txt = "Porfavor seleciona un deportista de la tabla";
    		AlertasUsuario.crearAlertaError(tablaDeportista.getScene().getWindow(), txt, "Error al selecionar deportista");
    		
    	}
    }
    

    @FXML
    void eliminarDeporte(ActionEvent event) {

    }

    @FXML
    void eliminarEquipo(ActionEvent event) {

    }

    @FXML
    void eliminarEvento(ActionEvent event) {

    }

    @FXML
    void eliminarOlimpiada(ActionEvent event) {

    }

    @FXML
    void eliminarParticipacion(ActionEvent event) {

    }
}