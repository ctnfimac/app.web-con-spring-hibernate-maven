package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class ClienteController {
	
	@Inject
	private ServicioCliente servicioCliente;
	
	@Inject
	private ServicioProducto servicioProducto;
	
	@RequestMapping(path="/registrar-cliente", method=RequestMethod.POST)
	public ModelAndView registrarCliente(@ModelAttribute("cliente") Cliente clienteNuevo,HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		Integer resultado = servicioCliente.registrarCliente(clienteNuevo);
		System.out.println("resultado: " + resultado);
		String mensaje = "";
		if(resultado == 0 )mensaje = "hay campo/s vacio/s"; //System.out.println("campo vacio");
		else if(resultado == 1 ) mensaje = "Registro exitoso" ;//System.out.println("registrado con exito");
		else if(resultado == 2 ) mensaje = "El mail ingresado ya existe";//System.out.println("cliente existente");
		else mensaje = "Error en el sistema"; //System.out.println("error");
		modelo.put("respuesta", mensaje);
		/* esta parte es del home*/
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
		/**/
		return new ModelAndView("home",modelo);
	}
}
