package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conn.Connections;
import data.Vaittama;

//Yhteyden luominen
public class Dao {
	private static Connection conn;
	public Dao() {
		try {
			conn=Connections.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Sulkeminen
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Väittämien listaaminen
	public static  ArrayList<Vaittama> listAllVaittama() throws SQLException{
		ArrayList<Vaittama> list=new ArrayList<>();
		
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from vaittamat");
			while (RS.next()){
				int id = RS.getInt("id");
				String teksti = RS.getString("teksti");
				Vaittama vaittama=new Vaittama(id, teksti);
				list.add(vaittama);
			}
			
			RS.close();
			return list;
		
	}
	//Väittämän luominen
	public static void createVaittama(Vaittama f) {
		try {
			String sql="insert into vaittamat (teksti) values (?)";
			 PreparedStatement preparedStmt = conn.prepareStatement(sql);
			    preparedStmt.setString (1, f.getTeksti());
			    preparedStmt.execute();
			    conn.close();
			        
		}
		
catch(SQLException e) {
			
		}

}
}
