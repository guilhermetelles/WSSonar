<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="horasForaDoAr" required="true" description="Total de horas fora do ar"%>
<%@ attribute name="webService" required="true" description="Web Service" type="br.com.wssonar.model.WebService" %>
<div class="row">
	<div class="col-lg-4">

		<div class="panel panel-default">
			<div class="panel-heading"><b>Informações dos testes</b></div>

			<ul class="list-group">
			
				<li class="list-group-item"><span class="badge">${horasForaDoAr}</span>
					Total de horas fora do ar *</li>
					
				<li class="list-group-item"><span class="badge">${webService.wsCountTotal}</span>
					Total de testes</li>
					
				<li class="list-group-item"><span class="badge">${webService.wsCountPositive}</span>
					Total de testes positivos</li>
					
				<li class="list-group-item"><span class="badge">${webService.wsCountNegative}</span>
					Total de testes negativos</li>
			</ul>

		</div> <!-- panel -->
		
		<span style="font-size:10px;">* No período especificado</span>
	</div> <!-- col 4 -->
</div> <!-- row -->