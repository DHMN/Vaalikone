package data;

import java.io.Serializable;
import javax.persistence.*;

import data.Vaittama;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name="Vastaus.findAll", query="SELECT l FROM Vastaus l")
public class Vastaus implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String vastausteksti;


	//bi-directional many-to-many association to Fishbreed
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name="vaittamabyvastaus"
		, joinColumns={
			@JoinColumn(name="vastausid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="vaittamaid")
			}
		)
	private List<Vaittama> vaittamat;
	// Peruskonstruktori
	public Vastaus() {

	}

	//Konstruktori vastausid:llä ja tekstillä
	public Vastaus(String vastausteksti) {
		this.vastausteksti = vastausteksti;
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

	public void setVastausteksti(String vastausteksti) {
		this.vastausteksti = vastausteksti;
	}

	public int getId() {
		return id;
	}


	public String getVastausteksti() {
		return vastausteksti;
	}

	public String getIdString() {
		return String.valueOf(id);
	}
	
	public List<Vaittama> getVaittamat() {
		return this.vaittamat;
	}
	
	public void addVaittama(Vaittama vaittama) {
		if (vaittamat==null) {
			vaittamat=new ArrayList<>();
		}
		vaittamat.add(vaittama);
	}
	
	public void setVaittamat(List<Vaittama> vaittamat) {
		this.vaittamat = vaittamat;
	}

	// Olion tulostamiseen toString
	public String toString() {
		return id + "" + vastausteksti;
	}

}
