<%-- 
    Document   : 404
    Created on : 03/03/2018, 13:08:47
    Author     : renan.rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>

<vetweb:exception tipoException="500" title="INTERNAL SERVER ERROR">
    <jsp:body>
        ${mensagemErro}
    </jsp:body>
</vetweb:exception>
