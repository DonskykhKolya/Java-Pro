<%--
  Created by IntelliJ IDEA.
  User: Nikolas
  Date: 29.11.2018
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Publish</title>
</head>
<body>
<form action="/save" method="POST">
    <hr color="#b93a0b" size="4">
    <center>
        <table border="1">
            <p align="center"><tr>
                <th>
                    <input type="text" name="Title" placeholder="Write title">
                </th>
                <td>
                    <textarea rows="20" cols="80" name="Text" placeholder="Write your text..."></textarea>
                </td>
            </tr></p>
        </table>
    </center>
    <center><input type="submit" value="Save"/></center>
</form>
</body>
</html>
