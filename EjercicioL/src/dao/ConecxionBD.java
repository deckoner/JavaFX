package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.AeropuertoPublico;

public class ConecxionBD {
	public static Connection conectar() {
		try {

			Class. forName ("com.mysql.jdbc.Driver");	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/aeropuertos?serverTimezone=CET", "admin","password");

			return con;
			
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
		return null;
	}
	
	public static boolean verificarUsuario(String user, String pwd) {
				
		Connection con = conectar();
		try {
			
			//Preparamos la consulta
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM aeropuertos.usuarios WHERE usuarios.usuario = ? AND usuarios.password = ?");
			pst.setString(1, user);
			pst.setString(2, pwd);
			
			//Ejecutamos la consulta
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			//Comprobamos que el usuario y contraseña sean corectos
			if (user.equals(rs.getString("usuario")) && pwd.equals(rs.getString("password"))) {
				
				return true;
			} else {
				
				return false;
			}

			
		} catch (SQLException e) {
			
			return false;
		}
	}
	
	public static boolean anadirAvion(int id, int nAsientos, int vMaxima, int activado, int idAeropuerto, String modelo) {
		
		Connection con = conectar();
		
		boolean hecho = false;

		try {
			//Preparamos la consulta
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("INSERT INTO `aeropuertos`.`aviones` (`modelo`, `numero_asientos`, `velocidad_maxima`, `activado`, `id_aeropuerto`) VALUES (?, ?, ?, ?, ?)");
			pst.setInt(1, id);
			pst.setString(2, modelo);			
			pst.setInt(1, nAsientos);
			pst.setInt(2, vMaxima);			
			pst.setInt(1, activado);
			pst.setInt(2, idAeropuerto);
			
			//Ejecutamos la consulta
			pst.executeUpdate();
			
			hecho = true;
			
			return hecho;
		
		
		} catch (SQLException e) {
			
			return hecho;
		}
	}
	
	public static ArrayList<AeropuertoPublico> getAeropuertosPublicos() {
		
		Connection con = conectar();
		
		ArrayList<AeropuertoPublico> listaAerpuertosPublicos = new ArrayList<AeropuertoPublico>();
		
		String  consulta = "SELECT aeropuertos.id, aeropuertos.nombre, direcciones.pais, direcciones.ciudad, direcciones.calle, direcciones.numero, aeropuertos.anio_inauguracion, aeropuertos.capacidad FROM aeropuertos "
				+ "inner JOIN aeropuertos.aeropuertos_publicos ON aeropuertos.id = aeropuertos_publicos.id_aeropuerto "
				+ "inner JOIN aeropuertos.direcciones ON aeropuertos.id = direcciones.id";
		
		try {
			
			PreparedStatement pst=(PreparedStatement) con.prepareStatement(consulta);
			
			//Ejecutamos la consulta
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				AeropuertoPublico a = new AeropuertoPublico(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
				
				listaAerpuertosPublicos.add(a);
				
			}
			
			return listaAerpuertosPublicos;
			
		} catch (SQLException e) {
			
			return listaAerpuertosPublicos;
		}
	}
}
