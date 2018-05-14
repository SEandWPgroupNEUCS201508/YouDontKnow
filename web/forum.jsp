<%@ page import="com.neu.youdontknow.models.Article" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/13/18
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        List<Article> articleList = (List<Article>)request.getSession().getAttribute("article_list");
    %>
    <title>Test</title>
</head>
<body>
    <div><%
        if(articleList != null) {
            for (Article article : articleList) {
                out.print("<h1>");
                out.print("| user_id " + article.getUser_id() + " | ");
                out.print("title " + article.getTitle() + " | ");
                out.print("content " + article.getContent() + " | ");
                out.print("date " + article.getPublished_date() + " | ");
                out.print("time " + article.getPublished_time() + " | ");
                out.print("forum " + article.getForum() + " |");
                out.print("</h1><br>");
            }
        } else out.print("No article in test forum");
    %></div>
</body>
</html>
