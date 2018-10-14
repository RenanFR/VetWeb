<%-- 
    Document   : modal.tag
    Created on : 31 de julho de 2018
    Author     : renan.rodrigues@metasix.com.br
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@attribute name="prontuario" required="true" type="java.lang.Object" %>
<%@attribute name="tiposDeAtendimento" required="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

       <!-- Modal -->
       <div class="modal fade" id="modalAtendimento" tabindex="-1" role="dialog" aria-labelledby="labelModalAtendimento" aria-hidden="true">
         <div class="modal-dialog" role="document">
           <div class="modal-content">
             <div class="modal-header">
               <h5 class="modal-title" id="labelModalAtendimento"><strong><spring:message code="adcAtendimento" arguments="${prontuario.animal.nome}"/></strong></h5>
               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                 <span aria-hidden="true">&times;</span>
               </button>
             </div>
             <div class="modal-body">
             <form:form servletRelativeAction="/prontuario/adicionarAtendimento?prontuarioId=${prontuario.prontuarioId}"
					method="POST" modelAttribute="atendimento" id="formAtendimento">
                   <tbody>
                   	<form:hidden path="ocorrenciaId" id="ocorrenciaId"	/>
                       <tr>
                           <th><spring:message code="tipoDeAtendimento"/>: </th>
                           <td>
                               <form:select id="tipoDeAtendimento" path="tipoDeAtendimento" items="${tiposDeAtendimento}" onchange="ajaxService.buscarModeloPorTipoDeAtendimento()">
                               </form:select>
                           </td>
                       </tr>
                       
                       <br	/>
                       
                       <tr>
                           <th><spring:message code="modeloAtendimento"/>: </th>
                           <td id="modelo"><form:textarea path="preenchimentoModeloAtendimento" name="preenchimentoModeloAtendimento" id="preenchimentoModeloAtendimento"></form:textarea></td>
                       </tr>
                       <tr>
                       	<th><spring:message code="dataAtendimento"/>:	</th>
                       	<td id="campoDataAtendimento"><input type="datetime-local" name="data" id="dataAtendimento"></input></td>
                       </tr>
                   </tbody>
                   
                   <div class="modal-footer">
                       <input type="reset" value="reset" class="btn btn-primary"   />
                       <input type="submit" value="submit" class="btn btn-primary" id="btnIncluirAtendimento"  />
                   </div>
               </form:form>
             </div>
           </div>
         </div>
       </div>