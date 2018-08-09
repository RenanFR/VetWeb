<%-- 
    Document   : modal.tag
    Created on : 31 de julho de 2018
    Author     : renan.rodrigues@metasix.com.br
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@attribute name="prontuario" required="true" type="java.lang.Object" %>
<%@attribute name="patologias" required="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

      <!-- Modal -->
      <div class="modal fade" id="modalPatologia" tabindex="-1" role="dialog" aria-labelledby="labelModalPatologia" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="labelModalPatologia"><strong><spring:message code="adcPatologia" arguments="${prontuario.animal.nome}"/></strong></h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <form:form servletRelativeAction="/prontuario/adicionarPatologia?prontuarioId=${prontuario.prontuarioId}" method="POST">
                  <caption><spring:message code="adcAtendimento" arguments="${prontuario.animal.nome}"/></caption>
                  <tbody>
                      <input type="text" name="prontuarioId" id="prontuarioId" hidden  />
                      <input type="text" name="prontuarioPatologiaId" id="prontuarioPatologiaId" hidden  />
                      <br	/>
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
                      <br	/>
                      <tr>
                      	<th><spring:message code="dataAtendimento"/>:	</th>
                      	<td><input type="datetime-local" name="data" id="inclusaoPatologia" /></td>
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