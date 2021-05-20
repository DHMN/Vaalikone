package data;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import data.Vastaus;
import data.Vaittama;
import data.Ehdokas;

@Entity
public class Yhdistys implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "ehdokasid")
	Ehdokas ehdokas;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "vastausid")
	Vastaus vastaus;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "vaittamaid")
	Vaittama vaittama;

	public Yhdistys() {
		
	}
	
	public Yhdistys(Ehdokas ehdokas, Vastaus vastaus, Vaittama vaittama) {
		this.ehdokas = ehdokas;
		this.vastaus = vastaus;
		this.vaittama = vaittama;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setEhdokas(Ehdokas ehdokas) {
		this.ehdokas = ehdokas;
	}
	
	public Ehdokas getEhdokas() {
		return this.ehdokas;
	}

	
	public void setVastaus(Vastaus vastaus) {
		this.vastaus = vastaus;
	}
	
	public Vastaus getVastaus() {
		return this.vastaus;
	}
	
	public void setVaittama(Vaittama vaittama) {
		this.vaittama = vaittama;
	}
	
	public Vaittama getVaittama() {
		return this.vaittama;
	};

}
