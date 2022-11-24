package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utiles.ConecxionBD;
import javafx.collections.ObservableList;
import model.Deportista;

public class DeportistaDAO {

	public static void modificarDeportistaBD(Deportista d) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "UPDATE `olimpiadas`.`Deportista` SET `nombre` = ?, `sexo` = ?, `peso` = ?, `altura` = ? WHERE (`id_deportista` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, d.getNombre());
	        pst.setString(2, d.getGenero());
	        pst.setInt(5, d.getId());
	        
	        // Comprobamos si peso y altura esta vacio
	        // Si lo estan pasaremos un NULL para la consulta
	        if (d.getPeso() == 0) {
	        	pst.setNull(3, 0);
	        	
	        } else {
	            pst.setInt(3, d.getPeso());
	
	        }
	        
	        if (d.getAltura() == 0) {
	        	pst.setNull(4, 0);
	        	
	        } else {
	        	pst.setInt(4, d.getAltura());
	        	
	        }
	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void anadirDeportistaBD(String nombre, String genero, String peso, String altura) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "INSERT INTO `olimpiadas`.`Deportista` (`nombre`, `sexo`, `peso`, `altura`) VALUES (?, ?, ?, ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setString(1, nombre);
	        pst.setString(2, genero);
	        
	        // Comprobamos si peso y altura esta vacio
	        // Si lo estan pasaremos un NULL para la consulta
	        if (peso.equals("")) {
	        	pst.setNull(3, 0);
	        	
	        } else {
	            pst.setString(3, peso);
	
	        }
	        
	        if (altura.equals("")) {
	        	pst.setNull(4, 0);
	        	
	        } else {
	        	pst.setString(4, altura);
	        	
	        }
	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void rellenarTablaDeportista(ObservableList<Deportista> Listadeportista) {
		
		Listadeportista.clear();
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "SELECT Deportista.*, Equipo.nombre as equipoNombre FROM ((Deportista\n"
					+ "LEFT JOIN Participacion ON Deportista.id_deportista = Participacion.id_deportista)\n"
					+ "LEFT JOIN Equipo ON Participacion.id_equipo = Equipo.id_equipo) ORDER BY Deportista.nombre, Participacion.edad DESC;";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
		
			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Creamos las variables que usaremos para crear el objeto y recorrer el resultado
			int id, peso, altura, ultimoRegistro;
			String sexo, nombre, equipo;
			String nombreAnterior = "";
			
			// Nos posicionamos en el ltimo registro para guardar el numero maximo y volvemos al principio
			rs.last();
			ultimoRegistro = rs.getRow() - 1;
			rs.beforeFirst();
			
			for (int i = 0; i <= ultimoRegistro; i++) {
				
				rs.next();
				
				
				if (!nombreAnterior.equals(rs.getString("nombre"))) {
					// Cogemos los datos para crear el objeto Deportista y lo creamos
					id = rs.getInt("id_deportista");
					nombre = rs.getString("nombre");
					sexo = rs.getString("sexo");
					peso = rs.getInt("peso");
					altura = rs.getInt("altura");
					
					if (rs.getString("equipoNombre") == null) {
						
						equipo = "Sin participaciones";
					} else {
						
						equipo = rs.getString("equipoNombre");
	
					}
					
					nombreAnterior = rs.getString("nombre");
					
					Deportista d = new Deportista(id, peso, altura, nombre, sexo, equipo);
	
					// La agregamos al ObservableList
					Listadeportista.add(d);
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean eliminarDeportistaBD(Deportista d) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "DELETE FROM `olimpiadas`.`Deportista` WHERE (`id_deportista` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, d.getId());

	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			return true;
			
		} catch (SQLException e) {
			
			return false;
		}	
	}
}
