<%-- 
    Document   : detalhesAnimal
    Created on : 02/01/2018, 13:05:14
    Author     : renan.rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Animal ${animal.nome}">
    <table class="table table-responsive" id="detalhesAnimal">
        <caption>Animal ${animal.nome}   <a href="<c:url value="/prontuario/prontuarioDoAnimal/${animal.animalId}"></c:url>"><i class="fa fa-clipboard fa-2x"></i></a></caption>
        <tbody>
            <tr>
                <th><spring:message code="nomeAnimal"/></th>
                <td>${animal.nome}</td>
            </tr>
            <tr>
                <th><spring:message code="nascimento"/></th>
                <td>${animal.dtNascimento}</td>
            </tr>
            <tr>
                <th><spring:message code="esteril"/></th>
                <td>${animal.esteril}</td>
            </tr>
            <tr>
                <th><spring:message code="status"/></th>
                <td>${animal.status}</td>
            </tr>
            <tr>
                <th><spring:message code="peso"/></th>
                <td>${animal.peso}</td>
            </tr>
            <tr>
                <th><spring:message code="pelagem"/></th>
                <td>${animal.pelagem.descricao}</td>
            </tr>
            <tr>
                <th><spring:message code="especie"/></th>
                <td>${animal.raca.descricao}</td>
            </tr>
        </tbody>
    </table>
</vetweb:layout>
