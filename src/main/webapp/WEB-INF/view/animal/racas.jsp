<%-- 
    Document   : racas
    Created on : 14/02/2018, 17:35:16
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Ra�as">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#racas').dataTable({
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
    
         <a href="<c:url value="/animais/cadastroRaca"></c:url>" style="color: white">
	        <button class="btn btn-primary">
	            <i class="fa fa-save"></i>
               <spring:message code="cadastro"></spring:message>
	        </button>   
         </a>
         
        <table class="table table-responsive" id="racas">
            <thead>
                <tr>
                    <th>Ra�a</th>
                    <th>Esp�cie</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${racas}" var="raca">
                    <tr>
                        <td>
                            ${raca.descricao}
                            <a href="<c:url value="/animais/atualizarRaca/${raca.racaId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/animais/removerRaca/${raca.racaId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                            
                        </td>
                        <td>
                            ${raca.especie.descricao}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
                
    </jsp:body>
</vetweb:layout>