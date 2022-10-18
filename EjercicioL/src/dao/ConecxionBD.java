package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConecxionBD {
	public static Connection conectar() {
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
			
			e.printStackTrace();
		}
		return null;
	}
	
	public static void login(String user, String psw) {
		
		Connection con = conectar();
		Statement s;

		try {
			
			String sentencia = "SELECT * FROM aeropuertos.usuarios WHERE usuarios.usuario = '"+ user +"' AND usuarios.password = '"+psw+"'";
;
			s = con.createStatement();
			ResultSet rs = s.executeQuery (sentencia);
			
			while (rs.next()) {
				
				System.out.println(rs.getString(0));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
