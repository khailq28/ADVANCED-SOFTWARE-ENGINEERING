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
        <link rel="stylesheet" type="text/css" href="css/loadingPrg.css"/>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
                                    <a data-toggle="modal" data-target="#exampleModal"
                                       style="cursor: -webkit-grabbing; cursor: grabbing;" id="info">
                                        <i class="fa fa-user-circle"></i>
                                    </a> &nbsp;
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
        
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">User Info</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="modal-content">
                        
                    </div>
                </div>
            </div>
        </div>
        <script src="js/home.js"></script>
    </body>
</html>
