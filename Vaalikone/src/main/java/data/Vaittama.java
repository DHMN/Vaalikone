package data;

public class Vaittama {
	String teksti;
	int id;
	
	// Peruskonstruktori
	public Vaittama() {
		
	}
	
	//Konstruktori ID:llä ja tekstillä
	public Vaittama(int id, String teksti) {
		this.id = id;
		this.teksti = teksti;
	}
	
	//Konstruktori tekstillä
	public Vaittama(String teksti) {
			this.teksti = teksti;
	}
	
	//Setterit ja getterit
	public void setID(int id) {
		this.id = id;
	}
	
	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}
	
	public int getId() {
		return id;
	}
	
	public String getIdString() {
		return String.valueOf(id);
	}
	
	//Olion tulostamiseen toString
	public String toString() {
		return id+""+teksti;
	}
}
