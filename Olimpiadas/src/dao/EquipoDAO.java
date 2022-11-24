package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utiles.ConecxionBD;
import javafx.collections.ObservableList;
import model.Equipo;

public class EquipoDAO {

	public static void rellenarTablaEquipo(ObservableList<Equipo> ListaEquipo) {
		
		ListaEquipo.clear();
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM olimpiadas.Equipo;");
		
			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Creamos las variables que usaremos para crear el objeto y recorrer el resultado
			int id, ultimoRegistro;
			String nombre, iniciales;
			
			// Nos posicionamos en el ltimo registro para guardar el numero maximo y volvemos al principio
			rs.last();
			ultimoRegistro = rs.getRow() - 1;
			rs.beforeFirst();
			
			for (int i = 0; i <= ultimoRegistro; i++) {
				
				rs.next();
				
				// Cogemos los datos para crear el objeto Olimpiada y lo creamos
				id = rs.getInt("id_equipo");
				nombre = rs.getString("nombre");
				iniciales = rs.getString("iniciales");
				
				Equipo e = new Equipo(id, nombre, iniciales);
	
				// La agregamos al ObservableList
				ListaEquipo.add(e);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void anadirEquipoBD(String nombre, String iniciales) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "INSERT INTO `olimpiadas`.`Equipo` (`nombre`, `iniciales`) VALUES (?, ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, nombre);
	        pst.setString(2, iniciales);
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void modificarEquipoBD(int id, String nombre, String iniciales) {
		Connection con = ConecxionBD.conectar();
		
		try {
	
			//Preparamos la consulta
			String sentencia = "UPDATE `olimpiadas`.`Equipo` SET `nombre` = ?, `iniciales` = ? WHERE (`id_equipo` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, nombre);
	        pst.setString(2, iniciales);
	        pst.setInt(3, id);
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean eliminarEquipoBD(Equipo e) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "DELETE FROM `olimpiadas`.`Equipo` WHERE (`id_equipo` = ?);";
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
