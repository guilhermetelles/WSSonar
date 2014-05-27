<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<!-- Sidebar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#"> <i class="fa fa-bullseye"></i> WSSonar</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">
			<li>
				<a href="${contextPath}/sonar">
					<i class="fa fa-dashboard"></i>
					WebService Sonar 
				</a>
			</li>
			<li><a href="${contextPath}/history/report"><i class="fa fa-bar-chart-o"></i>
					Relatórios</a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"
					data-toggle="dropdown">
					<i class="fa fa-user"></i>
					Usuários 
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${contextPath}/user">
						<i class="fa fa-edit"></i> Registrar</a>
					</li>
					<li><a href="${contextPath}/user/list">
						<i class="fa fa-list"></i> Listar</a>
					</li>
				</ul>
			</li>
		</ul>

		<ul class="nav navbar-nav navbar-right navbar-user">
			
			<!-- Alerts -->
<!-- 			<li class="dropdown alerts-dropdown"><a href="#" -->
<!-- 				class="dropdown-toggle" data-toggle="dropdown"><i -->
<!-- 					class="fa fa-bell"></i> Alerts <span class="badge">3</span> <b -->
<!-- 					class="caret"></b></a> -->
<!-- 				<ul class="dropdown-menu"> -->
<!-- 					<li><a href="#">Default <span class="label label-default">Default</span></a></li> -->
<!-- 					<li><a href="#">Primary <span class="label label-primary">Primary</span></a></li> -->
<!-- 					<li><a href="#">Success <span class="label label-success">Success</span></a></li> -->
<!-- 					<li><a href="#">Info <span class="label label-info">Info</span></a></li> -->
<!-- 					<li><a href="#">Warning <span class="label label-warning">Warning</span></a></li> -->
<!-- 					<li><a href="#">Danger <span class="label label-danger">Danger</span></a></li> -->
<!-- 					<li class="divider"></li> -->
<!-- 					<li><a href="#">View All</a></li> -->
<!-- 				</ul> -->
<!-- 			</li> -->
			
			<!-- User -->
			<li class="dropdown user-dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<i class="fa fa-user"></i> ${logged_user.usName} <b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${contextPath}/login/logout"><i class="fa fa-power-off"></i> Log Out</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>