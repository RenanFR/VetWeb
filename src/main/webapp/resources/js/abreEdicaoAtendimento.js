var abreEdicaoAtendimento = function(idAtendimento) {
	var inputAtendimentoId = $('#atendimentoId');
	var inputTipoDeAtendimento = $('#tipoDeAtendimento');
	var inputPreenchimentoModeloAtendimento = $('#preenchimentoModeloAtendimento');
	var inputDataAtendimento = $('#dataAtendimento');
	$.ajax({
	    type: 'GET',
	    url: '/VetWeb/prontuario/editarAtendimento/' + idAtendimento,
	    contentType: 'text/html',
	    success: function (data, textStatus, jqXHR) {
	    	alert(data.atendimentoId);
	    	inputAtendimentoId.val(data.atendimentoId);
	    	inputTipoDeAtendimento.val(data.tipoDeAtendimento);
	    	tinymce.get('preenchimentoModeloAtendimento').setContent(data.preenchimentoModeloAtendimento);
	    	alert(data.dataAtendimento);
	    	inputDataAtendimento.val(data.dataAtendimento);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	    	alert('Erro ao buscar o atendimento para edição.	');
	    }
	});	
};
