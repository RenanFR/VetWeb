<%-- 
    Document   : agendamento
    Created on : 31 de julho de 2018
    Author     : renan.rodrigues@metasix.com.br
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Agendamento">

    <jsp:attribute name="script">
        <script src="<c:url value="/resources/js/fullcalendar/lib/moment.min.js"></c:url>" type="text/javascript"></script>
        
        <script src="<c:url value="/resources/js/fullcalendar/fullcalendar.js"></c:url>" type="text/javascript"></script>
        
        <script src="<c:url value="/resources/js/fullcalendar/pt-br.js"></c:url>" type="text/javascript"></script>
        
    	<script src="<c:url value="/resources/js/app/ajaxService.js"></c:url>" type="text/javascript"></script>
        
        <script>
	        $(document).ready(function() {
	    		  $('#calendar').fullCalendar({
	    			  events: 'http://localhost:8080/vetweb/agendamento/eventos',
	    			  eventClick: function(calEvent, jsEvent, view) {
	    				  	ajaxService.buscaOcorrenciaProntuario(calEvent.type, calEvent.id)
	    				    $('#modalOcorrenciaProntuario').modal('show');

						}
	    		  });
	        });
        </script>
        
    </jsp:attribute>
    
    <jsp:body>
          
          <div id="calendar">
          </div>
          
          <vetweb:modalOcorrenciaProntuario></vetweb:modalOcorrenciaProntuario>
          
    </jsp:body>
    
</vetweb:layout>
