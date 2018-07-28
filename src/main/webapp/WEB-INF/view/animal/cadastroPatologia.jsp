<%-- 
    Document   : cadastroPatologia
    Created on : 22/02/2018, 20:32:35
    Author     : renan.rodrigues
--%>

<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<vetweb:layout title="Cadastro Patologia">

    <form:form servletRelativeAction="/animais/addPatologia" method="POST" modelAttribute="patologia">
        <table class="table table-responsive">
            <caption><spring:message code="cadastroPatologia"/></caption>
            <tbody>
            	<form:hidden path="patologiaId"/>
                <tr>
                    <th><label for="nome"><spring:message code="nome"/></label></th>
                    <td><form:input path="nome" id="nome"></form:input></td>                    
                </tr>
                <tr>
                    <th><label for="ativo"><spring:message code="ativo"/></label></th>
                    <td>
                        <form:radiobutton path="ativo" id="ativo" value="true"/>true
                        <form:radiobutton path="ativo" id="ativo" value="false"/>false
                    </td>
                </tr>
                <tr>
                    <th><label for="descricao"><spring:message code="descricao"/></label></th>
                    <td><form:input path="descricao" id="descricao"/></td>                    
                </tr>                
            </tbody>
        </table>
        <input type="submit" value="submit"  />
        <input type="reset" value="reset"  />                
    </form:form>
    
</vetweb:layout>