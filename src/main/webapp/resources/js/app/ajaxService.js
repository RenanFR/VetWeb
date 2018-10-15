var ajaxService = {
		
	editarAtendimento: function(atendimentoId) {
		
		var inputAtendimentoId = $('#ocorrenciaId');
		var select = $("select[id='tipoDeAtendimento']");
		var preenchimentoModeloAtendimento = tinymce.get('preenchimentoModeloAtendimento');
		var dataAtendimento = $('#dataAtendimento');
			$.ajax({
			    type: 'GET',
			    url: '/vetweb/ajax/prontuario/editarAtendimento/' + atendimentoId,
			    contentType: 'text/html',
			    success: function (data, textStatus, jqXHR) {
			    	var formAtendimento = $('#formAtendimento');
			    	inputAtendimentoId.val(data.ocorrenciaId);
			    	select.val(data.tipoDeAtendimento.nome);
			    	if (data.preenchimentoModeloAtendimento != null) {
			    		preenchimentoModeloAtendimento.setContent(data.preenchimentoModeloAtendimento);
			    	} else {
			    		preenchimentoModeloAtendimento.setContent(data.tipoDeAtendimento.modeloAtendimento);
			    	}
			    	var date = new Date();
			    	var day = date.getDate();
			    	var month = date.getMonth() + 1;
			    	var year = date.getFullYear();
			    	var hours = date.getHours();
			    	var minutes = date.getMinutes();
			    	if (day < 10) { day = '0'+ day} 
			    	if (month < 10) { month = '0' + month} 
			    	if (minutes < 10) { minutes = '0' + minutes} 
			    	date = year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
			    	dataAtendimento.val(date);
			    },
			    error: function (jqXHR, textStatus, errorThrown) {
			    	alert('ERRO AO BUSCAR O ATENDIMENTO PARA EDIÇÃO.	');
			    }
			});
			
	},
	
	editarVacina: function(vacinaId) {
		
		var inputVacinaId = $('#prontuarioVacinaId');
		var select = $("select[id='vacinas']");
		$.ajax({
		    type: 'GET',
		    url: '/vetweb/ajax/prontuario/editarProntuarioVacina/' + vacinaId,
		    contentType: 'text/html',
		    success: function (data, textStatus, jqXHR) {
		    	inputVacinaId.val(data.ocorrenciaId);
		    	select.val(data.vacina.nome);
		    	var date = new Date();
		    	var day = date.getDate();
		    	var month = date.getMonth() + 1;
		    	var year = date.getFullYear();
		    	var hours = date.getHours();
		    	var minutes = date.getMinutes();
		    	if (day < 10) { day = '0'+ day} 
		    	if (month < 10) { month = '0' + month} 
		    	if (minutes < 10) { minutes = '0' + minutes} 
		    	date = year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
		    	$('#inclusaoVacina').val(date);
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	alert('ERRO AO BUSCAR A VACINA PARA EDIÇÃO.	');
		    }
		});	
	},
	
	editarExame: function(exameId) {
		
		var inputExameId = $('#ocorrenciaExameId');
		var select = $("select[id='exames']");
		$.ajax({
			type: 'GET',
			url: '/vetweb/ajax/prontuario/editar-ocorrencia/exame/' + exameId,
			contentType: 'text/html',
			success: function (data, textStatus, jqXHR) {
				inputExameId.val(data.ocorrenciaId);
				select.val(data.exame.descricao);
				var date = new Date();
				var day = date.getDate();
				var month = date.getMonth() + 1;
				var year = date.getFullYear();
				var hours = date.getHours();
				var minutes = date.getMinutes();
				if (day < 10) { day = '0'+ day} 
				if (month < 10) { month = '0' + month} 
				if (minutes < 10) { minutes = '0' + minutes} 
				date = year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
				$('#dataExame').val(date);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert('ERRO AO BUSCAR EXAME PARA EDIÇÃO.	');
			}
		});	
	},
	
	editarPatologia: function(prontuarioPatologiaId) {
		
		var inputProntuarioPatologiaId = $('#prontuarioPatologiaId');
		var select = $("select[id='patologias']");
		var inclusaoPatologia = $('#inclusaoPatologia');
		$.ajax({
		    type: 'GET',
		    url: '/vetweb/ajax/prontuario/editarProntuarioPatologia/' + prontuarioPatologiaId,
		    contentType: 'text/html',
		    success: function (data, textStatus, jqXHR) {
		    	inputProntuarioPatologiaId.val(data.ocorrenciaId);
		    	select.val(data.patologia.nome);
		    	var date = new Date();
		    	var day = date.getDate();
		    	var month = date.getMonth() + 1;
		    	var year = date.getFullYear();
		    	var hours = date.getHours();
		    	var minutes = date.getMinutes();
		    	if (day < 10) { day = '0'+ day} 
		    	if (month < 10) { month = '0' + month} 
		    	if (minutes < 10) { minutes = '0' + minutes} 
		    	date = year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
		    	inclusaoPatologia.val(date);
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	alert('ERRO AO BUSCAR A PATOLOGIA PARA EDIÇÃO.	');
		    }
		});
		
	},
	
	alterarStatusPagamentoAtendimento: function(atendimentoId) {
		
		$.ajax({
		    type: 'GET',
		    url: '/vetweb/ajax/prontuario/atualizaStatusPagoAtendimento/' + atendimentoId,
		    contentType: 'text/html',
		    success: function (data, textStatus, jqXHR) {
		    	alert('STATUS DE PAGAMENTO DO ATENDIMENTO ALTERADO.	');
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	alert('ERRO AO ALTERAR O STATUS DE PAGAMENTO DO ATENDIMENTO.	');
		    }
		});
		
	},
	
	alterarStatusPagamentoExame: function(exameId) {
		
		$.ajax({
			type: 'GET',
			url: '/vetweb/ajax/prontuario/atualizaStatusPagoExame/' + exameId,
			contentType: 'text/html',
			success: function (data, textStatus, jqXHR) {
				alert('STATUS DE PAGAMENTO DO EXAME ALTERADO.	');
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert('ERRO AO ALTERAR O STATUS DE PAGAMENTO DO EXAME.	');
			}
		});
		
	},
	
	alterarStatusPagamentoVacina: function(prontuarioVacinaId) {
		
		$.ajax({
		    type: 'GET',
		    url: '/vetweb/ajax/prontuario/atualizaStatusPagoVacina/' + prontuarioVacinaId,
		    contentType: 'text/html',
		    success: function (data, textStatus, jqXHR) {
		    	alert('STATUS DE PAGAMENTO DA VACINA ALTERADO.	');
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	alert('ERRO AO ALTERAR O STATUS DE PAGAMENTO DA VACINA.	');
		    }
		});
		
	},
	
	buscarModeloPorTipoDeAtendimento: function() {
		
	    var ta = $('#tipoDeAtendimento').val();
	    $.ajax({
	        type: 'GET',
	        url: '/vetweb/ajax/prontuario/modeloPorTipoDeAtendimento/' + ta,
	        contentType: 'text/html',
	        success: function (data, textStatus, jqXHR) {
	            tinymce.get('preenchimentoModeloAtendimento').setContent(data);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert('ERRO AO BUSCAR O MODELO DO TIPO DE ATENDIMENTO SELECIONADO. TINYMCE');
	        }
	    });
	    
	},
	
	buscarRacasPorEspecie: function() {
		
        var esp = $('#especies').val();
        $.ajax({
            type: 'GET',
            url: '/vetweb/ajax/prontuario/racasPorEspecie/' + esp,
            contentType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                if (data.length === 0) {
                    $('#racas').find('option').remove().end();
                } else {
                    $.each(data, function (i, raca) {
                        $('#racas').find('option').remove().end().append('<option>' + raca.descricao + '</option>').val(raca.descricao);
                    });
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('ERRO AO CARREGAR LISTA DE RACAS POR ESPECIE. ');
                $('#racas').find('option').remove().end();
            }
        });
        
	},
	
	buscarAnimaisPorCliente: function() {
		
		var codigoCliente = $('#slcProprietarios').val();
		
		$.ajax({
			type: 'GET',
			url: '/vetweb/ajax/prontuario/animaisPorCliente/' + codigoCliente,
			contentType: 'application/json',
			success: function (data, textStatus, jqXHR) {
				var listaAnimais = $('#slcAnimal');
				listaAnimais.empty();
				if (data.length === 0) {
					alert('O CLIENTE NÃO TEM ANIMAIS CADASTRADOS. ');
					listaAnimais.css('display', 'none');
				} else {
					$.each(data, function (i, animal) {
						listaAnimais
							.append($('<option>', { value: animal.animalId, text: animal.nome }));
					});
					listaAnimais.css('display', 'inline');
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert('ERRO AO CARREGAR LISTA DE ANIMAIS PARA O CLIENTE. ');
			}
		});
		
	},
	
	remarcarOcorrencia: function(id, tipoOcorrencia, dataHoraInicial, dataHoraFinal) {
		
		$.ajax({
			type: 'GET',
			url: '/vetweb/ajax/prontuario/ocorrencia/' + id,
			contentType: 'application/json',
			data: "tipoOcorrencia=" + tipoOcorrencia + "&dataHoraInicial=" + dataHoraInicial + "&dataHoraFinal=" + dataHoraFinal + ""
		});
		
	},
	
	carregarEnderecoPeloCEP: function() {
		
       var cep = $('#cep').val().replace(/\D/g, '');
       if(cep !== ''){
           var validacao = /^[0-9]{8}$/;
           if(validacao.test(cep)){
               $('#rua').val('loading');
               $('#bairro').val('loading');
               $('#cidade').val('loading');
               $('#estado').val('loading');
               $.ajax({
                    url: "https://viacep.com.br/ws/" + cep + "/json/?callback=?",
                    type: 'POST',
                    dataType: 'JSON',
                    crossDomain: true,
                    contentType: 'application/json',
                    success: function (data, textStatus, jqXHR) {
                        $('#rua').val(data.logradouro);
                        $('#bairro').val(data.bairro);
                        $('#cidade').val(data.localidade);
                        $('#estado').val(data.uf);
                        $('#complemento').val(data.complemento);
                        $('#cep').val(data.cep);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("ERRO AO CONSULTAR O VIACEP! VERIFIQUE SUA CONEXÃO.   ".toUpperCase());
                    }
               });
           }
       }
	}
	
}