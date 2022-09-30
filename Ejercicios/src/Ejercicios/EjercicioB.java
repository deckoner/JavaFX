package Ejercicios;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class EjercicioB extends Application  {
	
	//Iniciamos una observablelist de personas que añadimos al FX
	ObservableList<Persona> personasLista = FXCollections.observableArrayList();
	
	//Declaramos los TextFields
	TextField nombreFld;
	TextField apellidosFld;
	TextField edadFld;
	
    public static void main(String[] args) {
        Application.launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
				
        //buttons de la aplicacion
        Button agregarPersonaBtn = new Button("Agregar Persona");
        agregarPersonaBtn.setOnAction(e -> comprobarInformacion(stage));
		
		//Iniciamos los Labels de la aplicaicon
		Label nombreLbl = new Label("Nombre");
		Label ApellidosLbl = new Label("Apellidos");
		Label edadLbl = new Label("Edad");
		
		//Inicializamos los TextFields
		nombreFld = new TextField();
		apellidosFld = new TextField();
		edadFld = new TextField();
				
		//Creamos una TableView y sus respectivas columnas
		TableView<Persona> table = new TableView<>(personasLista);
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
	   			    		
		//Creacion de GridPane
		GridPane root = new GridPane();
		
		//Creacion de VBox
		VBox vBox = new VBox(10);
		vBox.setPrefSize(200, 200);
		vBox.getChildren().addAll(nombreLbl, nombreFld,
				ApellidosLbl, apellidosFld,
				edadLbl, edadFld, agregarPersonaBtn);

		//Ordenar GridPane
		root.add(vBox, 0, 0, 1, 1);
		root.add(table, 1, 0, 1, 1);
		
        ColumnConstraints cc1 = new ColumnConstraints();
        ColumnConstraints cc2 = new ColumnConstraints();
        RowConstraints rc2 = new RowConstraints();
        
        cc2.setHgrow(Priority.ALWAYS);
        rc2.setVgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(cc1,cc2);
        root.getRowConstraints().addAll(rc2);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Populating TableViews");
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
