package Ejercicios;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EjercicioA extends Application {
        
    private TextField profesionFld;
    private TextField nHermanosFld;
    
    private RadioButton hombreBtn;
    private RadioButton mujerBtn;
    private RadioButton otroBtn;
    
    private Slider comprasSlider;
    private Slider verTeleSlider;
    private Slider cineSlider;
    
    private CheckBox deporteCbx;
    ObservableList<String> items = FXCollections.observableArrayList("Tenis", "Futbol", "Baloncesto");
    private ListView<String> deportes;
    private ComboBox<String> edadComBox;
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        
        //Labels de la aplicacion
        Label titutloLbl = new Label("Encuesta");
        titutloLbl.getStyleClass().add("titutloLbl");
        Label profesionLbl = new Label("Profesion:");
        Label nHermanosLbl = new Label("N Hermanos:");
        Label edadLbl = new Label("Edad:");
        Label sexoLbl = new Label("Sexo:");
        Label cualLbl = new Label("¿Cual?:");
        Label gradoLbl = new Label("¿Marque del 1 al 10 su grado de aficcion?");
        gradoLbl.getStyleClass().add("gradoLbl");
        Label comprasLbl = new Label("Compras:");
        Label televisionLbl = new Label("Ver television:");
        Label cineLbl = new Label("Ir al cine:");

        //Textfields de la aplicacion
        profesionFld = new TextField();;
        nHermanosFld = new TextField();
        
        //ComBoxs de las aplicaciones
        edadComBox = new ComboBox<>();
        edadComBox.getItems().addAll("Menores de 18", "Entre 18 y 30", "Entre 31 y 50", "Entre 51 y 70", "Mayores de 70");
        edadComBox.getSelectionModel().selectFirst();
        
        //RadioButtons de la aplicacion
        hombreBtn = new RadioButton("Hombre");
        mujerBtn = new RadioButton("Mujer");
        otroBtn = new RadioButton("Otro");
        ToggleGroup grupoGenero = new ToggleGroup();
        //Activamos la primera opcion por defecto
        hombreBtn.setSelected(true);

        
        //CheckBox de la aplicacion
        deporteCbx = new CheckBox("¿Practica algun deporte?");
        deporteCbx.getStyleClass().add("deporteCbx");
        deporteCbx.selectedProperty().addListener(this::changed);
        
        //Sliders de la aplicacion
        comprasSlider = getSlider();
        verTeleSlider = getSlider();
        cineSlider = getSlider();
        
        //buttons de la aplicacion
        Button aceptarBtn = new Button("Aceptar");
        aceptarBtn.setOnAction(e -> comprobacion(stage));
        
        Button cancelarBtn = new Button("Cancelar");
        cancelarBtn.setOnAction(e -> Platform.exit());

        //ListView de la aplicacion
        deportes = new ListView<>(items);
        deportes.setDisable(true);
        deportes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deportes.setPrefSize(50, 70);
 
        //GridPane de la aplicacion
        GridPane root = new GridPane();
        GridPane menuGenero = new GridPane();
        
        //Aliniamos los nodos
        GridPane.setHalignment(titutloLbl, HPos.CENTER);
        GridPane.setHalignment(gradoLbl, HPos.CENTER);
                
        //Hbox de la aplicacion
        VBox vboxPrueba = new VBox(40);
        vboxPrueba.setSpacing(5);
        
        //creamos un FlowPane
        FlowPane flow = new FlowPane();
        flow.getChildren().addAll(aceptarBtn, cancelarBtn);
        
        
        //Orden del Hbox
        vboxPrueba.getChildren().addAll(cualLbl, deportes);
        
        //orden del menuGenero
        menuGenero.add(hombreBtn, 0, 0, 1, 1);
        menuGenero.add(mujerBtn, 1, 0, 1, 1);
        menuGenero.add(otroBtn, 2, 0, 1, 1);
        
        //orde grupo edad
        grupoGenero.getToggles().addAll(hombreBtn, mujerBtn, otroBtn);
        
        //Orden de la interfaz
        root.add(titutloLbl, 0, 0, GridPane.REMAINING, 1);
        root.add(profesionLbl, 0, 1, 1, 1);
        root.add(profesionFld, 1, 1, GridPane.REMAINING, 1);
        root.add(nHermanosLbl, 0, 2, 1, 1);
        root.add(nHermanosFld, 1, 2, 1, 1);
        root.add(edadLbl, 2, 2, 1, 1);
        root.add(edadComBox, 3, 2, 1, 1);
        root.add(sexoLbl, 0, 3, 1, 1);
        root.add(menuGenero, 1, 3, GridPane.REMAINING, 1);
        root.add(deporteCbx, 0, 4, 2, 1);
        root.add(vboxPrueba, 2, 4, GridPane.REMAINING, 1);
        root.add(gradoLbl, 0, 5, GridPane.REMAINING, 1);
        root.add(comprasLbl, 0, 6, 1, 1);
        root.add(comprasSlider, 1, 6, GridPane.REMAINING, 1);
        root.add(televisionLbl, 0, 7, 1, 1);
        root.add(verTeleSlider, 1, 7, GridPane.REMAINING, 1);
        root.add(cineLbl, 0, 8,1, 1);
        root.add(cineSlider, 1, 8, GridPane.REMAINING, 1);
        root.add(flow, 0, 9, GridPane.REMAINING, 1);
        root.setHgap(25);
        root.setVgap(10);
        
        ColumnConstraints cc1 = new ColumnConstraints();
        ColumnConstraints cc2 = new ColumnConstraints();
        ColumnConstraints cc3 = new ColumnConstraints();
        ColumnConstraints cc4 = new ColumnConstraints();
        
        cc4.setHgrow(Priority.ALWAYS);
        
        root.getColumnConstraints().addAll(cc1,cc2,cc3,cc4);

        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ENCUESTA");
        String url = getClass().getResource("/application.css").toString();
        scene.getStylesheets().add(url);
        stage.show();
    }
    private void crearAlerta(Window win, String txt, boolean error) {
    	
    	if (error == true) {
    		
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.initOwner(win);
            alert.setHeaderText(null);
            alert.setContentText(txt);
            alert.showAndWait();
    	} else {
    		
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.initOwner(win);
            alert.setHeaderText(null);
            alert.setContentText(txt);
            alert.showAndWait();
    	}
    }
    
    private void comprobacion(Window win) {
        String txt = "";
        
        if ((profesionFld.getText() == "" || nHermanosFld.getText() == "") ||
                (deporteCbx.isSelected() == true && deportes.getSelectionModel().getSelectedItem() == null)) {
            
            if (profesionFld.getText() == "") {
                txt = "Tienes que introducir un nombre \n";
            }
            
            if (nHermanosFld.getText() == "") {
                
                txt = txt + "El numero de hermanos debe de ser un numero valido \n";
            }
            if (deporteCbx.isSelected() == true && deportes.getSelectionModel().getSelectedItem() == null) {
                
                txt = txt + "Debes de elegir un deporte de la lista \n";
            }
            
            crearAlerta(win, txt, true);
        } else {
            
            if (deporteCbx.isSelected() == true) {
                txt = ("Profesion: " + profesionFld.getText() + "\n" +
                        "N hermanos: " + nHermanosFld.getText() + "\n" +
                        "Edad: " + edadComBox.getValue() + "\n" +
                        "Sexo: " + genero() + "\n" +
                        "Deportes: " + listaDeportes() + "\n" +
                        "Grado de aficcion a las compras: " + String.valueOf(comprasSlider.getValue()) + "\n" +
                        "Grado de aficcion a ver la television: " + String.valueOf(verTeleSlider.getValue()) + "\n" +
                        "Grado de aficcion a ir al cine: " + String.valueOf(cineSlider.getValue()));
                
                crearAlerta(win, txt, false);
            } else {
                
                txt = ("Profesion: " + profesionFld.getText() + "\n" +
                        "N hermanos: " + nHermanosFld.getText() + "\n" +
                        "Edad: " + edadComBox.getValue() + "\n" +
                        "Sexo: " + genero() + "\n" +
                        "Grado de aficcion a las compras: " + String.valueOf(comprasSlider.getValue()) + "\n" +
                        "Grado de aficcion a ver la television: " + String.valueOf(verTeleSlider.getValue()) + "\n" +
                        "Grado de aficcion a ir al cine: " + String.valueOf(cineSlider.getValue()));
                
                crearAlerta(win, txt, false);
            }

        }
    }
    
    private Slider getSlider() {
        Slider slider = new Slider(0.0, 10.0, 0.0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(5);
        return slider;
    }
    
    private void changed(ObservableValue<? extends Boolean> observable,
            Boolean oldValue,
            Boolean newValue) {
    	
                if (deporteCbx.isIndeterminate()) {
                    deportes.setDisable(true);

                } else if (deporteCbx.isSelected()) {
                    deportes.setDisable(false);
                } else {
                    deportes.setDisable(true);
                }
    }
    
    private String genero() {
                
        if (hombreBtn.getText().isEmpty() == false) {
            return    hombreBtn.getText();
        }
        if (mujerBtn.getText().isEmpty() == false) {
            return    mujerBtn.getText();
        }else {
            return    otroBtn.getText();
        }
    }
    
    private String listaDeportes() {
        
        String listaIndice = "";
        ObservableList<String> indice = deportes.getSelectionModel().getSelectedItems();
        
        for (Object o : indice) {
            listaIndice = listaIndice + o + "\n";
        }
        
        listaIndice.indexOf(listaIndice);
        
        return listaIndice;
    }
}
