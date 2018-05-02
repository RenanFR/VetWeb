<%-- 
    Document   : cadastroUsuario
    Created on : 02/11/2017, 20:22:20
    Author     : renanrodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><!--   Adc. token p/ proteção contra csrf -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<vetweb:layout title="">
    <jsp:body>
        <h2>Novo Usuário</h2>
        <form:form servletRelativeAction="/usuarios/cadastrar" method="POST" modelAttribute="usuario">
            <fieldset>
                <label for="username">Login:  </label>
                <input type="text" name="username" id="username"  />
                <label for="name">Nome de Usuário:  </label>
                <input type="text" name="name" id="name"  />
                <label for="password">Senha:  </label>
                <input type="password" name="password" id="password"  />
                <label for="authorities[0].descricaoPerfil">Perfil de Acesso:  </label>
                <select name="authorities[0].descricaoPerfil" id="authorities[0].descricaoPerfil">
                    <option value="ROLE_usuario">usuario</option>
                    <option value="ROLE_admin">admin</option>
                </select>
            </fieldset>
            <input type="submit" id="cadastrar" name="cadastrar"    />
        </form:form>
        <p>${result}</p>
    </jsp:body>
</vetweb:layout>
