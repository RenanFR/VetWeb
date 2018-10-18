<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Exames">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#exames').dataTable({
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
    
	    <a href="<c:url value="/exames/form"></c:url>" style="color: white">
	        <button class="btn btn-primary">
	    	    <i class="fa fa-save"></i>
	           <spring:message code="cadastro"></spring:message>
	        </button>    
	    </a>
	    
        <table class="table table-responsive" id="exames">
            <thead>
                <tr>
                    <th><spring:message code="descricao"></spring:message></th>
                    <th><spring:message code="exameId"></spring:message></th>
                    <th><spring:message code="apresentacao"></spring:message></th>
                    <th><spring:message code="encerramento"></spring:message></th>
                    <th><spring:message code="custo"></spring:message></th>
                    <th><spring:message code="duracao"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${exames}" var="exame">
                    <tr>
                        <td>
                            ${exame.descricao}
							<a href="<c:url value="/exames/atualizar/${exame.exameId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>
                            <a href="<c:url value="/exames/remover/${exame.exameId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>
                        </td>
                        <td>
                            ${exame.exameId}
                        </td>
                        <td>
                            ${exame.apresentacao}
                        </td>
                        <td>
                            ${exame.encerramento}
                        </td>
                        <td>
                            ${exame.preco}
                        </td>
                        <td>
                            ${exame.duracao}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:body>
</vetweb:layout>