package data;

public class Vaittama {
	String teksti;
	int id;

	// Peruskonstruktori
	public Vaittama() {

	}

	// Konstruktori ID:llä ja tekstillä
	public Vaittama(String id, String teksti) {
		setId(id);
		this.teksti = teksti;
	}

	// Konstruktori tekstillä
	public Vaittama(String teksti) {
		this.teksti = teksti;
	}

	// Setterit ja getterit
	public void setID(int id) {
		this.id = id;
	}

	// MUUTTAA LOMAKKEELTA TULLEEN STRING ID:N INTIKSI
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing - the value of id won't be changed
		}
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

	public String getTeksti() {
		return teksti;
	}

	// Olion tulostamiseen toString
	public String toString() {
		return id + "" + teksti;
	}
}
