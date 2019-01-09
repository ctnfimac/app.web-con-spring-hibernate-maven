package ar.edu.unlam.tallerweb1.controladores;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorLoginTest{
	
	@Mock
	private ServicioLogin servicioLogin;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private Usuario usuario;
	
	@InjectMocks
	private ControladorLogin controladorLogin;
	

	@Before
	public void inyeccionDeMocksInicializada() {
		MockitoAnnotations.initMocks(this);
	}
	//import static org.mockito.Mockito.*;
	//import static org.assertj.core.api.Assertions.*;
	@Test
	public void testDelControladorValidarLoginInorrectoTest(){
//		ServicioLogin fakeServicio = mock(ServicioLogin.class);
//		HttpServletRequest requestMock = mock(HttpServletRequest.class);
//		HttpSession sessionMock = mock(HttpSession.class);
//		Usuario usuario = mock(Usuario.class);
		
		when(servicioLogin.consultarUsuario(any(Usuario.class))).thenReturn(null);
		
		ModelAndView map = controladorLogin.validarLogin(usuario, null);
		
		assertThat(map.getViewName()).isEqualTo("login");
		assertThat(map.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
		verify(session, times(0)).setAttribute("ROL", "ADMIN");// 0  que no tiene  ocurrencia
	}
	
	@Test
	public void testDelControladorValidarLoginCorrectoParaRepartidor(){
		when(request.getSession()).thenReturn(session);
		when(servicioLogin.consultarUsuario(any(Usuario.class))).thenReturn(usuario);
		when(usuario.getId()).thenReturn(91L);
		when(usuario.getEmail()).thenReturn("ctn@gmail.com");
		when(usuario.getPassword()).thenReturn("admin");
		when(usuario.getRol()).thenReturn("admin");
		
		ModelAndView modelo = controladorLogin.validarLogin(usuario, request);
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:/repartidorAdmin");
		assertThat(modelo.getModel()).isEmpty();
		verify(session,times(1)).setAttribute("usuario", "ctn@gmail.com"); //1 que hay ocurrencia / que aparece
		verify(session,times(1)).setAttribute("ROL", "admin");
	}
	
	@Test
	public void testDelControladorValidarLoginCorrectoParaCliente(){
		when(request.getSession()).thenReturn(session);
		when(servicioLogin.consultarUsuario(any(Usuario.class))).thenReturn(usuario);
		when(usuario.getId()).thenReturn(91L);
		when(usuario.getEmail()).thenReturn("cliente@gmail.com");
		when(usuario.getPassword()).thenReturn("cliente");
		when(usuario.getRol()).thenReturn("user");
		
		ModelAndView modelo = controladorLogin.validarLogin(usuario, request);
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:/homeEstetica");
		assertThat(modelo.getModel()).isEmpty();
		verify(session,times(1)).setAttribute("usuario", "cliente@gmail.com");
		verify(session,times(1)).setAttribute("ROL", "user");
	}
}
