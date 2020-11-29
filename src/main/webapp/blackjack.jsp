<%-- 
    Document   : playWithComputer
    Created on : Nov 22, 2020, 7:34:47 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="login.jsp" %>
<!doctype html>
<html>
    <head>
        <title>Black Jack</title>
        <link rel="stylesheet" type="text/css" href="css/loadingPrg.css"/>
        <link rel="stylesheet" href="css/blackjack.css" type="text/css" media="screen" title="no title" charset="utf-8">
        <link href="https://fonts.googleapis.com/css?family=Bad+Script|Courgette|Dancing+Script|Kalam|Marck+Script" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <meta charset="utf-8" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <script src="js/blackjack_bk.js"></script>
        <div id="loading" style="display: none;">
            <i class="fa fa-spinner fa-3x fa-spin"></i>
        </div>
        <div class="start">
            <table>
                <tr>
                    <th  style="padding: 10px">
                        <a href="main.jsp">
                            <i class="fa fa-home" aria-hidden="true"
                           style="color: white; font-size: 35px; width: 35px; height: 35px; "></i>
                        </a>
                    </th>
                    <th><img src="images/no-image.jpg" style="width: 130px; height: 130px; border: 5px solid orange;" ></th>
                    <th style="text-align: left; color: yellow; padding: 5px" id="info"></th>
                    <th id="bet"></th>
                </tr>
            </table>
            <form method="POST" action="">
                <table>
                    <tr>
                        <th>
                            <label for="startValues">Start Values:</label>
                            <input type="text" name="startValues" id="startValues"><br>
                            <label for="betAmount">Bet Amount:</label>
                            <input type="number" name="betAmount" id="betAmount">
                        </th>
                        <th>
                            <input class="playBtn" type="submit" value="Play">
                        </th> 
                    </tr>
                </table>
            </form>
        </div>
        <div class="game">
        </div>
    </body>
</html>
