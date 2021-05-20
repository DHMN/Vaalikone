package data;

import java.io.Serializable;

public class Kerays implements Serializable {
	int ehdokasid;
	int vaittamaid;
	int vastausteksti;
	
	public Kerays() {
		
	}
	
	public void setEhdokasid(String ehdokasid) {
		try {
			this.ehdokasid = Integer.parseInt(ehdokasid);
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing - the value of id won't be changed
		}
	}
	public void setVaittamaid(String vaittamaid) {
		try {
			this.vaittamaid = Integer.parseInt(vaittamaid);
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing - the value of id won't be changed
		}
	}
	
	public void setVastausteksti(String vastausteksti) {
		try {
			this.vastausteksti = Integer.parseInt(vastausteksti);
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing - the value of id won't be changed
		}
	}

//	
	public void setEhdokasid(int ehdokasid) {
		this.ehdokasid = ehdokasid;
	}
	
	public void setVaittamaid(int vaittamaid) {
		this.vaittamaid = vaittamaid;
	}
	
	public void setVastausteksti(int vastausteksti) {
		this.vastausteksti = vastausteksti;
	}
	
	public int getEhdokasid() {
		return ehdokasid;
	}
	
	public int getVaittamaid() {
		return vaittamaid;
	}
	
	public int getVastausteksti() {
		return vastausteksti;
	}
}
