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
			conn = Connections.getConnection();
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

	// Väittämien listaaminen
	public ArrayList<Vaittama> listVaittama() {
		ArrayList<Vaittama> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from vaittamat");
			while (RS.next()) {
				Vaittama vaittama = new Vaittama();
				vaittama.setID(RS.getInt("id"));
				vaittama.setTeksti(RS.getString("teksti"));
				list.add(vaittama);
			}
			return list;

		} catch (SQLException e) {
			return null;
		}

	}

	// Väittämän luominen
	public void createVaittama(Vaittama f) {
		try {
			String sql = "insert into vaittamat (teksti) values (?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, f.getTeksti());
			preparedStmt.execute();
			conn.close();

		}

		catch (SQLException e) {

		}

	}

	// PÄIVITETÄÄN JOTAIN KALAA TIETOKANNASSA
	public ArrayList<Vaittama> updateVaittama(Vaittama f) {
		try {
			// TÄSSÄ PITÄÄ KÄYTTÄÄ PREPARESTATEMENTTIA KOSKA LAUSE SISÄLTÄÄ SYÖTETTYJÄ
			// MUUTTUJIA.
			// ELI PELKKÄ STATEMENT EI RIITTÄÄ
			String sql = "update vaittamat set teksti=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// STRING SGL LAUSEEN ENSIMMÄISEN (1) KYSYMYSMERKIN KOHDALLE (?) ASETETAAN ID
			// ELI TÄLLE METODILLE PARAMETRINA SYÖTETTY BREED
			pstmt.setString(1, f.getTeksti());
			// STRING SGL LAUSEEN TOISEN (2) KYSYMYSMERKIN KOHDALLE (?) ASETETAAN ID
			// ELI TÄLLE METODILLE PARAMETRINA SYÖTETTY ID
			pstmt.setInt(2, f.getId());
			// PÄIVITETÄÄN TIETOKANTA (EXECUTEQUERY LUKIESSA, MUUTEN EXECUTEUPDATE)
			pstmt.executeUpdate();
			// PALAUTETAAN readAllFish() METODI, JOKA KÄY MUUTTUNEEN LISTAN UUDELLEEN LÄPI
			return listVaittama();
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Vaittama readVaittama(String id) {
		Vaittama vaittama=null;
		try {
			// HAETAAN TAULUKOSTA KALA, JONKA ID VASTAA METODILLE PARAMETRINA SYÖTETTYÄ ID:TÄ
			String sql="select * from vaittamat where id=?";
			// TÄSSÄ PITÄÄ KÄYTTÄÄ PREPARESTATEMENTTIA KOSKA LAUSE SISÄLTÄÄ SYÖTETTYJÄ MUUTTUJIA. 
			// ELI PELKKÄ STATEMENT EI RIITTÄÄ
			PreparedStatement pstmt=conn.prepareStatement(sql);
			// STRING SGL LAUSEEN ENSIMMÄISEN (1) KYSYMYSMERKIN KOHDALLE (?) ASETETAAN ID 
			// ELI TÄLLE METODILLE PARAMETRINA SYÖTETTY ID
			pstmt.setString(1, id);
			// LUETAAN TIETOKANNASTA (EXECUTEQUERY LUKIESSA, MUUTEN EXECUTEUPDATE)
			ResultSet RS=pstmt.executeQuery();
			while (RS.next()){
				// LUODAAN UUSI KALA OLIO
				vaittama = new Vaittama();
				// KALALLE ANNETAAN ROTU TIETOKANNAN BREED SARAKKEEN MUKAAN
				vaittama.setID(RS.getInt("id"));
				vaittama.setTeksti(RS.getString("teksti"));
			}
			// PALAUTETAAN KALA OLIO F
			return vaittama;
		}
		catch(SQLException e) {
			return null;
		}
	}

}
