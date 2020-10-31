<%-- 
    Document   : fogot
    Created on : Oct 28, 2020, 11:22:44 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Forgot your password?</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
        <style>
            /* Chrome, Safari, Edge, Opera */
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            /* Firefox */
            input[type=number] {
                -moz-appearance: textfield;
            }
        </style>
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg01.jpg');">
                <div class="wrap-login100 p-l-110 p-r-110 p-t-62 p-b-33">
                    <form class="login100-form validate-form flex-sb flex-w"
                          action="User" method="post">
                        <span class="login100-form-title p-b-53">
                            Reset your password
                        </span>

                        <%
                            if (session.getAttribute("errorupdate") != null) {
                                out.print("<div class='alert alert-warning alert-dismissible fade show container' role='alert'>"
                                        + session.getAttribute("errorupdate") + 
                                        "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
                                        + "<span aria-hidden='true'>&times;</span>"
                                        + "</button>"
                                        + "</div>");
                                session.removeAttribute("errorupdate");
                            }
                        %>
                        
                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Email
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Email is required">
                            <input class="input100" type="email" name="email" id="email">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="container-login100-form-btn m-t-17">
                            <button class="login100-form-btn" type="button" id="sendMail">
                                Send OTP to your email.
                            </button>
                        </div>

                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                OTP
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "OTP is required">
                            <input class="input100" name="otp" id="otp" type="number"
                                   pattern="/^-?\d+\.?\d*$/" onKeyPress="if (this.value.length == 6)
                                               return false;" />
                            <span class="focus-input100"></span>
                        </div>

                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                New Password
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Password is required">
                            <input class="input100" type="password" name="pass" id="form_pass">
                            <span class="focus-input100"></span>
                        </div>
                        <span class="container" id="password_erorr"></span>
                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Repeat password
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Password is required">
                            <input class="input100" type="password" name="repeatPass" id="form_repeat">
                            <span class="focus-input100"></span>
                        </div>
                        <span class="container" id="repeatPass_erorr"></span>
                        
                        <input type="hidden" name="action" value="update">
                        <div class="container-login100-form-btn m-t-17">
                            <button class="login100-form-btn">
                                Send password reset email.
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>
        <script src="js/validate.js" type="text/javascript"></script>
        <script src="js/sendMail.js" type="text/javascript"></script>
    </body>
</html>
