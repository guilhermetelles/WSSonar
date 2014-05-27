<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<template:page title="Usuários">

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
			
				<h1> Usuários <small>Listar</small> </h1>
				<ol class="breadcrumb">
	              <li><a href="${contextPath}/sonar"><i class="fa fa-dashboard"></i> WSSonar</a></li>
	              <li class="active"><i class="fa fa-list"></i> Listar Usuários</li>
	            </ol>
	            
	        </div>
        </div>
        
        <div class="row">
			<div class="col-lg-8">
		            
				<div class="table-responsive">
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr>
								<th class="header">ID <i class="fa fa-sort"></i></th>
								<th class="header">Nome <i class="fa fa-sort"></i></th>
								<th class="header">E-mail <i class="fa fa-sort"></i></th>
								<th class="header">Username <i class="fa fa-sort"></i></th>
								<th class="header">Opções </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td>${user.usId}</td>
									<td>${user.usName}</td>
									<td>${user.usEmail}</td>
									<td>${user.usUsername}</td>
									<td style="width:170px">
									
										<form id="form_user_update" action="${contextPath}/user/${user.usId}" method="POST">
											<button type="submit" class="btn btn-primary btn-sm left opt_data" style="margin-right: 10px;"> <i class="fa fa-pencil"></i> Editar </button>
										</form>
										<form:form id="form_user_delete" action="${contextPath}/user/${user.usId}" method="DELETE">
											<button type="submit" class="btn btn-danger btn-sm"> <i class="fa fa-times"></i> Deletar </button>
										</form:form>
									
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				
				</div>
				
				<a href="${contextPath}/user" class="btn btn-success">
					<i class="fa fa-plus"></i> Registrar
				</a>
				<a class="btn btn-primary" onclick="window.history.back()">
					<i class="fa fa-reply"></i> Cancelar 
				</a>
				
			</div>
		</div>
	
	</jsp:body>

</template:page>