<%-- 
    Document   : modal.tag
    Created on : 31 de julho de 2018
    Author     : renan.rodrigues@metasix.com.br
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!-- Modal -->
    <div class="modal fade" id="modalOcorrenciaProntuario" tabindex="-1" role="dialog" aria-labelledby="labelModalOcorrencia" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="labelModalOcorrencia"><strong><spring:message code="ocorrencia" /></strong></h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          
          <div class="modal-body">
          
          </div>
          
        </div>
      </div>
    </div>