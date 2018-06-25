<%-- 
    Document   : 403
    Created on : 01/04/2018, 18:14:16
    Author     : renan.rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>

<vetweb:exception tipoException="403" title="Forbidden">
    <jsp:body>
        ${mensagemErro}
    </jsp:body>
</vetweb:exception>

