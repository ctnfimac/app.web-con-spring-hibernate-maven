package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import ar.edu.unlam.tallerweb1.tpoModels.Item;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;


public class ControladorProductoTest {
	@Mock
	private ServicioPedido servicioPedido;
	
	@Mock
	private ServicioCliente servicioCliente;
	
	@Mock
	private ServicioProducto servicioProducto;
	
	@Mock
	private ServicioLogin servicioLogin;
	
	@Mock 
	private Item item;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@InjectMocks
	private ProductoController productoController;
	
	@Before
	public void iniciarInjecciones(){
		MockitoAnnotations.initMocks(this);
	}
	
//	@Test 
//	public void testQueVerificaIrAProductosSinProductos(){
//		when(request.getSession()).thenReturn(session);
//		when(servicioProducto.obtenerProductos()).thenReturn(new ArrayList<Item>());
//		when(session.getAttribute("carrito")).thenReturn(null);
//		
//		ModelAndView modelo = productoController.irAProductos(request);
//		
//		assertThat(modelo.getViewName()).isEqualTo("productos");
//		assertThat(modelo.getView()).isEqualTo(null);
//		verify(session, times(1)).getAttribute("carrito");
//	}
	
//	@Test 
//	public void testQueVerificaIrAProductosConProductos(){
//		when(request.getSession()).thenReturn(session);
//		when(servicioProducto.obtenerProductos()).thenReturn(new ArrayList<Item>());
//		when(session.getAttribute("carrito")).thenReturn(new HashMap<Item,Integer>());
//		when(session.getAttribute("ROL")).thenReturn("user");
//		
//		ModelAndView modelo = productoController.irAProductos(request);
//		
//		assertThat(modelo.getViewName()).isEqualTo("productos");
//		assertThat(modelo.getModel().get("rol")).isNotEqualTo(null);//??
//		assertThat(modelo.getModel().get("cantidad")).isNotEqualTo(null);
//		assertThat(modelo.getModel().get("listaDeProductos")).isNotEqualTo(null);
//		verify(session, times(2)).getAttribute("carrito");
//		verify(session, times(0)).setAttribute("ROL","user");
//	}
	
	@Test 
	public void testQueVerificaIrAaddCarritoConCarritoVacio(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrito")).thenReturn(null);
		when(servicioProducto.obtenerProductoPorId(anyLong())).thenReturn(item);
		
		ModelAndView modelo = productoController.irAaddCarrito(1L, request);
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:productos");
		verify(session, times(1)).getAttribute("carrito");
	}
	
	@Test 
	public void testQueVerificaIrAaddCarritoConCarritoConMasDeUnProducto(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrito")).thenReturn(new  HashMap<Item,Integer>());
		when(servicioProducto.obtenerProductoPorId(anyLong())).thenReturn(item);
		
		ModelAndView modelo = productoController.irAaddCarrito(1L, request);
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:productos");
		
		verify(session, times(2)).getAttribute("carrito");
	}
	
}
