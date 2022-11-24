package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utiles.ConecxionBD;
import javafx.collections.ObservableList;
import model.Evento;

public class EventoDAO {

	public static void rellenarTablaEvento(ObservableList<Evento> ListaEvento) {
		
		ListaEvento.clear();
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "SELECT Evento.id_evento, Evento.nombre, Evento.id_olimpiada, Evento.id_deporte, Olimpiada.nombre as nombreOlimpiada, Deporte.nombre as nombreDeporte FROM ((olimpiadas.Evento \n"
					+ "INNER JOIN Olimpiada ON Evento.id_olimpiada = Olimpiada.id_olimpiada)\n"
					+ "INNER JOIN Deporte ON Evento.id_deporte = Deporte.id_deporte);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
		
			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Creamos las variables que usaremos para crear el objeto y recorrer el resultado
			int id_Evento, id_olimpiada, id_deporte, ultimoRegistro;
			String nombre, nombreOlimpiada, nombreDeporte;
			
			// Nos posicionamos en el ltimo registro para guardar el numero maximo y volvemos al principio
			rs.last();
			ultimoRegistro = rs.getRow() - 1;
			rs.beforeFirst();
			
			for (int i = 0; i <= ultimoRegistro; i++) {
				
				rs.next();
				
				// Cogemos los datos para crear el objeto Evento y lo creamos
				id_Evento = rs.getInt("id_Evento");
				nombre = rs.getString("nombre");
				id_olimpiada = rs.getInt("id_olimpiada");
				id_deporte = rs.getInt("id_deporte");
				nombreOlimpiada = rs.getString("nombreOlimpiada");
				nombreDeporte = rs.getString("nombreDeporte");
	
				
				Evento e = new Evento(id_Evento, id_olimpiada, id_deporte, nombre, nombreOlimpiada, nombreDeporte);
	
				// La agregamos al ObservableList
				ListaEvento.add(e);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void anadirEventoBD(String nombre, int idDeporte, int idOlimpiada) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "INSERT INTO `olimpiadas`.`Evento` (`nombre`, `id_olimpiada`, `id_deporte`) VALUES (?, ?, ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, nombre);
	        pst.setInt(2, idOlimpiada);
	        pst.setInt(3, idDeporte);
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void modificarEventoBD(Evento e) {
		Connection con = ConecxionBD.conectar();
		
		try {
	
			//Preparamos la consulta
			String sentencia = "UPDATE `olimpiadas`.`Evento` SET `nombre` = ?, `id_olimpiada` = ?, `id_deporte` = ? WHERE (`id_evento` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, e.getNombre());
	        pst.setInt(2, e.getIdOlimpiada());
	        pst.setInt(3, e.getIdDeporte());
	        pst.setInt(4, e.getId());
	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	}
	
	public static boolean eliminarEventoBD(Evento e) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "DELETE FROM `olimpiadas`.`Evento` WHERE (`id_evento` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, e.getId());

	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			return true;
			
		} catch (SQLException e1) {
			
			return false;
		}
	}
}
