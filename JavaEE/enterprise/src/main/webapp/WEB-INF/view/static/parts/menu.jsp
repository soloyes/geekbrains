<%@ page import="xyz.solovev.enterprise.utils.Attributes" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final String pageAttribute = (String) request.getAttribute(Attributes.PAGE_ATTRIBUTE);
%>
<nav class="navbar navbar-expand-lg navbar-light bg-info">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item <% if (pageAttribute.equals(Attributes.MAIN)) {%>active <%}%>">
                <a class="nav-link" href="/"><%=Attributes.MAIN%></a>
            </li>
            <li class="nav-item <% if (pageAttribute.equals(Attributes.CATALOG)) {%>active <%}%>">
                <a class="nav-link" href="/catalog"><%=Attributes.CATALOG%></a>
            </li>
            <li class="nav-item <% if (pageAttribute.equals(Attributes.CART)) {%>active <%}%>">
                <a class="nav-link" href="/cart"><%=Attributes.CART%></a>
            </li>
            <li class="nav-item <% if (pageAttribute.equals(Attributes.ORDERS)) {%>active <%}%>">
                <a class="nav-link" href="/orders"><%=Attributes.ORDERS%></a>
            </li>
            <li class="nav-item <% if (pageAttribute.equals(Attributes.PRODUCTS)) {%>active <%}%>">
                <a class="nav-link" href="/products"><%=Attributes.PRODUCTS%></a>
            </li>
            <li class="nav-item <% if (pageAttribute.equals(Attributes.CHECKOUT)) {%>active <%}%>">
                <a class="nav-link" href="/checkout"><%=Attributes.CHECKOUT%></a>
            </li>
        </ul>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>