<%-- 
    Document   : detalhesAnimal
    Created on : 02/01/2018, 13:05:14
    Author     : renan.rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<vetweb:layout title="Animal ${animal.nome}">
    <table class="table table-responsive" id="detalhesAnimal">
        <caption>Animal ${animal.nome}	</caption>
        <tbody>
            <tr>
                <th><spring:message code="fotoAnimal"/></th>
                <td>
				    <c:if test="${fn:containsIgnoreCase(animal.imagem, 'imagens')}">
						<img src="${pageContext.request.contextPath}${animal.imagem}"
							height="30%" alt="${animal.imagem}" />                
				    </c:if>
				    <c:if test="${not fn:containsIgnoreCase(animal.imagem, 'imagens')}">
						<img src="${animal.imagem}"
							height="30%" alt="${animal.imagem}" />                
				    </c:if>
				</td>
            </tr>
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
