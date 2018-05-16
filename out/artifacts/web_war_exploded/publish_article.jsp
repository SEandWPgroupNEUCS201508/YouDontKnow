<%@ page import="com.neu.youdontknow.models.User" %><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/11/18
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pub aricle test</title>
    <h1><%
        User user = (User)(request.getSession().getAttribute("user"));
        if(null != user) {
            out.println(user.getId());
            out.println(user.getUsername());
            out.println(user.getPassword());
            out.println(user.getEmail());
        } else {
            out.print("<h1>You haven't login yet!</h1>");
        }
    %></h1>
</head>
<body>
<form action="/publish_article" method="post">
    <%--<label for="username">用户名</label>--%>
    <%--<input type="text"  id="username" placeholder="请输入用户名" name="username"><br/>--%>
    <%--<label for="password">密码</label>--%>
    <%--<input type="password" id="password" placeholder="请输入密码" name="password"><br/>--%>
    <%--<input type="submit"  width="100" value="登录" name="submit" border="0"/>--%>
    <%--private int id;--%>
    <%--private String title;--%>
    <%--private String content;--%>
    <%--private int user_id;--%>
    <%--private String forum;--%>
    <%--private Date published_date;--%>
    <%--private Time published_time;    --%>
        <label for="title">article title</label>
        <input type="text" id="title" placeholder="this is title" name="title"><br>
        <label for="content">content</label>
        <input type="text" id="content" placeholder="this is content" name="content"><br>
        <input type="hidden" value="<%out.print(user.getId());%>" id="user_id" name="user_id">
        <input type="hidden" value="test" id="forum" name="forum">
        <input type="submit"  width="100" value="publish" name="submit" border="0"><br>
</form>
</body>
</html>
