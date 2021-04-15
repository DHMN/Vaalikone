package vaalikone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import data.Kayttaja;
import data.Vaittama;
import data.Vastaus;
import data.Vastausvaihtoehdot;

@WebServlet(name = "HelloAppEngine", urlPatterns = { "/hello" })
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

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
	}
	
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
		String id = request.getParameter("id");
		String teksti = request.getParameter("teksti");

		Vaittama vaittama = new Vaittama(id, teksti);

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
		String vaittamaId = request.getParameter("vaittamaId");

		Vastaus vastaus = new Vastaus(vaittamaId, vastausteksti);

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
}
