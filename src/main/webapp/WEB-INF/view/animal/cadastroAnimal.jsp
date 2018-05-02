<%-- 
    Document   : cadastroAnimal
    Created on : 16/11/2017, 17:46:39
    Author     : 11151504898
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><!--   Adc. token p/ proteção contra csrf -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags úteis do spring framework   -->
<vetweb:layout title="cadastroAnimal">
    <jsp:attribute name="script">
        <script src="<c:url value="/resources/js/racasPorEspecie.js"></c:url>" type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <c:if test="${animal.animalId == null}">
            <p> Novo Animal </p>
            <c:url var="action" value="/animais/cadastrar"></c:url>
        </c:if>
        <c:if test="${animal.animalId != null}">
            <p> Editar Animal ${animal.animalId}    </p>
            <c:url var="action" value="/animais/atualizar/${animal.animalId}"></c:url>
        </c:if>
        <form:form servletRelativeAction="/animais/cadastrar" method="POST" modelAttribute="animal"><!--   modelAttribute:    Tipo recebido no Valid  -->
            <fieldset>
                <table class="table table-responsive" id="detalhesAnimal">
                    <caption>Animal ${animal.nome}</caption>
                    <form:hidden path="animalId" id="animalId"></form:hidden>
                        <tbody>
                            <tr>
                                <th><label for="nome">Nome</label></th>
                                <td><form:input path="nome" id="nome" htmlEscape="true"></form:input></td>
                            <td><form:errors path="nome" cssClass="errors"></form:errors><!-- O path define o nome do campo (getter) na classe modelo que estamos utilizando   -->                        </td>
                            </tr>
                            <tr>
                                <th><label for="dtNascimento">Data de Nascimento</label></th>
                                <td><form:input type="date" name="dtNascimento" path="dtNascimento"    /></td>
                            </tr>
                            <tr>
                                <th><label for="esteril">Esteril</label></th>
                                <td>
                                    <form:radiobutton path="esteril" id="esteril" value="true"></form:radiobutton>true
                                    <form:radiobutton path="esteril" id="esteril" value="false"></form:radiobutton>false
                                </td>
                            </tr>
                            <tr>
                                <th><label for="status">Status</label></th>
                                <td>
                                    <form:radiobutton path="status" id="status" value="true"></form:radiobutton>true
                                    <form:radiobutton path="status" id="status" value="false"></form:radiobutton>false
                                </td>
                            </tr>
                            <tr>
                                <th><label for="peso">Peso</label></th>
                                <td><form:input path="peso" id="peso"></form:input></td>
                                <td><form:errors path="peso" cssClass="errors"></form:errors></td>
                            </tr>
                            <tr>
                                <th><label for="pelagem">Pelagem</label></th>
                                <td><form:select path="pelagem" items="${pelagens}" id="pelagens"></form:select></td>
                            </tr>
                            <tr>
                                <th><label for="especie">Espécie</label></th>
                                <td>
                                    <select id="especies">
                                        <c:forEach items="${especies}" var="especie">
                                            <option>${especie}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="raca">Raça</label></th>
                                <td><form:select path="raca" items="${especies}" id="racas"></form:select></td>
                            </tr>
                            <tr>
                                <th><label for="proprietario">Proprietário</label></th>
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
