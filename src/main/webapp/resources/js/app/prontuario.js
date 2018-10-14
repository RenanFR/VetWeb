$('#btnIncluirVacina').on('click', function(event) {
	event.preventDefault();
	let debitoSeHouver = $('#possuiDebitoVacina').text();
	let formularioVacina = $('#frmIncluirVacina');
	if (debitoSeHouver != null && debitoSeHouver == 'true') {
		let autorizaInclusaoComDebito = confirm('O CLIENTE POSSUI DÉBITO DE VACINA NÃO PAGA, DESEJA AUTORIZAR UMA NOVA INCLUSÃO MESMO ASSIM?');
		if(autorizaInclusaoComDebito) {
			formularioVacina.submit();	
        } else {
			$('#modalVacina').modal('toggle');
        }
	} else {
		formularioVacina.submit();
	}	
});

$('#btnIncluirAtendimento').on('click', function(event) {
	event.preventDefault();
	let debitoSeHouver = $('#possuiDebitoAtendimento').text();
	let formularioAtendimento = $('#formAtendimento');
	if (debitoSeHouver != null && debitoSeHouver == 'true') {
		let autorizaInclusaoComDebito = confirm('O CLIENTE POSSUI DÉBITO DE ATENDIMENTO NÃO PAGO, DESEJA AUTORIZAR UMA NOVA INCLUSÃO MESMO ASSIM?');
		if(autorizaInclusaoComDebito) {
			formularioAtendimento.submit();	
		} else {
			$('#modalAtendimento').modal('toggle');
		}
	} else {
		formularioAtendimento.submit();
	}	
});

$('#btnIncluirExame').on('click', function(event) {
	event.preventDefault();
	let debitoSeHouver = $('#possuiDebitoExame').text();
	let formularioExame = $('#frmIncluirExame');
	if (debitoSeHouver != null && debitoSeHouver == 'true') {
		let autorizaInclusaoComDebito = confirm('O CLIENTE POSSUI DÉBITO DE EXAME NÃO PAGO, DESEJA AUTORIZAR UMA NOVA INCLUSÃO MESMO ASSIM?');
		if(autorizaInclusaoComDebito) {
			formularioExame.submit();	
		} else {
			$('#modalExame').modal('toggle');
		}
	} else {
		formularioExame.submit();
	}	
});