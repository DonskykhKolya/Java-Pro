<%@ page import="java.util.HashMap" %>
<%@ page import="com.gmail.ndonskih63.details.ThisPost" %>

<%--
  Created by IntelliJ IDEA.
  User: Nikolas
  Date: 28.11.2018
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome in Blog!</title>
  </head>
    <body>
      <form action="/enter" method="POST">
        <p align="center"><input type="text" name="login" placeholder="Login"></p>
        <p align="center"><input type="password" name="password" placeholder="Password"></p>
        <p align="center"><input type="submit" value="Enter"></p>
      </form>
    </body>
</html>
