package Ejercicios;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class EjercicioD extends Application  {
	
	private Scene scene;
	
	//Iniciamos una observablelist de personas que añadimos al FX
	private ObservableList<Persona> personasLista = FXCollections.observableArrayList();
	
	//Declaramos el TableView
	private TableView<Persona> table = new TableView<>(personasLista);
	
	//Declaramos los TextFields
	private TextField nombreFld;
	private TextField apellidosFld;
	private TextField edadFld;
	
    public static void main(String[] args) {
        Application.launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
				
        //buttons de la aplicacion
        Button agregarPersonaBtn = new Button("Agregar Persona");
        agregarPersonaBtn.setOnAction(e -> nuevaEscena(stage));
        agregarPersonaBtn.setStyle("-fx-padding: 10 10 10 10;");
	
		//Inicializamos los TextFields
		nombreFld = new TextField();
		apellidosFld = new TextField();
		edadFld = new TextField();
				
		//Creamos una TableView y sus respectivas columnas
	    TableColumn<Persona, String> nombreColum = new TableColumn<>("Nombre");
	    nombreColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	    TableColumn<Persona, String> apellidoColum = new TableColumn<>("Apellidos");
	    apellidoColum.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
	    TableColumn<Persona, Integer> edadColum = new TableColumn<>("Edad");
	    edadColum.setCellValueFactory(new PropertyValueFactory<>("edad"));
	    
	    //Añadimos las columnas al TableView
	    table.getColumns().add(nombreColum);
	    table.getColumns().add(apellidoColum);
	    table.getColumns().add(edadColum);

		//Creacion de VBox
		VBox vBox1 = new VBox(10);
		vBox1.getChildren().addAll(table, agregarPersonaBtn);
		vBox1.setAlignment(Pos.CENTER);
		vBox1.setVgrow(table, Priority.ALWAYS);
		
		scene = new Scene(vBox1);
		stage.setScene(scene);
		stage.setTitle("Populating TableViewsddddddddd");
		stage.show();
	}
	
    private void nuevaEscena(Window win) {
    	
		//Creamos el Stage de la ventana diciendole que es el hijo del anterior
		Stage stage = new Stage();
		stage.initOwner(win);
    	
    	//Creamos las etiquetas
    	Label nombreLbl = new Label("Nombre:");
    	Label apellidosLbl = new Label("Apellidos:");
    	Label edadLbl = new Label("Edad:");
    	
    	//Inicializamos los textFields
    	nombreFld = new TextField();
		apellidosFld = new TextField();
		edadFld = new TextField();
    	
		//Creamos los botones y le asignamos sus funciones
        Button guardarBtn = new Button("Agregar Persona");
        guardarBtn.setOnAction(e -> comprobarInformacion(stage));
        Button cancelarBtn = new Button("Cancelar");
        cancelarBtn.setOnAction(e -> stage.close());
				
        //creamos un FlowPane
        FlowPane flow = new FlowPane();
        flow.getChildren().addAll(guardarBtn, cancelarBtn);
        flow.setHgap(50);
        flow.setAlignment(Pos.CENTER);
        
        //Creamos el gridpanel y ponemos todos los nodos que tendra
        GridPane root = new GridPane();
        root.add(nombreLbl, 0, 0, 1,1);
        root.add(nombreFld, 1, 0, 1, 1);
        root.add(apellidosLbl, 0, 1, 1, 1);
        root.add(apellidosFld, 1, 1, 1, 1);
        root.add(edadLbl, 0, 2, 1, 1);
        root.add(edadFld, 1, 2, 1, 1);
        root.add(flow, 0, 3, 2, 1);   
        
        //Separacion de las columnas
        root.setHgap(20);
        root.setVgap(10);
                
        //Creamos la nueva escena
        Scene newScene = new Scene(root);
        
        stage.setScene(newScene);
        stage.setTitle("Nueva persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
    }
	
	private void crearAlerta(Window win, String txt, boolean error, String titulo) {
	    	
	    	if (error == true) {
	    		
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle(titulo);
	            alert.initOwner(win);
	            alert.setHeaderText(null);
	            alert.setContentText(txt);
	            alert.showAndWait();
	    	} else {
	    		
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle(titulo);
	            alert.initOwner(win);
	            alert.setHeaderText(null);
	            alert.setContentText(txt);
	            alert.showAndWait();
	    	}
	    }
	
	private void AgregarPersona(){
		
		//Creamos un objeto Persona con los datos introducidos por el usuario
		Persona p = new Persona(nombreFld.getText(), apellidosFld.getText(), Integer.parseInt(edadFld.getText()));
		
		//añadimos la persona a la lista
		personasLista.add(p);
	}
	
    private void comprobarInformacion(Window win) {
    	
    	String txt = "";
    	
    	//Comprobamos si la informacion intreoducida es correcta
    	if (esNumero(edadFld.getText()) && (!nombreFld.getText().equals("")) && (!apellidosFld.getText().equals(""))) {
    		
    		//Creamos un objeto Persona con los datos introducidos por el usuario
    		Persona p = new Persona(nombreFld.getText(), apellidosFld.getText(), Integer.parseInt(edadFld.getText()));
    		
    		if (compararPersonas(p)) {
    			
        		txt = "La persona introducida ya existe";
        		
        		crearAlerta(win, txt, true, "Error");
    		} else {
    			
    			AgregarPersona();
    			
        		txt = "Se a introducido correctamente la persona a la tabla";
        		
        		crearAlerta(win, txt, false, "Informacion");
    		}
    	
        //si no es correcta se le avisara al usuario con un panel de error
    	} else {
    		
    		//Creamos el txt para el mensaje de error
    		if (nombreFld.getText().equals("")) {
    			
    			txt += "El campo Nombre no puede estar vacio \n";
    		}
    		
    		if (apellidosFld.getText().equals("")) {
    			
    			txt += "El campo Apellidos no puede estar vacio \n";
    		}
    		
    		if (!esNumero(edadFld.getText())) {
    			
    			txt += "La edad introducida no es un numero \n";
    		}
    		
    		crearAlerta(win, txt, true, "Error");
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

    private boolean compararPersonas(Persona personaAnadir) {
    	
    	Boolean personaRepetida = false;
    	
    	for (Persona personaEnLista : personasLista) {
    		
    		if (personaEnLista.compararPersona(personaAnadir)) {
    			personaRepetida = true;
    		}
		}
    	
    	return personaRepetida;    
    	} 
}
