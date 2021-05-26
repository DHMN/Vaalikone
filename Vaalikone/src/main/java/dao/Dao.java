package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conn.Connections;
import data.Kayttaja;
import data.Vaittama;
import data.Vastausvaihtoehdot;

// Yhteyden luominen
public class Dao {
	private Connection conn;

	public Dao() {
		try {
			conn = Connections.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Sulkeminen
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Väittämien listaaminen
	public ArrayList<Vaittama> listVaittama() {
		ArrayList<Vaittama> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from vaittama");
			while (RS.next()) {
				Vaittama vaittama = new Vaittama();
				vaittama.setId(RS.getInt("id"));
				vaittama.setTeksti(RS.getString("teksti"));
				list.add(vaittama);
			}
			RS.close();
			return list;

		} catch (SQLException e) {
			return null;
		}

	}

	// Väittämän luominen
	public void createVaittama(Vaittama f) {
		try {
			String sql = "insert into vaittama (teksti) values (?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, f.getTeksti());
			preparedStmt.executeUpdate();

		} catch (SQLException e) {

		}
	}

	// PÄIVITETÄÄN TIETTY VÄITTÄMÄ TIETOKANNASSA
	public ArrayList<Vaittama> updateVaittama(Vaittama vaittama) {
		try {
			String sql = "update vaittama set teksti=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println("väittämän teksti DAOn updateVaittama metodiin tultaessa: " + vaittama.getTeksti());
			System.out.println("väittämän teksti DAOn updateVaittama metodiin tultaessa: " + vaittama.getId());
			pstmt.setString(1, vaittama.getTeksti());
			pstmt.setInt(2, vaittama.getId());
			pstmt.executeUpdate();
			
			return listVaittama();

		} catch (SQLException e) {
			return null;
		}
	}

	// LUETAAN TIETTY VÄITTÄMÄ TIETOKANNASTA
	public Vaittama readVaittama(String id) {
		Vaittama vaittama = null;
		try {
			String sql = "select * from vaittama where id=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			ResultSet RS = pstmt.executeQuery();
			while (RS.next()) {

				vaittama = new Vaittama();
				vaittama.setId(RS.getInt("id"));
				vaittama.setTeksti(RS.getString("teksti"));
			}
			RS.close();
			return vaittama;

		} catch (SQLException e) {
			return null;
		}
	}

	// POISTETAAN VÄITTÄMÄ TIETOKANNASTA
	public ArrayList<Vaittama> deleteVaittama(String id) {
		try {
			String sql = "delete from vaittama where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
	        
			return listVaittama();
			
		} catch (SQLException e) {
			return null;
		}
	}


	// VASTAUSVAIHTOEHTOJEN HAKEMINEN TIETOKANNASTA
	public ArrayList<Vastausvaihtoehdot> listVastausvaihtoehdot() {
		ArrayList<Vastausvaihtoehdot> list2 = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from vastausvaihtoehdot");
			while (RS.next()) {
				Vastausvaihtoehdot v = new Vastausvaihtoehdot();
				v.setID(RS.getInt("id"));
				v.setVv(RS.getString("vastausvaihtoehto"));
				list2.add(v);
			}
			RS.close();
			return list2;

		} catch (SQLException e) {
			return null;
		}
	}
	
	// SALASANAN JA SÄHKÖPOSTIN TARKISTUS
	public String checkLogin(Kayttaja kayttaja) throws SQLException, ClassNotFoundException {
		String email = kayttaja.getEmail();
	    String password = kayttaja.getPassword();
	    System.out.println("Tähän tuleva email on: " + email);
	    System.out.println("Tähän tuleva salasana on: " + password);
	 
	    String emailDB = "";
	    String passwordDB = "";
	 
	    try
	    {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select email,password from kayttajat");
	 
	        while(RS.next())
	        {
	            emailDB = RS.getString("email");
	            passwordDB = RS.getString("password");
	    	    System.out.println("Tietokannassa oleva salasana on: " + passwordDB);
	    	    System.out.println("Tietokannassa oleva email on: " + emailDB);
	 
	            if(email.equals(emailDB) && password.equals(passwordDB)) {
	            	RS.close();
	    	    System.out.println("Admin rooli");
					return "Admin_Role";
	            }
	        }

	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("virheellinen kt tai ss");
	    return "Invalid user credentials";
	}
}
