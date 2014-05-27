<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<template:page title="Relatórios">

	<jsp:body>
	
		<template:menu/>		
	
		<c:set value="${pageContext.request.contextPath}" var="contextPath" />
		
		<div class="row">
				<div class="col-lg-12">
				
					<h1> Relatórios </h1>
					<ol class="breadcrumb">
		              <li><a href="${contextPath}/sonar"><i class="fa fa-dashboard"></i> WSSonar</a></li>
		              <li class="active"><i class="fa fa-bar-chart-o"></i> Relatórios </li>
		            </ol>
		            
		        </div>
	        </div>
		
		<div class="row">
			<div class="col-lg-12">
			
				<form action="${contextPath}/history/report" method="post">
				
					<div class="control-group left">
				      <label class="control-label">Web Service</label>
				      <div class="controls">
				        <select id="webService" name="webService" required>
				          <option value="1">Web Service de Compra</option>
				          <option value="2">Web Service de Cotação</option>
				        </select>
				      </div>
				    </div>
				    
				     <script>
						$(function() {
							var options = {maxDate: "-1d", dateFormat: "dd/mm/yy"};
							
							$( "#datepicker1" ).datepicker(options);
							$( "#datepicker2" ).datepicker(options);
						});
					 </script>
					
					<div class="datepicker">
						<label>Data 1: </label>
						<input type="text" id="datepicker1" name="datepicker1" class="form-control datepicker" required/>
					</div>
					
					<div class="datepicker">
						<label>Data 2: </label>
						<input type="text" id="datepicker2" name="datepicker2" class="form-control datepicker" required/>
					</div>
					
					<br/>
					
					<button type="submit" class="btn btn-primary report-bottom"> Gerar Relatório </button>
				</form>
			</div> <!-- col 12 -->
		</div> <!-- row -->
		
		<br/>
		
		<br/>

		<c:if test="${not empty webService}">
			<div class="row">
				<div class="col-lg-7">
				
					<form id="form_download_report" action="${contextPath}/history/report/download" method="POST">
						<input type="hidden" id="hiddenDatepicker1" name="hiddenDatepicker1" value="${datepicker1}"/>
						<input type="hidden" id="hiddenDatepicker2" name="hiddenDatepicker2" value="${datepicker2}"/>
						<input type="hidden" id="hiddenWebService" name="hiddenWebService" value="${webService.wsId}"/>
						
						<button type="submit" class="btn btn-success btn-lg"><i class="fa fa-download">&nbsp;</i>Download</button>
						
						<button type="button" class="btn btn-primary btn-lg" onclick="location.reload()"><i class="fa fa-refresh">&nbsp;</i>Recarregar</button>
					</form>
				
					<h3> ${webService.wsName} <small> ${webService.wsDescription} 
						<c:choose>
							<c:when test="${webService.wsStatus}">
								<span class="label label-success">Online</span>
							</c:when>
							<c:otherwise>
								<span class="label label-danger">Offline</span>
							</c:otherwise>									
						</c:choose>
						</small> 
					</h3>	
					
					<hr/>    
						
				</div> <!-- col 7 -->
			</div> <!-- row -->

			<!-- History Tests Information -->
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