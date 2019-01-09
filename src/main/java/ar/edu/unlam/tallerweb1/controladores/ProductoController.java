package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Localidad;

@Controller
public class ProductoController {
	
	@Inject
	private ServicioProducto servicioProducto;
	
	@Inject
	private ServicioCliente servicioCliente;
	
	@Inject
	private ServicioPedido servicioPedido;
	
	@Inject
	private ServicioLogin servicioLogin;
	
	
//	@RequestMapping(path = "/prodPielGrasa", method = RequestMethod.GET)
//	public ModelAndView irAProdPielGrasa() {
//		return new ModelAndView("prodPielGrasa");
//	}
//	
//	@RequestMapping(path = "/prodPielSeca", method = RequestMethod.GET)
//	public ModelAndView irAProdPielSeca() {
//		return new ModelAndView("prodPielSeca");
//	}
//	
//	@RequestMapping(path = "/prodPielMixta", method = RequestMethod.GET)
//	public ModelAndView irAProdPielMixta() {
//		return new ModelAndView("prodPielMixta");
//	}
	
	// Control del carrito
	@RequestMapping(path="/cargar-productos",method = RequestMethod.GET)
	public ModelAndView irAcargarProductos(HttpServletRequest request){
		HttpSession misession= (HttpSession) request.getSession();
		misession.invalidate();
		servicioProducto.CargarProductos();
		servicioCliente.cargarClientes();
		servicioPedido.cargarMotos();
		servicioLogin.cargarUsuarios();
		return new ModelAndView("redirect:homeEstetica");
	}
	
	
//	@RequestMapping(path="/home-productos",method = RequestMethod.GET)
//	public ModelAndView irAhomeProductos(HttpServletRequest request){
//		ModelMap modelo = new ModelMap();
//
//		List<Item> listaDeProductos = servicioProducto.obtenerProductos();
//		modelo.put("listaDeProductos", listaDeProductos);
//		
//		Integer size = 0;
//		
//		HttpSession misession= (HttpSession) request.getSession();
//		if(misession.getAttribute("carrito") != null) {
//			Map<Item,Integer> productos = (Map<Item,Integer>) misession.getAttribute("carrito");
//			size = productos.size();
//		}
//		modelo.put("cantidad", size);
//		return new ModelAndView("home-productos",modelo);
//	}
	
//	@RequestMapping(path = "/productos", method = RequestMethod.GET)
//	public ModelAndView irAProductos(HttpServletRequest request) {
//		ModelMap modelo = new ModelMap();
//
//		List<Item> listaDeProductos = servicioProducto.obtenerProductos();
//		modelo.put("listaDeProductos", listaDeProductos);
//		
//		Integer size = 0;
//		
//		HttpSession misession= (HttpSession) request.getSession();
//		if(misession.getAttribute("carrito") != null) {
//			Map<Item,Integer> productos = (Map<Item,Integer>) misession.getAttribute("carrito");
//			size = productos.size();
//		}
//		String rol = (String) misession.getAttribute("ROL");
//		modelo.put("rol", rol);
//		//System.out.println(misession.getAttribute("ROL"));
//		modelo.put("cantidad", size);
//		return new ModelAndView("productos",modelo);
//	}
	
	@RequestMapping(path="/add-carrito", method = RequestMethod.GET)
	public ModelAndView irAaddCarrito(@RequestParam(value="id") Long id, HttpServletRequest request){

		HttpSession misession= (HttpSession) request.getSession();
	
		if(misession.getAttribute("carrito") == null) {
			//System.out.println("esta vacio");
			Map<Item,Integer> carrito = new HashMap<Item,Integer>();
			Item item = servicioProducto.obtenerProductoPorId(id);
			carrito.put(item, 1);
			misession.setAttribute("carrito", carrito);
		}else{
			HashMap<Item,Integer> carrito = (HashMap<Item,Integer>) misession.getAttribute("carrito");			
			Item item = servicioProducto.obtenerProductoPorId(id);
			if(!this.productoExistenteEnElCarrito(item, carrito)){
				carrito.put(item, 1);
				//Integer tamanio = carrito.size();
				misession.setAttribute("carrito", carrito);
			}
		}
//		return new ModelAndView("redirect:productos");
		return new ModelAndView("redirect:home");
	}
	
	
	@RequestMapping(path="/aumentar-producto")
	public ModelAndView agregarCantidadProducto(@RequestParam(value="id") Long id, HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		HttpSession misession= (HttpSession) request.getSession();
		Map<Item,Integer> carrito = (HashMap<Item,Integer>) misession.getAttribute("carrito");
		Map<Item,Integer> auxiliar = new HashMap<Item, Integer>();
		for (Map.Entry<Item, Integer> entry : carrito.entrySet()) {
			Integer contador = entry.getValue();
			if(entry.getKey().getId() == id) contador++;	
			auxiliar.put(entry.getKey(), contador);
		}
		misession.removeAttribute("carrito");
		misession.setAttribute("carrito", auxiliar);
		return new ModelAndView("redirect:mostrar-carrito");
	}
	
	@RequestMapping(path="/disminuir-producto")
	public ModelAndView disminuirCantidadProducto(@RequestParam(value="id") Long id, HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		HttpSession misession= (HttpSession) request.getSession();
		Map<Item,Integer> carrito = (HashMap<Item,Integer>) misession.getAttribute("carrito");
		Map<Item,Integer> auxiliar = new HashMap<Item, Integer>();
		for (Map.Entry<Item, Integer> entry : carrito.entrySet()) {
			Integer contador = entry.getValue();
			if(entry.getKey().getId() == id && contador > 1) contador--;	
			auxiliar.put(entry.getKey(), contador);
		}
		misession.removeAttribute("carrito");
		misession.setAttribute("carrito", auxiliar);
		return new ModelAndView("redirect:mostrar-carrito");
	}
	
	
	@RequestMapping(path="/mostrar-carrito")
	public ModelAndView irAMostrarCarrito(HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		HttpSession misession= (HttpSession) request.getSession();
		
		Integer size = 0;
		
		if(misession.getAttribute("carrito") != null) {
			HashMap<Item,Integer> mapa = (HashMap<Item,Integer>) misession.getAttribute("carrito");
			size = mapa.size();
			modelo.put("mapa", mapa);	
		}
		String rol = (String) misession.getAttribute("ROL");
		modelo.put("rol", rol);
		modelo.put("cantidad", size);
		return new ModelAndView("mostrar-carrito",modelo);
	}
	
	
	@RequestMapping(path="/eliminar-producto-del-carrito", method = RequestMethod.GET)
	public ModelAndView eliminarProductoDelCarrito(@RequestParam(value="id") Long id,HttpServletRequest request){
		HttpSession misession= (HttpSession) request.getSession();
		
		if(misession.getAttribute("carrito") != null ){
			Map<Item,Integer> mapa = (HashMap<Item,Integer>) misession.getAttribute("carrito");
			Map<Item,Integer> auxiliar = new HashMap<Item, Integer>();
			for (Map.Entry<Item, Integer> entry : mapa.entrySet()) {
				if(entry.getKey().getId() != id){
					auxiliar.put(entry.getKey(), entry.getValue());
				}	
			}
			misession.removeAttribute("carrito");
			if(auxiliar.size() >= 1 )misession.setAttribute("carrito",auxiliar);
		}
		
		return new ModelAndView("redirect:mostrar-carrito");
	}
	
	
	private Boolean productoExistenteEnElCarrito(Item item, HashMap<Item,Integer> mapa){
		Boolean resultado = false;
		for (Map.Entry<Item, Integer> entry : mapa.entrySet()) {
			if(entry.getKey().getId() == item.getId() ) resultado = true;
		}
		return resultado;
	}
	//* fin Control del carrito
	
	// pruebas para el mapa
//	@RequestMapping(path="/prueba-mapa", method = RequestMethod.GET)
//	public ModelAndView irApruebaMapa(HttpServletRequest request){
//		ModelMap modelo = new ModelMap();
//		
//		// buscar usuario por nombre de usuario
//		Cliente cliente = servicioCliente.buscarClientePorNombre("Christian");
//		String direccion = "";
//		if(cliente != null ){
//			Localidad localidadObj = cliente.getLocalidad();
//			String calle = cliente.getCalle();
//			String altura = cliente.getAltura();
//			String localidad = localidadObj.getDescripcion();
//			direccion = calle + " " + altura + ", " + localidad;
//		}
//	
//		HttpSession misession= (HttpSession) request.getSession();
//		
//		Integer size = 0;
//		
//		if(misession.getAttribute("carrito") != null) {
//			HashMap<Item,Integer> mapa = (HashMap<Item,Integer>) misession.getAttribute("carrito");
//			size = mapa.size();
//			modelo.put("mapa", mapa);	
//		}
//		modelo.put("cantidad", size);
//		
//		
//		modelo.put("direccion", direccion);
//		return new ModelAndView("prueba-mapa",modelo);
//	}
	//* fin pruebas para el mapa
	
	@RequestMapping(path="/productos-filtrados", method = RequestMethod.GET)
	public ModelAndView filtrarProducto(@RequestParam(value="tipo") String tipo,HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		List<Item> listaDeProductos = servicioProducto.filtrarPorPiel(tipo);
		
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		
		HttpSession misession= (HttpSession) request.getSession();
		Integer size = 0;
		
		if(misession.getAttribute("carrito") != null) {
			HashMap<Item,Integer> mapa = (HashMap<Item,Integer>) misession.getAttribute("carrito");
			size = mapa.size();
			modelo.put("mapa", mapa);	
		}
		modelo.put("cantidad", size);
		
		String rol = (String) misession.getAttribute("ROL");
		modelo.put("rol", rol);
		
		modelo.put("titulo", tipo);
		modelo.put("productos", listaDeProductos);
		return new ModelAndView("productos-filtrados",modelo);
	}
	
}
