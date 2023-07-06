
package controller.servlets.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(asyncSupported=true, urlPatterns={"/logoutServlet"})
public class LogoutServlet
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie[] arrcookie = cookies;
            int n = cookies.length;
            for (int i = 0; i < n; ++i) {
                Cookie cookie = arrcookie[i];
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/Empyrean");
                response.addCookie(cookie);
            }
        }
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/login.jsp");
    }
}