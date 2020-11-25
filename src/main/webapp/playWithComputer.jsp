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
        <link rel="stylesheet" href="css/playGame.css" type="text/css" media="screen" title="no title" charset="utf-8">
        <link href="https://fonts.googleapis.com/css?family=Bad+Script|Courgette|Dancing+Script|Kalam|Marck+Script" rel="stylesheet">
        <meta charset="utf-8" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <script src="js/play.js"></script>
        <div class="start">
            <table>
                <tr>
                    <th><img src="images/no-image.jpg" style="width: 130px; height: 130px; border: 5px solid orange;" ></th>
                    <th style="text-align: left; color: white; padding: 5px" id="info"></th>
                    <th id="bet"></th>
                </tr>
            </table>
            <form method="POST" action="">
                <label for="betAmount">Bet Amount:</label>
                <input type="number" name="betAmount" id="betAmount">
                <input class="playBtn" type="submit" value="Play">
            </form>
        </div>
        <div class="game">
        </div>
    </body>
</html>
