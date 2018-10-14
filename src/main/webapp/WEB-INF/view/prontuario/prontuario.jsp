<%-- 
    Document   : prontuario
    Created on : 21/03/2018, 21:14:53
    Author     : renan.rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<vetweb:layout title="Prontuário ${prontuario.animal.nome}">

    <jsp:attribute name="js">
    
        <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=2i80p03koooieys6i5h5yz1n9d4uaxrwt1iaoy9938bmcahs"></script>
                  
        <script>tinymce.init({ selector:'#preenchimentoModeloAtendimento' });</script>
                
        <script>
            $(document).ready(function(){
               $('#historicos').dataTable({
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
    
    <jsp:attribute name="script">
    	<script src="<c:url value="/resources/js/app/ajaxService.js"></c:url>" type="text/javascript"></script>
    	<script src="<c:url value="/resources/js/app/prontuario.js"></c:url>" type="text/javascript"></script>
    </jsp:attribute>
    
    <jsp:body>
			<div class="row">
				<div class="col-md-12">
					<button data-toggle="modal" data-target="#modalAtendimento" onclick="ajaxService.buscarModeloPorTipoDeAtendimento()" class="btn btn-info">
						<i class="fa fa-medkit fa-5x" aria-hidden="true"></i>
					</button>
					<button data-toggle="modal" data-target="#modalVacina" class="btn btn-warning">
						<i class="fa fa-eyedropper fa-5x"></i>
					</button>
					<button data-toggle="modal" data-target="#modalPatologia" class="btn btn-danger">
						<i class="fa  fa-plus-square fa-5x" aria-hidden="true"></i>
					</button>
					<button data-toggle="modal" data-target="#modalExame" class="btn btn-secondary">
						<i class="fa fa-stethoscope fa-5x" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			
		<div class="row">

			<div class="col-md-12">
				<table class="table table-bordered" id="historicos">
					<thead>
						<tr>
							<th><spring:message code="tipo"/>	<i class="fa fa-plus-square fa-2x" aria-hidden="true"></i></th>
							<th><spring:message code="historico"/>	<i class="fa fa-calendar fa-2x" aria-hidden="true"></i></th>
							<th><spring:message code="data"/><i class="fa fa-calendar-check-o fa-2x" aria-hidden="true"></i></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${historico}" var="elementoHistorico">
							<tr>
								<td>
									<c:if test="${elementoHistorico.tipo == 'ATENDIMENTO'}">
										<button data-toggle="modal" data-target="#modalAtendimento" onclick="ajaxService.editarAtendimento(${elementoHistorico.ocorrenciaId})">
											<i class="fa fa-file-text-o fa-lg"></i>
										</button>									
										<a href="<c:url value="/prontuario/removerAtendimentoDoProntuario/${prontuario.prontuarioId}/${elementoHistorico.ocorrenciaId}"></c:url>">
											<button>
												<i class="fa fa-trash-o fa-2x"></i>
											</button>
										</a>
									</c:if>
									<c:if test="${elementoHistorico.tipo == 'VACINA'}">
										<button data-toggle="modal" data-target="#modalVacina" onclick="ajaxService.editarVacina(${elementoHistorico.ocorrenciaId})">
											<i class="fa fa-file-text-o fa-lg"></i>
										</button>
										<a href="<c:url value="/prontuario/removerVacinaDoProntuario/${prontuario.prontuarioId}/${elementoHistorico.ocorrenciaId}?inclusaoOcorrenciaVacina=${elementoHistorico.data}"></c:url>">
										  	<button>
										  		<i class="fa fa-trash-o fa-2x"></i>
										  	</button>
										</a>
									</c:if>
									<c:if test="${elementoHistorico.tipo == 'PATOLOGIA'}">
										<button data-toggle="modal" data-target="#modalPatologia" onclick="ajaxService.editarPatologia(${elementoHistorico.ocorrenciaId})">
											<i class="fa fa-file-text-o fa-lg"></i>
										</button>
										<a href="<c:url value="/prontuario/removerPatologiaDoProntuario/${prontuario.prontuarioId}/${elementoHistorico.ocorrenciaId}"></c:url>">
										  	<button>
											  	<i class="fa fa-trash-o fa-2x"></i>
										  	</button>
										</a>
									</c:if>
									<c:if test="${elementoHistorico.tipo == 'EXAME'}">
										<button data-toggle="modal" data-target="#modalExame" onclick="ajaxService.editarExame(${elementoHistorico.ocorrenciaId})">
											<i class="fa fa-file-text-o fa-lg"></i>
										</button>
										<a href="<c:url value="/prontuario/removerExameDoProntuario/${prontuario.prontuarioId}/${elementoHistorico.ocorrenciaId}"></c:url>">
										  	<button>
											  	<i class="fa fa-trash-o fa-2x"></i>
										  	</button>
										</a>
									</c:if>
									${elementoHistorico.tipo}
								</td>
								<td>${elementoHistorico.descricao}</td>
								<td>${elementoHistorico.data}</td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
			<div id="possuiDebitoVacina" style="display: none;">${possuiDebitoVacina}</div>
			<div id="possuiDebitoAtendimento" style="display: none;">${possuiDebitoAtendimento}</div>
			<div id="possuiDebitoExame" style="display: none;">${possuiDebitoExame}</div>
		</div>
		
		<vetweb:modalAtendimento prontuario="${prontuario}" tiposDeAtendimento="${tiposDeAtendimento}"></vetweb:modalAtendimento>
		
		<vetweb:modalPatologia prontuario="${prontuario}" patologias="${patologia}"></vetweb:modalPatologia>
		
		<vetweb:modalVacina prontuario="${prontuario}" vacinas="${vacinas}"></vetweb:modalVacina>
		
		<vetweb:modalExame prontuario="${prontuario}" exames="${exames}"></vetweb:modalExame>
        
    </jsp:body>
</vetweb:layout>