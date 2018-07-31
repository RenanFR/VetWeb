<%-- 
    Document   : animaisDoCliente
    Created on : 02/01/2018, 12:49:44
    Author     : Maria Jéssica
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@attribute name="proprietario" required="true" type="java.lang.Object" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${proprietario.animais.size() >= 0}">
	
	<h5><spring:message code="animais"/></h5>
	
    <c:forEach items="${proprietario.animais}" var="animal">
        <ul>
            <li><a href="<c:url value="/animais/detalhesAnimal/${animal.nome}"></c:url>">${animal.nome}</a></li>
        </ul>
    </c:forEach>
    
</c:if>

<c:if test="${proprietario.animais.size() < 0}">

    Sem animal cadastrado
    
</c:if>
<button class="btn btn-success"><a href="<c:url value="/clientes/addAnimal/${proprietario.pessoaId}"></c:url>" style="color: white"><spring:message code="adcAnimal"/></a></button>
