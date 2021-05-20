package data;

import java.io.Serializable;
import javax.persistence.*;

import data.Vaittama;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vastaus implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int vastausteksti;


	//bi-directional one-to-many association to Fishbreed
	@OneToMany(mappedBy = "vastaus", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	List<Yhdistys> liitokset;
	
	
	// Peruskonstruktori
	public Vastaus() {

	}

	//Konstruktori vastausid:llä ja tekstillä
	public Vastaus(String vastausteksti) {
		this.setVastausteksti(vastausteksti);
	}
	
	//Konstruktori vastausid:llä ja tekstillä
	public Vastaus(int id) {
		this.id = id;
	}
	


	// Setterit ja getterit
	public void setId(int id) {
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
	
	// MUUTTAA LOMAKKEELTA TULLEEN STRING ID:N INTIKSI
	public void setVastausteksti(String vastausteksti) {
		try {
			this.vastausteksti = Integer.parseInt(vastausteksti);
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing - the value of id won't be changed
		}
	}

	public void setVastausteksti(int i) {
		this.vastausteksti = i;
	}

	public int getId() {
		return id;
	}


	public String getVastausteksti() {
		return String.valueOf(vastausteksti);
	}

	public String getIdString() {
		return String.valueOf(id);
	}
	
	public List<Yhdistys> getLiitokset() {
		return this.liitokset;
	}
	
	
	public void setLiitokset(List<Yhdistys> liitokset) {
		this.liitokset = liitokset;
	}

	// Olion tulostamiseen toString
	public String toString() {
		return id + "/" + vastausteksti;
	}

}
