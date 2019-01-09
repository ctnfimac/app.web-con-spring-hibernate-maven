package ar.edu.unlam.tallerweb1.controladores;

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

import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Localidad;
import ar.edu.unlam.tallerweb1.tpoModels.Moto;
import ar.edu.unlam.tallerweb1.tpoModels.Pedido;

@Controller
public class PedidoController {
	
	@Inject
	private ServicioCliente servicioCliente;
	
	@Inject
	private ServicioPedido servicioPedido;

	@RequestMapping(path="/confirmar-pedido")
	public ModelAndView irAConfirmarPedidoView(@RequestParam(value="precioTotal") Double precioTotal,HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		Integer size = 0;
		
		Cliente cliente = servicioCliente.buscarClientePorNombre("Christian");
		String direccion = "";
		if(cliente != null ){
			Localidad localidadObj = cliente.getLocalidad();
			String calle = cliente.getCalle();
			String altura = cliente.getAltura();
			String localidad = localidadObj.getDescripcion();
			direccion = calle + " " + altura + ", " + localidad;
		}
		
		HttpSession misession= (HttpSession) request.getSession();
		if(misession.getAttribute("carrito") != null) {
			Map<Item,Integer> productos = (Map<Item,Integer>) misession.getAttribute("carrito");
			size = productos.size();
		}
		String rol = (String) misession.getAttribute("ROL");
		modelo.put("rol", rol);
		modelo.put("direccion", direccion);
		modelo.put("cantidad", size);
		modelo.put("precioTotal", precioTotal);
		return new ModelAndView("confirmar-pedido",modelo);
	}
	
	@RequestMapping(path="/agregar-pedido", method=RequestMethod.GET)
	public ModelAndView irAagregarPedido(@RequestParam(value="costo_final") Double costo_final,
			@RequestParam(value="direccion") String direccion, HttpServletRequest request){
		Cliente cliente = servicioCliente.buscarClientePorNombre("Christian");
		Pedido pedido = new Pedido(1,costo_final,cliente,direccion);
		HttpSession misession= (HttpSession) request.getSession();
		Map<Item,Integer> productos = null;
		if(misession.getAttribute("carrito") != null) {
		     productos = (Map<Item,Integer>) misession.getAttribute("carrito");
		}
		this.servicioPedido.agregarPedido(pedido, productos);
		//misession.invalidate();
		misession.removeAttribute("carrito");
		// asignar moto
		this.servicioPedido.asignarMotoApedido(pedido);
		return new ModelAndView("redirect:home");
	}
	
	@RequestMapping(path="/pedidoAdmin", method=RequestMethod.GET)
	public ModelAndView irApedidoAdmin(HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		
		HttpSession misession= (HttpSession) request.getSession();
		String rol = (String) misession.getAttribute("ROL");
		if (rol != null){
			List<Pedido> pedidos = servicioPedido.obtenerPedidos();
			modelo.put("pedidos", pedidos);
			return new ModelAndView("pedidoAdmin",modelo);
		}else return new ModelAndView("redirect:home");
	}
	
	@RequestMapping(path="/repartidorAdmin", method=RequestMethod.GET)
	public ModelAndView irArepartidorAdmin(HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		
		HttpSession misession= (HttpSession) request.getSession();
		String rol = (String) misession.getAttribute("ROL");
		if (rol != null){
			List<Pedido> pedidos = servicioPedido.obtenerPedidos();
			List<Moto> motos = servicioPedido.obtenerMotos();
			Boolean pedidoEnEspera = servicioPedido.hayPedidoEnEspera();
			modelo.put("pedidos", pedidos);
			modelo.put("motos", motos);
			modelo.put("pedidoEnEspera",pedidoEnEspera);
			if(pedidoEnEspera == true )System.out.println("hay en espera");
			else System.out.println("no hay pedidos en espera");
			
			Cliente cliente = servicioCliente.buscarClientePorNombre("Christian");
			String direccion = "";
			if(cliente != null ){
				Localidad localidadObj = cliente.getLocalidad();
				String calle = cliente.getCalle();
				String altura = cliente.getAltura();
				String localidad = localidadObj.getDescripcion();
				direccion = calle + " " + altura + ", " + localidad;
			}
			modelo.put("direccion", direccion);
			
			return new ModelAndView("repartidorAdmin",modelo);
		}else return new ModelAndView("redirect:home");	
	}
	
	
	@RequestMapping(path="/finalizar-pedido")
	ModelAndView finalizarPedido(@RequestParam(value="moto") Long moto){
		ModelMap modelo = new ModelMap();
		this.servicioPedido.entregarPedidoYliberarMoto(moto);
		return new ModelAndView("redirect:repartidorAdmin");
	}
	
	@RequestMapping(path="/tomar-pedido")
	ModelAndView tomarPedido(@RequestParam(value="moto_id") Long moto_id){
		ModelMap modelo = new ModelMap();
		//System.out.println("estoy en tomar pedido");
		this.servicioPedido.tomarPedidoEnEspera(moto_id);
		return new ModelAndView("redirect:repartidorAdmin");
	}
	
	@RequestMapping(path="/pedidosFinalizados", method=RequestMethod.GET)
	public ModelAndView irApedidosFinalizados(){
		ModelMap modelo = new ModelMap();
		List<Pedido> pedidosFinalizados = servicioPedido.obtenerPedidosFinalizados();
		//List<Moto> motos = servicioPedido.obtenerMotos();
		//Boolean pedidoEnEspera = servicioPedido.hayPedidoEnEspera();
		modelo.put("pedidos", pedidosFinalizados);
		//modelo.put("motos", motos);
		//modelo.put("pedidoEnEspera",pedidoEnEspera);
		//if(pedidoEnEspera == true )System.out.println("hay en espera");
		//else System.out.println("no hay pedidos en espera");
		return new ModelAndView("pedidosFinalizados",modelo);
	}
	
	@RequestMapping(path="/verRuta", method=RequestMethod.GET)
	public ModelAndView irAverRuta(@RequestParam(value="moto") Long moto_id){
		ModelMap modelo = new ModelMap();
		String direccion = servicioPedido.obtenerDireccionDeUnPedidoEnProceso(moto_id);
		//System.out.println("direccion: " + direccion);	
		modelo.put("direccion", direccion);

		return new ModelAndView("verRuta",modelo);
	}
	
}
