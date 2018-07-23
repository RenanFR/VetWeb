<%-- 
    Document   : cadastroPelagem
    Created on : 17/02/2018, 00:13:25
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Cadastro Pelagem">
    <form:form servletRelativeAction="/animais/addPelagem" method="POST" modelAttribute="pelagem">
        <table class="table table-responsive">
            <caption><spring:message code="cadastroPelagem"></spring:message></caption>
            <form:hidden path="pelagemId" id="pelagemId"></form:hidden>
            <tbody>
                <tr>
                    <th><label for="descricao"><spring:message code="pelagem"></spring:message></label></th>
                    <td><form:input path="descricao" id="descricao"></form:input></td>
                </tr>
            </tbody>
        </table>
        <input type="submit" value="submit"  />
        <input type="reset" value="reset"  />                
    </form:form>
</vetweb:layout>
