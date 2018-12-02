<%@ page import="com.gmail.ndonskih63.details.ThisPost" %><%--
  Created by IntelliJ IDEA.
  User: Nikolas
  Date: 29.11.2018
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ThisPost thisPost = (ThisPost) session.getAttribute("post");%>
<html>
    <head>
         <title>Redact</title>
    </head>
    <body>
        <hr color="#b93a0b" size="4">
    <center>
        <table border="1">
            <td>
                <form action="/save" method="POST">
                    <textarea rows="30" cols="80" name="text"><%=thisPost.getText()%></textarea><br>
                    <input type="hidden" name="title" value="<%= thisPost.getId()%>">
                    <center><input type="submit" value="Save"/></center>
                </form>
            </td>
        </table>
    </center>
    </body>
</html>
