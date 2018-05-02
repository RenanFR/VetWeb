<%-- 
    Document   : vacinas
    Created on : 22/04/2018, 22:19:51
    Author     : Maria Jéssica
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="vacinas">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#vacinas').dataTable();
            });
        </script>         
    </jsp:attribute>    
    <jsp:body>
        <button class="btn btn-primary">
        <i class="fa fa-save"></i>
        <a href="<c:url value="/prontuario/cadastroVacina"></c:url>" style="color: white">   <spring:message code="cadastro"></spring:message></a>
        </button>    
        <table class="table table-responsive" id="vacinas">
            <thead>
                <tr>
                    <th><spring:message code="nome"></spring:message></th>
                    <th><spring:message code="grupo"></spring:message></th>
                    <th><spring:message code="status"></spring:message></th>
                    <th><spring:message code="laboratorio"></spring:message></th>
                    <th><spring:message code="custo"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${vacinas}" var="vacina">
                    <tr>
                        <td>
                            ${vacina.nome}
							<a href="<c:url value="/prontuario/atualizarVacina/${vacina.vacinaId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>
                            <a href="<c:url value="/prontuario/removerVacina/${vacina.vacinaId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>
                        </td>
                        <td>
                            ${vacina.grupo}
                        </td>
                        <td>
                            ${vacina.status}
                        </td>
                        <td>
                            ${vacina.laboratorio}
                        </td>
                        <td>
                            ${vacina.preco}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</vetweb:layout>
