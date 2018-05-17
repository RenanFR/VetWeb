<%-- 
    Document   : proprietarios
    Created on : 16/11/2017, 17:20:54
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!--    Importação JSTL -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!--  tags úteis do spring framework   -->

<vetweb:layout title="proprietarios">
    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#proprietarios').dataTable();
            });
        </script>                 
    </jsp:attribute>
    <jsp:body>
        <button class="btn btn-primary">
            <i class="fa fa-save"></i>
            <a href="<c:url value="/clientes/cadastro"></c:url>" style="color: white">   <spring:message code="cadastrar"></spring:message></a>
        </button>
        <table class="table table-striped table-bordered table-hover" id="proprietarios">
            <thead>
                <tr>
                    <th>Nome do Cliente </th>
                    <th>Animais</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${proprietarios}" var="proprietario">
                    <tr>
                        <td>
                            <a href="<c:url value="/clientes/detalhesCliente/${proprietario.pessoaId}"></c:url>">${proprietario.nome}</a>     
                            <a href="<c:url value="/clientes/atualizar/${proprietario.pessoaId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/clientes/remover/${proprietario.pessoaId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/clientes/financeiro/${proprietario.pessoaId}"></c:url>">
                                <i class="fa fa-money  fa-2x"></i>
                            </a>     
                        </td>
                        <td>
                            <vetweb:animaisDoCliente proprietario="${proprietario}"></vetweb:animaisDoCliente>
                        </td>
                    </tr>                
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</vetweb:layout>
