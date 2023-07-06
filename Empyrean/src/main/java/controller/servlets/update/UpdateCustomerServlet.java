package controller.servlets.update;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.dbconnection.DBConnection;
import model.PasswordEncryption;
import model.User;
import resources.myConstants;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updateCustomerServlet" })
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2, 
maxFileSize=1024 * 1024 * 10, 
maxRequestSize=1024 * 1024 * 10)

public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ServletContext context;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		context = getServletContext();
		DBConnection con = new DBConnection();
		HttpSession session = request.getSession(false);
		String previousEmail = (String.valueOf(session.getAttribute("user"))).concat("@gmail.com");
		String fname = request.getParameter("user_Fname");
		String lname = request.getParameter("user_Lname");
		String address = request.getParameter("user_address");
		String email = request.getParameter("user_email");
		String contact = request.getParameter("user_contact");
		String birthDate = request.getParameter("user_birthDate");
		String password = request.getParameter("user_password");
		Part imageURL =  request.getPart("profileURL");
		
		if(password.length()>30) {
			password = PasswordEncryption.decrypt(password, previousEmail);
		}

		if(imageURL.getSize()==0) {
			int result = con.updateCustomerDetailsNoUrl(myConstants.UPDATE_CUSTOMER_DETAILS, fname, lname, address, email, contact, birthDate, previousEmail,password);
			this.context.log(String.valueOf(result));
			if(result == 1) {
				request.setAttribute("updated", "True");
				session.setAttribute("user", (Object)email.replace("@gmail.com", ""));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/profile.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("errorDuringUpdate", "True");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/profile.jsp");
				dispatcher.forward(request, response);
			}
		}else{
			
			User user = new User();
			user.setImageUrlFromPart(imageURL);
			
			String savePath = myConstants.IMAGE_DIR_SAVE_PATH;
	        String fileName = user.getImageUrlFromPart();
	        if (!fileName.isEmpty() && fileName != null) {
	            imageURL.write(savePath + fileName);
	            
	        }
			int result = con.updateCustomerDetails(myConstants.UPDATE_CUSTOMER_DETAILS_URL, fname, lname, address, email, contact, birthDate,previousEmail,password,fileName );
			this.context.log(String.valueOf(result));
			if(result == 1) {
				request.setAttribute("updated", "True");
				session.setAttribute("user", (Object)email.replace("@gmail.com", ""));
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/profile.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("errorDuringUpdate", "True");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/profile.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		
	
	}

}
