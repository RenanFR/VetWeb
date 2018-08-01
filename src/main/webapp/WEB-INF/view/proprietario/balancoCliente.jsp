<%-- 
    Document   : proprietarios
    Created on : 16/11/2017, 17:20:54
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Balanço Financeiro ${proprietario.nome}">
    <jsp:attribute name="js">
		<script src="<c:url value="/resources/js/app/ajaxService.js"></c:url>" type="text/javascript"></script>
        <script>
            $(document).ready(function(){
               $('#balancoFinanceiro').dataTable();
            });
        </script>                 
    </jsp:attribute>
    <jsp:body>
        <table class="table table-striped table-bordered table-hover" id="balancoFinanceiro">
        <caption>
        	<h3>
        		<spring:message code="totalPendente"></spring:message> ${totalPendente}
        	</h3>
        </caption>
            <thead>
                <tr>
                    <th><spring:message code="atendimentoMedico"></spring:message></th>
                    <th><spring:message code="vacinas"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>
                        	<table>
                        		<tr>
                        			<th>
                        				<spring:message code="tipoDeAtendimento"></spring:message>
                        			</th>
                        			<th>
                        				<spring:message code="custo"></spring:message>
                        			</th>
                        			<th>
                        				<spring:message code="pago"></spring:message>
                        			</th>
                        			<th>
                        				<spring:message code="dataAtendimento"></spring:message>
                        			</th>
                        		</tr>
				                <c:forEach items="${atendimentosFeitos}" var="atendimento">
	                        		<tr>
	                        			<td>${atendimento.tipoDeAtendimento.nome}</td>
	                        			<td>${atendimento.tipoDeAtendimento.custo}</td>
	                        			<td>
	                        				<input type="checkbox" class="flagPago" ${atendimento.pago? 'checked' : ''} onclick="ajaxService.alterarStatusPagamentoAtendimento(${atendimento.atendimentoId})"	/>
	                        			</td>
	                        			<td>${atendimento.dataAtendimento}</td>
	                        		</tr>
				                </c:forEach>
                        	</table>
                        </td>
                        <td>
                        	<table>
                        		<tr>
                        			<th>
                        				<spring:message code="nome"></spring:message>
                        			</th>
                        			<th>
                        				<spring:message code="preco"></spring:message>
                        			</th>
                        			<th>
                        				<spring:message code="inclusaoVacina"></spring:message>
                        			</th>
                        			<th>
                        				<spring:message code="pago"></spring:message>
                        			</th>
                        		</tr>
				                <c:forEach items="${vacinasAplicadas}" var="vacina">
	                        		<tr>
	                        			<td>${vacina.vacina.nome}</td>
	                        			<td>${vacina.vacina.preco}</td>
	                        			<td>${vacina.inclusaoVacina}</td>
	                        			<td>
	                        				<input type="checkbox" class="flagPago" ${vacina.pago? 'checked' : ''} onclick="ajaxService.alterarStatusPagamentoVacina(${vacina.prontuarioVacinaId})"	/>
	                        			</td>
	                        		</tr>
				                </c:forEach>
                        	</table>                        
                        </td>
                    </tr>                
            </tbody>
        </table>
    </jsp:body>
</vetweb:layout>
