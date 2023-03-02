<%--
  Created by IntelliJ IDEA.
  User: YYY
  Date: 2023/3/2
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<form action="loginServlet" method="post">

  <div class="outline_border">

    <div class="headline">
          登录
    </div>

    <div class="login_box">
      用户名:<input type="text" name="username">
    </div>

    <div class="login_box">
      密码:<input type="password" name="password">
    </div>

      <div class="btn_commit">
        <button type="submit">登录</button>
      </div>

  </div>


</form>

</body>
</html>
