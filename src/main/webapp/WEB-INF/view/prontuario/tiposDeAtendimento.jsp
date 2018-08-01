<%-- 
    Document   : tiposDeAtendimento
    Created on : 01/03/2018, 22:06:34
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Tipos de Atendimento">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#tiposDeAtendimento').dataTable({
	           	    language: {
	        			"sEmptyTable": "Nenhum registro encontrado",
	        			"sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
	        			"sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
	        			"sInfoFiltered": "(Filtrados de _MAX_ registros)",
	        			"sInfoPostFix": "",
	        			"sInfoThousands": ".",
	        			"sLengthMenu": "_MENU_ resultados por página",
	        			"sLoadingRecords": "Carregando...",
	        			"sProcessing": "Processando...",
	        			"sZeroRecords": "Nenhum registro encontrado",
	        			"sSearch": "Pesquisar",
	        			"oPaginate": {
	        				"sNext": "Próximo",
	        				"sPrevious": "Anterior",
	        				"sFirst": "Primeiro",
	        				"sLast": "Último"
	        			},
	        			"oAria": {
	        				"sSortAscending": ": Ordenar colunas de forma ascendente",
	        				"sSortDescending": ": Ordenar colunas de forma descendente"
	        			}
	        	    }             	   
               });
            });
        </script>         
    </jsp:attribute>    
    <jsp:body>
    
        <a href="<c:url value="/prontuario/cadastroTipoAtendimento"></c:url>" style="color: white">
	        <button class="btn btn-primary">
            	<i class="fa fa-save"></i>
               <spring:message code="cadastro"></spring:message>
	        </button>
		</a>
		            
        <table class="table table-responsive" id="tiposDeAtendimento">
            <thead>
                <tr>
                    <th><spring:message code="nome"></spring:message></th>
                    <th><spring:message code="duracao"></spring:message></th>
                    <th><spring:message code="frequencia"></spring:message></th>
                    <th><spring:message code="modeloAtendimento"></spring:message></th>
                    <th><spring:message code="ativo"></spring:message></th>
                    <th><spring:message code="ativo"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tiposDeAtendimento}" var="tipoDeAtendimento">
                    <tr>
                        <td>
                            ${tipoDeAtendimento.nome}
                            <a href="<c:url value="/prontuario/atualizarTipoDeAtendimento/${tipoDeAtendimento.tipoDeAtendimentoId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/prontuario/removerTipoDeAtendimento/${tipoDeAtendimento.tipoDeAtendimentoId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                            
                        </td>
                        <td>${tipoDeAtendimento.duracao}</td>
                        <td>${tipoDeAtendimento.frequencia}</td>
                        <td>${tipoDeAtendimento.modeloAtendimento}</td>
                        <td>${tipoDeAtendimento.status}</td>
                        <td>${tipoDeAtendimento.custo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:body>
</vetweb:layout>
