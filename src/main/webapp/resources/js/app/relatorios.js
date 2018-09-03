var buscarParametros = function() {

	let relatorioSelecionado = $('#tipoRelatorio').val();

	$.ajax({
	    type: 'GET',
	    url: '/vetweb/relatorios/' + relatorioSelecionado,
	    contentType: 'text/html',
	    success: function (data, textStatus, jqXHR) {
			for (param in data.parameters) {
				$('#' + data.parameters[param].key + '').remove();
				$('#' + data.parameters[param].key + '').remove();
				$('[for="' + data.parameters[param].key + '"]').remove();
				$('[for="' + data.parameters[param].key + '"]').remove();
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
	    	$('#form input').remove();
	    	$('#form label').remove();
	    }
	});	
}

$(document).ready(function() {
	buscarParametros();
});

$('#tipoRelatorio').on('change', function() {
	buscarParametros();
});
