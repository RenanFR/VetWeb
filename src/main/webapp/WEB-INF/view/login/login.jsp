
<%-- 
    Document   : login
    Created on : 08/11/2017, 17:27:09
    Author     : 11151504898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><!--   Adc. token p/ proteção contra csrf -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<c:url value="/resources/css/bootstrap/bootstrap.css"></c:url>" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="<c:url value="/resources/images/pawprint.png"></c:url>">
            <style type="text/css">
                /*@CHARSET "UTF-8";*/
                /*
                over-ride "Weak" message, show font in dark grey
                */

                .progress-bar {
                    color: #333;
                } 

                /*
                Reference:
                http://www.bootstrapzen.com/item/135/simple-login-form-logo/
                */

                * {
                    -webkit-box-sizing: border-box;
                    -moz-box-sizing: border-box;
                    box-sizing: border-box;
                    outline: none;
                }

                .form-control {
                    position: relative;
                    font-size: 16px;
                    height: auto;
                    padding: 10px;

                    &:focus {
                        z-index: 2;
                    }
                }

                body {
                    background: url(<c:url value="/resources/images/background.jpg"></c:url>) no-repeat center center fixed;
                    -webkit-background-size: cover;
                    -moz-background-size: cover;
                    -o-background-size: cover;
                    background-size: cover;
                }

                .login-form {
                    margin-top: 60px;
                }

                form[role=login] {
                    color: #5d5d5d;
                    background: #f2f2f2;
                    padding: 26px;
                    border-radius: 10px;
                    -moz-border-radius: 10px;
                    -webkit-border-radius: 10px;
                }
                form[role=login] img {
                    display: block;
                    margin: 0 auto;
                    margin-bottom: 35px;
                }
                form[role=login] input,
                form[role=login] button {
                    font-size: 18px;
                    margin: 16px 0;
                }
                form[role=login] > div {
                    text-align: center;
                }

                .form-links {
                    text-align: center;
                    margin-top: 1em;
                    margin-bottom: 50px;
                }
                .form-links a {
                    color: #fff;
                }
            </style>
            <script src="<c:url value="/resources/js/jquery/jquery.js"></c:url>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"></c:url>" type="text/javascript"></script>
        </head>
        <body>
            <div class="container">

                <div class="row" id="pwd-container">
                    <div class="col-md-4"></div>

                    <div class="col-md-4">
                        <section class="login-form">
                        <form:form servletRelativeAction="login" role="login"><!--  POST. Dispara ação p/ login do filtro   -->
                            <img src="<c:url value="/resources/images/pawprint.png"></c:url>" class="img-responsive" alt=""/>
                                <fieldset>
                                    <label for="username"></label>Username
                                    <input type="text" name="username" id="username" class="form-control input-lg" placeholder="renanfr1047@gmail.com"  />
                                    <label for="password"></label>Password
                                    <input type="password" name="password" id="password" class="form-control input-lg" placeholder="password"  />
                                </fieldset>
                                <input type="submit" name="submit" value="login" class="btn btn-lg btn-primary btn-block"    />
                        </form:form>

                        <div class="form-links">
                            <a href="#">VetWeb</a>
                        </div>
                    </section>  
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </body>
</html>
