package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Ehdokas;

@Path("/ehdokasservice")
public class EhdokasService {
	//Create an EntityManagerFactory by the name of persistence unit - see persistence.xml
 	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");

    @POST
    @Path("/saveehdokas")
    @Consumes(MediaType.APPLICATION_JSON) //Method can receive POSTed data from an html form
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ehdokas> saveEhdokas(Ehdokas ehdokas) {
         	//And then EntityManager, which can manage the entities.
         	EntityManager em=emf.createEntityManager();
         	//When using default (RESOURCE-LOCAL) transaction typemy
         	//Every transaction must begin and end.
         	em.getTransaction().begin();
         	em.persist(ehdokas);
         	em.getTransaction().commit();
         	
     		//Read all the rows from table prey. Here the Prey must start with capital, 
     		//because class's name starts. This returns a List of Prey objects.
     		List<Ehdokas> list=(List<Ehdokas>) em.createQuery("select a from Ehdokas a").getResultList();
     		return list;   
    }
   
    @POST
    @Path("/addehdokas")
    @Consumes("application/x-www-form-urlencoded") //Method can receive POSTed data from an html form
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ehdokas> addEhdokas(@FormParam("ehdokasNro") int ehdokasNro, @FormParam("puolue") String puolue, @FormParam("etuNimi") String etuNimi, @FormParam("sukuNimi") String sukuNimi,
    		@FormParam("osoite") String osoite, @FormParam("postiNro") String postiNro, @FormParam("postiPka") String postiPka, @FormParam("miksi") String miksi ){
        Ehdokas ehdokas=new Ehdokas(ehdokasNro, puolue, etuNimi, sukuNimi, osoite, postiNro, postiPka, miksi);
       
        	//And then EntityManager, which can manage the entities.
        	EntityManager em=emf.createEntityManager();
        	//When using default (RESOURCE-LOCAL) transaction typemy
        	//Every transaction must begin and end.
        	em.getTransaction().begin();
        	em.persist(ehdokas);
        	em.getTransaction().commit();
        	
    		//Read all the rows from table prey. Here the Prey must start with capital, 
    		//because class's name starts. This returns a List of Prey objects.
    		@SuppressWarnings("unchecked")
			List<Ehdokas> list=(List<Ehdokas>) em.createQuery("select a from Ehdokas a").getResultList();
    		return list;      
    }
    
	// TÄMÄ HAKEE KAIKKI KALAT
	@GET
	@Path("/readfish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Ehdokas> readFish() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// XYX VOI OLLA VAIKKA TADAA, KUN VAAN JÄLKIMMÄINEN ON SAMA
		// FISH VIITTAA JAVA LUOKKAAN JA SE PITÄÄ OLLA KIRJOITETTU SAMALLA TAVALLA KUIN
		// LUOKKA
		// TÄMÄ LISTAA KERRALLA KAIKKI LUOKAN TIEDOT
		List<Ehdokas> list = em.createQuery("select xyx from Ehdokas xyx").getResultList();
		em.getTransaction().commit();
		return list;
	}

	// TÄMÄ LISÄÄ KALAN
	@POST
	@Path("/addfish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Ehdokas> addFish(Ehdokas ehdokas) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(ehdokas);// The actual insertion line
		em.getTransaction().commit();
		// Calling the method readFish() of this service
		List<Ehdokas> list = readFish();
		return list;
	}

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ehdokas> getAll(){
        return this.readEhdokas();
    }

    @GET
    @Path("/getehdokas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ehdokas getBook(@PathParam("ehdokasNro") int ehdokasNro) {
        List<Ehdokas> list=this.readEhdokas();
        try {
            return list.get(ehdokasNro);
        }
        catch (IndexOutOfBoundsException e) {
            return new Ehdokas();
        }
    }
   
    @DELETE
    @Path("/delete/{id}")
    public boolean deleteBook(@PathParam("ehdokasNro") int ehdokasNro) {
        List<Ehdokas> list=this.readEhdokas();
        try {
        	EntityManager em=emf.createEntityManager();
    		em.getTransaction().begin();
    		Ehdokas f=em.find(Ehdokas.class, ehdokasNro);
    		if (f!=null) {
    			em.remove(f);//The actual insertion line
    		}
    		em.getTransaction().commit();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    private List<Ehdokas> readEhdokas(){
        List<Ehdokas> list = new ArrayList<Ehdokas>();
            	EntityManager em=emf.createEntityManager();
            	list=(List<Ehdokas>) em.createQuery("select a from Ehdokas a").getResultList();
        		return list;      
        
    }
}