<%-- 
    Document   : exception
    Created on : 03/03/2018, 13:15:33
    Author     : Maria Jéssica
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="tipoException" required="true" %>
<%@ attribute name="title" required="true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Smooth Error Page template Responsive, Login form web template,Flat Pricing tables,Flat Drop downs  Sign up Web Templates, Flat Web forms, Login sign up Responsive web Forms, SmartPhone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <link rel="icon" type="image/png" href="<c:url value="/resources/images/pawprint.png"></c:url>">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <link href="<c:url value="/resources/css/exception.css"></c:url>" rel="stylesheet" type="text/css"/>
        <link href="//fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
        <link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <h1>Erro</h1>
        <div class="main-wthree">
                <h2>${tipoException}</h2>
                <p>
                    <span class="sub-agileinfo">
                    	Oops! 
	                    <jsp:doBody></jsp:doBody>
                    </span>
                </p>
        </div>
        <div class="copyright-w3-agile">
            &copy;  <%=java.time.LocalDate.now().getYear()%> vetweb.com | <spring:message code="desenvolvidoPor"></spring:message>: Renan, Murillo e André
        </div>
    </body>
</html>