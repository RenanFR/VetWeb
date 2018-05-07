<%-- 
    Document   : prontuario
    Created on : 21/03/2018, 21:14:53
    Author     : Maria Jéssica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags úteis do spring framework   -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->

<vetweb:layout title="prontuario ${prontuario.animal.nome}">
    <jsp:attribute name="js">
        <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=2i80p03koooieys6i5h5yz1n9d4uaxrwt1iaoy9938bmcahs"></script>          
        <script>tinymce.init({ selector:'#preenchimentoModeloAtendimento' });</script>        
        <script src="<c:url value="/resources/js/modeloPorTipoDeAtendimento.js"></c:url>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/abreEdicaoAtendimento.js"></c:url>" type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <table id="prontuario" class="table table-responsive">
            <caption><spring:message code="prontuario" arguments="${prontuario.animal.nome}"></spring:message></caption>
            <thead>
                <tr>
                    <th><spring:message code="atendimentoMedico"></spring:message>  <button id="btnEditarAtendimento" data-toggle="modal" data-target="#modalAtendimento" onclick="modeloPorTipoDeAtendimento()"><i class="fa fa-caret-right"></i></button> </th>
                    <th><spring:message code="patologias"></spring:message> <button data-toggle="modal" data-target="#modalPatologia"><i class="fa fa-caret-right"></i></button></th>
                    <th><spring:message code="vacina"></spring:message> <button data-toggle="modal" data-target="#modalVacina"><i class="fa fa-caret-right" aria-hidden="true"></i></button></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
	                    <ul>
		                    <c:forEach items="${prontuario.atendimentos}" var="atendimento">
		                    	<li>
			                        ${atendimento.preenchimentoModeloAtendimento} ${atendimento.dataAtendimento}	${atendimento.tipoDeAtendimento.custo}
		                        	<a href="<c:url value="/prontuario/removerAtendimentoDoProntuario/${prontuario.prontuarioId}/${atendimento.atendimentoId}"></c:url>"><i class="fa fa-arrow-circle-o-down fa-2x"></i></a>
									<button onclick="btnEditaAtendimento()">
									</button>
		                    	</li>
		                    </c:forEach>
	                    </ul>
                    </td>
                    <td>
                    	<ul>
	                        <c:forEach items="${prontuario.patologias}" var="prontuarioPatologia">
		                    	<li>
		                            ${prontuarioPatologia.patologia.nome}	${prontuarioPatologia.inclusaoPatologia}
		                            <a href="<c:url value="/prontuario/removerPatologiaDoProntuario/${prontuario.prontuarioId}/${prontuarioPatologia.prontuarioPatologiaId}"></c:url>"><i class="fa fa-arrow-circle-o-down fa-2x"></i></a>
		                    	</li>
	                        </c:forEach>
                    	</ul>
                    </td>
                    <td>
	                    <ul>
	                        <c:forEach items="${prontuario.vacinas}" var="prontuarioVacina">
		                    	<li>
		                            ${prontuarioVacina.vacina.nome}	${prontuarioVacina.inclusaoVacina}	${prontuarioVacina.vacina.preco}
		                            <a href="<c:url value="/prontuario/removerVacinaDoProntuario/${prontuario.prontuarioId}/${prontuarioVacina.prontuarioVacinaId}?inclusaoOcorrenciaVacina=${prontuarioVacina.inclusaoVacina}"></c:url>"><i class="fa fa-arrow-circle-o-down fa-2x"></i></a>
		                    	</li>
	                        </c:forEach>
	                    </ul>
                    </td>
                </tr>
            </tbody>
        </table>
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
              <form:form servletRelativeAction="/prontuario/adicionarAtendimento?prontuarioId=${prontuario.prontuarioId}" method="POST" modelAttribute="atendimento">
                    <caption><spring:message code="adcAtendimento" arguments="${prontuario.animal.nome}"></spring:message></caption>
                    <tbody>
                    	<input type="text" name="atendimentoId" id="atendimentoId" hidden="hidden"  />
                        <br>
                        <tr>
                            <th><spring:message code="tipoDeAtendimento"></spring:message>: </th>
                            <td>
                                <form:select id="tipoDeAtendimento" path="tipoDeAtendimento" items="${tiposDeAtendimento}" onchange="modeloPorTipoDeAtendimento()"></form:select>
                            </td>
                        </tr>
                        <br>
                        <tr>
                            <th><spring:message code="modeloAtendimento"></spring:message>: </th>
                            <td id="modelo"><form:textarea path="preenchimentoModeloAtendimento" name="preenchimentoModeloAtendimento" id="preenchimentoModeloAtendimento"></form:textarea></td>
                        </tr>
                        <tr>
                        	<th><spring:message code="dataAtendimento"></spring:message>:	</th>
                        	<td><form:input type="date" name="dataAtendimento" id="dataAtendimento" path="dataAtendimento"></form:input></td>
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
                        <br>
                        <tr>
                            <th><spring:message code="patologia"></spring:message>: </th>
                            <td>
                            	<select name="patologia" id="patologia">
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
                        <br>
                        <tr>
                            <th><spring:message code="vacina"></spring:message>: </th>
                            <td>
                            	<select name="vacina" id="vacina">
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