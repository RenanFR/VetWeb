<%-- 
    Document   : olaMundo
    Created on : 20/10/2017, 19:02:18
    Author     : 11151504898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<vetweb:layout title="Vetweb">
    <div class="row text-center pad-top">
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
            <div class="div-square">
                <a href="#">                    
                    <i class="fa fa-users fa-5x"></i>
                    <h4>${quantidadeClientes} cliente(s)</h4>
                </a>
            </div>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
            <div class="div-square">
                <a href="#">                    
                    <i class="fa fa-github-alt fa-5x"></i>
                    <h4>${quantidadeAnimais} animal(s)</h4>
                </a>
            </div>
        </div>                
    </div>
</vetweb:layout>
