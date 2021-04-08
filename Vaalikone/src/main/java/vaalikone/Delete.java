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
    name = "Delete",
    urlPatterns = {"/deleteeeee"}
)
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		String id=request.getParameter("id");
		ArrayList<Vaittama> list=null;

		list=dao.deleteVaittama(id);

		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/VaittamaList.jsp");
		dispatcher.forward(request, response);
	}
}