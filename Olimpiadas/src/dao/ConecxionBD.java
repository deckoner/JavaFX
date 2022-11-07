package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import model.Deportista;
import model.Evento;
import model.Olimpiada;

public class ConecxionBD {
	public static Connection conectar() {
		try {

			Class. forName ("com.mysql.jdbc.Driver");	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/olimpiadas?serverTimezone=CET", "admin","password");
			
			return con;
				
		} catch (SQLException e) {
				
		} catch (ClassNotFoundException e) {
				
		}
		return null;
	}
	
	public static void rellenarTablaOlimpiada(ObservableList<Olimpiada> ListaOlimpiada) {
		
		Connection con = conectar();

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
	
	public static void rellenarTablaDeportista(ObservableList<Deportista> Listadeportista) {
		
		Connection con = conectar();

		try {

			//Preparamos la consulta
			String sentencia = "SELECT Deportista.*, Equipo.nombre as equipoNombre FROM ((Deportista\n"
					+ "INNER JOIN Participacion ON Deportista.id_deportista = Participacion.id_deportista)\n"
					+ "INNER JOIN Equipo ON Participacion.id_equipo = Equipo.id_equipo) ORDER BY Deportista.nombre, Participacion.edad DESC;";
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
	
	public static void rellenarTablaEvento(ObservableList<Evento> ListaEvento) {
		
		Connection con = conectar();

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

}