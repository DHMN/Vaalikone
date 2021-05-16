package data;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name="Vaittama.findAll", query="SELECT f FROM Vaittama f")
public class Vaittama implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String teksti;
	
	//bi-directional many-to-many association to vastaukset
		@ManyToMany(mappedBy="vaittamat", cascade = CascadeType.PERSIST)
		private List<Vastaus> vastaukset;

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
	
	public List<Vastaus> getVastaukset() {
		return this.vastaukset;
	}
	
	public void addVastaus(Vastaus vastaus) {
		if (vastaukset==null) {
			vastaukset=new ArrayList<>();
		}
		vastaukset.add(vastaus);
	}
	
	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}
}
