<%-- 
    Document   : patologias
    Created on : 22/02/2018, 20:32:49
    Author     : renan.rodrigues
--%>

<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Patologias">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#patologias').dataTable({
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
    
        <a href="<c:url value="/animais/cadastroPatologia"></c:url>" style="color: white">
	        <button class="btn btn-primary">
	        	<i class="fa fa-save"></i>
	           	<spring:message code="cadastro"/>
	        </button>    
		</a>
			
        <table class="table table-responsive" id="patologias">
            <thead>
                <tr>
                    <th><spring:message code="patologias"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${patologias}" var="patologia">
                    <tr>
                        <td>
                            ${patologia.nome}
                            <a href="<c:url value="/animais/atualizarPatologia/${patologia.patologiaId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>
                            <a href="<c:url value="/animais/removerPatologia/${patologia.patologiaId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:body>
</vetweb:layout>
