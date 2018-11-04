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
		<script src="<c:url value="/resources/js/app/ajax-service.js"></c:url>" type="text/javascript"></script>
        <script>
            $(document).ready(function(){
               $('#balancoFinanceiro').dataTable();
            });
        </script>                 
    </jsp:attribute>
    
    <jsp:body>
        <table class="table table-striped table-bordered table-hover" id="balancoFinanceiro">
        <caption>
       		<spring:message code="totalPendente"/> ${totalPendente}
			<div class="${contemDebito ? "alert alert-danger alert-dismissable":"alert alert-success alert-dismissable"}">
                  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                  <h4><i class="${contemDebito ? "icon fa fa-ban":"icon fa fa-check"}"></i> ${contemDebito ? "CLIENTE POSSUI DÉBITOS, CASO TENHAM SIDO QUITADOS FAVOR ATUALIZAR OS REGISTROS":
                  "CLIENTE NÃO POSSUI NENHUM DÉBITO JUNTO A CLÍNICA"}!</h4>
            </div>
        </caption>
            <thead>
                <tr>
                    <th><spring:message code="atendimentoMedico"/></th>
                    <th><spring:message code="exames"/></th>
                    <th><spring:message code="vacinas"/></th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>
                        	<table>
                        		<tr>
                        			<th>
                        				<spring:message code="tipoDeAtendimento"/>
                        			</th>
                        			<th>
                        				<spring:message code="custo"/>
                        			</th>
                        			<th>
                        				<spring:message code="pago"/>
                        			</th>
                        			<th>
                        				<spring:message code="dataAtendimento"/>
                        			</th>
                        		</tr>
				                <c:forEach items="${atendimentosFeitos}" var="atendimento">
	                        		<tr>
	                        			<td>${atendimento.tipoDeAtendimento.nome}</td>
	                        			<td>${atendimento.tipoDeAtendimento.custo}</td>
	                        			<td>
	                        				<input type="checkbox" class="flagPago" ${atendimento.pago? 'checked' : ''} onclick="ajaxService.alterarStatusPagamentoAtendimento(${atendimento.ocorrenciaId})"	/>
	                        			</td>
	                        			<td>${atendimento.data}</td>
	                        		</tr>
				                </c:forEach>
                        	</table>
                        </td>
                        <td>
                        	<table>
                        		<tr>
                        			<th>
                        				<spring:message code="nome"/>
                        			</th>
                        			<th>
                        				<spring:message code="preco"/>
                        			</th>
                        			<th>
                        				<spring:message code="dataOcorrencia"/>
                        			</th>
                        			<th>
                        				<spring:message code="pago"/>
                        			</th>
                        		</tr>
				                <c:forEach items="${examesRealizados}" var="exame">
	                        		<tr>
	                        			<td>${exame.exame.descricao}</td>
	                        			<td>${exame.exame.preco}</td>
	                        			<td>${exame.data}</td>
	                        			<td>
	                        				<input type="checkbox" class="flagPago" ${exame.pago? 'checked' : ''} onclick="ajaxService.alterarStatusPagamentoExame(${exame.ocorrenciaId})"	/>
	                        			</td>
	                        		</tr>
				                </c:forEach>
                        	</table>
                        </td>
                        <td>
                        	<table>
                        		<tr>
                        			<th>
                        				<spring:message code="nome"/>
                        			</th>
                        			<th>
                        				<spring:message code="preco"/>
                        			</th>
                        			<th>
                        				<spring:message code="inclusaoVacina"/>
                        			</th>
                        			<th>
                        				<spring:message code="pago"/>
                        			</th>
                        		</tr>
				                <c:forEach items="${vacinasAplicadas}" var="vacina">
	                        		<tr>
	                        			<td>${vacina.vacina.nome}</td>
	                        			<td>${vacina.vacina.preco}</td>
	                        			<td>${vacina.data}</td>
	                        			<td>
	                        				<input type="checkbox" class="flagPago" ${vacina.pago? 'checked' : ''} onclick="ajaxService.alterarStatusPagamentoVacina(${vacina.ocorrenciaId})"	/>
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
