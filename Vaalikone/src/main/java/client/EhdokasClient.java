package client;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import data.*;


/**
 * Servlet implementation class Ehdokas
 */

@WebServlet(
	    name = "EhdokasClient",
	    urlPatterns = { "/ehdokasclient" }
	)
public class EhdokasClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EhdokasClient() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
       
        String deleteId=request.getParameter("deleteId");
       
        //Including an HTML form + start of the html page
        RequestDispatcher rd=request.getRequestDispatcher("form.html");
        rd.include(request, response);
       
        boolean deleteOk=false;
        if (deleteId!=null) {
            if (deleteEhdokas(deleteId)) {
                out.println("Ehdokas has been removed!<br>");
            }
        }
       
       
        // TODO Auto-generated method stub
        String uri = "http://127.0.0.1:8080/rest/ehdokasservice/getall";

        Client asiakas=ClientBuilder.newClient();
        WebTarget wt=asiakas.target(uri);
        Builder b=wt.request();
       
        //Create a GenericType to be able to get List of objects
        //This will be the second parameter of post method
        GenericType<List<Ehdokas>> genericList = new GenericType<List<Ehdokas>>() {};
       
        //Posting data (Entity<ArrayList<DogBreed>> e) to the given address
        //List<Ehdokas> returnedList=b.get(genericList);
       
        //And print the objects
        //for (int i=0;i<returnedList.size();i++) {
            //Ehdokas ehdokas=returnedList.get(i);
            //out.println(ehdokas+" <a href='./ehdokasclient?deleteId="+ehdokas.getEhdokasNro()+"'>Remove</a><br>");
        //}
       
        //Printing the end of an html document
        //out.println("</body></html>");
    }

    private boolean deleteEhdokas(String deleteId) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        String uri = "http://127.0.0.1:8080/rest/bookservice/delete/"+deleteId;

        Client asiakas=ClientBuilder.newClient();
        WebTarget wt=asiakas.target(uri);
        Builder b=wt.request();
       
        //Posting data (Entity<ArrayList<DogBreed>> e) to the given address
        boolean ok=b.delete(Boolean.class);
        return ok;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   doGet(request, response);

}
}