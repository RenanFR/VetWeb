<%-- 
    Document   : detalhesCliente
    Created on : 02/01/2018, 12:24:20
    Author     : Maria Jéssica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->

<vetweb:layout title="Cliente ${proprietario.nome}">
    <table class="table table-responsive" id="detalhesProprietario">
        <caption>Cliente ${proprietario.nome}   /   Dados pessoais</caption>
        <tbody>
            <tr>
                <th>Nome</th>
                <td>${proprietario.nome}</td>
            </tr>
            <tr>
                <th>RG</th>
                <td>${proprietario.rg}</td>
            </tr>
            <tr>
                <th>CPF</th>
                <td>${proprietario.cpf}</td>
            </tr>
            <tr>
                <th>Sexo</th>
                <td>${proprietario.sexo}</td>
            </tr>
            <tr>
                <th>Tipo de pessoa</th>
                <td>${proprietario.tipoPessoa}</td>
            </tr>
            <tr>
                <th>Data do cadastro</th>
                <td>${proprietario.inclusao}</td>
            </tr>
            <tr>
                <th>Data de nascimento</th>
                <td>${proprietario.nascimento}</td>
            </tr>
            <tr>
                <th>Idade</th>
                <td>${idadeCliente}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="enderecoProprietario">
        <caption>Cliente ${proprietario.nome}   /   Dados de endereço</caption>
        <tbody>
            <tr>
                <th>CEP</th>
                <td>${proprietario.endereco.cep}</td>
            </tr>
            <tr>
                <th>Logradouro</th>
                <td>${proprietario.endereco.rua}</td>
            </tr>
            <tr>
                <th>Bairro</th>
                <td>${proprietario.endereco.bairro}</td>
            </tr>
            <tr>
                <th>Cidade</th>
                <td>${proprietario.endereco.cidade}</td>
            </tr>
            <tr>
                <th>Estado</th>
                <td>${proprietario.endereco.estado}</td>
            </tr>
            <tr>
                <th>Número</th>
                <td>${proprietario.endereco.numero}</td>
            </tr>
            <tr>
                <th>Complemento</th>
                <td>${proprietario.endereco.complemento}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="contatoProprietario">
        <caption>Cliente ${proprietario.nome}   /   Dados de contato</caption>
        <tbody>
            <tr>
                <th>Tel. Fixo</th>
                <td>${proprietario.contato.telefone}</td>
            </tr>
            <tr>
                <th>Tel. Celular</th>
                <td>${proprietario.contato.celular}</td>
            </tr>
            <tr>
                <th>E-mail</th>
                <td>${proprietario.contato.email}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="adicionalProprietario">
        <caption>Cliente ${proprietario.nome}   /   Dados adicionais</caption>
        <tbody>
            <tr>
                <th>Profissão</th>
                <td>${proprietario.profissao}</td>
            </tr>
            <tr>
                <th>Como nos conheceu</th>
                <td>${proprietario.comoNosConheceu}</td>
            </tr>
            <tr>
                <th>Aceita notificações?</th>
                <td>${proprietario.aceitaNotificacoes}</td>
            </tr>
            <tr>
                <th>Observações</th>
                <td>${proprietario.observacoes}</td>
            </tr>
        </tbody>
    </table>
    <p>Animais</p>
    <vetweb:animaisDoCliente proprietario="${proprietario}"></vetweb:animaisDoCliente>
</vetweb:layout>