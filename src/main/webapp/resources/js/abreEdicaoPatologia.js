var abreEdicaoPatologia = function(idPatologia) {
	var inputProntuarioPatologiaId = $('#prontuarioPatologiaId');
	var select = $("select[id='patologias']");
	var inclusaoPatologia = $('#inclusaoPatologia');
	$.ajax({
	    type: 'GET',
	    url: '/VetWeb/prontuario/editarProntuarioPatologia/' + idPatologia,
	    contentType: 'text/html',
	    success: function (data, textStatus, jqXHR) {
	    	inputProntuarioPatologiaId.val(data.prontuarioPatologiaId);
	    	select.val(data.patologia.nome);
	    	var date = new Date();
	    	var day = date.getDate();
	    	var month = date.getMonth() + 1;
	    	var year = date.getFullYear();
	    	if (day < 10) { day = '0'+ day} if (month < 10) { month = '0' + month} date = day + '/' + month + '/' + year;
	    	inclusaoPatologia.attr('type', 'text');
	    	inclusaoPatologia.val(date);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	    	alert('Erro ao buscar a patologia para edição.	');
	    }
	});	
};
