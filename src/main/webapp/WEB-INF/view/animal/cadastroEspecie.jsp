<%-- 
    Document   : cadastroEspecie
    Created on : 11/02/2018, 22:28:16
    Author     : Maria Jéssica
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><!--   Adc. token p/ proteção contra csrf -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags úteis do spring framework   -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<vetweb:layout title="">
    <form:form servletRelativeAction="/animais/addEspecie" method="POST" modelAttribute="especie">
        <table class="table table-responsive">
            <caption>Nova Espécie</caption>
            <tbody>
                <form:hidden path="especieId" id="especieId"></form:hidden>
                <tr>
                    <th><label for="descricao">Espécie</label></th>
                    <td><form:input path="descricao" id="descricao" htmlEscape="true"></form:input></td>
                </tr>
            </tbody>
        </table>
        <input type="submit" value="submit"  />
        <input type="reset" value="reset"  />                
    </form:form>
</vetweb:layout>

