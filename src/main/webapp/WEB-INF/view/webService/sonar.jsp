<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<template:page title="Dashboard">

	<jsp:body>
	
	<template:menu/>		
	
		<c:set value="${pageContext.request.contextPath}" var="contextPath" />
		
		<div class="row">
			<div class="col-lg-12">
		
	            <c:if test="${not empty message}">
					<div class="alert alert-dismissable alert-success">
						<button class="close" data-dismiss="alert" type="button">×</button>
						${message}
					</div>
				</c:if>

				<h1> WebService Sonar </h1>
			
				<ol class="breadcrumb">
	              <li><i class="fa fa-dashboard"></i> WSSonar</li>
	            </ol>
	            
            </div>
       	</div>
       	
      	<div class="row">
      		
			<div class="col-lg-12">
				
				<div class="table-responsive">
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr>
								<th class="header">ID </th>
								<th class="header">Nome </th>
								<th class="header">Descrição </th>
								<th class="header">Última queda </th>
								<th class="header">Status </th>
								<th class="header">Opções </th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${webServices}" var="ws">
								<tr>
									<td>${ws.wsId}</td>
									<td>${ws.wsName}</td>
									<td>${ws.wsDescription}</td>
									<td>
										<fmt:formatDate type="both" pattern="dd MMMM, YYYY HH:mm:ss.SSS" value="${ws.lastHistory.htDownDate}" timeZone="Brazil/East"/> 
									</td>	
									<td>
										<c:choose>
											<c:when test="${ws.wsStatus}">
												<span class="label label-success">Online</span>
											</c:when>
											<c:otherwise>
												<span class="label label-danger">Offline</span>
											</c:otherwise>									
										</c:choose>
									</td>																				
									<td>
										<form id="form_web_service_history" action="${contextPath}/history/${ws.wsId}" method="POST">
											<button type="submit" class="btn btn-primary btn-sm left"> <i class="fa fa-list"></i> Histórico </button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
				</div> <!-- div table -->
				
				<!-- Botão para atualizar página -->
				<button onclick="location.reload()" class="btn btn-primary" type="button"><i class="fa fa-refresh">&nbsp;</i>Recarregar</button>
				
			</div> <!-- div col-lg-8 -->
		</div> <!-- div row -->
		
	</jsp:body>

</template:page>