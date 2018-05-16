<%@ page import="com.neu.youdontknow.models.User" %><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 4/25/18
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        User user = (User)(request.getSession().getAttribute("user"));
    %>
    <h1><%
        if(null != user) {
            out.println(user.getId());
            out.println(user.getUsername());
            out.println(user.getPassword());
            out.println(user.getEmail());
        } else {
            out.print("You haven't login yet!");
        }
    %></h1>
</body>
</html>
