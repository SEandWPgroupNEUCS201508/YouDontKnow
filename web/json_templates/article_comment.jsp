<%@ page import="java.util.List" %>
<%@ page import="com.neu.youdontknow.models.Comment" %><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/15/18
  Time: 2:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int article_id = (int)(request.getSession().getAttribute("article_id"));
    List<Comment> comments = (List<Comment>)(request.getSession().getAttribute("comment_list"));
    int len = comments.size();
%>
{
"article_id":<%out.print(article_id);%>,
"comment_list": [<%
    for(Comment comment: comments) {
        out.print('{');
        out.print("\"user_id\":");
        out.print(comment.getUser_id());
        out.print(",\"comment_id\":");
        out.print(comment.getId());
        out.print(",\"response_to_comment\":");
        out.print(comment.getComment_id());
        out.print(",\"comment\":");
        out.print("\"" + comment.getComment() + "\"");
        out.print(",\"published_date\":");
        out.print("\"" + comment.getPublished_date() + "\"");
        out.print(",\"published_time\":");
        out.print("\"" + comment.getPublished_time() + "\"");
        out.print("}");
        len --;
        if(len != 0) out.print(',');
    }
%>]
}
