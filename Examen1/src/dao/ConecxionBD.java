
package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javafx.collections.ObservableList;
import model.Producto;

public class ConecxionBD {
	public static Connection conectar() {
		Properties config = new Properties();
		InputStream configInput = null;
		
		try {
			// Cargamos el archivo de configuracion
			configInput = new FileInputStream("config.properties");
			config.load(configInput);
			
			config.getProperty("Drive");
			
			// Aqui cargariamos las variables para entrar a la base de datos pero
			// Por alguna razon me da error a usar los strings en vez de texto plano
			// Lo dejo sin comentar para que se vea que en la carga no da errores de ningun tipo
			String base = config.getProperty("Base_de_datos");
			String user = config.getProperty("user");
			String contra = config.getProperty("contra");


			Class. forName (config.getProperty("Drive"));	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/examen1?serverTimezone=CET", "admin","password");
			
			return con;
				
		} catch (SQLException e) {
				
		} catch (ClassNotFoundException e) {
				
		} catch (IOException e) {
			
		}
		
		return null;
	}
	
	public static void rellenarTablaOlimpiada(ObservableList<Producto> ListaProducto) {
				
		Connection con = conectar();

		try {

			//Preparamos la consulta
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM examen1.productos;");
		
			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Creamos las variables que usaremos para crear el objeto y recorrer el resultado
			int ultimoRegistro;
			double precio;
			String codigo, nombre;
			boolean disponible;
			
			// Nos posicionamos en el ltimo registro para guardar el numero maximo y volvemos al principio
			rs.last();
			ultimoRegistro = rs.getRow() - 1;
			rs.beforeFirst();
			
			for (int i = 0; i <= ultimoRegistro; i++) {
				
				rs.next();
				
				// Cogemos los datos para crear el objeto Olimpiada y lo creamos
				codigo = rs.getString("codigo");
				nombre = rs.getString("nombre");
				precio = rs.getDouble("precio");
				disponible = rs.getBoolean("disponible");
				
				Producto p = new Producto(codigo, nombre, precio, disponible);

				// La agregamos al ObservableList
				ListaProducto.add(p);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static Producto crearProducto(String nombre, String codigo, boolean disponible, double precio) {
		
		Connection con = conectar();

		try {
			
			Producto p = new Producto(codigo, nombre, precio, disponible);

			//Preparamos el update
			String sentencia = "INSERT INTO `examen1`.`productos` (`codigo`, `nombre`, `precio`, `disponible`) VALUES (?, ?, ?, ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
			
            pst.setString(1, codigo);
            pst.setString(2, nombre);
            pst.setDouble(3, precio);
            pst.setBoolean(4, disponible);

			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			return p;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public static void eliminarProducto(Producto p, ObservableList<Producto> ListaProducto) {
		
		Connection con = conectar();
		
		try {
			
			//Preparamos el update
			String sentencia = "DELETE FROM `examen1`.`productos` WHERE (`codigo` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
			
            pst.setString(1, p.getCodigo());

			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			ListaProducto.remove(p);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void modificarProducto(Producto p) {
		
		Connection con = conectar();
		
		try {
			
			//Preparamos el update
			String sentencia = "UPDATE `examen1`.`productos` SET `nombre` = ?, `precio` = ?, `disponible` = ? WHERE (`codigo` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
			
            pst.setString(1, p.getNombre());
            pst.setDouble(2, p.getPrecio());
            pst.setBoolean(3, p.isDisponible());
            pst.setString(4, p.getCodigo());


			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static boolean comprobarCodigo(String codigo) {
		
		Connection con = conectar();

		try {

			//Preparamos la consulta
			String sentencia = "SELECT * FROM examen1.productos WHERE codigo = ?;";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
			
			//Preparamos la consulta
            pst.setString(1, codigo);

			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Si ahi un registro en el resultado pasaremos false y si ahi uno true
			if (rs.next() == false) {
				return false;
				
			} else {
				return true;

			}
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
}
