<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/mdb.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
</head>
<body>

     <header>
     
		<%@include file='overall/nav_header.jsp' %>
 
 		<div class="view" style="background-image: url('./img/estetica1.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center center;">
            <div class="mask rgba-black-light d-flex justify-content-center align-items-center">
              <div class="container">
                <div class="row d-flex justify-content-center align-items-center">
<!--                   <div class="col-md-12 mb-4  col-md-4 white-text text-center"> -->
					<div class="col-xs-12 col-sm-6 mb-4 col-lg-8 white-text text-center">
                    <h2 class="h1-reponsive text-uppercase black-text font-weight-bold mb-0  pt-5 wow fadeInDown" data-wow-delay="0.3s">                 
							Cremas según su tipo de piel
                    </h2>
                    <hr class="hr-back my-4 wow fadeInDown" data-wow-delay="0.4s">
                    <h5 class="text-uppercase mb-4 black-text wow fadeInDown" data-wow-delay="0.4s"><strong>Seca, Grasa & Mixta</strong></h5>
                    <a class="btn border border-light black" data-wow-delay="0.4s">Vea nuestros Produtos</a>
<!--                     <a class="btn border border-light  black wow fadeInDown" data-wow-delay="0.4s">Servicios</a> -->
				  </div> 
			<c:if test="${empty rol}">
				  <div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<form:form class="text-center border border-light p-3 bg-white rounded mb-0 " action="validar-login" method="POST" modelAttribute="usuario">
							<p class="h4 mb-4">Login</p>							
							<form:input path="email" id="email" type="email" class="form-control mb-4" placeholder="E-mail" />
							<form:input path="password" type="password" id="password" class="form-control mb-4" placeholder="Password" />
							<button class="btn secondary-color-dark btn-block my-4" type="submit">Entrar</button>
							 <p>¿No eres miembro?
					        	<a href="" data-toggle="modal" data-target="#modalRegisterForm4">Registrarse</a>
					   		 </p>
						</form:form>
					
						<c:choose>
						    <c:when test="${respuesta=='Registro exitoso'}">
								<p class="text-center success-color text-white p-2"> 
									<c:out value = "${respuesta}"/>
								</p>
							</c:when>
							<c:when test="${respuesta=='hay campo/s vacio/s' || respuesta=='El mail ingresado ya existe'  }">
								<p class="text-center danger-color text-white p-2"> 
									<c:out value = "${respuesta}"/>
								</p>
							</c:when>
						 </c:choose>		
				  </div>
			</c:if>
<!--                 </div>           -->
              </div>   
            </div>   
          </div>
		</header>

	<section id="galeria" class="galeria mt-5 mb-5">
		<h2 class="text-center">Cremas</h2>
		<p class="text-center">Mire nuestros diferentes productos</p>
		
		<ul class="nav nav-tabs justify-content-center">
		  <li class="nav-item">
		    <a class="nav-link active" href="home">Todos</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="productos-filtrados?tipo=seca">Piel Seca</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="productos-filtrados?tipo=grasa">Piel Grasa</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="productos-filtrados?tipo=mixta">Piel Mixta</a>
		  </li>
		</ul>
				
		<div class="container-fluid">
			<div class="row d-flex flex-fill justify-content-center">
				<c:forEach items = "${listaDeProductos}" var = "producto">
				<div class="card p-1 m-1 mb-5">
					<img class="card-img-top" src="${producto.imagen}" width="600px" alt="${producto.nombre}">
					<div class="card-body">
						<p class="card-title" style="font-size:1.2em">${producto.nombre}</p>
						<p class="card-text mb-3">${producto.descripcion}</p>
						<div class="w-100 d-flex justify-content-center align-items-center">
							<span class="float-right block-example text-2 mr-2">$ ${producto.precio}</span>
							<a href="add-carrito?id=${producto.id}" class="fa fa-shopping-cart float-right block-example text-2 pl-2 deep-purple-textv" style="font-size:1.6em;"></a>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</section> 
	
	<div class="modal fade" id="modalRegisterForm4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header text-center">
	        <h4 class="modal-title w-100 font-weight-bold">Registrarse</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form:form action="registrar-cliente" method="POST" modelAttribute="cliente">
	      <div class="modal-body mx-3">
	      
	        <div class="md-form mb-5">
	          <i class="fas fa-user prefix grey-text"></i>
	          <form:input path="nombre" id="nombre" type="text" class="form-control validate" />
	          <label data-error="wrong" data-success="right" for="nombre">Tú nombre</label>
	        </div>
	        <div class="md-form mb-5">
	          <i class="fas fa-envelope prefix grey-text"></i>
	          <form:input path="email" id="registro_email" type="email" class="form-control validate" />
	          <label data-error="wrong" data-success="right" for="email">Tú email</label>
	        </div>
	
	        <div class="md-form mb-4">
	          <i class="fas fa-lock prefix grey-text"></i>
	          <form:input path="contrasenia" id="contrasenia" type="password" class="form-control validate" />
	          <label data-error="wrong" data-success="right" for="contrasenia">Tú contraseña</label>
	        </div>
	      
	      	<div class="form-row">
                <div class="col">
<!--                     First name -->
                    <div class="md-form">
                        <form:input path="calle" id="calle" type="text" class="form-control" />
                        <label for="calle">Calle</label>
                    </div>
                </div>
                <div class="col">
<!--                     Last name -->
                    <div class="md-form">
                        <form:input path="altura" id="altura" type="text"  class="form-control" />
                        <label for="altura">Altura</label>
                    </div>
                </div>
            </div>
            <form:select path="localidad" id="localidad" class="browser-default custom-select mb-4">
            	<form:option value="0">Elija Localidad</form:option>
            	<c:forEach items="${localidades}" var="localidad">
            		<form:option value="${localidad.id}">${localidad.descripcion } </form:option>
           		</c:forEach>
             </form:select>
     
	      </div>
	      <div class="modal-footer d-flex justify-content-center">
 			<button type="submit" class="btn btn-deep-orange">Registrar</button>
	      </div>
	      </form:form>
	    </div>
	  </div>
	</div>
	
	<%@include file='overall/footer_app.jsp' %>
	
<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/popper.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/mdb.min.js"></script>
</body>
</html>