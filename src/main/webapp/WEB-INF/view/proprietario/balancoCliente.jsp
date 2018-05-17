<%-- 
    Document   : proprietarios
    Created on : 16/11/2017, 17:20:54
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importa��o JSTL -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags �teis do spring framework   -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="proprietarios">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#balancoFinanceiro').dataTable();
            });
        </script>                 
    </jsp:attribute>
    <jsp:body>
        <button class="btn btn-primary">
            <i class="fa fa-save"></i>
            <a href="<c:url value="/clientes/cadastro"></c:url>" style="color: white">   <spring:message code="cadastrar"></spring:message></a>
        </button>
        <table class="table table-striped table-bordered table-hover" id="balancoFinanceiro">
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
                        			<th>tipoDeAtendimento</th>
                        			<th>custo</th>
                        			<th>pago</th>
                        			<th>dataAtendimento</th>
                        			<th>preenchimentoModeloAtendimento</th>
                        		</tr>
				                <c:forEach items="${atendimentosFeitos}" var="atendimento">
	                        		<tr>
	                        			<td>${atendimento.tipoDeAtendimento.nome}</td>
	                        			<td>${atendimento.tipoDeAtendimento.custo}</td>
	                        			<td>${atendimento.pago}</td>
	                        			<td>${atendimento.dataAtendimento}</td>
	                        			<td>${atendimento.preenchimentoModeloAtendimento}</td>
	                        		</tr>
				                </c:forEach>
                        	</table>
                        </td>
                        <td>
                        	<table>
                        		<tr>
                        			<th>nome</th>
                        			<th>preco</th>
                        			<th>inclusaoVacina</th>
                        			<th>pago</th>
                        		</tr>
				                <c:forEach items="${vacinasAplicadas}" var="vacina">
	                        		<tr>
	                        			<td>${vacina.vacina.nome}</td>
	                        			<td>${vacina.vacina.preco}</td>
	                        			<td>${vacina.inclusaoVacina}</td>
	                        			<td>${vacina.pago}</td>
	                        		</tr>
				                </c:forEach>
                        	</table>                        
                        </td>
                    </tr>                
            </tbody>
        </table>
    </jsp:body>
</vetweb:layout>
