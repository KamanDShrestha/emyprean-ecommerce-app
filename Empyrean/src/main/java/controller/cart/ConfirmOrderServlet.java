package controller.cart;

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
import javax.servlet.http.HttpSession;

import controller.dbconnection.DBConnection;
import resources.myConstants;

/**
 * Servlet implementation class ConfirmOrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/confirmOrderServlet" })
public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	
	 	HttpSession session = request.getSession(false);
	 	DBConnection con = new DBConnection();
	 	String total = request.getParameter("total_cost");
    	String user = String.valueOf(session.getAttribute("user"));
		String userEmail = user +"@gmail.com";
		String userID = con.selectAllQueryByID(myConstants.Get_User_ID_By_Email, userEmail);
		if(con.enterIntoOrders(userID, total)) {
			String cartID = con.getCartIDFromUser(userID);
			if(con.removeCartProducts(cartID)) {
				con.removeCart(userID);
			}
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/home.jsp");
		}
		else{
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/error.jsp");
		}


	}

}
