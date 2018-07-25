<%-- 
    Document   : vacinas
    Created on : 22/04/2018, 22:19:51
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Vacinas">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#vacinas').dataTable({
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
        <button class="btn btn-primary">
        <i class="fa fa-save"></i>
        <a href="<c:url value="/prontuario/cadastroVacina"></c:url>" style="color: white">   <spring:message code="cadastro"></spring:message></a>
        </button>    
        <table class="table table-responsive" id="vacinas">
            <thead>
                <tr>
                    <th><spring:message code="nome"></spring:message></th>
                    <th><spring:message code="grupo"></spring:message></th>
                    <th><spring:message code="status"></spring:message></th>
                    <th><spring:message code="laboratorio"></spring:message></th>
                    <th><spring:message code="custo"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${vacinas}" var="vacina">
                    <tr>
                        <td>
                            ${vacina.nome}
							<a href="<c:url value="/prontuario/atualizarVacina/${vacina.vacinaId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>
                            <a href="<c:url value="/prontuario/removerVacina/${vacina.vacinaId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>
                        </td>
                        <td>
                            ${vacina.grupo}
                        </td>
                        <td>
                            ${vacina.status}
                        </td>
                        <td>
                            ${vacina.laboratorio}
                        </td>
                        <td>
                            ${vacina.preco}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:body>
</vetweb:layout>