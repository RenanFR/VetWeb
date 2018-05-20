<%-- 
    Document   : cadastroEspecie
    Created on : 11/02/2018, 22:28:16
    Author     : Maria J�ssica
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><!--   Adc. token p/ prote��o contra csrf -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags �teis do spring framework   -->
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

