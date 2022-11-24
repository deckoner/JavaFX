package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utiles.ConecxionBD;
import javafx.collections.ObservableList;
import model.Participacion;

public class ParticipacionDAO {

	public static void rellenarTablaParticipacion(ObservableList<Participacion> ListaParticipacion) {
		
		ListaParticipacion.clear();
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT Participacion.*, Deportista.nombre as DeportistaNombre, Evento.nombre as EventoNombre, Equipo.nombre as EquipoNombre\n"
					+ "FROM Participacion, Deportista, Evento, Equipo WHERE Participacion.id_deportista = Deportista.id_deportista AND \n"
					+ "Participacion.id_evento = Evento.id_evento AND \n"
					+ "Participacion.id_equipo = Equipo.id_equipo;");
		
			//Ejecutamos la consulta
			ResultSet rs;
			rs = pst.executeQuery();
			
			// Creamos las variables que usaremos para crear el objeto y recorrer el resultado
			int idDeportista, idEvento, idEquipo, edad, ultimoRegistro;
			String medalla, deportistaNombre, eventoNombre, equipoNombre;
			
			// Nos posicionamos en el ltimo registro para guardar el numero maximo y volvemos al principio
			rs.last();
			ultimoRegistro = rs.getRow() - 1;
			rs.beforeFirst();
			
			for (int i = 0; i <= ultimoRegistro; i++) {
				
				rs.next();
				
				// Cogemos los datos para crear el objeto Olimpiada y lo creamos
				idDeportista = rs.getInt("id_deportista");
				idEvento = rs.getInt("id_evento");
				idEquipo = rs.getInt("id_equipo");
				edad = rs.getInt("edad");
				medalla = rs.getString("medalla");
				deportistaNombre = rs.getString("DeportistaNombre");
				eventoNombre = rs.getString("EventoNombre");
				equipoNombre = rs.getString("EquipoNombre");
	
				Participacion p = new Participacion(idDeportista, idEvento, idEquipo, edad, medalla, deportistaNombre, eventoNombre, equipoNombre);
	
				// La agregamos al ObservableList
				ListaParticipacion.add(p);
			}
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void anadirParticipacionBD(int idDeportista, int idEvento, int idEquipo, int edad, String medalla) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "INSERT INTO `olimpiadas`.`Participacion` (`id_deportista`, `id_evento`, `id_equipo`, `edad`, `medalla`) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, idDeportista);
	        pst.setInt(2, idEvento);
	        pst.setInt(3, idEquipo);
	        pst.setInt(4, edad);
			pst.setString(5, medalla);
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void modificarParticipacionBD(Participacion p) {
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "UPDATE `olimpiadas`.`Participacion` SET `id_equipo` = ?, `edad` = ?, `medalla` = ? WHERE (`id_deportista` = ?) and (`id_evento` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, p.getIdEquipo());
	        pst.setInt(2, p.getEdad());
	        pst.setString(3, p.getMedalla());
	        pst.setInt(4, p.getIdDeportista());
	        pst.setInt(5, p.getIdEvento());
	        
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean eliminarParticipacionBD(Participacion p) {
		
		Connection con = ConecxionBD.conectar();
	
		try {
	
			//Preparamos la consulta
			String sentencia = "DELETE FROM `olimpiadas`.`Participacion` WHERE (`id_deportista` = ?) and (`id_evento` = ?);";
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(sentencia);
	        pst.setInt(1, p.getIdDeportista());
	        pst.setInt(2, p.getIdEvento());
	
			//Ejecutamos la consulta y cerramos conexión
			pst.executeUpdate();
			pst.close();
			
			return true;
			
		} catch (SQLException e) {
			
			return false;
		}
	}
}
