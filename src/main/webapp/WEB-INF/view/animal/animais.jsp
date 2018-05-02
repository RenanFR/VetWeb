<%-- 
    Document   : animais
    Created on : 16/11/2017, 17:46:28
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags úteis do spring framework   -->

<vetweb:layout title="animais">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#animais').dataTable();
            });
        </script>         
    </jsp:attribute>
    <jsp:body>
        <button class="btn btn-primary">
            <i class="fa fa-save"></i>
            <a href="<c:url value="/animais/cadastro"></c:url>" style="color: white">   <spring:message code="cadastrar"></spring:message></a>
        </button>        
        <table class="table table-striped table-bordered table-hover" id="animais">
            <thead>
                <tr>
                    <th>Animal  </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${animais}" var="animal">
                    <tr>
                        <td>
                            <a href="<c:url value="/animais/detalhesAnimal/${animal.nome}"></c:url>">${animal.nome}</a>     
                            <a href="<c:url value="/animais/atualizar/${animal.animalId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/animais/remover/${animal.animalId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                        
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</vetweb:layout>
