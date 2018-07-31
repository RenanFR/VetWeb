<%-- 
    Document   : detalhesCliente
    Created on : 02/01/2018, 12:24:20
    Author     : Maria Jéssica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Cliente ${proprietario.nome}">
    <table class="table table-responsive" id="detalhesProprietario">
        <caption><spring:message code="cliente"/> ${proprietario.nome}   /   <spring:message code="dadosPessoais"/></caption>
        <tbody>
            <tr>
                <th><spring:message code="nome"/></th>
                <td>${proprietario.nome}</td>
            </tr>
            <tr>
                <th><spring:message code="RG"/></th>
                <td>${proprietario.rg}</td>
            </tr>
            <tr>
                <th><spring:message code="CPF"/></th>
                <td>${proprietario.cpf}</td>
            </tr>
            <tr>
                <th><spring:message code="sexo"/></th>
                <td>${proprietario.sexo}</td>
            </tr>
            <tr>
                <th><spring:message code="tipoPessoa"/></th>
                <td>${proprietario.tipoPessoa}</td>
            </tr>
            <tr>
                <th><spring:message code="inclusao"/></th>
                <td>${proprietario.inclusao}</td>
            </tr>
            <tr>
                <th><spring:message code="nascimento"/></th>
                <td>${proprietario.nascimento}</td>
            </tr>
            <tr>
                <th>Idade</th>
                <td>${idadeCliente}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="enderecoProprietario">
        <caption><spring:message code="cliente"/> ${proprietario.nome}   /   <spring:message code="dadosEndereco"/></caption>
        <tbody>
            <tr>
                <th><spring:message code="endereco.cep"/></th>
                <td>${proprietario.endereco.cep}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.rua"/></th>
                <td>${proprietario.endereco.rua}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.bairro"/></th>
                <td>${proprietario.endereco.bairro}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.cidade"/></th>
                <td>${proprietario.endereco.cidade}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.estado"/></th>
                <td>${proprietario.endereco.estado}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.numero"/></th>
                <td>${proprietario.endereco.numero}</td>
            </tr>
            <tr>
                <th><spring:message code="endereco.complemento"/></th>
                <td>${proprietario.endereco.complemento}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="contatoProprietario">
        <caption><spring:message code="cliente"/> ${proprietario.nome}   /   <spring:message code="dadosContato"/></caption>
        <tbody>
            <tr>
                <th><spring:message code="contato.telefone"/></th>
                <td>${proprietario.contato.telefone}</td>
            </tr>
            <tr>
                <th><spring:message code="contato.celular"/></th>
                <td>${proprietario.contato.celular}</td>
            </tr>
            <tr>
                <th><spring:message code="contato.email"/></th>
                <td>${proprietario.contato.email}</td>
            </tr>
        </tbody>
    </table>
    <table class="table table-responsive" id="adicionalProprietario">
        <caption><spring:message code="cliente"/> ${proprietario.nome}   /   <spring:message code="dadosAdicionais"/></caption>
        <tbody>
            <tr>
                <th><spring:message code="profissao"/></th>
                <td>${proprietario.profissao}</td>
            </tr>
            <tr>
                <th><spring:message code="comoNosConheceu"/></th>
                <td>${proprietario.comoNosConheceu}</td>
            </tr>
            <tr>
                <th><spring:message code="aceitaNotificacoes"/></th>
                <td>${proprietario.aceitaNotificacoes}</td>
            </tr>
            <tr>
                <th><spring:message code="ativo"/></th>
                <td>${proprietario.ativo}</td>
            </tr>
            <tr>
                <th><spring:message code="observacoes"/></th>
                <td>${proprietario.observacoes}</td>
            </tr>
        </tbody>
    </table>
    
    <vetweb:animaisCliente proprietario="${proprietario}"></vetweb:animaisCliente>
    
</vetweb:layout>