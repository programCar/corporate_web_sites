<%--
  Created by IntelliJ IDEA.
  User: Chengxumiao
  Date: 2023/3/1
  Time: 22:35
  To change this template use File | Settings | File Templates.


  说明：
      此页面为首页页面展示的头部部分
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Welcome to ChickenOlderBrother</title>
    <link type="text/css" rel="stylesheet" href="css/index.css">
</head>
<body>
    <div class="div_01">

        <div class="div_02">
            <h1>你好,欢迎光临鸡哥网站公司</h1>
            <h2>做鸡，我们是认真的</h2>

            <%--
                作用：
                （1）协助自动登录功能的实现
                （2）局部更新：
                            1、为未登录或未注册的用户提供登录与注册的选项
                            2、为已登录的用户提高自动登录功能
                （3）采用技术：
                            1、采用jstl技术达成信息的局部更新功能
                            2、采用el技术与sevlet技术达成获取用户数据展示功能以及是否登录功能判断
            --%>
            <c:choose>
                <c:when test="${sessionScope.get('loginOk') == null}">
                    <a href="login.jsp">请登录</a>新用户!<a href="register.jsp">[账号注册]</a>
                </c:when>
                <c:otherwise>
                    <p>欢迎您，尊贵的${sessionScope.get('loginOk').getUsername()}用户来到鸡美基地</p>
                </c:otherwise>
            </c:choose>

        </div>

        <div class="div_03"><img src="img/chicken.png"></div>

    <div class="div_04">
    <a href="#">首页</a>&nbsp;|&nbsp;
        <a href="#">公司新闻</a>&nbsp;|&nbsp;
        <a href="#">产品展示</a>|&nbsp;
        <a href="#">在线咨询</a>&nbsp;|&nbsp;
        <a href="#">监督投诉</a>&nbsp;|&nbsp;
        <a href="#">员工之窗</a>&nbsp;| &nbsp;
            <a href="#">联系我们</a>
    </div>

    </div>
</body>
</html>
