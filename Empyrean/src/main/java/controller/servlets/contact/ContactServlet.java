package controller.servlets.contact;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dbconnection.DBConnection;
import resources.myConstants;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ContactServlet" })
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String message = request.getParameter("comment");
		DBConnection con = new DBConnection();
		String uID =  con.selectAllQueryByID(myConstants.Get_User_ID_By_Email, email);
		if(!uID.isEmpty()) {
			if(con.enterIntoQueries(uID, message)) {
	            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/contact.jsp");
			}
			else {
				request.setAttribute("errorMessage", "Message could not be sent");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/contact.jsp");
	            dispatcher.forward((ServletRequest)request, (ServletResponse)response);
			}
		}
		
		PrintWriter out = response.getWriter();
		out.print(name+email+phone+message);

	}

}
