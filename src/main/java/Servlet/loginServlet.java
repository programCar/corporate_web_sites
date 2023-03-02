package Servlet;

import Dao.Select;
import Perple.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        User user = Select.selectPerson(username);

        if (user == null){
            session.setAttribute("loginNull",null);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        req.getRequestDispatcher("AutoLoginServlet").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
