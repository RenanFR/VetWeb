<%-- 
    Document   : cadastroAnimal
    Created on : 16/11/2017, 17:46:39
    Author     : renan.rodrigues
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="cadastroAnimal">
    <jsp:attribute name="script">
        <script src="<c:url value="/resources/js/app/ajaxService.js"></c:url>" type="text/javascript"></script>
        <script>
        	$(document).ready(ajaxService.buscaRacasPorEspecie());
        </script>
    </jsp:attribute>
    <jsp:body>
        <c:if test="${animal.animalId == null}">
            <c:url var="action" value="/animais/cadastrar"></c:url>
        </c:if>
        <c:if test="${animal.animalId != null}">
            <c:url var="action" value="/animais/atualizar/${animal.animalId}"></c:url>
        </c:if>
        <form:form servletRelativeAction="/animais/cadastrar" method="POST" modelAttribute="animal" enctype="multipart/form-data">
            <fieldset>
                <table class="table table-responsive" id="detalhesAnimal">
                    <caption>Animal ${animal.nome}</caption>
                    <form:hidden path="animalId" id="animalId"></form:hidden>
                        <tbody>
                        	<tr>
                        		<td><label for="imagem"><spring:message code="imagem"></spring:message></label></td>
                        		<td><input type="file" name="imagemFile"	/></td>
                        	</tr>
                            <tr>
                                <th><label for="nome"><spring:message code="nome"></spring:message></label></th>
                                <td><form:input path="nome" id="nome" htmlEscape="true"></form:input></td>
                            <td><form:errors path="nome" cssClass="errors"></form:errors>
                            </tr>
                            <tr>
                                <th><label for="dtNascimento"><spring:message code="nascimento"></spring:message></label></th>
                                <td><form:input type="date" name="dtNascimento" path="dtNascimento"    /></td>
                            </tr>
                            <tr>
                                <th><label for="esteril"><spring:message code="esteril"></spring:message></label></th>
                                <td>
                                    <form:radiobutton path="esteril" id="esteril" value="true"></form:radiobutton>true
                                    <form:radiobutton path="esteril" id="esteril" value="false"></form:radiobutton>false
                                </td>
                            </tr>
                            <tr>
                                <th><label for="status"><spring:message code="status"></spring:message></label></th>
                                <td>
                                    <form:radiobutton path="status" id="status" value="true"></form:radiobutton>true
                                    <form:radiobutton path="status" id="status" value="false"></form:radiobutton>false
                                </td>
                            </tr>
                            <tr>
                                <th><label for="peso"><spring:message code="peso"></spring:message></label></th>
                                <td><form:input path="peso" id="peso"></form:input></td>
                                <td><form:errors path="peso" cssClass="errors"></form:errors></td>
                            </tr>
                            <tr>
                                <th><label for="pelagem"><spring:message code="pelagem"></spring:message></label></th>
                                <td><form:select path="pelagem" items="${pelagens}" id="pelagens"></form:select></td>
                            </tr>
                            <tr>
                                <th><label for="especie"><spring:message code="especie"></spring:message></label></th>
                                <td>
                                    <select id="especies" onchange="ajaxService.buscaRacasPorEspecie()">
                                        <c:forEach items="${especies}" var="especie">
                                            <option>${especie}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="raca"><spring:message code="raca"></spring:message></label></th>
                                <td><form:select path="raca" items="${especies}" id="racas"></form:select></td>
                            </tr>
                            <tr>
                                <th><label for="proprietario"><spring:message code="proprietario"></spring:message></label></th>
                                <td><form:select path="proprietario" items="${proprietarios}" disabled="${desabilitaTrocaProprietario}"></form:select></td>
                            </tr>
                            <form:hidden path="proprietario.pessoaId" id="proprietario"></form:hidden>
                        </tbody>
                </table>
            </fieldset>
            <input type="submit" value="submit"  />
            <input type="reset" value="reset"  />
        </form:form>
          
    </jsp:body>
</vetweb:layout>
