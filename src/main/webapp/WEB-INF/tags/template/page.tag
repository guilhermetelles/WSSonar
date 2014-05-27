<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title" required="true" description="Page title" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${title} - WSSonar</title>
    
    <c:set value="${pageContext.request.contextPath}" var="contextPath" />

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/static/css/bootstrap.css" rel="stylesheet">
    
    <!-- Add custom CSS here -->
    <link href="${contextPath}/static/css/sb-admin.css" rel="stylesheet">
    <link href="${contextPath}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${contextPath}/static/css/custom.css" rel="stylesheet">
		
	<!-- Charts -->
    <!-- Charts -->
    <link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
	
	 <!-- jQuery -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
	<!-- Bootstrap JS -->
    <script src="${contextPath}/static/js/bootstrap.js"></script>
	
  </head>

  <body>
    <div id="wrapper">
      <div id="page-wrapper">
		<jsp:doBody/>          
          
      </div><!-- /#page-wrapper -->

    </div><!-- /#wrapper -->

    <!-- JavaScript -->
<%--     <script src="static/js/jquery-1.10.2.js"></script> --%>


  </body>
</html>
