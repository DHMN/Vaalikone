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
	private Connection conn;
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
	public ArrayList<Vaittama> readAllVaittama() {
		ArrayList<Vaittama> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from vaittamat");
			while (RS.next()){
				Vaittama f=new Vaittama();
				f.setID(RS.getInt("id"));
				f.setTeksti(RS.getString("teksti"));
				list.add(f);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	//Väittämän luominen
	public void CreateVaittama(Vaittama f) {
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
