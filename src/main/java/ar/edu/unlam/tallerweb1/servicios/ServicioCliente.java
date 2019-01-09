package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.tpoModels.Cliente;
import ar.edu.unlam.tallerweb1.tpoModels.Localidad;

public interface ServicioCliente {
	void cargarClientes();
	Cliente buscarClientePorNombre(String nombre);
	Integer registrarCliente(Cliente cliente);
	List<Localidad> obtenerLocalidades();
}
