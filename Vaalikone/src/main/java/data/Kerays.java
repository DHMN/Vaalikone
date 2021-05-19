package data;

import java.io.Serializable;

public class Kerays implements Serializable {
	String ehdokasid;
	String vaittamaid;
	String vastausteksti;
	
	public Kerays() {
		
	}
	
	public void setEhdokasid(String ehdokasid) {
		this.ehdokasid = ehdokasid;
	}
	
	public void setVaittamaid(String vaittamaid) {
		this.vaittamaid = vaittamaid;
	}
	
	public void setVastausteksti(String vastausteksti) {
		this.vastausteksti = vastausteksti;
	}
	
	public String getEhdokasid() {
		return ehdokasid;
	}
	
	public String getVaittamaid() {
		return vaittamaid;
	}
	
	public String getVastausteksti() {
		return vastausteksti;
	}
}
