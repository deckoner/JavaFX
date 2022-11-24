package Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}