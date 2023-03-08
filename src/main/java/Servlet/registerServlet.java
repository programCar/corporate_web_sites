package Servlet;

import Dao.Insert;
import InspectTool.InspectRegisterInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 注册控制器，捕获注册页面所提交的信息，配合JavaBean作出相应的处理
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决捕获信息中文乱码问题
        req.setCharacterEncoding("utf-8");

        /**
         * 捕获注册页面提交的信息
         * registerUsername 注册的用户名信息
         * registerPassword 注册的用户密码信息
         * registerIdCard   注册的用户身份证信息
         * registerYouName  注册的用户真实姓名问题
         */
        String registerUsername = req.getParameter("register_username");

        String registerPassword = req.getParameter("register_password");

        String registerIdCard = req.getParameter("register_IdCard");

        String registerYouName = req.getParameter("register_YourName");

        //针对用户输入信息可能错误，获取已经处理好的用户注册信息错误提示的收集池子
        Map<String, String> error_pool = InspectRegisterInfo.inspectRegisterInfo(registerUsername, registerPassword, registerIdCard,registerYouName);

        //如果错误收集池子不为空，说明用户输入信息存在错误，将此池子存放进请求域中，请求转发到注册页面
        if (!error_pool.isEmpty()){
            req.setAttribute("error_pool",error_pool);
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }

        //假如此次用户输入信息没有错误，删除上次请求域中已存的错误收集池子
        req.removeAttribute("error_pool");

        //将用户的注册信息注册到数据库
        Insert.insert(registerUsername,registerPassword,registerIdCard,registerYouName);


        //将注册成功的用户信息存在请求域中
        req.setAttribute("login_username",registerUsername);

        //将注册成功的用户信息存在请求域中
        req.setAttribute("login_password",registerPassword);

        //请求转发到自动登录控制器
        req.getRequestDispatcher("AutoLoginServlet").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
