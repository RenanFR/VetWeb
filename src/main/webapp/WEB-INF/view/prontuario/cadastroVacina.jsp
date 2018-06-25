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

<vetweb:layout title="cadastroVacina">
    <jsp:attribute name="js">
    </jsp:attribute>
    <jsp:body>
        <form:form servletRelativeAction="/prontuario/addVacina" method="POST" modelAttribute="vacina">
            <table class="table table-responsive">
                <caption><spring:message code="adcVacina"></spring:message></caption>
                <tbody>
                    <form:hidden path="vacinaId" id="vacinaId"></form:hidden>
                    <tr>
                        <th><label for="nome"><spring:message code="nome"></spring:message></label></th>
                        <td><form:input path="nome" id="nome"></form:input></td>
                    </tr>
                    <tr>
                        <th><label for="grupo"><spring:message code="grupo"></spring:message></label></th>
                        <td><form:input path="grupo" id="grupo"></form:input></td>
                    </tr>
                    <tr>
                        <th><label for="status"><spring:message code="ativo"></spring:message></label></th>
                        <td>
                            <form:radiobutton path="status" value="true"></form:radiobutton>true
                            <form:radiobutton path="status" value="false"></form:radiobutton>false
                        </td>
                    </tr>
                    <tr>
                        <th><label for="laboratorio"><spring:message code="laboratorio"></spring:message></label></th>
                        <td><form:input path="laboratorio" id="laboratorio"></form:input></td>
                    </tr>
                    <tr>
                        <th><label for="custo"><spring:message code="custo"></spring:message></label></th>
                        <td><form:input path="preco" id="custo"></form:input></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="submit"  />
            <input type="reset" value="reset"  />                
        </form:form>
    </jsp:body>
</vetweb:layout>
