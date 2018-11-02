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
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        
        <script src="<c:url value="/resources/js/fullcalendar/fullcalendar.js"></c:url>" type="text/javascript"></script>
        
        <script src="<c:url value="/resources/js/fullcalendar/pt-br.js"></c:url>" type="text/javascript"></script>
        
    	<script src="<c:url value="/resources/js/app/ajax-service.js"></c:url>" type="text/javascript"></script>
        
        <script>
        
	        var remarcacaoInicio = null;
	        
	        var remarcacaoFim = null;
	        
	        var remarcacao = null;
	        
	        var remarcacaoType = null;
        
	        $(document).ready(function() {
        		$('#dataRemarcacao').datetimepicker();
	        	ajaxService.buscarAnimaisPorCliente();
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
	   				 	ajaxService.buscarAnimaisPorCliente();
	   				    var enderecoProntuario = $('#irParaOProntuario');
	   				    var url = '/vetweb/agendamento/ocorrencia/';
	   				    enderecoProntuario.attr('href', url + $('#type').text() + '/' + $('#id').text());
					},
					eventDrop: function(event, delta, revertFunc, jsEvent, ui, view) {
					    if (!confirm("CONFIRMA A REMARCAÇÃO DA OCORRÊNCIA DE " + event.type + "?")) {
					        revertFunc();
				      	} else {
				      		try {
			   				 	ajaxService.remarcarOcorrencia(event.id, event.type, moment(event.start._i).format('YYYY-MM-DDTHH:mm'), moment(event.end._i).format('YYYY-MM-DDTHH:mm'));
				      		} catch(e) {
				      			 if (confirm('JÁ EXISTE OCORRÊNCIA NA DATA/INTERVALO SELECIONADO. DESEJA REMARCAR A OCORRÊNCIA SOBRESCRITA OU CANCELAR A OPERAÇÃO?')) {
				      				remarcacao = event.id;
				      				remarcacaoType = event.type;
				      				remarcacaoInicio = moment(event.start._i).format('YYYY-MM-DDTHH:mm');
				      				remarcacaoFim = moment(event.end._i).format('YYYY-MM-DDTHH:mm');
				      				$('#modalReagendar').modal('show');
				      			 }
				      		}
				      	}
					},
					selectable: true,
					selectHelper: true,
					select: function(start, end){
						$('#modalAgendamento').modal('show');
						$('#dataHoraInicial').val(moment(start._i).format('YYYY-MM-DDTHH:mm'));
						$('#dataHoraFinal').val(moment(end._i).format('YYYY-MM-DDTHH:mm'));
					},
					eventTextColor:	'#000000',
					events: 'http://localhost:8080/vetweb/agendamento/eventos'
	    		  });
	        });
	        
			$('#slcProprietarios').on('change', function() {
				ajaxService.buscarAnimaisPorCliente();
			});
			
			$('#btnRemarcacao').on('click', function() {
				ajaxService.remarcarOcorrenciasIntervalo(remarcacao, remarcacaoType, moment($('#dataRemarcacao').val()).format('YYYY-MM-DDTHH:mm'), remarcacaoInicio, remarcacaoFim);				
   				$('#modalReagendar').modal('toggle');
			});
			
			$('.rdoTipo').on('click', function() {
				var rdoSelecionado = $(this); 
				if (rdoSelecionado.val() === 'VACINA') {
					$('#slcVacina').css('display', 'block');
					$('#lblVacina').css('display', 'block');
					$('#slcAtendimento').css('display', 'none');
					$('#lblAtendimento').css('display', 'none');
					$('#slcExame').css('display', 'none');
					$('#lblExame').css('display', 'none');
				}
				if (rdoSelecionado.val() === 'ATENDIMENTO') {
					$('#slcAtendimento').css('display', 'block');
					$('#lblAtendimento').css('display', 'block');
					$('#slcVacina').css('display', 'none');
					$('#lblVacina').css('display', 'none');
					$('#slcExame').css('display', 'none');
					$('#lblExame').css('display', 'none');
				} 
				if (rdoSelecionado.val() === 'EXAME') {
					$('#slcExame').css('display', 'block');
					$('#lblExame').css('display', 'block');
					$('#lblAtendimento').css('display', 'none');
					$('#slcAtendimento').css('display', 'none');
					$('#slcVacina').css('display', 'none');
					$('#lblVacina').css('display', 'none');
				} 
				
				$('#lblDataHoraFinal').css('display', 'inline');
				$('#lblDataHoraInicial').css('display', 'inline');
				$('#dataHoraInicial').css('display', 'inline');
				$('#dataHoraFinal').css('display', 'inline');
							
			});
			
        </script>
        
    </jsp:attribute>

    <jsp:body>
          
          <div id="calendar">
          </div>
          
          <vetweb:modalOcorrenciaProntuario></vetweb:modalOcorrenciaProntuario>
          
          <vetweb:modalAgendamento proprietarios="${todosOsClientes}" 
				tiposDeAtendimento="${tiposDeAtendimento}" 
				vacinas="${todasAsVacinas}"
				exames="${exames}">
			</vetweb:modalAgendamento>
			
          <vetweb:modalReagendar></vetweb:modalReagendar>
            
    </jsp:body>

</vetweb:layout>
