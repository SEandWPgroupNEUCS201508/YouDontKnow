<%@ page import="com.neu.youdontknow.models.User" %><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/14/18
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
{
    <%
    User user = (User)request.getSession().getAttribute("user");
    if(user == null){
        out.print(",");
    }else{
        out.print("\"user_id\":" + user.getId());
        out.print(",\"username\":\"" + user.getUsername() + "\"");
        out.print(",\"email\":\"" + user.getEmail() + "\"");
    }
    %>
}
