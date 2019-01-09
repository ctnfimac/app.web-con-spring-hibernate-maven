package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.tpoModels.Item;

public interface ProductoDao {
	void CargarProductos();
	List<Item> obtenerProductos();
	List<Item> obtenerProductosSeleccionados(List<Long> ids);
	Item obtenerProductoPorId(Long id);
	List<Item> filtrarPorPiel(String tipo);
}
