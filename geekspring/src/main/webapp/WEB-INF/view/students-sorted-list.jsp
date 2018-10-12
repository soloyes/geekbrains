<%@ page import="com.geekbrains.entities.Student" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    final Object pageAttribute = request.getAttribute("studentsMap");
    final Map<Student, Integer> students = (Map<Student, Integer>) pageAttribute;
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Students List</title>
</head>
<body>
<div align="center">
    <h1>Students List</h1>
    <table border="1">
        <th>Id</th>
        <th>Name</th>
        <th>Info</th>

        <%for (Map.Entry<Student, Integer> e : students.entrySet()) {%>
        <tr>
            <td><%=e.getKey().getId()%></td>
            <td><%=e.getKey().getName()%></td>
            <td><%=e.getValue()%></td>
        </tr>
        <%}%>
    </table>
    <br>
    <a href="/">Back To Main Page</a>
</div>
</body>
</html>