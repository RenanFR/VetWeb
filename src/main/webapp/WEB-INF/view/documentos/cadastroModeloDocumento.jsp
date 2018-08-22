<%-- 
    Document   : cadastroVacina
    Created on : 22/04/2018, 22:22:21
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<vetweb:layout title="Cadastro Modelo Documento">

    <jsp:attribute name="js">
    	<script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=2i80p03koooieys6i5h5yz1n9d4uaxrwt1iaoy9938bmcahs"></script>
    	<script>tinymce.init({ selector:'textarea' });</script>
    </jsp:attribute>
    
    <jsp:body>
        <form:form servletRelativeAction="/documentos/submitForm" method="POST" modelAttribute="modeloDocumento">
            <table class="table table-responsive">
                <caption><spring:message code="adcModeloDocumento"/></caption>
                <tbody>
                    <form:hidden path="modelodocumentoId" id="modelodocumentoId"></form:hidden>
                    <tr>
                        <th><label for="nome"><spring:message code="nome"/></label></th>
                        <td><form:input path="nome" id="nome"/></td>
                    </tr>
                    <tr>
                        <th><label for="infoCliente"><spring:message code="infoCliente"/></label></th>
                        <td><form:checkbox path="infoCliente"/></td>
                    </tr>
                    <tr>
                        <th><label for="modelo"><spring:message code="modelo"/></label></th>
                        <td>
                            <form:textarea path="modelo" id="modelo"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="submit"  />
            <input type="reset" value="reset"  />                
        </form:form>
    </jsp:body>
</vetweb:layout>
