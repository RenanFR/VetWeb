<%-- 
    Document   : agendamento
    Created on : 16/11/2017, 17:46:39
    Author     : renan.rodrigues
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
        
        <script>
	        $(document).ready(function() {
	    		  $('#calendar').fullCalendar({
	    			  
	    		  });
	        });
        </script>
        
    </jsp:attribute>
    
    <jsp:body>
          
          <div id="calendar">
          </div>
          
    </jsp:body>
    
</vetweb:layout>
