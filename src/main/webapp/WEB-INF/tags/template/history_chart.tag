<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="chartInfo" required="true" description="Chart Information"%>

<div class="row">
	<div class="col-lg-12">
		<h3>Gráfico de horas online</h3>

		<div id="webservice-chart"></div>

	</div>
</div>

<script>
	        var chartInfo = '${chartInfo}';
	        var chartArray = chartInfo.split(',');
	        
	        var chart = new Array();
	        
		    for (var i = 0, ii = chartArray.length; i < ii; i++) {
		    	
		    	info = chartArray[i].split('=');
		    	info[0] = info[0].replace(' ', '');
		    	info[0] = info[0].replace('{', '');
		    	
		    	chart[i]= {y: info[0], a: info[1]} ;
		    	console.log(chart[i]);
		    };
		    
	       	Morris.Bar({
	       	  element: 'webservice-chart',
	       	  data: chart,
	       	  xkey: 'y',
	       	  ykeys: ['a'],
	       	  ymax: 24,
	       	  labels: ['Horas Online']
	       	});
</script>
