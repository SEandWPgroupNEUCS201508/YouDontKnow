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
    String forum = (String)(request.getAttribute("forum"));
    int pre_last_id = (int)(request.getAttribute("last_id"));
    int limit_num = (int)(request.getAttribute("limit_num"));
    List<Article> articles = (List<Article>)(request.getAttribute("article_list"));
    int length = articles.size();
%>
{
    "forum":<%out.print("\"" + forum + "\"");%>,
    "pre_last_id":<%out.print(pre_last_id);%>,
    "limit_num":<%out.print(limit_num);%>,
    "article_node_list": [<%
        if(articles == null || articles.isEmpty()){

        }else{
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
                length--;
                if(length > 0) out.print(',');
            }
        }
    %>]
}