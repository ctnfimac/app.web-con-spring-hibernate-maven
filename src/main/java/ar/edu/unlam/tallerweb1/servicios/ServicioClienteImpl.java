package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.clases.EmailUtil;
import ar.edu.unlam.tallerweb1.dao.ClienteDao;
import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Localidad;

@Service("servicioCliente")
@Transactional
public class ServicioClienteImpl implements ServicioCliente{

	@Inject
	private ClienteDao servicioClienteDao;
	
	@Override
	public void cargarClientes() {
		servicioClienteDao.cargarClientes();
	}

	@Override
	public Cliente buscarClientePorNombre(String nombre) {
		return servicioClienteDao.buscarClientePorNombre(nombre);
	}

	@Override
	public Integer registrarCliente(Cliente cliente) {
		return servicioClienteDao.registrarCliente(cliente);
	}

	@Override
	public List<Localidad> obtenerLocalidades() {
		return servicioClienteDao.obtenerLocalidades();
	}
	
	@Override
	public void enviarEmail(Cliente clienteNuevo) {
		final String fromEmail = "pruebas@christianperalta.com";
		final String password = "Desarrollo99";
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		//SMTP Host
		props.put("mail.smtp.host", "c1360878.ferozo.com");
		//TLS Puerto
		props.put("mail.smtp.port", "465");
		//activo autenticacion
		props.put("mail.smtp.auth", "true");
		//activo STARTTLS
		props.put("mail.smtp.starttls.enable", "true");
		
        //Creo un objeto autenticador para pasar como argumento a Session.getInstance
		Authenticator auth = new Authenticator() {
			//Desabilito el getPasswordAuthentication de nuestra cuenta
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session  session = Session.getInstance(props, auth);
		
		//preparo el mensaje
		String cuerpoMensaje = clienteNuevo.getNombre() + "Gracias por registrarte nuestra web!";
		
		EmailUtil.sendEmail(session, clienteNuevo.getEmail(),"Registro en la Web de cremas", cuerpoMensaje);
		
	}
	
}
