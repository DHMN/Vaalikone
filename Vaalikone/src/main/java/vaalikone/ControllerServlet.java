package vaalikone;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import dao.Dao;
import data.Ehdokas;
import data.Kayttaja;
import data.Vaittama;
import data.Vastaus;
import data.Vastausvaihtoehdot;
import data.Yhdistys;

@WebServlet(urlPatterns = { "/hello", "/addehdokas", "/deleteehdokas", "/updateehdokas", "/readehdokas",
		"/readtoupdateehdokas" })
public class ControllerServlet extends HttpServlet {
	Dao dao = new Dao();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();
		List<Ehdokas> list = null;
		List<Yhdistys> list2 = null;

		try {
			switch (action) {

			case "/new":
				createVaittama(request, response);
				break;
			case "/insert":
				createVaittama(request, response);
				break;
			case "/delete":
				deleteVaittama(request, response);
				break;
			case "/update":
				update(request, response);
				break;
			case "/readtoupdate":
				updateVaittama(request, response);
				break;
			case "/answers":
				addVastaus(request, response);
				break;
			case "/login":
				login(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;
			case "/addehdokas":
				list = addehdokas(request);
				break;
			case "/deleteehdokas":
				String id = request.getParameter("id");
				list = deleteehdokas(request);
				break;
			case "/updateehdokas":
				list = updateehdokas(request);
				break;
			case "/readehdokas":
				list = readehdokas(request);
				break;
			case "/ehdokasAnswers":
				list2 = ehdokasAnswers(request);
				request.setAttribute("yhdistyslist", list2);
				RequestDispatcher rd = request.getRequestDispatcher("./jsp/VaittamaList.jsp");
				rd.forward(request, response);
				break;
			case "/readtoupdateehdokas":
				Ehdokas f = readtoupdateehdokas(request);
				request.setAttribute("ehdokas", f);
				RequestDispatcher rh = request.getRequestDispatcher("./jsp/EhdokasUpdate.jsp");
				rh.forward(request, response);
				return;

			default:
				listVaittama(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("ehdokaslist", list);
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/EhdokasNew.jsp");
		rd.forward(request, response);
		
	}

	/********* VANHAT METODIT **********/

	// VÄITTÄMIEN LISTAUS
	public void listVaittama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ArrayList<Vaittama> list = new ArrayList<>();
		list = dao.listVaittama();
		request.setAttribute("list", list);

		listVastausvaihtoehdot(request, response);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/VaittamaList.jsp");
		dispatcher.forward(request, response);
	}

	// VASTAUSVAIHTOEHTOJEN LISTAUS
	private void listVastausvaihtoehdot(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ArrayList<Vastausvaihtoehdot> list2 = new ArrayList<>();
		list2 = dao.listVastausvaihtoehdot();
		request.setAttribute("list2", list2);
	}

	// VÄITTÄMÄN LUONTI
	private void createVaittama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String teksti = request.getParameter("teksti");
		Vaittama newVaittama = new Vaittama(teksti);
		dao.createVaittama(newVaittama);
		response.sendRedirect("hello");
	}

	// VÄITTÄMÄN POISTAMINEN
	private void deleteVaittama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		dao.deleteVaittama(id);
		listVaittama(request, response);
	}

	// VÄITTÄMÄN HAKEMINEN MUOKKAUSTA VARTEN
	private void updateVaittama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		Vaittama vaittama = null;

		vaittama = dao.readVaittama(id);

		request.setAttribute("list", vaittama);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/VaittamatEdit.jsp");
		dispatcher.forward(request, response);
	}

	// VÄITTÄMÄN MUOKKAUS
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String teksti = request.getParameter("teksti");
		String id = request.getParameter("id");

		Vaittama vaittama = new Vaittama(teksti, id);
		System.out.println("väittämän teksti update metodiin tultaessa: " + teksti);
		System.out.println("väittämän id update metodiin tultaessa: " + vaittama.getId());
		ArrayList<Vaittama> list = null;

		list = dao.updateVaittama(vaittama);

		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/VaittamaList.jsp");
		dispatcher.forward(request, response);
	}

	// VASTAUKSET TIETOKANTAAN
	private void addVastaus(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String vastausteksti = request.getParameter("vastausteksti");

		Vastaus vastaus = new Vastaus(vastausteksti);

		dao.addVastaus(vastaus);
		response.sendRedirect("hello");
	}

	// SISÄÄNKIRJAUTUMINEN
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Kayttaja kayttaja = new Kayttaja();

		kayttaja.setEmail(email);
		kayttaja.setPassword(password);

		try {
			String userValidate = dao.checkLogin(kayttaja);

			if (userValidate.equals("Admin_Role")) {

				HttpSession session = request.getSession(); // Creating a session
				session.setAttribute("Admin", email); // setting session attribute
				request.setAttribute("userName", email);

				// request.getRequestDispatcher("../home").forward(request, response);
				request.getRequestDispatcher("/hello").forward(request, response);
				System.out.println("Logged in as " + email);

			} else {
				// System.out.println("Error message = "+userValidate);
				request.setAttribute("errMessage", userValidate);
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	// KIRJAUDUTAAN ULOS
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // Fetch session object

		if (session != null) {

			session.invalidate(); // removes all session attributes bound to the session
			request.setAttribute("errMessage", "You have logged out successfully");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			requestDispatcher.forward(request, response);
			System.out.println("Logged out");
		}

	}

	/********* EHDOKKAAN KÄSITTELY **********/

	private Ehdokas readtoupdateehdokas(HttpServletRequest request) {
		String id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/ehdokasservice/readtoupdateehdokas/" + id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		Ehdokas fish = b.get(Ehdokas.class);
		return fish;
	}

	private List<Ehdokas> addehdokas(HttpServletRequest request) {
		Ehdokas f = new Ehdokas(request.getParameter("ehdokasNro"), request.getParameter("puolue"),
				request.getParameter("etuNimi"), request.getParameter("sukuNimi"), request.getParameter("osoite"),
				request.getParameter("postiNro"), request.getParameter("postiPka"), request.getParameter("miksi"));
		System.out.println("Tähän asti tullaan heittämällä " + f);
		String uri = "http://127.0.0.1:8080/rest/ehdokasservice/addehdokas";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		Entity<Ehdokas> e = Entity.entity(f, MediaType.APPLICATION_JSON);
		GenericType<List<Ehdokas>> genericList = new GenericType<List<Ehdokas>>() {
		};

		List<Ehdokas> returnedList = b.post(e, genericList);
		return returnedList;
	}

	private List<Ehdokas> readehdokas(HttpServletRequest request) {
		String uri = "http://127.0.0.1:8080/rest/ehdokasservice/readehdokas";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		GenericType<List<Ehdokas>> genericList = new GenericType<List<Ehdokas>>() {
		};

		List<Ehdokas> returnedList = b.get(genericList);
		return returnedList;
	}

	private List<Ehdokas> updateehdokas(HttpServletRequest request) {
		Ehdokas f = new Ehdokas(request.getParameter("id"), request.getParameter("ehdokasNro"),
				request.getParameter("puolue"), request.getParameter("etuNimi"), request.getParameter("sukuNimi"),
				request.getParameter("osoite"), request.getParameter("postiNro"), request.getParameter("postiPka"),
				request.getParameter("miksi"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/ehdokasservice/updateehdokas";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		Entity<Ehdokas> e = Entity.entity(f, MediaType.APPLICATION_JSON);
		GenericType<List<Ehdokas>> genericList = new GenericType<List<Ehdokas>>() {
		};

		List<Ehdokas> returnedList = b.put(e, genericList);
		return returnedList;
	}

	private List<Ehdokas> deleteehdokas(HttpServletRequest request) {
		String id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/ehdokasservice/deleteehdokas/" + id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		GenericType<List<Ehdokas>> genericList = new GenericType<List<Ehdokas>>() {
		};

		List<Ehdokas> returnedList = b.delete(genericList);
		return returnedList;
	}

	private List<Yhdistys> ehdokasAnswers(HttpServletRequest request) {
//		ArrayList<Yhdistys> yhdistyslista= new ArrayList<>();
//		String ehdokasNro = request.getParameter("ehdokasNro");	
//		Ehdokas c = new Ehdokas();
//		c.setId(Integer.parseInt(ehdokasNro));
//		for (int i = 0; i < 2; i++) {
//			String vastaus = request.getParameter("vaittamanArvo"+i);
//			String vaittama = request.getParameter("vaittamaId"+i);
//			Vastaus a = new Vastaus(vastaus);
//			Vaittama b = new Vaittama(vaittama);
//			Yhdistys e = new Yhdistys(c, a, b);
//			yhdistyslista.add(e);
//			System.out.println("Väittämän id " + yhdistyslista);
//		}
//		
//		String uri = "http://127.0.0.1:8080/rest/ehdokasservice/yhdistys";
//
//		//A list of DogBreed objects to send to our web-service 
//		Client asiakas=ClientBuilder.newClient();
//		WebTarget wt=asiakas.target(uri);
//		Builder b=wt.request();
//		
//		//Create an Entity object to send by post method
//		Entity<ArrayList<Yhdistys>> f=Entity.entity(yhdistyslista, MediaType.APPLICATION_JSON);
//
//		//Create a GenericType to be able to get List of objects
//		//This will be the second parameter of post method
//		GenericType<List<Yhdistys>> genericList = new GenericType<List<Yhdistys>>() {};
//		
//		//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
//		List<Yhdistys> returnedList=b.post(f, genericList);
//		
//		for (Yhdistys db:returnedList) {
//			System.out.println(db);
//		}

		ArrayList<Vaittama> vaittamalist = new ArrayList<>();
		ArrayList<Yhdistys> yhdistyslista = new ArrayList<>();
		
		vaittamalist = dao.listVaittama();
		int arvo = 0;

		for (int i = 0; i < vaittamalist.size(); i++) {

			System.out.println(vaittamalist.get(i).getId());

			System.out.println("Väittämän id " + request.getParameter("vaittamaId" + (i + 1)));
			System.out.println("Vastausteksti "+ request.getParameter("vastausteksti" + request.getParameter("vaittamaId" + (i + 1))));

			System.out.println(request.getParameter("ehdokasNro"));
			
			if (request.getParameter("vastausteksti" + request.getParameter("vaittamaId" + (i + 1))).equals("täysin samaa mieltä")) {
				arvo = 5;
				System.out.println("Vaittaman arvo " + arvo);
			}
			if (request.getParameter("vastausteksti" + request.getParameter("vaittamaId" + (i + 1))).equals("samaa mieltä")) {
				arvo = 4;
				System.out.println("Vaittaman arvo " + arvo);
			}
			if (request.getParameter("vastausteksti" + request.getParameter("vaittamaId" + (i + 1))).equals("neutraali")) {
				arvo = 3;
				System.out.println("Vaittaman arvo " + arvo);
			}
			if (request.getParameter("vastausteksti" + request.getParameter("vaittamaId" + (i + 1))).equals("eri mieltä")) {
				arvo = 2;
				System.out.println("Vaittaman arvo " + arvo);
			}
			if (request.getParameter("vastausteksti" + request.getParameter("vaittamaId" + (i + 1))).equals("täysin eri mieltä")) {
				arvo = 1;
				System.out.println("Vaittaman arvo " + arvo);
			}

			System.out.println(request.getParameter("ehdokasNro"));
			
			//String ehdokasNro = request.getParameter("ehdokasNro");

			Ehdokas ehdokas = new Ehdokas(request.getParameter("ehdokasNro"));
			Vastaus vastaus = new Vastaus(request.getParameter("vastausteksti" + request.getParameter("vaittamaId" + (i + 1))));
			Vaittama vaittama = new Vaittama(request.getParameter("vaittamaId" + (i + 1)));
//			ehdokas.setId(1);
//			vastaus.setId(1);
//			vaittama.setId(1);
			Yhdistys yhdistys = new Yhdistys(ehdokas, vastaus, vaittama);
			yhdistyslista.add(yhdistys);
			System.out.println("Yhdistyksen id " + yhdistys.getId());
			System.out.println("Yhdistyksen ehdokas " + yhdistys.getEhdokas());
			System.out.println("Yhdistyksen vaittama " + yhdistys.getVaittama());
			System.out.println("Yhdistyksen vastaus " + yhdistys.getVastaus());
			System.out.println("Ykhistyslista " + yhdistyslista);
		}
		
		String uri = "http://127.0.0.1:8080/rest/ehdokasservice/yhdistys";
		Client asiakas = ClientBuilder.newClient();
		WebTarget wt = asiakas.target(uri);
		Builder b = wt.request();

		Entity<ArrayList<Yhdistys>> f = Entity.entity(yhdistyslista, MediaType.APPLICATION_JSON);
		GenericType<List<Yhdistys>> genericList = new GenericType<List<Yhdistys>>() {
		};

		List<Yhdistys> returnedList = b.post(f, genericList);

		return returnedList;
	}

}