<%-- 
    Document   : main
    Created on : Oct 28, 2020, 7:06:10 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="login.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game bài tiến lên</title>
        <link rel="stylesheet" type="text/css" href="css/home.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <body>
        <div class="frameHomePage">
            <table>
                <tr>
                    <td style="width: 40%">
                        <ul>
                            <li class="titleRank">Ranking list</li>
                            <%
                                for(int i = 1; i < 6; i++) {
                                    String sClass = "userRank" + i;
                                    out.print("<li class='userRank "+sClass+"'>");
                                    out.print("<table>"
                                                + "<tr>"
                                                    + "<td style='1%'><div class='numRank'>"+i+"</div></td>"
                                                    + "<td style='width: 40px'><div class='avatar'></div></td>"
                                                    + "<td>"
                                                        + "<div class='infoUser'>"
                                                            + "<div class='name'>Name</div>"
                                                            + "<div class='money'>100k</div"
                                                        + "</div>"
                                                    + "</td>"
                                                + "</tr>"
                                            + "</table>");
                                    out.print("</li>");
                                }
                            %>
                        </ul>
                    </td>
                    <td></td>
                    <td style="width: 35%; text-align: right; padding-right: 40px">
                        <button class="btn btnActionHome play">PLAY NOW</button>
                        <br>
                        <button class="btn btnActionHome room">FIND ROOM</button>
                        
                        <div style="text-align: center; width: 100%; margin-top: 10px">
                            <div class="frameMyInfo">
                                <div class="avatar"></div>
                                <div class="name"><strong>Name: </strong><%= session.getAttribute("name") %></div>
                                <div class="money"><strong>Coin: </strong>100</div>
                                <div class="btnActionBar">
                                    <i class="fa fa-info-circle"></i> &nbsp;
                                    <i class="fa fa-user-circle"></i> &nbsp;
                                    <i class="fa fa-cog"></i> &nbsp;
                                    <a href="User"><i class="fa fa-sign-out"></i></a>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="3"></td>
                </tr>
            </table>
        </div>
    </body>
</html>
