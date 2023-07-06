package controller.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dbconnection.DBConnection;
import resources.myConstants;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveFromCartServlet" })
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);

	        boolean loggedIn = session != null && session.getAttribute("user") != null;
	        if (loggedIn){
	        	PrintWriter out = response.getWriter();
	        	String user = String.valueOf(session.getAttribute("user"));
	    		String userEmail = user +"@gmail.com";

	    		DBConnection con = new DBConnection();
	    		String userID = con.selectAllQueryByID(myConstants.Get_User_ID_By_Email, userEmail);	            
	            String cartId = con.getCartIDFromUser(userID);
	            String productID = request.getParameter("remove");
	            Boolean remove= con.removeProductFromCart(productID, cartId);
	            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/cart.jsp");

	        }
	            
	}
	

}
