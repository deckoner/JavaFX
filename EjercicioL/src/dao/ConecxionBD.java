package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AlertasUsuario;

public class ConecxionBD {
	public static Connection conectar() {
		try {

			Class. forName ("com.mysql.jdbc.Driver");	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/aeropuertos?serverTimezone=CET", "admin","password");

			return con;
			
		} catch (SQLException e) {
			
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			System.out.println("Class no encontrada");
			e.printStackTrace();
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
}
