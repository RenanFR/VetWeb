var buscarParametros = function() {

	let relatorioSelecionado = $('#tipoRelatorio').val();

	$.ajax({
	    type: 'GET',
	    url: '/vetweb/relatorios/' + relatorioSelecionado,
	    contentType: 'text/html',
	    success: function (data, textStatus, jqXHR) {
	    	$('input').not('[value="Reset"], [value="Print"], [name="_csrf"]').remove();
	    	$('label').remove();
			for (param in data.parameters) {
				let form = $('#form');
				form.append('<tr>');
				let label = '<th><label for="' + data.parameters[param].key + '">' + data.parameters[param].key + '</label></th>';
				form.append(label);
				form.append('<td>');
				let input = '<input type="' + data.parameters[param].type + '" id="' + data.parameters[param].key + '"	name="' + data.parameters[param].key + '"  />';
				form.append(input);
				form.append('</td>');
				form.append('</tr>');
	        }
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	    	$('input').not('[value="Reset"], [value="Print"], [name="_csrf"]').remove();
	    	$('label').remove();
	    }
	});	
}

$(document).ready(function() {
	buscarParametros();
});

$('#tipoRelatorio').on('change', function() {
	buscarParametros();
});
