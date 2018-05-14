<%@ page import="com.neu.youdontknow.models.User" %><%--
  Created by IntelliJ IDEA.
  User: ddqszcxy
  Date: 4/25/18
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat Test</title>
    <script>
        var socket = new WebSocket("ws://localhost:8080/chat");
        // accept the msg from server
        socket.onmessage=function(ev){
            var obj =  JSON.parse(ev.data);
            document.getElementById("getmessage").value=obj.message;
            document.getElementById("time").value=obj.time;
        }
        var sendevent = function (){
            // get content of the input form
            alert("send success");
            // construct a json
            var obj = JSON.stringify({
                source:"2",
                destination:"1",
                message:"hello"
            });
            socket.send(obj);
        }
    </script>
</head>
<body>
    <%
        User user = (User)(request.getSession().getAttribute("user"));
    %>
    <span id = "userid"><script>document.getCookie("userId")</script></span>
    <form>
        destination:<input type="text" name="destination" id="destination"><br>
        message:<input type="text" name="message" id="message" /><br>
        getmessage: <input type="text" name="getmessage" id="getmessage" /><br>
        time:<input type="text", name="time", id="time"/><br>
        <button id="send" type="button" name="submit" onclick="sendevent()" >send message</button>
    </form>
</body>
</html>
