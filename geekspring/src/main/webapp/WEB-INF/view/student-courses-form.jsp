<%@ page import="com.geekbrains.entities.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.geekbrains.entities.Course" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%
    final Object pageAttribute = request.getAttribute("courses");
    final List<Course> courseList = (List<Course>) pageAttribute;
%>

<html>
<body>
<h1>Student Courses Result:</h1>
<br>
<ul>
    <%for (Course c : courseList) {%>
    <li><%=c.getTitle()%>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>