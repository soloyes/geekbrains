<%@ page import="xyz.solovev.enterprise.utils.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <%
        final String pageAttribute = (String) request.getAttribute(Attributes.PAGE_ATTRIBUTE);
    %>
    <title><%=pageAttribute%>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
