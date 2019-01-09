<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%-- <%@page import="java.util.*"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrito</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/mdb.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

</head>
<body>
   <%@include file='overall/nav_header.jsp' %>

	<div class="titulo">
    <div class="subtituloTipoDePiel">
        <h1>CARRITO DE COMPRAS</h1>
    </div>
    
	<div class="container">
		<div class="row justify-content-center">
			<div class="mb-3">
				<h2 class="text-center mt-3 text-primary">Tús Productos</h2>
				<div class="card-body">
				<div class="table-responsive text-center">
					<table class="table table-bordered" id="dataTable" width="100%%" cellspacing="0">
					<thead>
						<tr>
						<th>Imagen</th>
						<th>Nombre</th>
						<th>Descripcion</th>
						<th>Categoria</th>
						<th>Cantidad</th>
						<th>Precio($)</th>
						<th>Operación</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="total" value="${0}"/>
					<c:set var="total_final" value="${0}"/>
					<c:forEach items = "${mapa}" var = "entry">
					    <c:set var="total" value="${ total + entry.key.precio}"/>
					    <c:set var="precio_n_producto" value="${ entry.value * entry.key.precio}"/>
					    <c:set var="total_final" value="${total_final + precio_n_producto}"/>
		 				<tr>
							<td><img src="${entry.key.imagen}" width=100></td>
							<td class="align-middle">${entry.key.nombre}</td>
							<td class="align-middle">${entry.key.descripcion}</td>
							<td class="align-middle">${entry.key.categoria.getDescripcion()}</td>
							<td class="align-middle">
							<div class="btn-group" role="group" aria-label="Basic example">
								<input type="text" class="text-center" value="${entry.value}" disabled>
								<a	href="aumentar-producto?id=${entry.key.id}" type="button" class="btn btn-primary"><i class="fa fa-caret-up"></i></a>
								<a	href="disminuir-producto?id=${entry.key.id}" type="button" class="btn btn-primary"><i class="fa fa-caret-down"></i></a>
							</div>
							</td>
							<td class="align-middle"><c:out value="${precio_n_producto}"></c:out></td>
							<td class="align-middle">
								<div class="btn-group" role="group">
									<a href="eliminar-producto-del-carrito?id=${entry.key.id}" class="btn text-white btn-danger align-middle">Eliminar</a>
								</div>
							</td>
						</tr>
					</c:forEach>	
					</tbody>
					<thead>
						<tr>
						<th class="text-right text-2" colspan="5">TOTAL:</th>
						<th><c:out value="${total_final}"></c:out></th>
						<c:if test="${cantidad > 0 && rol == 'user'}">
							<th><a href="confirmar-pedido?precioTotal=${total_final}" class="btn btn-primary btn-lg active" class="text-right" type="button">Siguiente</a></th>
<!-- 							prueba-mapa -->
						</c:if>		
						<c:if test="${cantidad == 0 || rol != 'user'}">
							<th><button type="button" class="btn btn-secondary btn-lg" disabled>Siguiente</button></th>
						</c:if>		
						</tr>
					</thead>
					</table>
				</div>
				</div>
			</div>
			</div>
		</div>
	    <%@include file='overall/footer_app.jsp' %>
</body>
</html>