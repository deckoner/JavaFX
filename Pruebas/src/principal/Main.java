package principal;

import java.sql.*;
import com.mysql.jdbc.PreparedStatement;

public class Main {
	public static void main(String[] arg){
		
		try {
			Class. forName ("com.mysql.jdbc.Driver");	
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/aeropuertos", "admin","password");
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("SLECT * FROM aviones whre numero_asientos>?");
			System.out.println("Introduce aisentos");
			pst.setString(1, Consola.leeString());
			ResultSet rs = pst.executeQuery("SELECT * FROM aviones");
			System.out.println("id\tmodelo");
			while (rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
			}
			
			rs.close();
			pst.close();
			con.close();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
