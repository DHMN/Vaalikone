package data;

public class Vastaus {
	String vastausteksti;
	int id;
	int vaittamaId;

	// Peruskonstruktori
	public Vastaus() {

	}

	// Konstruktori ID:llä, vastausid:llä ja tekstillä
	public Vastaus(String id, String vaittamaId, String vastausteksti) {
		setId(id);
		setVaittamaId(id);
		this.vastausteksti = vastausteksti;
	}

	// Konstruktori ID:llä ja tekstillä
	public Vastaus(String vaittamaId, String vastausteksti) {
		setVaittamaId(vaittamaId);
		this.vastausteksti = vastausteksti;
	}

	// Konstruktori tekstillä
	public Vastaus(String vastausteksti) {
		this.vastausteksti = vastausteksti;
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

	// Setterit ja getterit
	public void setVaittamaId(int id) {
		this.vaittamaId = id;
	}

	// MUUTTAA LOMAKKEELTA TULLEEN STRING ID:N INTIKSI
	public void setVaittamaId(String id) {
		try {
			this.vaittamaId = Integer.parseInt(id);
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing - the value of id won't be changed
		}
	}

	public void setVastausteksti(String vastausteksti) {
		this.vastausteksti = vastausteksti;
	}

	public int getId() {
		return id;
	}

	public int getVaittamaId() {
		return vaittamaId;
	}

	public String getVastausteksti() {
		return vastausteksti;
	}

	public String getIdString() {
		return String.valueOf(id);
	}

	// Olion tulostamiseen toString
	public String toString() {
		return id + "" + vastausteksti;
	}

}
