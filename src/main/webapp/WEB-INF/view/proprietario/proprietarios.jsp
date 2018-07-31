<%-- 
    Document   : proprietarios
    Created on : 16/11/2017, 17:20:54
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Proprietários">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#proprietarios').dataTable({
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
            <a href="<c:url value="/clientes/cadastro"></c:url>" style="color: white">   <spring:message code="cadastrar"></spring:message>	</a>
        </button>
        <table class="table table-striped table-bordered table-hover" id="proprietarios">
            <thead>
                <tr>
                    <th><spring:message code="proprietario"/></th>
                    <th><spring:message code="animais"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${proprietarios}" var="proprietario">
                    <tr>
                        <td>
                            <a href="<c:url value="/clientes/detalhesCliente/${proprietario.pessoaId}"></c:url>">${proprietario.nome}</a>     
                            <a href="<c:url value="/clientes/atualizar/${proprietario.pessoaId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/clientes/remover/${proprietario.pessoaId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/clientes/financeiro/${proprietario.pessoaId}"></c:url>">
                                <i class="fa fa-money  fa-2x"></i>
                            </a>     
                        </td>
                        <td>
                            <vetweb:animaisCliente proprietario="${proprietario}"></vetweb:animaisCliente>
                        </td>
                    </tr>                
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</vetweb:layout>
