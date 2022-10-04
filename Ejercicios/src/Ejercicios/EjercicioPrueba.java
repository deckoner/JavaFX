package Ejercicios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Persona;

public class EjercicioPrueba {
	
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Persona> personTable;
	@FXML
	private TableColumn<Persona, String> nombre;
	@FXML
	private TableColumn<Persona, String> apellidos;

	private ObservableList<Persona> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public EjercicioPrueba() {
		masterData.add(new Persona("Hans", "Muster", 44));
		masterData.add(new Persona("Ruth", "Mueller", 56));
		masterData.add(new Persona("Heinz", "Kurz", 123));
		masterData.add(new Persona("Cornelia", "Meier",23));
		masterData.add(new Persona("Werner", "Meyer", 43));
		masterData.add(new Persona("Lydia", "Kunz",23));
		masterData.add(new Persona("Anna", "Best",12));
		masterData.add(new Persona("Stefan", "Meier",56));
		masterData.add(new Persona("Martin", "Mueller", 89));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Initializes the table columns and sets up sorting and filtering.
	 */
	@FXML
	private void initialize() {
		// 0. Initialize the columns.
		nombre.setCellValueFactory(cellData -> cellData.getValue().propiedadNombre());
		apellidos.setCellValueFactory(cellData -> cellData.getValue().propiedadApellidos());

		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Persona> filteredData = new FilteredList<>(masterData, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (person.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (person.getApellidos().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Persona> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(personTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		personTable.setItems(sortedData);
	}
}