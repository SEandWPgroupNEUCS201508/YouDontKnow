<%@ page import="com.neu.youdontknow.models.Article"%><%@ page import="java.util.List"%><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/21/18
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/jsp;charset=UTF-8" language="java" %>
<%
    List<Article> resList = (List<Article>)request.getAttribute("resList");
    request.removeAttribute("resList");
    String word = (String)request.getAttribute("word");
    request.removeAttribute("word");
    int length = resList.size();
%>
{
    "search_word":<%out.print(word);%>,
    "search_list":[<%
        for(Article article : resList) {
            out.print('{');
            out.print("\"user_id\":");
            out.print(article.getUser_id());
            out.print(",\"article_id\":");
            out.print(article.getId());
            out.print(",\"title\":");
            out.print("\"" + article.getTitle() + "\"");
            out.print(",\"content\":");
            out.print("\"" + article.getContent() + "\"");
            out.print(",\"published_date\":");
            out.print("\"" + article.getPublished_date() + "\"");
            out.print(",\"published_time\":");
            out.print("\"" + article.getPublished_time() + "\"");
            out.print("}");
            length--;
            if(length > 0) out.print(',');
        }
    %>]
}