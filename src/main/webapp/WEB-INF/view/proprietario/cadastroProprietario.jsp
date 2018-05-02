<%-- 
    Document   : cadastroProprietario
    Created on : 23/10/2017, 18:38:07
    Author     : 11151504898
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--   Form c/ utilidades do spring    -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><!--   Adc. token p/ proteção contra csrf -->
<vetweb:layout title="Cadastro de Cliente">
    <jsp:attribute name="script">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/viacep.js"></script>
    </jsp:attribute>
    <jsp:attribute name="mascaras">   
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mascaras.js"></script>
    </jsp:attribute>
    <jsp:body><!--  Substituí o jsp:doBody no layout. Deve vir por último   -->
        <form:form servletRelativeAction="/clientes/cadastrar" method="POST" modelAttribute="proprietario">
            <table class="table table-responsive" id="detalhesProprietario">
                <caption>Cliente ${proprietario.nome}   /   Dados pessoais</caption>
                <form:hidden path="pessoaId" id="pessoaId"></form:hidden>
                <tbody>
                    <tr>
                        <th><label for="nome">Nome da Pessoa</label></th>
                        <td><form:input path="nome" id="nome" maxlength="100"></form:input></td>
                        <td><form:errors path="nome" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="rg">RG</label></th>
                        <td><form:input path="rg" id="rg" cssClass="rg"></form:input></td>
                        <td><form:errors path="rg" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="cpf">CPF</label></th>
                        <td><form:input path="cpf" id="cpf" cssClass="cpf"></form:input></td>
                        <td><form:errors path="cpf" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="sexo">Sexo</label></th>
                        <td>
                            <form:radiobutton path="sexo" id="sexo" value="M"   />M
                            <form:radiobutton path="sexo" id="sexo" value="F"   />F
                        </td>
                        <td><form:errors path="sexo" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="tipoPessoa">Tipo de Pessoa</label></th>
                        <td>
                            <form:radiobutton path="tipoPessoa" id="tipoPessoa" value="FISICA"  />FISICA<!--  Para enum value(s) devem ser compatíveis com as constantes  -->
                            <form:radiobutton path="tipoPessoa" id="tipoPessoa" value="JURIDICA"    />JURIDICA
                        </td>
                        <td><form:errors path="tipoPessoa" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="nascimento">Data de Nascimento</label></th>
                        <td><form:input path="nascimento" id="nascimento" type="date" ></form:input></td>
                        <td><form:errors path="nascimento" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="inclusao">Data de Cadastro</label></th>
                        <td><form:input path="inclusao" id="inclusao" type="date"></form:input></td>
                        <td><form:errors path="inclusao" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="nacionalidade">Nacionalidade</label></th>
                        <td><form:select path="nacionalidade" items="${paises}"></form:select></td>
                        <td><form:errors path="nacionalidade" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-responsive" id="enderecoProprietario">
                <caption>Cliente ${proprietario.nome}   /   Dados de endereço</caption>
                <tbody>
                    <tr>
                        <th><label for="endereco.cep">Cep:  </label></th>
                        <td><form:input path="endereco.cep" id="cep"></form:input></td>
                        <td><form:errors path="endereco.cep" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.rua">Rua:  </label></th>
                        <td><form:input path="endereco.rua" id="rua"></form:input></td>
                        <td><form:errors path="endereco.rua" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.bairro">Bairro:  </label></th>
                        <td><form:input path="endereco.bairro" id="bairro"></form:input></td>
                        <td><form:errors path="endereco.bairro" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.cidade">Cidade:  </label></th>
                        <td><form:input path="endereco.cidade" id="cidade"></form:input></td>
                        <td><form:errors path="endereco.cidade" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.estado">Estado:  </label></th>
                        <td><form:input path="endereco.estado" id="estado"></form:input></td>
                        <td><form:errors path="endereco.estado" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.numero">Número:  </label></th>
                        <td><form:input path="endereco.numero" id="numero"></form:input></td>
                        <td><form:errors path="endereco.numero" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="endereco.complemento">Complemento:  </label></th>
                        <td><form:input path="endereco.complemento" id="complemento"></form:input></td>
                        <td><form:errors path="endereco.complemento" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-responsive" id="contatoProprietario">
                <caption>Cliente ${proprietario.nome}   /   Dados de contato</caption>
                <tbody>
                    <tr>
                        <th><label for="contato.telefone">Telefone:  </label></th>
                        <td><form:input path="contato.telefone" id="telefone" cssClass="telefone"></form:input></td>
                        <td><form:errors path="contato.telefone" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="contato.celular">Celular:  </label></th>
                        <td><form:input path="contato.celular" id="celular" cssClass="celular"></form:input></td>
                        <td><form:errors path="contato.celular" cssClass="errors"></form:errors>  </td>
                    </tr>
                    <tr>
                        <th><label for="contato.email">Email:  </label></th>
                        <td><form:input path="contato.email" id="email"></form:input></td>
                        <td><form:errors path="contato.email" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-responsive" id="adicionalProprietario">
                <caption>Cliente ${proprietario.nome}   /   Dados adicionais</caption>
                <tbody>
                    <tr>
                        <th><label for="profissao">Profissão</label></th>
                        <td><form:select path="profissao" items="${profissoes}"></form:select></td>
                        <td><form:errors path="profissao" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="comoNosConheceu">Como nos conheçeu</label></th>
                        <td><form:input path="comoNosConheceu" id="comoNosConheceu"></form:input></td>
                        <td><form:errors path="comoNosConheceu" cssClass="errors"></form:errors></td>
                    </tr>
                    <tr>
                        <th><label for="aceitaNotificacoes">Aceita notificações?</label></th>
                        <td>
                            <form:radiobutton path="aceitaNotificacoes" id="aceitaNotificacoes" value="true"    />Sim
                            <form:radiobutton path="aceitaNotificacoes" id="aceitaNotificacoes" value="false"   />Não                            
                        </td>
                    </tr>
                    <tr>
                        <th><label for="observacoes">Observações</label></th>
                        <td><form:textarea path="observacoes" id="observacoes"></form:textarea></td>
                        <td><form:errors path="observacoes" cssClass="errors"></form:errors></td>
                    </tr>
                </tbody>
            </table>                                          
            <input type="submit" value="submit"  />            
        </form:form> 
    </jsp:body>
</vetweb:layout>