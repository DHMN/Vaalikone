package vaalikone;

import java.io.IOException;
import java.io.NotSerializableException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import data.Kayttaja;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
        // TODO Auto-generated constructor stub
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
        String password = request.getParameter("password");
         
        Dao newdao = new Dao();
       
        
         
			try {
			Kayttaja kayttaja = newdao.checkLogin(email, password);
            String destPage = "/";
            HttpSession session = request.getSession(false);
   
             
            if (kayttaja != null) {
            	String ok = "true";
                session.setAttribute("kayttajaOk", ok);
                destPage = "/";
            } else {
                String message = "Väärä salasana tai sähköposti";
                request.setAttribute("message", message);
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
           
            
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				throw new ServletException(e);
           
        }
	}
}
