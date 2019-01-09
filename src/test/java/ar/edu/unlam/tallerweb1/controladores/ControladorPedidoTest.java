package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Moto;
import ar.edu.unlam.tallerweb1.tpoModels.Pedido;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ControladorPedidoTest {
	@Mock
	private Pedido pedido;
	
	@Mock
	private Cliente cliente;
	
	@Mock 
	private Moto moto;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private ServicioPedido servicioPedido;
	
	@Mock
	private ServicioCliente servicioCliente;
	
	@Mock 
	private Map<Item,Integer> articulosEnSession;
	
	@Mock 
	private Map<Item,Integer> productos;
	
	@InjectMocks
	private PedidoController pedidoController;
	
	@Before
	public void inyeccionDeMocksInicializada(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testQueVerificaSiSeConfirmoElPedidoNoEncuentraAlCliente(){
		when(request.getSession()).thenReturn(session);
		when(servicioCliente.buscarClientePorNombre(any(String.class))).thenReturn(null);
		when(session.getAttribute(any(String.class))).thenReturn(null);
		
		ModelAndView modelo = pedidoController.irAConfirmarPedidoView(20.00, request);
		
		assertThat(modelo.getViewName()).isEqualTo("confirmar-pedido");
		assertThat(modelo.getModel()).isNotEmpty();
		verify(session, times(0)).setAttribute("carrito", null);
	}
	
	
	@Test
	public void testQueVerificaTomarPedido(){
		ModelAndView modelo = pedidoController.tomarPedido(any(Long.class));
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:repartidorAdmin");
		assertThat(modelo.getModel()).isEmpty();
		verify(session, times(0)).setAttribute("carrito", null);
	}
	
	@Test
	public void testQueVerificaObtenerPedidosFinalizados(){
		when(servicioPedido.obtenerPedidosFinalizados()).thenReturn(new ArrayList<Pedido>());
		ModelAndView modelo = pedidoController.irApedidosFinalizados();
		
		assertThat(modelo.getViewName()).isEqualTo("pedidosFinalizados");
		assertThat(modelo.getModel()).isNotEmpty();
		verify(session, times(0)).setAttribute("carrito", null);
	}
	
	@Test
	public void testQueVerificaIrAverRutaIncorrecta(){
		//
		when(servicioPedido.obtenerDireccionDeUnPedidoEnProceso(any(Long.class))).thenReturn(null);
		
		ModelAndView modelo = pedidoController.irAverRuta(any(Long.class));
		
		assertThat(modelo.getViewName()).isEqualTo("verRuta");
		assertThat(modelo.getModel().get("direccion")).isEqualTo(null);
		verify(session, times(0)).setAttribute("carrito", null);
	}
	
	@Test
	public void testQueVerificaIrAverRutaCorrecta(){
		when(servicioPedido.obtenerDireccionDeUnPedidoEnProceso(anyLong())).thenReturn("Test");
		
		ModelAndView modelo = pedidoController.irAverRuta(any(Long.class));
		
		assertThat(modelo.getViewName()).isEqualTo("verRuta");
		assertThat(modelo.getModel().get("direccion")).isEqualTo("Test");
		verify(session, times(0)).setAttribute("ROL", null);
	}
	
	
}
