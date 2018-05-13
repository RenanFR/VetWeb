var abreEdicaoVacina = function(idVacina) {
	var inputVacinaId = $('#prontuarioVacinaId');
	var select = $("select[id='vacinas']");
	$.ajax({
	    type: 'GET',
	    url: '/VetWeb/prontuario/editarProntuarioVacina/' + idVacina,
	    contentType: 'text/html',
	    success: function (data, textStatus, jqXHR) {
	    	inputVacinaId.val(data.prontuarioVacinaId);
	    	select.val(data.vacina.nome);
	    	var date = new Date();
	    	var day = date.getDate();
	    	var month = date.getMonth() + 1;
	    	var year = date.getFullYear();
	    	if (day < 10) { day = '0'+ day} if (month < 10) { month = '0' + month} date = day + '/' + month + '/' + year;
	    	$('#inclusaoVacina').attr('type', 'text');
	    	$('#inclusaoVacina').val(date);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	    	alert('Erro ao buscar a vacina para edição.	');
	    }
	});	
};
