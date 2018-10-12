<%@ page import="java.util.List" %>
<%@ page import="com.geekbrains.entities.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%
    final Object pageAttribute = request.getAttribute("students");
    final List<Student> studentList = (List<Student>) pageAttribute;
%>

<html>
<body>
<h1>Course Students Result:</h1>
<br>
<h1>Total Students: <%=studentList.size()%>
</h1>
<ul>
    <%for (Student s : studentList) {%>
    <li><%=s.getName()%>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>