<%--
  Created by IntelliJ IDEA.
  User: Chengxumiao
  Date: 2023/3/6
  Time: 18:26
  To change this template use File | Settings | File Templates.


  说明：
      此页面为用户的注册页面，实现用户的注册功能
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to ChickenOlderBrother</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
<form action="registerServlet" method="post">
<div id="register-box">
    <h1>Register</h1>
    <%--

        此部分为用户输入注册信息部分

        功能:
            1、用户注册的用户名，密码，身份证，真实姓名信息的输入
            2、用户错误输入的提示信息展示
            3、用户的输入信息保留(保证用户输入信息被验证时不受服务器请求转发所清空影响)
            4、提交注册信息

        实现：
            1、第一项功能实现:采用input标签实现
            2、第二项功能实现：采用el技术提取请求域中所存储的“error_pool”Map错误集合，然后提取对应的各自输入信息框所对应的key来获取错误value值，达到向用户展示错误输入的提示
            3、第三项功能实现：采用el技术配合input标签中的value属性，提取param域中各输入框所对应的key的value信息（即用户表单中输入的信息），将提取到的信息放在input标签的value属性中，达到对用户输入信息的保留
            4、第四项功能实现：采用form表单中的submit类型按钮触发提交用户信息提交功能，配合from表单收集用户输入的信息达到将用户信息提交到后台

    --%>
    <div class="form">
        <div class="item">
            <i class="fa fa-github-alt" style="font-size:24px"></i>
            <input type="text" placeholder="Username" name="register_username" value="${param.get("register_username")}">
            <div class="error">${pageContext.request.getAttribute("error_pool").username_illegal}
                ${pageContext.request.getAttribute("error_pool").username_null}
                ${pageContext.request.getAttribute("error_pool").username_exist}</div>
        </div>
        <div class="item">
            <i class="fa fa-search" style="font-size:24px"></i>
            <input type="text" placeholder="Password" name="register_password" value="${param.get("register_password")}">
            <div class="error">${pageContext.request.getAttribute("error_pool").password_illegal}
                ${pageContext.request.getAttribute("error_pool").password_null}
                ${pageContext.request.getAttribute("error_pool").password_exist}</div>
        </div>
        <div class="item">
            <i class="fa fa-search" style="font-size:24px"></i>
            <input type="text" placeholder="IdCard" name="register_IdCard" value="${param.get("register_IdCard")}">
            <div class="error">${pageContext.request.getAttribute("error_pool").idCard_illegal}
                ${pageContext.request.getAttribute("error_pool").idCard_null}
                ${pageContext.request.getAttribute("error_pool").idCard_exist}</div>
        </div>
        <div class="item">
            <i class="fa fa-search" style="font-size:24px"></i>
            <input type="text" placeholder="YouName" name="register_YourName" value="${param.get("register_YourName")}">
            <div class="error">${pageContext.request.getAttribute("error_pool").yourName_null}</div>
        </div>
    </div>
    <button type="submit">Register</button>
</div>
</form>
</body>
</html>
