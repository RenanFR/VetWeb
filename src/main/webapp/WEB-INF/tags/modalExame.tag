<%-- 
    Document   : modal.tag
    Created on : 31 de julho de 2018
    Author     : renan.rodrigues@metasix.com.br
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@attribute name="prontuario" required="true" type="java.lang.Object" %>
<%@attribute name="exames" required="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

      <!-- Modal -->
      <div class="modal fade" id="modalExame" tabindex="-1" role="dialog" aria-labelledby="labelModalExame" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="labelModalExame"><strong><spring:message code="adcExame"/></strong></h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <form:form servletRelativeAction="/prontuario/adicionarExame?prontuarioId=${prontuario.prontuarioId}" method="POST" id="frmIncluirExame">
                  <caption><spring:message code="adcExame"/></caption>
                  <tbody>
                  
                      <input type="text" name="prontuarioId" id="prontuarioId" hidden  />
                      
                      <input type="text" name="ocorrenciaExameId" id="ocorrenciaExameId" hidden  />
                      
                      <br	/>
                      
                      <tr>
                          <th><spring:message code="exame"></spring:message>: </th>
                          <td>
                          	<select name="exame" id="exames">
                               <c:forEach items="${exames}" var="exame">
                               	<option value="${exame}">${exame}</option>
                               </c:forEach>
                              </select>
                          </td>
                      </tr>
                      <br	/>
                      <tr>
                      	<th><spring:message code="data"/>:	</th>
                      	<td><input type="datetime-local" name="data" id="dataExame" /></td>
                      </tr>                        
                  </tbody>
                  
                  <div class="modal-footer">
                      <input type="reset" value="reset" class="btn btn-primary"   />
                      <input type="submit" value="submit" class="btn btn-primary" id="btnIncluirExame"  />
                  </div>
              </form:form>
            </div>
          </div>
        </div>
      </div>