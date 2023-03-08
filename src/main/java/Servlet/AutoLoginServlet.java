package Servlet;

import Perple.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 自动登录控制器，实现用户的自动登录功能
 */
@WebServlet("/AutoLoginServlet")
public class AutoLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();

        //删除用户未登录的标识信息
        session.removeAttribute("loginError");

        //设置会话session存在服务器时间为10分钟
        session.setMaxInactiveInterval(60*10);

        //将用户信息的登录信息包装为 “User” JavaBean存进session域，以此实现为前端自动登录的用户展示提示或欢迎的信息提取
        session.setAttribute("loginOk",new User((String) req.getAttribute("login_username"), (String) req.getAttribute("login_password")));

        //设置客户端cookie中的session专属ID为此sessionID
        Cookie cookie = new Cookie("JSESSIONID", session.getId());

        //设置客户端cookie存活时间为10分钟
        cookie.setMaxAge(60*10);

        //客户端作用域为全局
        cookie.setPath("/");

        //为响应功能添加cookie对象
        resp.addCookie(cookie);

        //请求转发到首页
        req.getRequestDispatcher("index.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
