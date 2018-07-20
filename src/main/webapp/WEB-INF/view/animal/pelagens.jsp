<%-- 
    Document   : pelagens
    Created on : 17/02/2018, 00:20:57
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="Pelagens">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#pelagens').dataTable();
            });
        </script>         
    </jsp:attribute>    
    <jsp:body>
        <button class="btn btn-primary">
            <i class="fa fa-save"></i>
            <a href="<c:url value="/animais/cadastroPelagem"></c:url>" style="color: white">   <spring:message code="cadastro"></spring:message></a>
        </button>    
        <table class="table table-responsive" id="pelagens">
            <thead>
                <tr>
                    <th><spring:message code="pelagem"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pelagens}" var="pelagem">
                    <tr>
                        <td>
                            ${pelagem.descricao}
                            <a href="<c:url value="/animais/atualizarPelagem/${pelagem.pelagemId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/animais/removerPelagem/${pelagem.pelagemId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                      
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</vetweb:layout>
