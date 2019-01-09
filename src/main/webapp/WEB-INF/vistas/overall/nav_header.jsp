  <nav class="navbar navbar-expand-lg navbar-dark fixed-top scrolling-navbar ">
        <div class="container">
             <a class="navbar-brand" href="home"><strong>COSMÉTICA</strong></a>
             <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent-7" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
             </button>
             <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <ul class="navbar-nav ml-auto">
                 <li class="nav-item active">
                   <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
                 </li>
                   <c:if test="${rol == 'user'}">
              			<li class="nav-item">
              				<a class="nav-link" href="pedidoAdmin">Mis Pedidos</a>
              			</li>
                </c:if>
                <c:if test="${rol == 'admin'}">
                	<li class="nav-item">
                		<a class="nav-link" href="repartidorAdmin">ADMIN</a>
                	</li>
                </c:if>
                 <li class="nav-item">
				<a class="nav-link " href="mostrar-carrito"><span class="fa fa-shopping-cart "></span>${cantidad}</a>
			 </li>
               </ul>
             </div>
        </div>
   </nav>