<%-- 
    Document   : main
    Created on : Oct 28, 2020, 7:06:10 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game bài tiến lên</title>
        <link rel="stylesheet" type="text/css" href="css/homePage.css"/>
    </head>
    <body>
        <div class="frameHomePage">
            <table>
                <tr>
                    <td style="width: 40%">
                        <div>Logo</div>
                        <ul>
                            <li class="titleRank">Ranking list</li>
                            <%
                                for(int i = 1; i <6; i++) {
                                    out.print("<li>");
                                    out.print("<div class='numRank'>"+i+"</div>");
                                    out.print("<div class='avatar'></div>");
                                    out.print("<div class='infoUser'>");
                                    out.print("<div class='name'>Name</div>");
                                    out.print("<div class='money'>100k</div>");
                                    out.print("</div>");
                                    out.print("</li>");
                                }
                            %>
                        </ul>
                    </td>
                    <td></td>
                    <td style="width: 40%"></td>
                </tr>
                <tr>
                    <td colspan="3"></td>
                </tr>
            </table>
        </div>
    </body>
</html>
