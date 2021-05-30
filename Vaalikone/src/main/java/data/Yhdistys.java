package data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;
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
	@JsonBackReference(value="yh-eh")
	@JoinColumn(name = "ehdokasid")
	Ehdokas ehdokas;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonBackReference(value="yh-vt")
	@JoinColumn(name = "vaittamaid")
	public Vaittama vaittama;
	
	private String vastaus;

	public Yhdistys() {
		
	}
	
	public Yhdistys(Ehdokas ehdokas, String vastaus, Vaittama vaittama) {
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

	
	public void setVastaus(String vastaus) {
		this.vastaus = vastaus;
	}
	
	public String getVastaus() {
		return this.vastaus;
	}
	
	public void setVaittama(Vaittama vaittama) {
		this.vaittama = vaittama;
	}
	
	public Vaittama getVaittama() {
		return this.vaittama;
	};

}
