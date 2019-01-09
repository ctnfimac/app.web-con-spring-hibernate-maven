package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Localidad;

@Repository("clienteDao")
public class ClienteDaoImpl implements ClienteDao{

	private final Integer CAMPO_VACIO = 0;
	private final Integer REGISTRO_CORRECTO = 1;
	private final Integer CLIENTE_EXISTENTE = 2;
	
	@Inject
    private SessionFactory sessionFactory;
	
	public void cargarClientes() {
	  final Session session = sessionFactory.getCurrentSession();
	  Localidad localidad1 = new Localidad("San Justo");
	  Localidad localidad2 = new Localidad("villa Luzuriaga");
	  Localidad localidad3 = new Localidad("Liniers");
	  Localidad localidad4 = new Localidad("Ramos Mejia");
	  //String nombre, String calle, String altura, Localidad localidad, String telefono
	  Cliente cliente1 = new Cliente("Christian","las tunas","11122",localidad3,"464466445");
	  Cliente cliente2 = new Cliente("Karin","Venezuela","5500",localidad2,"1560984500");
	  cliente1.setEmail("christianipc.1987@gmail.com");
	  
	  session.save(localidad1);
	  session.save(localidad2);
	  session.save(localidad3);
	  session.save(localidad4);
	  session.save(cliente1);
	  session.save(cliente2);
		  

	}

	@Override
	public Cliente buscarClientePorNombre(String nombre) {
		final Session session = sessionFactory.getCurrentSession();
		Cliente cliente = null;
		cliente = (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("nombre", nombre))
				.uniqueResult();
		return cliente;
	}

	@Override
	public Integer registrarCliente(Cliente cliente) {
		Integer resultado = -1;
		
		if(this.campoVacioEnElFormulario(cliente) != true){
			if( this.buscarClientePorEmail(cliente.getEmail()) == null){
				Session session = this.sessionFactory.getCurrentSession();
				Localidad localidad = this.obtenerLocalidadPorId(Long.valueOf(cliente.getLocalidad().getDescripcion()));
				cliente.setLocalidad(localidad);
				cliente.setRol("user");
				session.save(cliente);
				resultado = this.REGISTRO_CORRECTO;
			}else resultado = this.CLIENTE_EXISTENTE;
		}else resultado = this.CAMPO_VACIO;

		
		return resultado;
	}
	
	private Boolean campoVacioEnElFormulario(Cliente cliente){
		Localidad localidad = cliente.getLocalidad();
		System.out.println("descripcion de la localidad: " + localidad.getDescripcion() );
		Boolean resultado = false;
		if(cliente.getNombre() == "" || cliente.getEmail() == "" || cliente.getContrasenia() == "" || 
		   cliente.getCalle() == "" || cliente.getAltura() == "" || cliente.getLocalidad().getDescripcion() == "0") 
			resultado = true;
		return resultado;
	}

	@Override
	public Cliente buscarClientePorEmail(String email) {
		final Session session = sessionFactory.getCurrentSession();
		Cliente cliente = null;
		cliente = (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
		return cliente;
	}

	@Override
	public List<Localidad> obtenerLocalidades() {
		List<Localidad> listaDeLocalidades = null;
		
		Session session = this.sessionFactory.getCurrentSession();
		listaDeLocalidades = session.createCriteria(Localidad.class)
				.list();
		
		return listaDeLocalidades;
	}

	@Override
	public Localidad obtenerLocalidadPorId(Long id) {
		Localidad localidad = null;
		
		Session session = this.sessionFactory.getCurrentSession();
		localidad = (Localidad) session.createCriteria(Localidad.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		
		return localidad;
	}

}
