package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ProductoDao;
import ar.edu.unlam.tallerweb1.tpoModels.Item;

@Service("servicioProducto")
@Transactional
public class ServicioProductoImpl implements ServicioProducto{
	@Inject
	private ProductoDao servicioProductoDao;
	
	@Override
	public void CargarProductos() {
		servicioProductoDao.CargarProductos();
	}

	@Override
	public List<Item> obtenerProductos() {
		return servicioProductoDao.obtenerProductos();
	}

	@Override
	public List<Item> obtenerProductosSeleccionados(List<Long> ids) {
		return servicioProductoDao.obtenerProductosSeleccionados(ids);
	}

	@Override
	public Item obtenerProductoPorId(Long id) {
		return servicioProductoDao.obtenerProductoPorId(id);
	}

	@Override
	public List<Item> filtrarPorPiel(String tipo) {
		return servicioProductoDao.filtrarPorPiel(tipo);
	}
}
