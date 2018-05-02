$(document).ready(function () {
    $('.rg').mask('99.999.999-9');//Aplica a função nos campos com a classe em questão
    $('.telefone').mask('(99)9999-9999');
    $('.celular').mask('(99)9999-9999?9');//O ? indica que o último dígito é opcional
    $('.cpf').mask('999.999.999-99', {reverse: true});
});