package data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vaittama implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String teksti;
	
	//bi-directional many-to-many association to vastaukset
	@OneToMany(mappedBy = "vaittama", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference(value="yh-vt")
	List<Yhdistys> liitokset;

	// Peruskonstruktori
	public Vaittama() {

	}

	// Konstruktori tekstillä
	public Vaittama(String teksti) {
		this.teksti = teksti;
	}
	
	// Konstruktori tekstillä ja idllä
	public Vaittama(String teksti, String id) {
		this.teksti = teksti;
		this.setId(id);
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
		return id + "/" + teksti;
	}
	
	public List<Yhdistys> getLiitokset() {
		return this.liitokset;
	}
	
	
	public void setLiitokset(List<Yhdistys> liitokset) {
		this.liitokset = liitokset;
	}
}
