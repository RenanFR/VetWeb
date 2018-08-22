<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Modelos">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#modelos').dataTable({
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
    
	    <a href="<c:url value="/documentos/form"></c:url>" style="color: white">
	        <button class="btn btn-primary">
	    	    <i class="fa fa-save"></i>
	           <spring:message code="cadastro"></spring:message>
	        </button>    
	    </a>
	    
        <table class="table table-responsive" id="modelos">
            <thead>
                <tr>
                    <th><spring:message code="nome"></spring:message></th>
                    <th><spring:message code="infoCliente"></spring:message></th>
                    <th><spring:message code="modelo"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${modelos}" var="modelo">
                    <tr>
                        <td>
                            ${modelo.nome}
							<a href="<c:url value="/documentos/atualizarModelo/${modelo.modelodocumentoId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>
                            <a href="<c:url value="/documentos/removerModelo/${modelo.modelodocumentoId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>
                        </td>
                        <td>
                            ${modelo.infoCliente}
                        </td>
                        <td>
                            ${modelo.modelo}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:body>
</vetweb:layout>