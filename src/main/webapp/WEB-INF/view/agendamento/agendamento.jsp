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
					header: {
						left: 'prev,next today',
						center: 'title',
						right: 'month,agendaWeek,agendaDay'
					},
					defaultDate: Date(),
					navLinks: true,
					editable: true,
					eventClick: function(calEvent, jsEvent, view) {
						$('#modalOcorrenciaProntuario #id').text(calEvent.id);
						$('#modalOcorrenciaProntuario #type').text(calEvent.type);
						$('#modalOcorrenciaProntuario #title').text(calEvent.title);
						$('#modalOcorrenciaProntuario #start').text(calEvent.start.format('DD/MM/YYYY HH:mm:ss'));
	   				    $('#modalOcorrenciaProntuario').modal('show');
	   				    var enderecoProntuario = $('#irParaOProntuario');
	   				    var url = '/vetweb/agendamento/ocorrencia/';
	   				    enderecoProntuario.attr('href', url + $('#type').text() + '/' + $('#id').text());
					},
					selectable: true,
					selectHelper: true,
					select: function(start, end){
						alert(start + ' | ' + end);
					},					
					events: 'http://localhost:8080/vetweb/agendamento/eventos'
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
