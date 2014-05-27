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
			
				<h1>Usuários <small>${page}</small></h1>
				<ol class="breadcrumb">
	              <li><a href="${contextPath}/sonar"><i class="fa fa-dashboard"></i> WSSonar</a></li>
	              <li class="active"><i class="fa fa-edit"></i> ${page} Usuário</li>
	            </ol>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-6">

				<form:form name="form_user_registration" commandName="user"
						action="/user" method="POST">
						
						
						<form:hidden path="usId" />
			
						<label for="user_name">Nome </label>
						<form:input type="text" path="usName" id="user_name" class="form-control"/>
						<br /> <label for="user_email">E-mail </label>
						<form:input type="text" path="usEmail" id="user_email" class="form-control"/>
						<br /> <label for="user_username">Usuário </label>
						<form:input type="text" path="usUsername" id="user_username" class="form-control"/>
						<br />
			
						<c:choose>
							<c:when test="${newUser}">
								<label for="user_password">Senha </label>
								<form:input type="password" path="usPassword" id="user_password" class="form-control"/>
								<br />
<!-- 								<label for="user_password">Confirme sua Senha </label> -->
<!-- 								<input type="password" id="confirm_user_password" class="form-control"/> -->
<!-- 								<br /> -->
							</c:when>
							<c:otherwise>
								<form:hidden path="usPassword" />
							</c:otherwise>
						</c:choose>
		
						<form:button type="submit" class="btn btn-success">
							<i class="fa fa-floppy-o"></i> Salvar 
						</form:button>
						<a class="btn btn-primary" onclick="window.history.back()">
							<i class="fa fa-reply"></i> Voltar 
						</a>
				</form:form>
				
			</div>
		</div>
		
	</jsp:body>

</template:page>