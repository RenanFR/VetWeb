var atualizaPagamentoAtendimento = function(atendimentoId) {
	$.ajax({
	    type: 'GET',
	    url: '/VetWeb/prontuario/atualizaStatusPagoAtendimento/' + atendimentoId,
	    contentType: 'text/html',
	    success: function (data, textStatus, jqXHR) {
	    	alert('Status de pagamento do atendimento alterado.	');
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	    	alert('Erro ao alterar o status de pagamento do atendimento.	');
	    }
	});	
};