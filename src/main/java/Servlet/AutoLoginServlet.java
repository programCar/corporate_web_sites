package Servlet;

import Perple.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AutoLoginServlet")
public class AutoLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        session.removeAttribute("loginNull");

        session.setMaxInactiveInterval(60*10);

        session.setAttribute("loginOk",new User(req.getParameter("username"),req.getParameter("password")));

        Cookie cookie = new Cookie("JSESSIONID", session.getId());

        cookie.setMaxAge(60*10);

        cookie.setPath("/");

        resp.addCookie(cookie);

        req.getRequestDispatcher("login.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
