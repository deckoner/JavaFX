package Ejercicios;

import java.util.Arrays;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class EjercicioE extends Application  {
	
	Scene scene;
	
	//Iniciamos una observablelist de personas que añadimos al FX
	ObservableList<Persona> personasLista = FXCollections.observableArrayList();
	
	//Declaramos el TableView
	TableView<Persona> table = new TableView<>(personasLista);
	
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
        agregarPersonaBtn.setOnAction(e -> nuevaEscena());
        
        //buttons de la aplicacion
        Button modificarBtn = new Button("Modificar Persona");
        modificarBtn.setOnAction(e -> nuevaEscenaModificar());
        
        //buttons de la aplicacion
        Button eliminarBtn = new Button("Eliminar Persona");
        eliminarBtn.setOnAction(e -> eliminar());
		
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
		HBox hBox1 = new HBox(10);
		hBox1.getChildren().addAll(agregarPersonaBtn, modificarBtn, eliminarBtn);
		
		//Creacion de VBox
		VBox vBox1 = new VBox(10);
		vBox1.getChildren().addAll(table, hBox1);
		
		scene = new Scene(vBox1);
		stage.setScene(scene);
		stage.setTitle("Populating TableViews");
		stage.show();
	}
	
    private void anadirPersona(Window win) {
    	
    	String txt = "";
    	
    	//Comprobamos si la informacion intreoducida es correcta
    	if (esNumero(edadFld.getText()) && (!nombreFld.getText().equals("")) && (!apellidosFld.getText().equals(""))) {
    		
    		//Creamos un objeto Persona con los datos introducidos por el usuario
    		Persona p = new Persona(nombreFld.getText(), apellidosFld.getText(), Integer.parseInt(edadFld.getText()));
    		
    		if (compararPersonas(p)) {
    			
        		txt = "La persona introducida ya existe";
        		
        		//Creamos una alerta que informe al usuario que se ha introducido correctamente los datos
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.initOwner(win);
                alert.setHeaderText(null);
                alert.setContentText(txt);
                alert.showAndWait();
    		} else {
    			
        		personasLista.add(p);
        		
        		txt = "Se a introducido correctamente la persona a la tabla";
        		
        		//Creamos una alerta que informe al usuario que se ha introducido correctamente los datos
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.initOwner(win);
                alert.setHeaderText(null);
                alert.setContentText(txt);
                alert.showAndWait();
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
    		
    		//Creamos la ventana de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.initOwner(win);
            alert.setHeaderText(null);
            alert.setContentText(txt);
            alert.showAndWait();
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
    	
    	for (Persona personaEnLista : personasLista) {
    		
    		if (personaEnLista.compararPersona(personaAnadir)) {
    			
    			return true;
    		} else {
    			
    			return false;
    		}	
		}
    	
    	return false;
    }

    private void nuevaEscena() {
    	
    	nombreFld = new TextField();
		apellidosFld = new TextField();
		edadFld = new TextField();
    	
        Button guardarBtn = new Button("Agregar Persona");
        
        Button cancelarBtn = new Button("Cancelar");
        cancelarBtn.setOnAction(e -> Platform.exit());
		
        GridPane root = new GridPane();
        
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(guardarBtn, cancelarBtn);

        root.addRow(0, new Label("Nombre:"), nombreFld);
        root.addRow(1, new Label("Apellido:"), apellidosFld);
        root.addRow(2, new Label("Edad:"), edadFld);
        root.addRow(3, hBox);
        
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        
        guardarBtn.setOnAction(e -> anadirPersona(newStage));

        newStage.setScene(newScene);
        newStage.setTitle("Nueva persona");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
        
    }
    
    private void nuevaEscenaModificar() {
    	
		Persona p = table.getSelectionModel().getSelectedItem();
    	
    	nombreFld = new TextField();
		apellidosFld = new TextField();
		edadFld = new TextField();
		
		nombreFld.setText(p.getNombre());
		apellidosFld.setText(p.getApellidos());
		edadFld.setText(String.valueOf(p.getEdad()));
    	
        Button guardarBtn = new Button("Guardar");
        
        Button cancelarBtn = new Button("Cancelar");
        cancelarBtn.setOnAction(e -> Platform.exit());
		
        GridPane root = new GridPane();
        
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(guardarBtn, cancelarBtn);

        root.addRow(0, new Label("Nombre:"), nombreFld);
        root.addRow(1, new Label("Apellido:"), apellidosFld);
        root.addRow(2, new Label("Edad:"), edadFld);
        root.addRow(3, hBox);
        
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        
        guardarBtn.setOnAction(e -> modificar(newStage));

        newStage.setScene(newScene);
        newStage.setTitle("Nueva persona");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
        
    }

    private void eliminar() {	
    	
        TableViewSelectionModel<Persona> tsm = table.getSelectionModel();
        
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);

        Arrays.sort(selectedIndices);

        for(int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i].intValue());
            table.getItems().remove(selectedIndices[i].intValue());
        }

    }
    
    private void modificar(Window win) {
    	
    	if (table.getSelectionModel().getSelectedItem() != null) {
    		
        	String txt = "";
        	
        	//Comprobamos si la informacion intreoducida es correcta
        	if (esNumero(edadFld.getText()) && (!nombreFld.getText().equals("")) && (!apellidosFld.getText().equals(""))) {
        		
        		Persona p = table.getSelectionModel().getSelectedItem();
        		Persona pModificada = new Persona(nombreFld.getText(), apellidosFld.getText(), Integer.parseInt(edadFld.getText()));
        		
        		for (int i = 0; i < personasLista.size(); i++) {
        			if (personasLista.get(i).equals(pModificada)) {
        				
        				txt = "Esta persona es exactemente igual a otra";
        				
                		//Creamos la ventana de error
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.initOwner(win);
                        alert.setHeaderText(null);
                        alert.setContentText(txt);
                        alert.showAndWait();
        			} else {
        				
        				p.setNombre(pModificada.getNombre());
        				p.setApellidos(pModificada.getApellidos());
        				p.setEdad(pModificada.getEdad());
        				
        				table.refresh();
        				
        				txt = "Persona modificada correctamente";
        				
                		//Creamos la ventana de error
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informacion");
                        alert.initOwner(win);
                        alert.setHeaderText(null);
                        alert.setContentText(txt);
                        alert.showAndWait();
        			}
        		}
        		
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
        		
        		//Creamos la ventana de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.initOwner(win);
                alert.setHeaderText(null);
                alert.setContentText(txt);
                alert.showAndWait();
        	}
    	}
    }
}
