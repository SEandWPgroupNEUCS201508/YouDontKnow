<%@ page import="com.neu.youdontknow.models.User" %><%--
  Created by IntelliJ IDEA.
  User: hallwood
  Date: 5/14/18
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
    <json:property name="user_id" value="${user.id}"/>
    <json:property name="username" value="${user.username}"/>
    <json:property name="email" value="${user.email}"/>
</json:object>
