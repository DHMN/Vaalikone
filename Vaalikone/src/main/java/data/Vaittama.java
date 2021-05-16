package data;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vaittama implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String teksti;
	
	//bi-directional many-to-many association to vastaukset
	@OneToMany(mappedBy = "vaittama", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	List<Yhdistys> liitokset;

	// Peruskonstruktori
	public Vaittama() {

	}

	// Konstruktori tekstillä
	public Vaittama(String teksti) {
		this.teksti = teksti;
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
	
	public List<Yhdistys> getLiitokset() {
		return this.liitokset;
	}
	
	
	public void setLiitokset(List<Yhdistys> liitokset) {
		this.liitokset = liitokset;
	}
}
