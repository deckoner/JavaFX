package javafxcsvtableview;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 

public class Prueba extends Application {
 
	private void exportToCSV(ActionEvent evt) {
	    FileChooser chooser = new FileChooser();
	    chooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("CSV file", "*.csv"),
	            new FileChooser.ExtensionFilter("Tab-delimited file", "*.txt"));
	    File file = chooser.showSaveDialog(dataReconciliatorView.getStage());
	    if (file != null) {
	        CSVFormat format = CSVFormat.RFC4180;

	        String outputFormat = chooser.getSelectedExtensionFilter().getDescription();
	        if (outputFormat.equalsIgnoreCase("Tab-delimited file"))
	            format = CSVFormat.TDF;//from   w ww  . j a v a2s  .  c  o  m

	        try {
	            List<List<String>> dataAsTable = getDataAsTable();
	            fillCSVFormat(format, new FileWriter(file), dataAsTable);

	            Alert window = new Alert(Alert.AlertType.CONFIRMATION,
	                    "CSV file '" + file + "' saved with " + (dataAsTable.get(0).size() - 1) + " rows.");
	            window.showAndWait();

	        } catch (IOException e) {
	            Alert window = new Alert(Alert.AlertType.ERROR, "Could not save CSV to '" + file + "': " + e);
	            window.showAndWait();
	        }
	    }
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}