package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utiles.ConecxionBD;
import javafx.collections.ObservableList;
import model.Olimpiada;

public class OlimpiadaDAO {

	public static void rellenarTablaOlimpiada(ObservableList<Olimpiada> ListaOlimpiada) {
		
		ListaOlimpiada.clear();
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM olimpiadas.Olimpiada;");
		
			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Creamos las variables que usaremos para crear el objeto y recorrer el resultado
			int id, anio, ultimoRegistro;
			String nombre, temporada, ciudad;
			
			// Nos posicionamos en el ltimo registro para guardar el numero maximo y volvemos al principio
			rs.last();
			ultimoRegistro = rs.getRow() - 1;
			rs.beforeFirst();
			
			for (int i = 0; i <= ultimoRegistro; i++) {
				
				rs.next();
				
				// Cogemos los datos para crear el objeto Olimpiada y lo creamos
				id = rs.getInt("id_olimpiada");
				nombre = rs.getString("nombre");
				anio = rs.getInt("anio");
				temporada = rs.getString("temporada");
				ciudad = rs.getString("ciudad");
				
				Olimpiada o = new Olimpiada(id, anio, nombre, temporada, ciudad);
	
				// La agregamos al ObservableList
				ListaOlimpiada.add(o);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void modificarOlimpiadaBD(Olimpiada o) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "UPDATE `olimpiadas`.`Olimpiada` SET `nombre` = ?, `anio` = ?, `temporada` = ?, `ciudad` = ? WHERE (`id_olimpiada` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, o.getNombre());
			pst.setInt(2, o.getAnio());
	        pst.setString(3, o.getTemporada());
	        pst.setString(4, o.getCiudad());
	        pst.setInt(5, o.getId());
	
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void anadirOlimpiadaBD(int anio, String nombre, String temporada, String ciudad) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "INSERT INTO `olimpiadas`.`Olimpiada` (`nombre`, `anio`, `temporada`, `ciudad`) VALUES (?, ?, ?, ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, anio);
	        pst.setString(2, nombre);
	        pst.setString(3, temporada);
	        pst.setString(4, ciudad);
	
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean eliminarOlimpiadaBD(Olimpiada o) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "DELETE FROM `olimpiadas`.`Olimpiada` WHERE (`id_olimpiada` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, o.getId());

	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			return true;
			
		} catch (SQLException e) {
			
			return false;
		}
	}
}
