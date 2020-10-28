<%-- 
    Document   : sign_up
    Created on : Oct 28, 2020, 8:17:57 PM
    Author     : khai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Create your account</title>
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
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
                <div class="wrap-login100 p-l-110 p-r-110 p-t-62 p-b-33">
                    <form class="login100-form validate-form flex-sb flex-w"
                          method="post">
                        <span class="login100-form-title p-b-53">
                            Create your account
                        </span>

                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                Username
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Username is required">
                            <input class="input100" type="text" name="username" id="form_user">
                            <span class="focus-input100"></span>
                        </div>

                        <span class="container" id="username_erorr"></span>

                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Password
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Password is required">
                            <input class="input100" type="password" name="pass" id="form_pass">
                            <span class="focus-input100"></span>
                        </div>

                        <span class="container" id="password_erorr"></span>
                        
                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Repeat Password
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Repeat password is required">
                            <input class="input100" type="password" name="RepeatPass" id="form_repeat">
                            <span class="focus-input100"></span>
                        </div>
                        <span class="container" id="repeatPass_erorr"></span>
                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Your name
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Your name is required">
                            <input class="input100" type="text" name="name" >
                            <span class="focus-input100"></span>
                        </div>
                        
                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Email
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Email is required">
                            <input class="input100" type="email" name="email" >
                            <span class="focus-input100"></span>
                        </div>

                        <input type="hidden" name="action" value="signUp">
                        <div class="container-login100-form-btn m-t-17">
                            <button class="login100-form-btn">
                                Create account
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
    </body>
</html>
