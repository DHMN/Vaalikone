package vaalikone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Vaittama;

@WebServlet(name = "HelloAppEngine", urlPatterns = { "/hello" })
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();

		try {
			switch (action) {
			/*
			 * case "/new": showNewForm(request, response); break;
			 */
			case "/insert":
				createVaittama(request, response);
				break;
			/*
			 * case "/delete": deleteVaittama(request, response); break; case "/edit":
			 * showEditForm(request, response); break; case "/update":
			 * updateVaittama(request, response); break;
			 */
			default:
				listVaittama(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	public void listVaittama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ArrayList<Vaittama> list = new ArrayList<>();
		list = dao.listVaittama();
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/VaittamaList.jsp");
		dispatcher.forward(request, response);
	}

	private void createVaittama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String teksti = request.getParameter("teksti");
		Vaittama newVaittama = new Vaittama(teksti);
		dao.createVaittama(newVaittama);
		response.sendRedirect("list");
	}

	/*
	 * private void updateBook(HttpServletRequest request, HttpServletResponse
	 * response) throws SQLException, IOException { int id =
	 * Integer.parseInt(request.getParameter("id")); String title =
	 * request.getParameter("title"); String author =
	 * request.getParameter("author"); float price =
	 * Float.parseFloat(request.getParameter("price"));
	 * 
	 * Book book = new Book(id, title, author, price); bookDAO.updateBook(book);
	 * response.sendRedirect("list"); }
	 * 
	 * private void deleteBook(HttpServletRequest request, HttpServletResponse
	 * response) throws SQLException, IOException { int id =
	 * Integer.parseInt(request.getParameter("id"));
	 * 
	 * Book book = new Book(id); bookDAO.deleteBook(book);
	 * response.sendRedirect("list");
	 */


}
