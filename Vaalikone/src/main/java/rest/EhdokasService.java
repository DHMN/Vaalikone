package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Ehdokas;

@Path("/ehdokasservice")
public class EhdokasService {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikonejpa");

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
		System.out.println("Ehdokkaan id"+ehdokas.getId());
		System.out.println("Ehdokas uudet tietdot" + f);
		if (f != null) {
			em.merge(ehdokas);
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
}