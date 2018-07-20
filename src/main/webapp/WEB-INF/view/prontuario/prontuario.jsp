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
        <script src="<c:url value="/resources/js/app/ajaxService.js"></c:url>" type="text/javascript"></script>
        <script>
            $(document).ready(function(){
               $('#historicos').dataTable();
            });
        </script>        
    </jsp:attribute>
    
    <jsp:body>
		<div class="row">

			<div class="col-md-6">
				<table class="table table-bordered" id="historicos">
					<thead>
						<tr>
							<th><spring:message code="historico"></spring:message>	<i class="fa fa-calendar fa-2x" aria-hidden="true"></i></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${historico}" var="elementoHistorico">
							<tr>
								<td>
									<c:if test="${elementoHistorico.tipo == 'ATENDIMENTO'}">
										<div class="panel panel-info">
										  <div class="panel-heading">${elementoHistorico.tipo}</div>
										  <div class="panel-body">
										  
											<button data-toggle="modal" data-target="#modalAtendimento" onclick="ajaxService.editarAtendimento(${elementoHistorico.elementohistoricoId})">
												<i class="fa fa-file-text-o fa-lg"></i>
											</button>
											
											<button>
												<a href="<c:url value="/prontuario/removerAtendimentoDoProntuario/${prontuario.prontuarioId}/${elementoHistorico.elementohistoricoId}"></c:url>"><i class="fa fa-trash-o fa-2x"></i></a>
											</button>
											
										  	${elementoHistorico.descricao}
										  	
										  </div>
										    <div class="panel-footer">${elementoHistorico.data}</div>
										</div>
									</c:if>
									<c:if test="${elementoHistorico.tipo == 'VACINA'}">
										<div class="panel panel-warning">
										  <div class="panel-heading">${elementoHistorico.tipo}</div>
										  <div class="panel-body">
										  
											<button data-toggle="modal" data-target="#modalVacina" onclick="ajaxService.editarVacina(${elementoHistorico.elementohistoricoId})">
												<i class="fa fa-file-text-o fa-lg"></i>
											</button>										  
										  	
										  	<button>
										  		<a href="<c:url value="/prontuario/removerVacinaDoProntuario/${prontuario.prontuarioId}/${elementoHistorico.elementohistoricoId}?inclusaoOcorrenciaVacina=${elementoHistorico.data}"></c:url>"><i class="fa fa-trash-o fa-2x"></i></a>
										  	</button>
										  	
										    ${elementoHistorico.descricao}
										  </div>
										    <div class="panel-footer">${elementoHistorico.data}</div>
										</div>
									</c:if>
									<c:if test="${elementoHistorico.tipo == 'PATOLOGIA'}">
										<div class="panel panel-danger">
										  <div class="panel-heading">${elementoHistorico.tipo}</div>
										  <div class="panel-body">
										  
											<button data-toggle="modal" data-target="#modalPatologia" onclick="ajaxService.editarPatologia(${elementoHistorico.elementohistoricoId})">
												<i class="fa fa-file-text-o fa-lg"></i>
											</button>
																			  
										  	<button>
											  	<a href="<c:url value="/prontuario/removerPatologiaDoProntuario/${prontuario.prontuarioId}/${elementoHistorico.elementohistoricoId}"></c:url>"><i class="fa fa-trash-o fa-2x"></i></a>
										  	</button>
										  	
										    ${elementoHistorico.descricao}
										  </div>
										    <div class="panel-footer">${elementoHistorico.data}</div>
										</div>
									</c:if>
								</td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
			
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-3">
						<button data-toggle="modal" data-target="#modalAtendimento" onclick="ajaxService.buscaModeloPorTipoDeAtendimento()" class="btn btn-info">
							<i class="fa fa-medkit fa-5x" aria-hidden="true"></i>
						</button>
					</div>
					<div class="col-md-3">
						<button data-toggle="modal" data-target="#modalVacina" class="btn btn-warning">
							<i class="fa fa-eyedropper fa-5x"></i>
						</button>
					</div>
					<div class="col-md-3">
						<button data-toggle="modal" data-target="#modalPatologia" class="btn btn-danger">
							<i class="fa  fa-plus-square fa-5x" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</div>
			
		</div>
        <!-- Modal -->
        <div class="modal fade" id="modalAtendimento" tabindex="-1" role="dialog" aria-labelledby="labelmodalAtendimento" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="labelmodalAtendimento"><strong><spring:message code="adcAtendimento" arguments="${prontuario.animal.nome}"></spring:message></strong></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
              <form:form servletRelativeAction="/prontuario/adicionarAtendimento?prontuarioId=${prontuario.prontuarioId}"
				method="POST" modelAttribute="atendimento" id="formAtendimento">
                    <caption><spring:message code="adcAtendimento" arguments="${prontuario.animal.nome}"></spring:message></caption>
                    <tbody>
                    	<input type="text" name="atendimentoId" id="atendimentoId" hidden="hidden"  />
                        <br>
                        <tr>
                            <th><spring:message code="tipoDeAtendimento"></spring:message>: </th>
                            <td>
                                <form:select id="tipoDeAtendimento" path="tipoDeAtendimento" items="${tiposDeAtendimento}" onchange="ajaxService.buscaModeloPorTipoDeAtendimento()">
                                </form:select>
                            </td>
                        </tr>
                        <br>
                        <tr>
                            <th><spring:message code="modeloAtendimento"></spring:message>: </th>
                            <td id="modelo"><form:textarea path="preenchimentoModeloAtendimento" name="preenchimentoModeloAtendimento" id="preenchimentoModeloAtendimento"></form:textarea></td>
                        </tr>
                        <tr>
                        	<th><spring:message code="dataAtendimento"></spring:message>:	</th>
                        	<td id="campoDataAtendimento"><input type="date" name="dataAtendimento" id="dataAtendimento"></input></td>
                        </tr>
                    </tbody>
                    
                    <div class="modal-footer">
                        <input type="reset" value="reset" class="btn btn-primary"   />
                        <input type="submit" value="submit" class="btn btn-primary"   />
                    </div>
                </form:form>
              </div>
            </div>
          </div>
        </div>                   
        <!-- Modal -->
        <div class="modal fade" id="modalPatologia" tabindex="-1" role="dialog" aria-labelledby="labelmodalPatologia" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="labelmodalPatologia"><strong><spring:message code="adcPatologia" arguments="${prontuario.animal.nome}"></spring:message></strong></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
              <form:form servletRelativeAction="/prontuario/adicionarPatologia?prontuarioId=${prontuario.prontuarioId}" method="POST">
                    <caption><spring:message code="adcAtendimento" arguments="${prontuario.animal.nome}"></spring:message></caption>
                    <tbody>
                        <input type="text" name="prontuarioId" id="prontuarioId" hidden="hidden"  />
                        <input type="text" name="prontuarioPatologiaId" id="prontuarioPatologiaId" hidden="hidden"  />
                        <br>
                        <tr>
                            <th><spring:message code="patologia"></spring:message>: </th>
                            <td>
                            	<select name="patologia" id="patologias">
	                                <c:forEach items="${patologias}" var="patologia">
	                                	<option value="${patologia}">${patologia}</option>
	                                </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <br>
                        <tr>
                        	<th><spring:message code="dataAtendimento"></spring:message>:	</th>
                        	<td><input type="date" name="inclusaoPatologia" id="inclusaoPatologia" /></td>
                        </tr>                        
                    </tbody>
                    
                    <div class="modal-footer">
                        <input type="reset" value="reset" class="btn btn-primary"   />
                        <input type="submit" value="submit" class="btn btn-primary"   />
                    </div>
                </form:form>
              </div>
            </div>
          </div>
        </div>                   
        <!-- Modal -->
        <div class="modal fade" id="modalVacina" tabindex="-1" role="dialog" aria-labelledby="labelmodalVacina" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="labelmodalVacina"><strong><spring:message code="adcVacina" arguments="${prontuario.animal.nome}"></spring:message></strong></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
              <form:form servletRelativeAction="/prontuario/adicionarVacina?prontuarioId=${prontuario.prontuarioId}" method="POST">
                    <caption><spring:message code="adcVacina" arguments="${prontuario.animal.nome}"></spring:message></caption>
                    <tbody>
                        <input type="text" name="prontuarioId" id="prontuarioId" hidden="hidden"  />
                        <input type="text" name="prontuarioVacinaId" id="prontuarioVacinaId" hidden="hidden"  />
                        <br>
                        <tr>
                            <th><spring:message code="vacina"></spring:message>: </th>
                            <td>
                            	<select name="vacina" id="vacinas">
	                            	<c:forEach items="${vacinas}" var="vacina">
	                            		<option value="${vacina}">${vacina}</option>	                            	
	                            	</c:forEach>
                            	</select>
                            </td>
                        </tr>
                        <br>
                        <tr>
                        	<th><spring:message code="dataAtendimento"></spring:message>:	</th>
                        	<td><input type="date" name="inclusaoVacina" id="inclusaoVacina" /></td>
                        </tr>                        
                    </tbody>
                    
                    <div class="modal-footer">
                        <input type="reset" value="reset" class="btn btn-primary"   />
                        <input type="submit" value="submit" class="btn btn-primary"   />
                    </div>
                </form:form>
              </div>
            </div>
          </div>
        </div>
    </jsp:body>
</vetweb:layout>