package controller.servlets.signin;

import controller.dbconnection.DBConnection;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.User;
import resources.myConstants;

@WebServlet(asyncSupported=true, urlPatterns={"/signInServlet"})
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2, 
maxFileSize=1024 * 1024 * 10, 
maxRequestSize=1024 * 1024 * 10)
public class SignInServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection con= new DBConnection();
        String fName = request.getParameter("userFName");
        String lName = request.getParameter("userLName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        String contactNo = request.getParameter("userPhone");
        String userAdd = request.getParameter("userAddress");
        String userCCN = request.getParameter("userCCN");
        String userBirth = request.getParameter("userBirth");
        Part userImg = request.getPart("userImg");
        
        User user = new User(fName, lName, userAdd, email, password, userImg, userCCN, userBirth, contactNo);
        String savePath = myConstants.IMAGE_DIR_SAVE_PATH;
        String fileName = user.getImageUrlFromPart();
        if (!fileName.isEmpty() && fileName != null) {
            userImg.write(savePath + fileName);
        }
        
        if (con.isUserRegistered(email)) {
        	con.registerNewUser(user);
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/login.jsp");

        } else {
			request.setAttribute("errorMessage", "User Email already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/signIn.jsp");
            dispatcher.forward((ServletRequest)request, (ServletResponse)response);

        }
    }
}