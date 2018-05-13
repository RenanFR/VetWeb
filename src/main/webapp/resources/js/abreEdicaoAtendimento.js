var abreEdicaoAtendimento = function(idAtendimento) {
	var inputAtendimentoId = $('#atendimentoId');
	var select = $("select[id='tipoDeAtendimento']");
	var preenchimentoModeloAtendimento = tinymce.get('preenchimentoModeloAtendimento');
	var dataAtendimento = $('#dataAtendimento');
	$.ajax({
	    type: 'GET',
	    url: '/VetWeb/prontuario/editarAtendimento/' + idAtendimento,
	    contentType: 'text/html',
	    success: function (data, textStatus, jqXHR) {
	    	var formAtendimento = $('#formAtendimento');
	    	inputAtendimentoId.val(data.atendimentoId);
	    	select.val(data.tipoDeAtendimento.nome);
	    	preenchimentoModeloAtendimento.setContent(data.preenchimentoModeloAtendimento);
	    	var date = new Date();
	    	var day = date.getDate();
	    	var month = date.getMonth() + 1;
	    	var year = date.getFullYear();
	    	if (day < 10) { day = '0'+ day} if (month < 10) { month = '0' + month} date = day + '/' + month + '/' + year;
	    	dataAtendimento.attr('type', 'text');
	    	dataAtendimento.val(date);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	    	alert('Erro ao buscar o atendimento para edição.	');
	    }
	});	
};
