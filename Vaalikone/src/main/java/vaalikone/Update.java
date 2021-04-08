package vaalikone;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Vaittama;

@WebServlet(
    name = "Update",
    urlPatterns = {"/update"}
)
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException {
		response.sendRedirect("index.html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		
		// LUETAAN VAITTAMATEDIT FORMILTA SAADUT PARAMETRIT ID JA TEKSTI
		String id = request.getParameter("id");
		String teksti = request.getParameter("teksti");

		// LUODAAN OLIO SAADUILLA PARAMETREILLÄ
		Vaittama f = new Vaittama(id, teksti);

		// LUODAAN TYHJÄ LISTA
		ArrayList<Vaittama> list = null;
		
		// LÄHETETÄÄN DAO LUOKAN UPDATEVAITTAMA METODILLE
		list = dao.updateVaittama(f);

		// LAITETAAN LIST ATTRIBUUTIKSI LIST
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/VaittamaList.jsp");
		dispatcher.forward(request, response);

	}
}