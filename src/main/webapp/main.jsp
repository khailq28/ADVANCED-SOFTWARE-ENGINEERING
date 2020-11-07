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
        <link rel="stylesheet" type="text/css" href="css/homePage.css"/>
        <link href="css/loadingPrg.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <div id="loading" style="display: none;">
            <i class="fa fa-spinner fa-3x fa-spin"></i>
        </div>  
        
        <div class="frameHomePage">
            <table>
                <tr>
                    <td style="width: 40%">
                        <ul id="rankingList">
                           
                        </ul>
                    </td>
                    <td></td>
                    <td style="width: 35%; text-align: right; padding-right: 40px">
                        <button class="btn btnActionHome play" id="play">PLAY NOW</button>
                        <br>
                        <button class="btn btnActionHome room">FIND ROOM</button>

                        <div style="text-align: center; width: 100%; margin-top: 10px">
                            <div class="frameMyInfo">
                                <div class="avatar" id=""></div>
                                <div class="name" id="myname"></div>
                                <div class="money" id="mymoney"></div>
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
        <script src="js/homePage.js"></script>
    </body>
</html>
