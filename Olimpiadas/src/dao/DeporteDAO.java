package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utiles.ConecxionBD;
import javafx.collections.ObservableList;
import model.Deporte;

public class DeporteDAO {

	public static void rellenarTablaDeporte(ObservableList<Deporte> ListaDeporte) {
		
		ListaDeporte.clear();
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM olimpiadas.Deporte;");
		
			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Creamos las variables que usaremos para crear el objeto y recorrer el resultado
			int id, ultimoRegistro;
			String nombre;
			
			// Nos posicionamos en el ltimo registro para guardar el numero maximo y volvemos al principio
			rs.last();
			ultimoRegistro = rs.getRow() - 1;
			rs.beforeFirst();
			
			for (int i = 0; i <= ultimoRegistro; i++) {
				
				rs.next();
				
				// Cogemos los datos para crear el objeto Olimpiada y lo creamos
				id = rs.getInt("id_deporte");
				nombre = rs.getString("nombre");
				
				Deporte d = new Deporte(nombre, id);
	
				// La agregamos al ObservableList
				ListaDeporte.add(d);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void anadirDeporteBD(String nombre) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "INSERT INTO `olimpiadas`.`Deporte` (`nombre`) VALUES (?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, nombre);
	
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void modificarDeporteBD(String nombre, int id) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "UPDATE `olimpiadas`.`Deporte` SET `nombre` = ? WHERE (`id_deporte` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, nombre);
	        pst.setInt(2, id);
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean eliminarDeporteBD(Deporte p) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "DELETE FROM `olimpiadas`.`Deporte` WHERE (`id_deporte` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, p.getId());

	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			return true;
			
		} catch (SQLException e) {
			
			return false;
		}	
	}
}
