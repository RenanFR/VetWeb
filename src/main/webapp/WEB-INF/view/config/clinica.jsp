<%-- 
    Document   : clinica
    Created on : 24/02/2018, 08:33:59
    Author     : renan.rodrigues
--%>

<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<vetweb:layout title="clinica">
    ${clinica.razaoSocial}
    ${clinica.fundadaEm}
    ${clinica.cnpj}
    ${clinica.proprietario}
    <button class="btn btn-default">
        <a href="<c:url value="/config/atualizarClinica/${clinica.cnpj}"></c:url>"><i class="fa fa-edit"></i></a>
    </button>
</vetweb:layout>