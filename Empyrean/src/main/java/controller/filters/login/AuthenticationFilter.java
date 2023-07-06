package controller.filters.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dbconnection.DBConnection;

public class AuthenticationFilter
implements Filter {
    private ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String uri = req.getRequestURI();
        DBConnection con = new DBConnection();
        
        boolean isLoginJsp = uri.endsWith("login.jsp");
        boolean isLoginServlet = uri.endsWith("loginServlet");
        boolean isLogoutServlet = uri.endsWith("logoutServlet");
        boolean isSignJsp = uri.endsWith("signin.jsp");
        boolean isSignInServlet = uri.endsWith("signInServlet");
        boolean isCartJsp = uri.endsWith("cart.jsp");
        boolean isAdminJsp = uri.endsWith("admin.jsp");
        boolean isLoggedInAdmin = false;
        this.context.log("Requested resource" + uri);
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        if(loggedIn) {
        	String userEmail = String.valueOf(session.getAttribute("user"))+"@gmail.com";
        	isLoggedInAdmin = con.getUserRole(userEmail);
        }
        if ((loggedIn && isSignJsp && !isLogoutServlet) && (!uri.contains("css") && uri.contains("img"))) {
            res.sendRedirect(String.valueOf(req.getContextPath()) + "/pages/home.jsp");
        } else if (loggedIn && isLoginJsp && !isSignJsp){
            res.sendRedirect(String.valueOf(req.getContextPath()) + "/pages/home.jsp");
        } else if (!loggedIn && isCartJsp) {
            res.sendRedirect(String.valueOf(req.getContextPath()) + "/pages/login.jsp");
        
        }else if(!isLoggedInAdmin && isAdminJsp) {
            res.sendRedirect(String.valueOf(req.getContextPath()) + "/pages/home.jsp");
        }
        else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}