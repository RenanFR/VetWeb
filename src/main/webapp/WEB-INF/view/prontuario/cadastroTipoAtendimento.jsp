<%-- 
    Document   : cadastroTipoAtendimento
    Created on : 01/03/2018, 20:33:19
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<vetweb:layout title="Cadastro Tipo Atendimento">

    <jsp:attribute name="js">
        <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=2i80p03koooieys6i5h5yz1n9d4uaxrwt1iaoy9938bmcahs"></script>          
        <script>tinymce.init({ selector:'textarea' });</script>        
    </jsp:attribute>
    
    <jsp:body>
        <form:form servletRelativeAction="/prontuario/addTipoAtendimento" method="POST" modelAttribute="tipoDeAtendimento">
            <table class="table table-responsive">
                <caption><spring:message code="cadTipoAtendimento"/></caption>
                <tbody>
                    <form:hidden path="tipoDeAtendimentoId" id="tipoDeAtendimentoId"></form:hidden>
                    <tr>
                        <th><label for="nome"><spring:message code="nome"/></label></th>
                        <td><form:input path="nome" id="nome"></form:input></td>
                    </tr>
                    <tr>
                        <th><label for="duracao"><spring:message code="duracao"/></label></th>
                        <td>
                        	<select name="duracao">
	                        	<c:forEach items="${duracoesValidas}" var="duracao">
	                        		<option>${duracao}</option>
	                        	</c:forEach>
                        	</select>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="frequencia"><spring:message code="frequencia"/></label></th>
                        <td><form:select path="frequencia" items="${frequenciasValidas}" id="frequencia"></form:select></td>
                    </tr>
                    <tr>
                        <th><label for="status"><spring:message code="ativo"/></label></th>
                        <td>
                            <form:radiobutton path="status" value="true"></form:radiobutton>true
                            <form:radiobutton path="status" value="false"></form:radiobutton>false
                        </td>
                    </tr>
                    <tr>
                        <th><label for="modeloAtendimento"><spring:message code="modeloAtendimento"/></label></th>
                        <td><form:textarea path="modeloAtendimento" id="modeloAtendimento"></form:textarea></td>
                    </tr>
                    <tr>
                        <th><label for="custo"><spring:message code="custo"/></label></th>
                        <td><form:input path="custo" id="custo"></form:input></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="submit"  />
            <input type="reset" value="reset"  />                
        </form:form>
    </jsp:body>
</vetweb:layout>
