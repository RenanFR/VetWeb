//viacep. Usado p/ popular campos de endereço com base no cep
$(document).ready(function(){//Função executada após carregamento do documento
   $('#cep').blur(function(){//Seletor p/ Id. Evento ao deixar campo
       var cep = $(this).val().replace(/\D/g, '');//Recebe valor do campo. Mantém somente dígitos
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
   });
});