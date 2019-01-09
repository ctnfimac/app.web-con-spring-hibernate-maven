package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Localidad;

public interface ClienteDao {
	void cargarClientes();
	Cliente buscarClientePorNombre(String nombre);
	Integer registrarCliente(Cliente cliente);
	Cliente buscarClientePorEmail(String email);
	List<Localidad> obtenerLocalidades();
	Localidad obtenerLocalidadPorId(Long id);
}
