<%-- 
    Document   : minigame
    Created on : Nov 25, 2020, 8:51:15 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>
            MINIGAME
        </title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/mini_game.css"> <!-- Chỉnh đường dẫn dùm :) -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/loadingPrg.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">	
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <div id="loading" style="display: none;">
            <i class="fa fa-spinner fa-3x fa-spin"></i>
        </div>
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
            </tr>
        </table>
        <div class="wrapper">
            <h1 id="title"><strong>Click any card to begin</strong></h1>
            <p id="time"><span id="seconds">00</span>:<span id="tens">00</span></p>
            <div id="container">

            </div>
            <div id="result">
            </div>
        </div>
    </body>
    <script src="js/mini_game.js"></script>
</html>
