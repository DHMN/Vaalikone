package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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
   
    public Ehdokas() {
       
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
        this.pathPic=pathPic;
    }
    
    // TÄMÄ TAI SEURAAVA METODI ON TURHA. KATSOTAAN MYÖHEMMIN KUNTOON
    public Ehdokas(int ehdokasNro, String puolue, String etuNimi, String sukuNimi, String osoite, String postiNro, String postiPka, String miksi) {
    	this.setEhdokasNro(ehdokasNro);
        this.puolue=puolue;
        this.etuNimi=etuNimi;
        this.sukuNimi=sukuNimi;
        this.osoite=osoite;
        this.postiNro=postiNro;
        this.postiPka=postiPka;
        this.miksi=miksi;
        this.pathPic = "Not defined";
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
        this.pathPic = "Not defined";
    }
    
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setId(String id) {
        try {
            this.id = Integer.parseInt(id);
        }
        catch(NumberFormatException e) {
            this.id=0;
        }
    }
    
    public int getEhdokasNro() {
        return ehdokasNro;
    }
    
    public void setEhdokasNro(int ehdokasNro) {
        this.ehdokasNro=ehdokasNro;
        }
    
    public void setEhdokasNro(String ehdokasNro) {
    	 try {
             this.ehdokasNro = Integer.parseInt(ehdokasNro);
         }
         catch(NumberFormatException e) {
             this.ehdokasNro=0;
         }
    }
    
    public String getPuolue() {
        return puolue;
    }
    
    public void setPuolue(String puolue) {
        this.puolue=puolue;
        }
    
    public String getEtunimi() {
        return etuNimi;
    }
    
    public void setEtuNimi(String etuNimi) {
        this.etuNimi=etuNimi;
        }
    
    public String getSukuNimi() {
        return sukuNimi;
    }
    
    public void setSukuNimi(String sukuNimi) {
        this.sukuNimi=sukuNimi;
        }
    
    public String getOsoite() {
        return osoite;
    }
    
    public void setOsoite(String osoite) {
        this.osoite=osoite;
        }
    
    public String getPostiNro() {
        return postiNro;
    }
    
    public void setPostiNro(String postiNro) {
        this.postiNro=postiNro;
        }
    
    public String getPostiPka() {
        return postiPka;
    }
    
    public void setPostiPka(String postiPka) {
        this.postiPka=postiPka;
        }
    
    public String getMiksi() {
        return miksi;
    }
    
    public void setMiksi(String miksi) {
        this.miksi=miksi;
        }
    
    public String getPathPic() {
        return pathPic;
    }
    
    public void setPathPic(String pathPic) {
        this.pathPic=pathPic;
        }
  
    public String toString() {
        return this.id+": "+this.ehdokasNro+"/"+this.puolue+"/"+this.etuNimi+"/"+this.sukuNimi+"/"+this.osoite+"/"+this.postiNro+"/"+this.postiPka+"/"+this.miksi+"/"+this.pathPic;
    }
}
