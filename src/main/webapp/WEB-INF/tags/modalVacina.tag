<%-- 
    Document   : modal.tag
    Created on : 31 de julho de 2018
    Author     : renan.rodrigues@metasix.com.br
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@attribute name="prontuario" required="true" type="java.lang.Object" %>
<%@attribute name="vacinas" required="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

      <!-- Modal -->
      <div class="modal fade" id="modalVacina" tabindex="-1" role="dialog" aria-labelledby="labelModalVacina" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="labelModalVacina"><strong><spring:message code="adcVacina" arguments="${prontuario.animal.nome}"></spring:message></strong></h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <form:form servletRelativeAction="/prontuario/adicionarVacina?prontuarioId=${prontuario.prontuarioId}" method="POST" id="frmIncluirVacina">
                  <caption><spring:message code="adcVacina" arguments="${prontuario.animal.nome}"></spring:message></caption>
                  <tbody>
                      <input type="text" name="prontuarioId" id="prontuarioId" hidden="hidden"  />
                      <input type="text" name="prontuarioVacinaId" id="prontuarioVacinaId" hidden="hidden"  />
                      <br>
                      <tr>
                          <th><spring:message code="vacina"/>: </th>
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
                      	<th><spring:message code="dataAtendimento"/>:	</th>
                      	<td><input type="datetime-local" name="data" id="inclusaoVacina" /></td>
                      </tr>                        
                  </tbody>
                  
                  <div class="modal-footer">
                      <input type="reset" value="reset" class="btn btn-primary"   />
                      <input type="submit" value="submit" class="btn btn-primary" id="btnIncluirVacina"  />
                  </div>
              </form:form>
            </div>
          </div>
        </div>
      </div>