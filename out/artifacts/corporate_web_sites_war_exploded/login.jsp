<%--
  Created by IntelliJ IDEA.
  User: Chengxumiao
  Date: 2023/3/2
  Time: 23:25
  To change this template use File | Settings | File Templates.


  说明：
      此页面为用户的登录页面，实现用户的登录功能
      注意：
          如果用户在登录过的规定时间内，此页面将会被跳过，采取自动登录功能实现
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome to ChickenOlderBrother</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="webjars/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>

<form action="loginServlet" method="post">

<div id="login-box">

  <h1>Login</h1>

  <div class="form">

    <div class="item">

      <i class="fa fa-github-alt" style="font-size:24px"></i>
      <input type="text" placeholder="Username" name="login_username" value="${param.get('login_username')}">

    </div>

    <div class="item">

      <i class="fa fa-search" style="font-size:24px"></i>
      <input type="text" placeholder="Password" name="login_password" value="${param.get('login_password')}">

    </div>
  </div>
  <button type="submit">Login</button>
</div>

</form>
<script>
  $.ajax({
    success : function () {
      if (${sessionScope.get('loginError') != null}){
        ${sessionScope.remove('loginError')}
        if (!confirm('登录用户名或密码错误,请重新输入')) {
          window.location.href = 'index.jsp';
        }
      }

    }
  })
</script>
</body>
</html>
