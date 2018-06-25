<%-- 
    Document   : cadastroEspecie
    Created on : 11/02/2018, 22:28:16
    Author     : renan.rodrigues
--%>

<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<vetweb:layout title="cadastroEspecie">
    <form:form servletRelativeAction="/animais/addEspecie" method="POST" modelAttribute="especie">
        <table class="table table-responsive">
            <caption>
            	<spring:message code="novaEspecie"></spring:message>
            </caption>
            <tbody>
                <form:hidden path="especieId" id="especieId"></form:hidden>
                <tr>
                    <th><label for="descricao"><spring:message code="especie"></spring:message></label></th>
                    <td><form:input path="descricao" id="descricao" htmlEscape="true"></form:input></td>
                </tr>
            </tbody>
        </table>
        <input type="submit" value="submit"  />
        <input type="reset" value="reset"  />                
    </form:form>
</vetweb:layout>

