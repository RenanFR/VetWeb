<%-- 
    Document   : relatorios
    Created on : 16/11/2017, 17:20:54
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<vetweb:layout title="Relat�rios">

    <jsp:attribute name="js">
    
    </jsp:attribute>
    
    <jsp:body>

        <form:form servletRelativeAction="/relatorios" method="POST">
        
			<table class="table table-responsive">
			
              <caption><spring:message code="selecioneRelatorio"/></caption>
              
              <tbody>
                  
                  <tr>
                      <th><spring:message code="relatoriosDisponiveis"	/>: </th>
                      <td>
                      	<select name="tipoRelatorio" id="tipoRelatorio">
                           	<option value="Ocorrencia"><spring:message code="ocorrenciasPorTipo"	/></option>
						</select>
                      </td>
                  </tr>
                                          
              </tbody>
              
            </table>
            
            <input type="reset" value="reset" class="btn btn-primary"   />
            <input type="submit" value="print" class="btn btn-primary"   />
              
          </form:form>

    </jsp:body>
    
</vetweb:layout>
