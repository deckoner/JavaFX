package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConecxionBD {
	public static Connection conectar() {;
		try {
			//Properties p = new Properties();
			//p.load(new FileInputStream(new File("configuration.properties")));
			//String password = p.getProperty("password");
			//String usuario = p.getProperty("usuarioDB");
			//String jdbc = p.getProperty("jdbc");
			//String url = jdbc + usuario + password;
			
			//Class. forName ("com.mysql.jdbc.Driver");	
			Class. forName ("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/aeropuertos?serverTimezone=CET", "admin","password");
			
			return con;
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
