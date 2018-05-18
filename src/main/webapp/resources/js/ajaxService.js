var ajaxService = {
		
	editarAtendimento: function(atendimentoId) {
		
		var inputAtendimentoId = $('#atendimentoId');
		var select = $("select[id='tipoDeAtendimento']");
		var preenchimentoModeloAtendimento = tinymce.get('preenchimentoModeloAtendimento');
		var dataAtendimento = $('#dataAtendimento');
			$.ajax({
			    type: 'GET',
			    url: '/VetWeb/prontuario/editarAtendimento/' + atendimentoId,
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
			    	alert('ERRO AO BUSCAR O ATENDIMENTO PARA EDIÇÃO.	');
			    }
			});
			
	},
	
	editarVacina: function(vacinaId) {
		
		var inputVacinaId = $('#prontuarioVacinaId');
		var select = $("select[id='vacinas']");
		$.ajax({
		    type: 'GET',
		    url: '/VetWeb/prontuario/editarProntuarioVacina/' + vacinaId,
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
		    	alert('ERRO AO BUSCAR A VACINA PARA EDIÇÃO.	');
		    }
		});	
		
	},
	
	editarPatologia: function(prontuarioPatologiaId) {
		
		var inputProntuarioPatologiaId = $('#prontuarioPatologiaId');
		var select = $("select[id='patologias']");
		var inclusaoPatologia = $('#inclusaoPatologia');
		$.ajax({
		    type: 'GET',
		    url: '/VetWeb/prontuario/editarProntuarioPatologia/' + prontuarioPatologiaId,
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
		    	alert('ERRO AO BUSCAR A PATOLOGIA PARA EDIÇÃO.	');
		    }
		});
		
	},
	
	alteraStatusPagamentoAtendimento: function(atendimentoId) {
		
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
		
	},
	
	buscaModeloPorTipoDeAtendimento: function() {
		
	    var ta = $('#tipoDeAtendimento').val();
	    $.ajax({
	        type: 'GET',
	        url: '/VetWeb/prontuario/modeloPorTipoDeAtendimento/' + ta,
	        contentType: 'text/html',
	        success: function (data, textStatus, jqXHR) {
	            tinymce.get('preenchimentoModeloAtendimento').setContent(data);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert('ERRO AO BUSCAR O MODELO DO TIPO DE ATENDIMENTO SELECIONADO. TINYMCE');
	        }
	    });
	    
	},
	
	buscaRacasPorEspecie: function() {
		
        var esp = $('#especies').val();
        $.ajax({
            type: 'GET',
            url: '/VetWeb/animais/racasPorEspecie/' + esp,
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
	
	carregaEnderecoPeloCEP: function() {
		
       var cep = $('#cep').val().replace(/\D/g, '');//Recebe valor do campo. Mantém somente dígitos
       if(cep !== ''){
           var validacao = /^[0-9]{8}$/;//RegEx (Padrão de caracteres) p/ validação || processamento de texto
           if(validacao.test(cep)){//RegEx, valida se texto bate c/ o padrão
               $('#rua').val('Carregando');//Atribuí loading enquanto a consulta ao Web service é feita
               $('#bairro').val('Carregando');
               $('#cidade').val('Carregando');
               $('#estado').val('Carregando');
               $.ajax({//Consulta ao Web service//Requisição HTTP c/ retorno JSON
                    url: "https://viacep.com.br/ws/" + cep + "/json/?callback=?",//url da requisição
                    type: 'POST',//Método HTTP p/ requisição
                    dataType: 'JSON',//Retorno JSON
                    crossDomain: true,//Permite requisição entre domínios !=
                    contentType: 'application/json',
                    success: function (data, textStatus, jqXHR) {//CallBack (Função) p/ caso sucesso
                        $('#rua').val(data.logradouro);//Atribuí campo do JSON retornado ao campo
                        $('#bairro').val(data.bairro);
                        $('#cidade').val(data.localidade);
                        $('#estado').val(data.uf);
                        $('#complemento').val(data.complemento);
                        $('#cep').val(data.cep);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {//CallBack p/ erro no Web service
                        alert("Erro ao consultar o viacep! Verifique sua conexão.   ".toUpperCase());
                    }
               });
           }
       }
	}
	
}