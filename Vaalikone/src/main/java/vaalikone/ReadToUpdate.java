//package vaalikone;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.Dao;
//import data.Vaittama;
//
///**
// * Servlet implementation class ReadToUpdate
// */
//@WebServlet("/readtoupdate")
//public class ReadToUpdate extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	Dao dao = new Dao();
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public ReadToUpdate() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		String id = request.getParameter("id");
//		Vaittama vaittama = null;
//
//		vaittama = dao.readVaittama(id);
//
//		request.setAttribute("list", vaittama);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/VaittamatEdit.jsp");
//		dispatcher.forward(request, response);
//	}
//}
