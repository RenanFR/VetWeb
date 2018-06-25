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

<vetweb:layout title="cadastroPatologia">
    <form:form servletRelativeAction="/animais/addPatologia" method="POST" modelAttribute="patologia">
        <table class="table table-responsive">
            <caption><spring:message code="cadastroPatologia"></spring:message></caption>
            <tbody>
                <tr>
                    <th><label for="nome"><spring:message code="nome"></spring:message></label></th>
                    <td><form:input path="nome" id="nome"></form:input></td>                    
                </tr>
                <tr>
                    <th><label for="ativo"><spring:message code="ativo"></spring:message></label></th>
                    <td>
                        <form:radiobutton path="ativo" id="ativo" value="true"></form:radiobutton>true
                        <form:radiobutton path="ativo" id="ativo" value="false"></form:radiobutton>false
                    </td>
                </tr>
                <tr>
                    <th><label for="descricao"><spring:message code="descricao"></spring:message></label></th>
                    <td><form:input path="descricao" id="descricao"></form:input></td>                    
                </tr>                
            </tbody>
        </table>
        <input type="submit" value="submit"  />
        <input type="reset" value="reset"  />                
    </form:form>
</vetweb:layout>