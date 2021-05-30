package rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.Ehdokas;
import data.Kerays;
import data.Yhdistys;
import data.Vaittama;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/ehdokasservice")
public class EhdokasService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

	// TÄMÄ HAKEE KAIKKI KALAT
	@GET
	@Path("/readehdokas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Ehdokas> readEhdokas() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Ehdokas> list = em.createQuery("select xyx from Ehdokas xyx").getResultList();
		em.getTransaction().commit();
		return list;
	}

	// TÄMÄ LISÄÄ KALAN
	@POST
	@Path("/addehdokas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Ehdokas> addEhdokas(Ehdokas ehdokas) {
		EntityManager em = emf.createEntityManager();
		System.out.println("Ehdokas servicelle asti tulee");
		em.getTransaction().begin();
		em.persist(ehdokas);
		em.getTransaction().commit();
		List<Ehdokas> list = readEhdokas();
		return list;
	}

	// TÄMÄ PÄIVITTÄÄ KALAN
	@PUT
	@Path("/updateehdokas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Ehdokas> updateEhdokas(Ehdokas ehdokas) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokas f = em.find(Ehdokas.class, ehdokas.getId());
		System.out.println("Ehdokkaan id" + ehdokas.getId());
		System.out.println("Ehdokas uudet tietdot" + f);
		if (f != null) {
			System.out.println("Ehdokas tiedot ennen mergeä: " + f);
			em.merge(ehdokas);
			System.out.println("Ehdokas tiedot mergen jälkeen: " + f);
			System.out.println("Ehdokas tiedot mergen jälkeen: " + ehdokas);
		}
		em.getTransaction().commit();
		List<Ehdokas> list = readEhdokas();
		return list;
	}

	// TÄMÄ POISTAA KALAN
	@DELETE
	@Path("/deleteehdokas/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Ehdokas> deleteEhdokas(@PathParam("id") int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokas f = em.find(Ehdokas.class, id);
		if (f != null) {
			em.remove(f);
		}
		em.getTransaction().commit();

		List<Ehdokas> list = readEhdokas();
		return list;
	}

	// TÄMÄ HAKEE YHDEN EHDOKKAAN
	@GET
	@Path("/readtoupdateehdokas/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Ehdokas readToUpdateEhdokas(@PathParam("id") int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokas f = em.find(Ehdokas.class, id);
		em.getTransaction().commit();
		return f;
	}

	@POST
	@Path("/yhdistys")
	// Syö keräyslistaa jolla hakee tarvittavat ja persistaa
	@Consumes(MediaType.APPLICATION_JSON) // Method receives object as a JSON string
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	public ArrayList<Kerays> yhdistys(ArrayList<Kerays> list) {
		// The parameter list could be saved into a database or a file
		// but here we just modify it to be sure, that it is usable
		EntityManager em = emf.createEntityManager();
		for (Kerays db : list) {
			if (db.ehdokasid != 0) {
				Ehdokas f = em.find(Ehdokas.class, db.getEhdokasid());
				Vaittama g = em.find(Vaittama.class, db.getVaittamaid());
				String h = Integer.toString(db.getVastausteksti());
				Yhdistys yhdistys = new Yhdistys(f, h, g);
				System.out.println("Yhdistystiedot service: " + yhdistys);
				em.getTransaction().begin();
				em.persist(yhdistys);// The actual insertion line
				em.getTransaction().commit();
			}
		}
		return list;
	}

	// TÄMÄ HAKEE KAIKKI KALAT
	@GET
	@Path("/readyhdistys")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Yhdistys> readYhdistys() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Yhdistys> list = em.createQuery("select xyx from Yhdistys xyx").getResultList();
		em.getTransaction().commit();

		return list;
	}

	// TÄMÄ LISÄÄ KALAN
	@POST
	@Path("/addyhdistys")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Yhdistys> addyhdistys(Yhdistys yhdistys) {
		EntityManager em = emf.createEntityManager();
		System.out.println("Ehdokas servicelle asti tulee");
		em.getTransaction().begin();
		em.persist(yhdistys);
		em.getTransaction().commit();
		List<Yhdistys> list = readYhdistys();
		return list;
	}

	@GET
	@Path("/showanswers/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Yhdistys> showanswers(@PathParam("id") int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Yhdistys> list = em.createQuery("select a from Yhdistys a where a.ehdokas.id = :id").setParameter("id", id)
				.getResultList();
		em.getTransaction().commit();
		return list;

	}

	@GET
	@Path("/showinfo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Ehdokas showInfo(@PathParam("id") int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokas f = em.find(Ehdokas.class, id);
		em.getTransaction().commit();
		return f;
	}

	@POST
	@Path("/fileupload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData, @FormDataParam("ehdokasNro") int ehdokasNro,
			@Context ServletContext sc) throws Exception {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokas f = em.find(Ehdokas.class, ehdokasNro);
		f.setPathPic("C:/temp/" + ehdokasNro + ".png");

		em.merge(f);
		em.getTransaction().commit();
		String UPLOAD_PATH = "C:/temp";
		
		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/" + ehdokasNro + ".png"));
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok("Data uploaded successfully !!").build();
	}
}
