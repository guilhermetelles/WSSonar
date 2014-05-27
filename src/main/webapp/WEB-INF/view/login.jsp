<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login - WSSonar</title>
    
    <c:set value="${pageContext.request.contextPath}" var="contextPath" />

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/static/css/bootstrap.css" rel="stylesheet">
    
    <!-- Add custom CSS here -->
    <link href="${contextPath}/static/css/sb-admin.css" rel="stylesheet">
    <link href="${contextPath}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${contextPath}/static/css/custom.css" rel="stylesheet">
    
		
	<!-- Charts -->
    <link href="${contextPath}/static/css/morris-0.4.3.min.css" rel="stylesheet" >
	<script src="${contextPath}/static/js/jquery/1.9.0/jquery.min.js"></script>
	<script src="${contextPath}/static/js/raphael-min.js"></script>
	<script src="${contextPath}/static/js/morris-0.4.3.min.js"></script>
	
	 <!-- jQuery -->
    <link href="${contextPath}/static/css/jquery-ui.css" rel="stylesheet" >
	<script src="${contextPath}/static/js/jquery-1.9.1.js"></script> 
	<script src="${contextPath}/static/js/jquery-ui.js"></script>
	
	<!-- Bootstrap JS -->
    <script src="${contextPath}/static/js/bootstrap.js"></script>
    
  </head>

  <body>
		<div class="row">
			<div class="col-lg-3 col-centered">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="fa fa-key"></span>
							Login
						</h3>
					</div>
					<div class="panel-body">
					<form:form name="form_login" commandName="user" action="/login"
							method="POST" role="form">
	
						<label for="user_username">Usuário </label>
						<form:input type="text" path="usUsername" id="user_username" class="form-control" />
						<br />
						<label for="user_password">Senha </label>
						<form:input type="password" path="usPassword" id="user_password" class="form-control"/>
						<br />
						
						<c:if test="${not empty message}">
							<div class="alert alert-dismissable alert-danger">
								<button class="close" data-dismiss="alert" type="button">×</button>
								${message}
							</div>
						</c:if>
			
						<form:button type="submit" class="btn btn-primary"> Login </form:button>
						
						
					</form:form>
					</div>
				</div>
			</div>
		</div>
<!-- JavaScript -->
    <script src="${contextPath}/static/js/jquery-1.10.2.js"></script>
    <script src="${contextPath}/static/js/bootstrap.js"></script>

  </body>
</html>
