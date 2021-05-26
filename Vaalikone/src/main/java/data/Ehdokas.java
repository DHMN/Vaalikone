package data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;

@Entity
public class Ehdokas implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    private int id;
    private int ehdokasNro;
    private String puolue;
    private String etuNimi;
    private String sukuNimi;
    private String osoite;
    private String postiNro;
	private String postiPka;
    private String miksi;
    private String pathPic;
    @OneToMany(mappedBy = "ehdokas", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference(value="yh-eh")
	List<Yhdistys> liitokset;
   
    public Ehdokas() {

    }

    public Ehdokas(String ehdokasNro) {
    	this.setEhdokasNro(ehdokasNro);
    }

    
    public Ehdokas(int ehdokasNro, String puolue, String etuNimi, String sukuNimi, String osoite, String postiNro, String postiPka, String miksi, String pathPic) {
        this.ehdokasNro=ehdokasNro;
        this.puolue=puolue;
        this.etuNimi=etuNimi;
        this.sukuNimi=sukuNimi;
        this.osoite=osoite;
        this.postiNro=postiNro;
        this.postiPka=postiPka;
        this.miksi=miksi;
        this.pathPic="Not Defined";
    }

	public Ehdokas(String ehdokasNro, String puolue, String etuNimi, String sukuNimi, String osoite, String postiNro, String postiPka, String miksi) {
        this.setEhdokasNro(ehdokasNro);
        this.puolue=puolue;
        this.etuNimi=etuNimi;
        this.sukuNimi=sukuNimi;
        this.osoite=osoite;
        this.postiNro=postiNro;
        this.postiPka=postiPka;
        this.miksi=miksi;
    }
    
    public Ehdokas(String id, String ehdokasNro, String puolue, String etuNimi, String sukuNimi, String osoite, String postiNro, String postiPka, String miksi) {
        this.setEhdokasNro(ehdokasNro);
        this.setId(id);
        this.puolue=puolue;
        this.etuNimi=etuNimi;
        this.sukuNimi=sukuNimi;
        this.osoite=osoite;
        this.postiNro=postiNro;
        this.postiPka=postiPka;
        this.miksi=miksi;
    }
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	// TÄMÄ VARMISTAA, ETTÄ STRINGINÄ TULLUT ID KÄÄNNETÄÄN INTIKSI
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}
	public void setEhdokasNro(String ehdokasNro) {
		try {
			this.ehdokasNro = Integer.parseInt(ehdokasNro);
		}
		catch (NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}
	public int getEhdokasNro() {
		return ehdokasNro;
	}
	public void setEhdokasNro(int ehdokasNro) {
		this.ehdokasNro = ehdokasNro;
	}
	public String getPuolue() {
		return puolue;
	}
	public void setPuolue(String puolue) {
		this.puolue = puolue;
	}
	public String getEtuNimi() {
		return etuNimi;
	}
	public void setEtuNimi(String etuNimi) {
		this.etuNimi = etuNimi;
	}
	public String getSukuNimi() {
		return sukuNimi;
	}
	public void setSukuNimi(String sukuNimi) {
		this.sukuNimi = sukuNimi;
	}
	public String getOsoite() {
		return osoite;
	}
	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}
	public String getPostiNro() {
		return postiNro;
	}
	public void setPostiNro(String postiNro) {
		this.postiNro = postiNro;
	}
	public String getPostiPka() {
		return postiPka;
	}
	public void setPostiPka(String postiPka) {
		this.postiPka = postiPka;
	}
	public String getMiksi() {
		return miksi;
	}
	public void setMiksi(String miksi) {
		this.miksi = miksi;
	}
    public String getPathPic() {
		return pathPic;
	}
	public void setPathPic(String pathPic) {
		this.pathPic = pathPic;
	}
    
	public List<Yhdistys> getLiitokset() {
		return this.liitokset;
	}
	
	public void setLiitokset(List<Yhdistys> liitokset) {
		this.liitokset = liitokset;
	}
  
    public String toString() {
        return this.id+": "+this.ehdokasNro+"/"+this.puolue+"/"+this.etuNimi+"/"+this.sukuNimi+"/"+this.osoite+"/"+this.postiNro+"/"+this.postiPka+"/"+this.miksi+"/"+pathPic;
    }
}
