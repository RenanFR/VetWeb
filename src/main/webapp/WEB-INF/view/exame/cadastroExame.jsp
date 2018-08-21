<%-- 
    Document   : cadastroVacina
    Created on : 22/04/2018, 22:22:21
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<vetweb:layout title="Cadastro Exame">

    <jsp:attribute name="js">
    	<script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=2i80p03koooieys6i5h5yz1n9d4uaxrwt1iaoy9938bmcahs"></script>
    	<script>tinymce.init({ selector:'textarea' });</script>
    </jsp:attribute>
    
    <jsp:body>
        <form:form servletRelativeAction="/exames/submitForm" method="POST" modelAttribute="exame">
            <table class="table table-responsive">
                <caption><spring:message code="adcExame"/></caption>
                <tbody>
                    <form:hidden path="exameId" id="exameId"></form:hidden>
                    <tr>
                        <th><label for="descricao"><spring:message code="descricao"/></label></th>
                        <td><form:input path="descricao" id="descricao"/></td>
                    </tr>
                    <tr>
                        <th><label for="apresentacao"><spring:message code="apresentacao"/></label></th>
                        <td><form:textarea path="apresentacao" id="apresentacao"/></td>
                    </tr>
                    <tr>
                        <th><label for="encerramento"><spring:message code="encerramento"/></label></th>
                        <td>
                            <form:textarea path="encerramento" id="encerramento"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="submit"  />
            <input type="reset" value="reset"  />                
        </form:form>
    </jsp:body>
</vetweb:layout>
