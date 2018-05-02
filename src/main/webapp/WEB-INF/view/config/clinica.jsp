<%-- 
    Document   : clinica
    Created on : 24/02/2018, 08:33:59
    Author     : Maria Jéssica
--%>

<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags úteis do spring framework   -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->

<vetweb:layout title="">
    ${clinica.razaoSocial}
    ${clinica.fundadaEm}
    ${clinica.cnpj}
    ${clinica.proprietario}
    <button class="btn btn-default">
        <a href="<c:url value="/config/atualizarClinica/${clinica.cnpj}"></c:url>"><i class="fa fa-edit"></i></a>
    </button>
</vetweb:layout>