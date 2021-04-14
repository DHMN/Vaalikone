//package vaalikone;
//
//import java.io.IOException;
//import java.io.NotSerializableException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import dao.Dao;
//import data.Kayttaja;
//
///**
// * Servlet implementation class LoginServlet
// */
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	// TODO Auto-generated constructor stub
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		PrintWriter out = response.getWriter();
//
//		Dao newdao = new Dao();
//
//		if (email != null && password != null) {
//
//		try {
//
//			Kayttaja kayttaja = newdao.checkLogin(email, password);
//			HttpSession session = request.getSession(true);
//
//			out.println(kayttaja.getEmail());
//			out.println(kayttaja.getPassword());
//
//			if (kayttaja.getEmail().equals(email) && kayttaja.getPassword().equals(password)) {
//				String ok = "true";
//				session.setAttribute("kayttajaOk", ok);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/hello");
//				dispatcher.forward(request, response);
//			}
//
//			else {
//				out.print("Sorry, username or password error!");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Login.jsp");
//				dispatcher.forward(request, response);
//			}
//
////			if (kayttaja != null) {
//////			if (kayttaja != null && kayttaja.getEmail() == email && kayttaja.getPassword() == password) {
////				String ok = "true";
////				session.setAttribute("kayttajaOk", ok);
////				destPage = "/asd";
////			} else {
////				String message = "Väärä salasana tai sähköposti";
////				request.setAttribute("message", message);
////				destPage = "/jsp/Login.jsp";
////			}
//
////			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
////			dispatcher.forward(request, response);
//
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			out.println("tämä on virhe " + e);
//			throw new ServletException(e);
//
//		}
//	}
//		else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Login.jsp");
//			dispatcher.forward(request, response);
//		}
//	}
//}
