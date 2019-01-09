<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%-- <%@page import="java.util.*"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>Confirmar Pedido</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/mdb.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

<!-- <link rel="stylesheet" href="css/estilosMenu.css"> -->
<!-- <link rel="stylesheet" href="css/productos.css"> -->
</head>
<body>

  	<%@include file='overall/nav_header.jsp' %>

	<div class="titulo" style="margin-top:100px;">
    <div class="subtituloTipoDePiel">
        <h2 class="text-center">Finalizar Compra</h2>
    </div>
    
	<div class="container">
		<div class="row justify-content-center">
			<div class="mb-3">
				<div class="card-body">
				<div class="table-responsive text-center">
					<table class="table table-bordered" id="dataTable" width="100%%" cellspacing="0">
					<thead>
						<tr>
						<th>Costo Total De Productos</th>
						<th>Costo de Envio</th>
						<th>Pecio Total</th>
						<th>Operación</th>
						</tr>
					</thead>
					<tbody>
					 <input type="hidden" value="${precioTotal}" id="precioProductos">
					 <input type="hidden" value="" id="precioEnvio">
					<c:set var="costo_de_envio" value="${0}"/>
					<c:set var="total_final" value="${precioTotal}"/>
					
		 				<tr>
							<td class="align-middle">${precioTotal}</td>
							<td class="align-middle"><span id="costoDeEnvio"></span></td>
							<td class="align-middle" id="total_final"></td>
							<td class="align-middle">
								<div class="btn-group" role="group">
									<form action="agregar-pedido" method="GET" name="formulario">
										<input type="hidden" id="costo_final" name="costo_final">
										<input type="submit" value="Confirmar" class="btn text-white btn-success align-middle">
<!-- 									</form> -->
<%-- 									<a href="agregar-pedido?precioTotal=${costo_final}" class="btn text-white btn-success align-middle">Confirmar</a> --%>
									<a href="mostrar-carrito" class="btn text-white btn-danger align-middle">Cancelar</a>
								</div>
							</td>
						</tr>
						<tr>
							<td class="align-middle">Dirección y localidad:</td>
							<td class="align-middle"><input type="text" id="direccion" name="direccion"  class="form-control" required></td>
								</form>
						</tr>
					</tbody>
					</table>
				</div>
				</div>
			</div>
			</div>
		</div>
		
<!-- 		<div>La distancia es de: <span id="distancia"> </span></div>  -->
<!-- 		<div>El tiempo estimado de llegada en auto es de: <span id="tiempoEnAuto"></span></div> -->
<!-- 		<div>Tiempo de entrega: <span id="tiempoDeEntrega"></span></div> -->
<!-- 		<div class="container mb-5"> -->
<!-- 			<div class="row justify-content-center"> -->
<!-- 				<div class="col-md-8"> -->
					<div class="mapa d-none" id="mapa" style="width: 100%; height: 500px;"></div>
<!-- 				</div>	 -->
<!-- 		    </div> -->
<!-- 		</div> -->
	  <input type="hidden" class="form-control" name="destino" value="${direccion}" id="destino">
	  <%@include file='overall/footer_app.jsp' %>
	  
	  <script type="text/javascript" src="js/confirmarPedido.js"></script>
	  <script async defer
	  src="https://maps.googleapis.com/maps/api/js?key=..&callback=mapa.initMap">
	  </script>
		  
</body>
</html>