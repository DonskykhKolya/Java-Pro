<%@ page import="com.gmail.ndonskih63.details.ThisPost" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gmail.ndonskih63.details.CompareToDate" %>
<%--
  Created by IntelliJ IDEA.
  User: Nikolas
  Date: 29.11.2018
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<center>
    <p align="left"><big><b>Welcome</b></big> <em><u><%=session.getAttribute("login")%>!</u></em></p>
    <form action="/logout" method="GET">
        <p align="right"><input type="submit" value="Logout"></p>
    </form>
    <hr color="#b93a0b" size="4">
    <h2>Posts:</h2>
    <%List<ThisPost> pst = (List<ThisPost>) session.getAttribute("posts");%>
    <%pst.sort(new CompareToDate());%>
    <table bordercolor="#126274" border="1" width="100%">
        <tr>
            <th>
                Title:
            </th>
            <th>
                Text:
            </th>
            <th>
                Date:
            </th>
            <th>
                Username:
            </th>
            <th>
                Options:
            </th>

        </tr>
        <%for (ThisPost thisPost : pst) {%>
        <tr>
            <td width="20%">
                <p align="center"><%=thisPost.getId()%></p>
            </td>
            <td width="50%">
                <p align="center"><%=thisPost.getText()%></p>
            </td>
            <td width="15%">
                <p align="center"><%=thisPost.getDate()%></p>
            </td>
            <td width="10%">
                <p align="center"><%=thisPost.getUser().getUsername()%></p>
            </td>
            <td width="5%">
                <div style="text-align: center;">
                    <%
                        if (session.getAttribute("login").equals("admin") ||
                                thisPost.getUser().getUsername().equals(session.getAttribute("login"))) {
                    %>
                    <form action="/redact" method="POST">
                        <input type="hidden" name="title" value="<%=thisPost.getId()%>">
                        <input type="submit" value="Redact"/>
                    </form>
                    <form action="/remove" method="POST">
                        <input type="hidden" name="title" value="<%=thisPost.getId()%>">
                        <input type="submit" value="Remove"/>
                    </form>
                    <%}%>
                </div>
            </td>
        </tr>
        <%}%>
    </table>
    <center>
        <form action="/publish" method="POST">
            <input type="submit" value="Publish"/>
        </form>
    </center>
</center>
</body>
</html>
