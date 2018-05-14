<%@ page import="com.neu.youdontknow.models.Article" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/15/18
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
    String forum = (String)(request.getSession().getAttribute("forum"));
    int pre_last_id = (int)(request.getSession().getAttribute("last_id"));
    int limit_num = (int)(request.getSession().getAttribute("limit_num"));
    List<Article> articles = (List<Article>)(request.getSession().getAttribute("article_list"));
    request.removeAttribute("forum");
    request.removeAttribute("last_id");
    request.removeAttribute("limit_num");
    request.removeAttribute("article_list");
%>
{
    "forum":<%out.print(forum);%>,
    "pre_last_id":<%out.print(pre_last_id);%>,
    "limit_num":<%out.print(limit_num);%>,
    "article_node_list": [<%
        for(Article article: articles) {
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
            limit_num --;
            if(limit_num != 0) out.print(',');
        }
    %>]
}