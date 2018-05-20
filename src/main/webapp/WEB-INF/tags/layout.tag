<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ attribute name="title" required="true" %><!--    Informa que recebe um atributo obrigatório title    -->
<%@ attribute name="script" fragment="true" %><!--   Informa que pode receber fragmento de página. No caso usado p/ script-->
<%@ attribute name="mascaras" fragment="true" %>
<%@ attribute name="js" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title><!-- Consumindo o atributo   -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"></c:url>"   />
        <link rel="icon" type="image/png" href="<c:url value="/resources/images/pawprint.png"></c:url>">
        <script src="<c:url value="/resources/js/jquery/jquery.js"></c:url>" type="text/javascript"></script>
        <!-- BOOTSTRAP STYLES-->
        <link href="<c:url value="/resources/css/bootstrap/bootstrap.css"></c:url>" rel="stylesheet" type="text/css"/>
        <!-- FONTAWESOME STYLES-->
        <link href="<c:url value="/resources/css/font-awesome/font-awesome.min.css"></c:url>" rel="stylesheet" type="text/css"/>
        <!-- CUSTOM STYLES-->
        <link href="<c:url value="/resources/css/custom.css"></c:url>" rel="stylesheet" type="text/css"/>
        <!-- GOOGLE FONTS-->
        <link href="<c:url value="/resources/css/jquery/datatables.css"></c:url>" rel="stylesheet" type="text/css"/>
        <script src="<c:url value="/resources/js/jquery/datatables.js"></c:url>" type="text/javascript"></script>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <jsp:invoke fragment="js"></jsp:invoke><!-- Aqui será passado o script (Fragmento) pela página cliente  -->
    </head>
	<body>
	    <div id="wrapper">
	    <div class="navbar navbar-inverse navbar-fixed-top">
	        <div class="adjust-nav">
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="/VetWeb/">
	                    <img src="<c:url value="/resources/images/Dog_Paw_Print.png"></c:url>" alt="" height="40" width="40"/>
	                </a>
	            </div>
	            <span class="logout-spn">
	                <a href="/VetWeb/logout" style="color: #fff;">Logout</a>
	            </span>
	        </div>
	    </div>
	    <!-- /. NAV TOP  -->
	    <nav class="navbar-default navbar-side" role="navigation">
	        <div class="sidebar-collapse">
	            <ul class="nav" id="main-menu">
	                <li class="active-link">
	                    <a href="<c:url value="/clientes/listar"></c:url>" ><i class="fa fa-user "></i><spring:message code="clientes"></spring:message> </a>
	                </li>
	                <li>
	                    <a href="<c:url value="/animais/listar"></c:url>"><i class="fa fa-github-alt "></i><spring:message code="animais"></spring:message>  </a>
	                </li>
	                <li class="dropdown">
	                    <a class="dropdown-toggle" href="blank.html" data-toggle="dropdown"><i class="fa fa-folder-open"></i><spring:message code="cadastros"></spring:message><span class="caret"></span></a>  
	                    <ul class="dropdown-menu">
	                        <li><a href="<c:url value="/animais/especies"></c:url>"><spring:message code="especies"></spring:message></a></li>
	                        <li><a href="<c:url value="/animais/racas"></c:url>"><spring:message code="racas"></spring:message></a></li>
	                        <li><a href="<c:url value="/animais/pelagens"></c:url>"><spring:message code="pelagens"></spring:message></a></li>
	                        <li><a href="<c:url value="/animais/patologias"></c:url>"><spring:message code="patologias"></spring:message></a></li>
	                        <li><a href="<c:url value="/prontuario/tiposDeAtendimento"></c:url>"><spring:message code="tiposAtendimento"></spring:message></a></li>
	                        <li><a href="<c:url value="/prontuario/vacinas"></c:url>"><spring:message code="vacinas"></spring:message></a></li>
	                    </ul>
	                </li>
	                <li>
	                    <a href="#"><i class="fa fa-qrcode "></i>My Link One</a>
	                </li>
	                <li>
	                    <a href="#"><i class="fa fa-bar-chart-o"></i>My Link Two</a>
	                </li>
	
	                <li>
	                    <a href="#"><i class="fa fa-edit "></i>My Link Three </a>
	                </li>
	                <li>
	                    <a href="#"><i class="fa fa-table "></i>My Link Four</a>
	                </li>
	                <li>
	                    <a href="<c:url value="${urlClinica}"></c:url>"><i class="fa fa-building "></i> <spring:message code="clinica" text="text"></spring:message>  </a>
	                </li>
	            </ul>
	        </div>
	    </nav>
	    <!-- /. NAV SIDE  -->
	    <div id="page-wrapper" >
	        <div id="page-inner">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h2>${title}</h2>   
	                </div>
	            </div>              
	            <!-- /. ROW  -->
	            <hr />
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="alert alert-info">
	                        <strong>Welcome Jhon Doe ! </strong> You Have No pending Task For Today.
	                    </div>
	                </div>
	            </div>
	            <div class="container-fluid">
	                <jsp:doBody /><!--  Recebe o corpo/implementaçao da página que irá utilizar o layout    -->                    
	            </div>
	        </div>
	    </div>
	    <div class="footer">
	        <div class="row">
	            <div class="col-lg-6" >
	                &copy;  <%=java.time.LocalDate.now().getYear()%> vetweb.com | <spring:message code="desenvolvidoPor"></spring:message>: Renan, Murillo e André
	            </div>
	            <div class="col-lg-6">
	                <a href="<c:url value="?locale=pt_BR"></c:url>"><img src="<c:url value="/resources/images/brazil (1).png"></c:url>" alt=""/></a>                        
	                <a href="<c:url value="?locale=en_US"></c:url>"><img src="<c:url value="/resources/images/united-states-of-america.png"></c:url>" alt=""/></a>                        
	                <spring:message code="olaMundo" text="text"></spring:message>                        
	            </div>
	        </div>
	    </div>
	    </div>
	    <jsp:invoke fragment="script"></jsp:invoke><!-- Aqui será passado o script (Fragmento) pela página cliente  -->
	    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"></c:url>" type="text/javascript"></script>
	    <script src="<c:url value="/resources/js/custom.js"></c:url>" type="text/javascript"></script>
	    <script src="<c:url value="/resources/js/jquery/jquery.maskedinput.js"></c:url>" type="text/javascript"></script>
	    <jsp:invoke fragment="mascaras"></jsp:invoke>
	</body>
</html>

