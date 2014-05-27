<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<template:page title="Histórico">

	<jsp:body>
	
		<template:menu/>		
	
		<c:set value="${pageContext.request.contextPath}" var="contextPath" />

		<div class="row">
			<div class="col-lg-12">
			
				<h1> Histórico <small>${webService.wsName}
					<c:choose>
						<c:when test="${webService.wsStatus}">
							<span class="label label-success">Online</span>
						</c:when>
						<c:otherwise>
							<span class="label label-danger">Offline</span>
						</c:otherwise>									
					</c:choose>
					</small> 
				</h1>
				<ol class="breadcrumb">
	              <li><a href="${contextPath}/sonar"><i class="fa fa-dashboard"></i> WSSonar</a></li>
	              <li class="active"><i class="fa fa-list"></i> Histórico </li>
	            </ol>
	            
	        </div>
        </div>
        
        <!-- History Tests Information -->
        <c:if test="${not empty webService}">
       		<template:history_test_info horasForaDoAr="${tempoTotalOffline}" webService="${webService}" />
        </c:if>
           
        <!-- History Chart -->    
		<c:if test="${not empty chartInfo}">
			<template:history_chart chartInfo="${chartInfo}"/>
        </c:if>
        
        <!-- History Table -->
        <c:if test="${not empty histories}">
	    	<template:history_table histories="${histories}" />
		</c:if>
		
		<br/>
       	<br/>
       	
       	<a class="btn btn-primary" onclick="window.history.back()">
			<i class="fa fa-reply"></i> Voltar 
		</a>
	
	</jsp:body>

</template:page>