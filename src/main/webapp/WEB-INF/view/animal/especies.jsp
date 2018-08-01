<%-- 
    Document   : especies
    Created on : 11/02/2018, 22:43:34
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Esp�cies">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#especies').dataTable({
	           	    language: {
	        			"sEmptyTable": "Nenhum registro encontrado",
	        			"sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
	        			"sInfoEmpty": "Mostrando 0 at� 0 de 0 registros",
	        			"sInfoFiltered": "(Filtrados de _MAX_ registros)",
	        			"sInfoPostFix": "",
	        			"sInfoThousands": ".",
	        			"sLengthMenu": "_MENU_ resultados por p�gina",
	        			"sLoadingRecords": "Carregando...",
	        			"sProcessing": "Processando...",
	        			"sZeroRecords": "Nenhum registro encontrado",
	        			"sSearch": "Pesquisar",
	        			"oPaginate": {
	        				"sNext": "Pr�ximo",
	        				"sPrevious": "Anterior",
	        				"sFirst": "Primeiro",
	        				"sLast": "�ltimo"
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
        
        <a href="<c:url value="/animais/cadastroEspecie"></c:url>" style="color: white">
	        <button class="btn btn-primary">
	        	    <i class="fa fa-save"></i>
	               <spring:message code="cadastro"></spring:message>
	        </button>
        </a>
            
        <table class="table table-responsive" id="especies">
            <thead>
                <tr>
                    <th><spring:message code="especie"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${especies}" var="especie">
                    <tr>
                        <td>
                            ${especie.descricao}
                            <a href="<c:url value="/animais/atualizarEspecie/${especie.especieId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/animais/removerEspecie/${especie.especieId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                      
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:body>
</vetweb:layout>