<%-- 
    Document   : modal.tag
    Created on : 06/8/2018.
    Author     : renan.rodrigues@metasix.com.br
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@attribute name="proprietarios" required="true" type="java.util.List" %>

<%@attribute name="tiposDeAtendimento" required="true" type="java.util.List" %>

<%@attribute name="vacinas" required="true" type="java.util.List" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

       <!-- Modal -->
       <div class="modal fade" id="modalAgendamento" tabindex="-1" role="dialog" aria-labelledby="labelModalAgendamento" aria-hidden="true">
       
         <div class="modal-dialog" role="document">
         
           <div class="modal-content">
           
             <div class="modal-header">
             
               <h5 class="modal-title" id="labelModalAgendamento"><strong><spring:message code="adcAgendamento"/></strong></h5>
               
               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                 <span aria-hidden="true">&times;</span>
               </button>
               
             </div>
             
             <div class="modal-body">
             
					<form action="">
						
	                   <tbody>
	                   
						<label id="lblCliente" for="slcProprietarios"><spring:message code="cliente"/></label>
						                   
		                   <select id="slcProprietarios">
		                   		<c:forEach items="${proprietarios}" var="prop">
		                   			<option value="${prop.pessoaId}">${prop}</option>
		                   		</c:forEach>
		                   </select>
		                   
		                   <br	/>
		                
						<label id="lblAnimal" for="slcAnimal"><spring:message code="nomeAnimal"/></label>   
		                   <select id="slcAnimal" style="display: none;"></select><br>
		                   
						<label id="lblTipo" for="tipoOcorrencia"><spring:message code="tipo"/></label>
						
	               		  <input type="radio" name="tipoOcorrencia" value="VACINA" class="rdoTipo"> Vacina
	               		  
	               		  <input type="radio" name="tipoOcorrencia" value="ATENDIMENTO" class="rdoTipo"> Atendimento<br	/>
	               		  
							<label id="lblVacina" for="slcVacina" style="display: none;"><spring:message code="selecioneVacina"/></label>
		                   <select id="slcVacina" style="display: none;">
		                   		<c:forEach items="${todasAsVacinas}" var="vacina">
		                   			<option value="${vacina.vacinaId}">${vacina.nome}</option>
		                   		</c:forEach>
		                   </select>
		                   
		                   <br	/>
		                   
							<label id="lblAtendimento" for="slcAtendimento" style="display: none;"><spring:message code="selecioneAtendimento"/></label>
		                   <select id="slcAtendimento" style="display: none;">
		                   		<c:forEach items="${tiposDeAtendimento}" var="atendimento">
		                   			<option value="${atendimento.tipoDeAtendimentoId}">${atendimento.nome}</option>
		                   		</c:forEach>
		                   </select>
	                   		
	                   		<br	/>
	                   		
							<label id="lblDataHoraInicial" for="dataHoraInicial" style="display: none;"><spring:message code="dataHoraInicial"/></label>
							<input type="datetime-local" name="dataHoraInicial" id="dataHoraInicial" style="display: none;">
							
		                   <br	/>
							
							<label id="lblDataHoraFinal" for="dataHoraFinal" style="display: none;"><spring:message code="dataHoraFinal"/></label>
							<input type="datetime-local" name="dataHoraFinal" id="dataHoraFinal" style="display: none;">
	                   		
	                   </tbody>
	                   
	                   <div class="modal-footer">
	                       <input type="reset" value="reset" class="btn btn-primary"   />
	                       <input type="submit" value="submit" class="btn btn-primary"   />
	                   </div>
	                   
					</form>
					
               
             </div>
             
           </div>
           
         </div>
         
       </div>