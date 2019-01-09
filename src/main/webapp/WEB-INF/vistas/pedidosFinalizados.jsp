<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Repartidor - Pedidos Finalizados</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/panel.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark static-top d-flex justify-content-between">
		<a class="navbar-brand mr-1" href="index.html">REPARTIDOR</a>  
		<!-- Navbar -->
<!-- 		<ul class="navbar-nav ml-auto ml-md-0"> -->
<!-- 			<li class="nav-item dropdown no-arrow"> -->
<!-- 				<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 					<i class="fas fa-user-circle fa-fw"></i> -->
<!-- 				</a> -->
<!-- 				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown"> -->
<!-- 					<a class="dropdown-item" href="#">%s</a> -->
<!-- 					<div class="dropdown-divider"></div> -->
<!-- 					<a class="dropdown-item" href="index.php">Home</a> -->
<!-- 					<div class="dropdown-divider"></div> -->
<!-- 					<a class="dropdown-item" href="index.php?route=salir">Cerrar Sesión</a> -->
<!-- 				</div> -->
<!-- 			</li> -->
<!-- 		</ul> -->
	</nav>

  <div id="wrapper">
	<!-- Sidebar -->
	<ul class="sidebar navbar-nav">
	  <!--<li class="nav-item">
			<a class="nav-link" href="index.php?route=admin&tabla=solicitudes">
			<i class="fas fa-fw fa-chart-area"></i>
			<span>Solicitudes</span></a>
		</li>-->
		<li class="nav-item">
			<a class="nav-link" href="home">
			<i class="fas fa-fw fa-chart-area"></i>
			<span>Inicio</span></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="repartidorAdmin">
			<i class="fas fa-fw fa-chart-area"></i>
			<span>Pedidos</span></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="pedidosFinalizados">
			<i class="fas fa-fw fa-chart-area"></i>
			<span>Pedidos Finalizados</span></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="cerrarSesion">
			<i class="fas fa-fw fa-chart-area"></i>
			<span>cerrar sesión</span></a>
		</li>
	</ul>
	<div id="content-wrapper">
	  <div class="container-fluid">
	<div class="card mb-3">
		<div class="card-header">
		<i class="fas fa-table"></i>
		Pedidos
		</div>
		<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered text-center table-dark" id="dataTable" width="100%%" cellspacing="0">
			<thead>
				<tr>
					<th>Id</th>
					<th>Cliente</th>
					<th>Direccion</th>
					<th>Estado</th>
					<th>Costo</th>
					<th>Repartidor Asignado</th>
				</tr>
			</thead>
			<tbody>
			  <c:forEach items = "${pedidos}" var = "pedido">
			    <tr>
					<td class="align-middle">${pedido.id}</td>
					<td class="align-middle">${pedido.cliente.nombre}</td>
					<td class="align-middle">${pedido.direccion}</td>
					<td class="align-middle">${pedido.getEstadoStr()}</td>
					<td class="align-middle">${pedido.precio}</td>
					<td class="align-middle">${pedido.moto.nombre}</td>
				</tr>	
			   </c:forEach>	
			</tbody>
			</table>
		</div>
		</div>
	</div>

	</div>
	<!-- /.container-fluid -->
<!-- 	  footer -->
	  </div>
		<!-- Sticky Footer -->
		<footer class="sticky-footer">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
				<span>Copyright © Christian Peralta</span>
				</div>
			</div>
		</footer>
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- /#wrapper -->
	<script src="./js/jquery-3.3.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<!-- <script src="./public/js/popper.min.js"></script>-->
</body>
</html>