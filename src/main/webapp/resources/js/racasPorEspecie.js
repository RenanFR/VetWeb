//Busca as raças associadas a determinada espécie
var racasPorEspecie = 
        function () {//Ao alterar a raça do select de espécie (Lista de valores)
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
                    alert('Erro ao carregar lista de racas por especie. ');
                    $('#racas').find('option').remove().end();
                }
            });
        };
$(document).ready(racasPorEspecie);
$('#especies').change(racasPorEspecie);