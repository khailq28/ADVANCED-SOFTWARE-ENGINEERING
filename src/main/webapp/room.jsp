<%-- 
    Document   : room
    Created on : Nov 17, 2020, 1:45:52 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="login.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Black Jack</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>

        <table>
            <tr>
                <td colspan="2">
                    <input type="hidden" id="username" value="<%= session.getAttribute("name") %>"/>
                </td>
            </tr>
            <tr>
                <td>
                    <textarea readonly="true" rows="10" cols="80" id="log"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" size="15" id="to" placeholder="To"/>
                    <input type="text" size="51" id="msg" placeholder="Message"/>
                    <button type="button" id="sendMsg" >Send</button>
                </td>
            </tr>
        </table>

    </body>

    <script src="js/room.js"></script>

</html>
