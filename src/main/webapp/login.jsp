<%-- 
    Document   : l
    Created on : Nov 2, 2020, 7:43:40 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("login" + session.getAttribute("id")) == null) {
        session.setAttribute("message", "You are not logged in");
        response.sendRedirect("index.jsp");
    }
%>
