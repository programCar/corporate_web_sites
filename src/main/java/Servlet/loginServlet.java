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

/**
 * 登录控制器，捕获登录页面所提交的信息，配合JavaBean作出相应的处理
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决捕获信息中文乱码问题
        req.setCharacterEncoding("utf-8");

        /**
         * 获取前端提交的信息
         * login_username 登录用户名
         * login_password 登录用户密码
         */

        String login_username = req.getParameter("login_username");

        String login_password = req.getParameter("login_password");

        HttpSession session = req.getSession();

        //获取login_username所对应数据库用户信息
        User user = Select.selectPerson(login_username,Select.SELECT_Person_SQL);

        //如果为空，则表示用户信息不存在，请求转发到前端，以提示用户
        if (user == null){
            session.setAttribute("loginError","error");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        //如果login_username所对应数据库用户信息存在，则校检登录密码是否正确，如果密码不正确，则请求转发到前端，以提示用户
        if (!user.getPassword().equals(login_password)){
            session.setAttribute("loginError","error");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        //如果登录信息正确，则将用户信息保存在请求域中
        req.setAttribute("login_username",login_username);

        req.setAttribute("login_password",login_password);

        //跳转到自动登录控制器
        req.getRequestDispatcher("AutoLoginServlet").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
