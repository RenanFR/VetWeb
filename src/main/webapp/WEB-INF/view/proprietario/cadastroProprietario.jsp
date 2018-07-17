<%-- 
    Document   : cadastroProprietario
    Created on : 23/10/2017, 18:38:07
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="Cadastro Proprietario">

    <jsp:attribute name="js">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/ajaxService.js"></script>
    </jsp:attribute>
    
    <jsp:attribute name="mascaras">   
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/mascaras.js"></script>
    </jsp:attribute>
    
    <jsp:body>
        <form:form servletRelativeAction="/clientes/cadastrar" method="POST" modelAttribute="proprietario">
            <table class="table table-responsive" id="detalhesProprietario">
                <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosPessoais"></spring:message></caption>
                <form:hidden path="pessoaId" id="pessoaId"></form:hidden>
                <tbody>
                    <tr>
                        <th><label for="nome"><spring:message code="nome"></spring:message></label></th>
                        <td><form:input path="nome" id="nome" maxlength="100"></form:input></td>
                        <td><form:errors path="nome" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="rg"><spring:message code="RG"></spring:message></label></th>
                        <td><form:input path="rg" id="rg" cssClass="rg"></form:input></td>
                        <td><form:errors path="rg" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="cpf"><spring:message code="CPF"></spring:message></label></th>
                        <td><form:input path="cpf" id="cpf" cssClass="cpf"></form:input></td>
                        <td><form:errors path="cpf" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="sexo"><spring:message code="sexo"></spring:message></label></th>
                        <td>
                            <form:radiobutton path="sexo" id="sexo" value="M"   />M
                            <form:radiobutton path="sexo" id="sexo" value="F"   />F
                        </td>
                        <td><form:errors path="sexo" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="tipoPessoa"><spring:message code="tipoPessoa"></spring:message></label></th>
                        <td>
                            <form:radiobutton path="tipoPessoa" id="tipoPessoa" value="FISICA"  /><spring:message code="fisica"></spring:message><!--  Para enum value(s) devem ser compatíveis com as constantes  -->
                            <form:radiobutton path="tipoPessoa" id="tipoPessoa" value="JURIDICA"    /><spring:message code="juridica"></spring:message>
                        </td>
                        <td><form:errors path="tipoPessoa" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="nascimento"><spring:message code="nascimento"></spring:message></label></th>
                        <td><form:input path="nascimento" id="nascimento" type="date" ></form:input></td>
                        <td><form:errors path="nascimento" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="inclusao"><spring:message code="inclusao"></spring:message></label></th>
                        <td><form:input path="inclusao" id="inclusao" type="date"></form:input></td>
                        <td><form:errors path="inclusao" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="nacionalidade"><spring:message code="nacionalidade"></spring:message></label></th>
                        <td><form:select path="nacionalidade" items="${paises}"></form:select></td>
                        <td><form:errors path="nacionalidade" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-responsive" id="enderecoProprietario">
                <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosEndereco"></spring:message></caption>
                <tbody>
                    <tr>
                        <th><label for="endereco.cep"><spring:message code="endereco.cep"></spring:message></label></th>
                        <td><form:input path="endereco.cep" id="cep" onblur="ajaxService.carregaEnderecoPeloCEP()"></form:input></td>
                        <td><form:errors path="endereco.cep" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.rua"><spring:message code="endereco.rua"></spring:message></label></th>
                        <td><form:input path="endereco.rua" id="rua"></form:input></td>
                        <td><form:errors path="endereco.rua" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.bairro"><spring:message code="endereco.bairro"></spring:message></label></th>
                        <td><form:input path="endereco.bairro" id="bairro"></form:input></td>
                        <td><form:errors path="endereco.bairro" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.cidade"><spring:message code="endereco.cidade"></spring:message></label></th>
                        <td><form:input path="endereco.cidade" id="cidade"></form:input></td>
                        <td><form:errors path="endereco.cidade" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.estado"><spring:message code="endereco.estado"></spring:message></label></th>
                        <td><form:input path="endereco.estado" id="estado"></form:input></td>
                        <td><form:errors path="endereco.estado" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.numero"><spring:message code="endereco.numero"></spring:message></label></th>
                        <td><form:input path="endereco.numero" id="numero"></form:input></td>
                        <td><form:errors path="endereco.numero" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.complemento"><spring:message code="endereco.complemento"></spring:message></label></th>
                        <td><form:input path="endereco.complemento" id="complemento"></form:input></td>
                        <td><form:errors path="endereco.complemento" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-responsive" id="contatoProprietario">
                <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosContato"></spring:message></caption>
                <tbody>
                    <tr>
                        <th><label for="contato.telefone"><spring:message code="contato.telefone"></spring:message></label></th>
                        <td><form:input path="contato.telefone" id="telefone" cssClass="telefone"></form:input></td>
                        <td><form:errors path="contato.telefone" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="contato.celular"><spring:message code="contato.celular"></spring:message></label></th>
                        <td><form:input path="contato.celular" id="celular" cssClass="celular"></form:input></td>
                        <td><form:errors path="contato.celular" cssClass="errors"></form:errors>  </td>
                    </tr>
                    <tr>
                        <th><label for="contato.email"><spring:message code="contato.email"></spring:message></label></th>
                        <td><form:input path="contato.email" id="email"></form:input></td>
                        <td><form:errors path="contato.email" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-responsive" id="adicionalProprietario">
                <caption><spring:message code="cliente"></spring:message> ${proprietario.nome}   /   <spring:message code="dadosAdicionais"></spring:message></caption>
                <tbody>
                    <tr>
                        <th><label for="profissao"><spring:message code="profissao"></spring:message></label></th>
                        <td><form:select path="profissao" items="${profissoes}"></form:select></td>
                        <td><form:errors path="profissao" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="comoNosConheceu"><spring:message code="comoNosConheceu"></spring:message></label></th>
                        <td><form:input path="comoNosConheceu" id="comoNosConheceu"></form:input></td>
                        <td><form:errors path="comoNosConheceu" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="aceitaNotificacoes"><spring:message code="aceitaNotificacoes"></spring:message></label></th>
                        <td>
                            <form:radiobutton path="aceitaNotificacoes" id="aceitaNotificacoes" value="true"    /><spring:message code="sim"></spring:message>
                            <form:radiobutton path="aceitaNotificacoes" id="aceitaNotificacoes" value="false"   /><spring:message code="nao"></spring:message>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="observacoes"><spring:message code="observacoes"></spring:message></label></th>
                        <td><form:textarea path="observacoes" id="observacoes"></form:textarea></td>
                        <td><form:errors path="observacoes" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>                                          
            <input type="submit" value="submit"  />            
        </form:form> 
        
    </jsp:body>
</vetweb:layout>