$(document).ready(function () {
    $('.rg').mask('99.999.999-9');
    $('.telefone').mask('(99)9999-9999');
    $('.celular').mask('(99)9999-9999?9');
    $('.cpf').mask('999.999.999-99', {reverse: true});
});