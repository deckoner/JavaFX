package controller;

import java.net.URL;
import java.util.ResourceBundle;
import dao.ConecxionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.AlertasUsuario;
import model.Producto;

public class ProductosController implements Initializable {
	
	@FXML
	private ObservableList<Producto> listaProducto = FXCollections.observableArrayList();
	
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSelecionImg;

    @FXML
    private CheckBox cbDisponible;

    @FXML
    private TableColumn<Producto, String> columnaCodigo;

    @FXML
    private TableColumn<Producto, Boolean> columnaDisponible;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    @FXML
    private TableColumn<Producto, Double> columnaPrecio;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPrecio;

    @FXML
    void actualizar(ActionEvent event) {
    	if (tablaProductos.getSelectionModel().getSelectedItem() != null) {
    		// Comprobamos si el precio es correcto
    		if (esDOuble(tfPrecio.getText()) ) {
    			
    			// Comprobaremos si el nombre esta vacio
    			if (!(tfNombre.getText().equals(""))) {
            		// Transformamos el precio en un double
                	Double precio = Double.parseDouble(tfPrecio.getText());
                	
            		tablaProductos.getSelectionModel().getSelectedItem().setNombre(tfNombre.getText());
            		tablaProductos.getSelectionModel().getSelectedItem().setPrecio(precio);;
            		tablaProductos.getSelectionModel().getSelectedItem().setDisponible(cbDisponible.isSelected());;
            		
                	ConecxionBD.modificarProducto(tablaProductos.getSelectionModel().getSelectedItem());
                	
                	// Refrescamos la tabla para que se vea el cambio en los productos listados
                	tablaProductos.refresh();
                	
            		// Creamos una alerta de informacion para informarle que el producto ha sido actualizado
            		String txt = "El producto ha sido actualizado";
            		AlertasUsuario.crearAlertaInformacion(btnLimpiar.getScene().getWindow(), txt, "INFO");
    				
    			} else {
            		// Creamos una alerta de error para informarle que el nombre del producto esta vacion
            		String txt = "El precio debe de tener algun nombre";
            		AlertasUsuario.crearAlertaError(btnLimpiar.getScene().getWindow(), txt, "Error nombre de producto");
    				
    			}
    			
    			
    		} else {
        		// Creamos una alerta de error para informarle que no es un double
        		String txt = "El precio del producto no es un numero real (Double)";
        		AlertasUsuario.crearAlertaError(btnLimpiar.getScene().getWindow(), txt, "Error tipo de precio");
    			
    		}
    	}
    }

    @FXML
    void ayuda(ActionEvent event) {
    	//Creamos el String con la informacion y creamos una alerta de informacion
    	String txt = "Gestion de productos 1.0 \nAutor: Brian Escobar";
    	AlertasUsuario.crearAlertaInformacion(btnLimpiar.getScene().getWindow(), txt, "INFO");
    }

    @FXML
    void crear(ActionEvent event) {
    	
    	if (validarProducto()) {
    		if (ConecxionBD.comprobarCodigo(tfCodigo.getText())) {
        		// Creamos una alerta de error para informar al usuario que ya existe un rpoducto con ese codigo
        		String txt = "Ya existe un producto con ese codigo";
        		AlertasUsuario.crearAlertaError(btnLimpiar.getScene().getWindow(), txt, "Error");
    			
    		} else {
        		// Transformamos el precio en un double
            	Double precio = Double.parseDouble(tfPrecio.getText());
            	
            	Producto p = ConecxionBD.crearProducto(tfNombre.getText(), tfCodigo.getText(), cbDisponible.isSelected(), precio);
            	
            	listaProducto.add(p);
            	
            	tablaProductos.refresh();
            	
    		}
    	}
    }

    @FXML
    void limpiar(ActionEvent event) {
    	// Dejaremos los campos como estaban al abrir la aplicacion
    	btnCrear.setDisable(false);
    	tfCodigo.setDisable(false);
    	cbDisponible.setSelected(false);
    	btnActualizar.setDisable(true);
    	
    	tfCodigo.setText("");
    	tfNombre.setText("");
    	tfPrecio.setText("");
    	
    }

    @FXML
    void slecionImg(ActionEvent event) {

    }
    
    @FXML
    void eliminarProducto(ActionEvent event) {
    	if (tablaProductos.getSelectionModel().getSelectedItem() != null) {
    		// Eliminamos el producto y actualizamos la tabla
    		ConecxionBD.eliminarProducto(tablaProductos.getSelectionModel().getSelectedItem(), listaProducto);
    		
    		// Creamos una alerta de informacion para informar al usuario de que el Producto ha sido borrado
    		String txt = "Se a eliminado correctamente el producto";
    		AlertasUsuario.crearAlertaInformacion(btnLimpiar.getScene().getWindow(), txt, "INFO");
    	}
    }
    
    @FXML
    void verImagen(ActionEvent event) {

    }
    
    @FXML
    void selecionarItemTabla(MouseEvent event) {
    	if (tablaProductos.getSelectionModel().getSelectedItem() != null) {
    		
    		//Primero pasamos toda la informacion del producto a los campos
    		tfCodigo.setText(tablaProductos.getSelectionModel().getSelectedItem().getCodigo());
    		tfNombre.setText(tablaProductos.getSelectionModel().getSelectedItem().getNombre());
    		tfPrecio.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getPrecio()));
    		cbDisponible.setSelected(tablaProductos.getSelectionModel().getSelectedItem().isDisponible());

    		
    		// Desactivamos el campo codigo y el boton crear
    		tfCodigo.setDisable(true);
    		btnCrear.setDisable(true);
    		
    		// activamos el boton modificar
    		btnActualizar.setDisable(false);
    		
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Hacemos las consultas a la base de datos para optener la informacion de las tablas principales
		ConecxionBD.rellenarTablaOlimpiada(listaProducto);

		
		// Tabla Olimpiada
		columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnaDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
		columnaDisponible.setCellFactory(CheckBoxTableCell.forTableColumn(columnaDisponible));

	    
		tablaProductos.setItems(listaProducto);		
	}
	
	private boolean esDOuble(String n) {
		
    	boolean resultado;

    	try {
    		Double.parseDouble(n);
    		resultado = true;
    		
    	} catch (NumberFormatException excepcion) {
    		resultado = false;
    		
    	}
    
    	return resultado;
	}
	
	private boolean validarProducto() {
		
		boolean resultado = true;
		
		String txt = "";
		
		if (tfNombre.getText().equals("")) {
			resultado = false;
			
    		
    		txt = txt + "El nombre no puede estar vacion \n"; 	
		}
		
		if (!esDOuble(tfPrecio.getText())) {
			resultado = false;
			
    		txt = txt + "El precio tiene que ser numero real y no estar vacio \n";
		}
		
		if (!(tfCodigo.getText().length() == 5)) {
			resultado = false;
			
			txt = txt + "El codigo debe tener 5 caracteres";
		}
		
		if (resultado == true) {
			return true;
			
		} else {
    		AlertasUsuario.crearAlertaError(btnLimpiar.getScene().getWindow(), txt, "Error");

			return false;

		}
	}
}

