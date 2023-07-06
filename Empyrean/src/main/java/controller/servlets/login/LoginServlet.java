package controller.servlets.login;

import controller.dbconnection.DBConnection;
import resources.myConstants;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(asyncSupported=true, urlPatterns={"/loginServlet"})
public class LoginServlet
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password= request.getParameter("userPass");
        DBConnection con = new DBConnection();
        String email = request.getParameter("userEmail");
        Boolean isUserRegistered = con.checkPassword(myConstants.Get_Password_By_Email, email, password );
        if (isUserRegistered != null && isUserRegistered.booleanValue()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", email.replace("@gmail.com", ""));
            session.setMaxInactiveInterval(3600);
            Cookie userName = new Cookie("user", email.replace("@gmail.com", ""));
            userName.setMaxAge(3600);
            response.addCookie(userName);
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid Username or Password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
            dispatcher.include((ServletRequest)request, (ServletResponse)response);
        }
    }
}