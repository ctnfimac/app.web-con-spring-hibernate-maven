package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
}
