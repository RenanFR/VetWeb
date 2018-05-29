<%-- 
    Document   : detalhesCliente
    Created on : 02/01/2018, 12:24:20
    Author     : Maria Jéssica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Cliente ${proprietario.nome}">
    <table class="table table-responsive" id="detalhesProprietario">
        <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosPessoais"></spring:message></caption>
        <tbody>
            <tr>
                <th><spring:message code="nome"></spring:message></th>
                <td>${proprietario.nome}</td>
            </tr>
            <tr>
                <th><spring:message code="RG"></spring:message></th>
                <td>${proprietario.rg}</td>
            </tr>
            <tr>
                <th><spring:message code="CPF"></spring:message></th>
                <td>${proprietario.cpf}</td>
            </tr>
            <tr>
                <th><spring:message code="sexo"></spring:message></th>
                <td>${proprietario.sexo}</td>
            </tr>
            <tr>
                <th><spring:message code="tipoPessoa"></spring:message></th>
                <td>${proprietario.tipoPessoa}</td>
            </tr>
            <tr>
                <th><spring:message code="inclusao"></spring:message></th>
                <td>${proprietario.inclusao}</td>
            </tr>
            <tr>
                <th><spring:message code="nascimento"></spring:message></th>
                <td>${proprietario.nascimento}</td>
            </tr>
            <tr>
                <th>Idade</th>
                <td>${idadeCliente}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="enderecoProprietario">
        <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosEndereco"></spring:message></caption>
        <tbody>
            <tr>
                <th><spring:message code="endereco.cep"></spring:message></th>
                <td>${proprietario.endereco.cep}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.rua"></spring:message></th>
                <td>${proprietario.endereco.rua}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.bairro"></spring:message></th>
                <td>${proprietario.endereco.bairro}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.cidade"></spring:message></th>
                <td>${proprietario.endereco.cidade}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.estado"></spring:message></th>
                <td>${proprietario.endereco.estado}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.numero"></spring:message></th>
                <td>${proprietario.endereco.numero}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.complemento"></spring:message></th>
                <td>${proprietario.endereco.complemento}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="contatoProprietario">
        <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosContato"></spring:message></caption>
        <tbody>
            <tr>
                <th><spring:message code="contato.telefone"></spring:message></th>
                <td>${proprietario.contato.telefone}</td>
            </tr>
            <tr>
                <th><spring:message code="contato.celular"></spring:message></th>
                <td>${proprietario.contato.celular}</td>
            </tr>
            <tr>
                <th><spring:message code="contato.email"></spring:message></th>
                <td>${proprietario.contato.email}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="adicionalProprietario">
        <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosAdicionais"></spring:message></caption>
        <tbody>
            <tr>
                <th><spring:message code="profissao"></spring:message></th>
                <td>${proprietario.profissao}</td>
            </tr>
            <tr>
                <th><spring:message code="comoNosConheceu"></spring:message></th>
                <td>${proprietario.comoNosConheceu}</td>
            </tr>
            <tr>
                <th><spring:message code="aceitaNotificacoes"></spring:message></th>
                <td>${proprietario.aceitaNotificacoes}</td>
            </tr>
            <tr>
                <th><spring:message code="ativo"></spring:message></th>
                <td>${proprietario.ativo}</td>
            </tr>
            <tr>
                <th><spring:message code="observacoes"></spring:message></th>
                <td>${proprietario.observacoes}</td>
            </tr>
        </tbody>
    </table>
    <p>Animais</p>
    <vetweb:animaisDoCliente proprietario="${proprietario}"></vetweb:animaisDoCliente>
</vetweb:layout>