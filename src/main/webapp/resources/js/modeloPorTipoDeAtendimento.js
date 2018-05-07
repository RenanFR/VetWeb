//Busca o texto c/ o modelo de atendimento de determinado tipo para preenchimento no prontuário
var modeloPorTipoDeAtendimento = 
        function () {//Ao alterar a tipo de atendimento do select busca o respectivo modelo
            var ta = $('#tipoDeAtendimento').val();
            $.ajax({
                type: 'GET',
                url: '/VetWeb/prontuario/modeloPorTipoDeAtendimento/' + ta,
                contentType: 'text/html',
                success: function (data, textStatus, jqXHR) {
                    tinymce.get('preenchimentoModeloAtendimento').setContent(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert('Erro ao buscar o modelo do tipo de atendimento selecionado. tinymce ');
                }
            });
        };