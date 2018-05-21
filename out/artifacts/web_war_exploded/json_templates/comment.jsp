<%@ page import="com.neu.youdontknow.models.Comment"%><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/15/18
  Time: 2:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
    Comment comment = (Comment)(request.getAttribute("comment"));
    request.removeAttribute("comment");
%>
{
    "user_id":<%out.print(comment.getUser_id());%>,
    "comment_id":<%out.print(comment.getId());%>,
    "response_to_comment":<%out.print(comment.getComment_id());%>,
    "comment":<%out.print("\""+comment.getComment()+"\"");%>,
    "published_date":<%out.print("\""+comment.getPublished_date()+"\"");%>,
    "published_time":<%out.print("\""+comment.getPublished_time()+"\"");%>
}