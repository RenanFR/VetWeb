<%-- 
    Document   : cadastroClinica
    Created on : 24/02/2018, 08:03:01
    Author     : renan.rodrigues
--%>

<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Cadastro Cliníca">
    <spring:message code="cadastroClinica"></spring:message>
    <form:form servletRelativeAction="/config/addClinica" method="POST" modelAttribute="clinica">
        <table class="table table-responsive">
            <caption><spring:message code="cadastroClinica"></spring:message></caption>
            <tbody>
                <tr>
                    <th><label for="razaoSocial"><spring:message code="razaoSocial"/></label></th>
                    <td><form:input path="razaoSocial" id="razaoSocial"></form:input></td>                    
                </tr>
                <tr>
                    <th><label for="fundadaEm"><spring:message code="fundadaEm"/></label></th>
                    <td><form:input type="date" name="fundadaEm" path="fundadaEm"    /></td>
                </tr>
                <tr>
                    <th><label for="cnpj"><spring:message code="cnpj"/></label></th>
                    <td><form:input path="cnpj" id="cnpj"></form:input></td>                    
                </tr>                
                <tr>
                    <th><label for="proprietario"><spring:message code="proprietario"/></label></th>
                    <td><form:input path="proprietario" id="proprietario"></form:input></td>                    
                </tr>                
            </tbody>
        </table>
        <input type="submit" value="submit"  />
        <input type="reset" value="reset"  /> 
    </form:form>
</vetweb:layout>
