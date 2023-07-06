package controller.servlets.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dbconnection.DBConnection;
import resources.myConstants;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddToCartServlet" })
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        if (loggedIn){
        	PrintWriter out = response.getWriter();

        	String user = String.valueOf(session.getAttribute("user"));
    		String pID = request.getParameter("toCart");
    		
    		String userEmail = user +"@gmail.com";

    		DBConnection con = new DBConnection();
    		String userID = con.selectAllQueryByID(myConstants.Get_User_ID_By_Email, userEmail);
            String cartID = con.selectAllQueryByID(myConstants.Get_All_Cart_By_User_ID, userID);
            if(cartID == null) {
            	out.println(con.enterIntoCart(userID));
                String cartID1 = con.selectAllQueryByID(myConstants.Get_All_Cart_By_User_ID, userID);
            	con.enterIntoCartProducts(cartID1, pID);
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/product.jsp");
            }
            else{
            	if(!con.isDuplicateCartProduct(cartID, pID)) {
                	con.enterIntoCartProducts(cartID, pID);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/product.jsp");
            	}
            	else {
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/home.jsp");
            	}
            }
        }
        else {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/error.jsp");

        }
		
		
	}

}