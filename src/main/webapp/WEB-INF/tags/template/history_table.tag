<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="histories" required="true" description="List of histories" type="java.util.List" %>
<div class="row">
	<div class="col-lg-12">
        
    	<h3>Histórico</h3>
        	
		<div class="table-responsive">
			<table class="table table-hover table-striped tablesorter">
				<thead>
					<tr>
						<th class="header">ID </th>
						<th class="header">Hora de caída </th>
						<th class="header">Hora de volta </th>
						<th class="header">Erro </th>
						<th class="header">Tempo fora do ar </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${histories}" var="history">
						<tr>
							<td>${history.htId}</td>
							<td>
								<fmt:formatDate type="both" pattern="dd MMMM, YYYY HH:mm:ss.SSS" value="${history.htDownDate}" timeZone="Brazil/East"/> 
							</td>
							<td>
								<fmt:formatDate type="both" pattern="dd MMMM, YYYY HH:mm:ss.SSS" value="${history.htBackOnline}" timeZone="Brazil/East" /> 
							</td>
							<td>${history.htErrorResult}</td>
							<td>${history.htOfflineTotalTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div> <!-- table-responsive -->
	</div> <!-- col 12 -->
</div> <!-- row -->
