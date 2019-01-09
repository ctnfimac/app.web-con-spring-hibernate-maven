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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Localidad;

@Controller
public class HomeController {
	
	@Inject
	private ServicioProducto servicioProducto;
	
	@Inject
	private ServicioCliente servicioCliente;
	
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request) {
			ModelMap modelo = new ModelMap();

			// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
			// al model attribute del form que esta definido en la vista 'login'
			Usuario usuario = new Usuario();
			modelo.put("usuario", usuario);
			
			Cliente cliente = new Cliente();
			modelo.put("cliente", cliente);
	
			List<Item> listaDeProductos = servicioProducto.obtenerProductos();
			modelo.put("listaDeProductos", listaDeProductos);
			
			List<Localidad> listaDeLocalidades = servicioCliente.obtenerLocalidades();
			modelo.put("localidades", listaDeLocalidades);
			
			Integer size = 0;
			
			HttpSession misession= (HttpSession) request.getSession();
			if(misession.getAttribute("carrito") != null) {
				Map<Item,Integer> productos = (Map<Item,Integer>) misession.getAttribute("carrito");
				size = productos.size();
			}
			String rol = (String) misession.getAttribute("ROL");
			modelo.put("rol", rol);
			modelo.put("cantidad", size);
		return new ModelAndView("home",modelo);
	}
	
	@RequestMapping(path = "/cerrarSesion", method = RequestMethod.GET)
	public ModelAndView irACerrarSesision(HttpServletRequest request) {
		HttpSession misession= (HttpSession) request.getSession();
		misession.removeAttribute("usuario");
		misession.removeAttribute("ROL");
		return new ModelAndView("redirect:home");
	}
	
}
