<%-- 
    Document   : olaMundo
    Created on : 20/10/2017, 19:02:18
    Author     : renan.rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<vetweb:layout title="Vetweb">

    <div class="row text-center pad-top">
    
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
            <div class="div-square">
                <a href="#">                    
                    <i class="fa fa-users fa-5x"></i>
                    <h4>${quantidadeClientes} <spring:message code="clientes"/></h4>
                </a>
            </div>
        </div>
        
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
            <div class="div-square">
                <a href="#">                    
                    <i class="fa fa-github-alt fa-5x"></i>
                    <h4>${quantidadeAnimais} <spring:message code="animais"/></h4>
                </a>
            </div>
        </div>
                        
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
            <div class="div-square">
                <a href="#">                    
                    <i class="fa fa-credit-card fa-5x"></i>
                    <h4>R$ ${totalPendente} <spring:message code="debitos"/></h4>
                </a>
            </div>
        </div>
        
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
            <div class="div-square">
                <a href="#">                    
                    <i class="fa fa-ban fa-5x"></i>
                    <ul>
                    	<h4>${clientesDevedores} <spring:message code="usuarioBloqueado"/></h4>
                    </ul>
                </a>
            </div>
        </div>
    </div>
    
</vetweb:layout>
