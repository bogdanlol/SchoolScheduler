import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections {
	public void Update(String sql){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			Statement st =con.createStatement();
			
			st.executeUpdate(sql);
			
			
			con.close();
			}
			catch(Exception e){
				System.out.println(e);
				
			}}
	public String Str(String sql){
		String a = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			
		}

		
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
		
			while (rs.next()){
				a =rs.getString(1);
	//	System.out.println(rs.getString(1));
			}
				
				connection.close();
		
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		return a;
	}
	public void LN(String sql){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
		
			while (rs.next()){
	System.out.println(rs.getString(1)+" "+rs.getString(2));
		}
				
				connection.close();
		
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
	public String StrCmbx(String sql){
		String a = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			
		}

		
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
		
			while (rs.next()){
				a =rs.getString(1);
				
	//	System.out.println(rs.getString(1));
			}
				
				connection.close();
		
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		return a;
	}
}
