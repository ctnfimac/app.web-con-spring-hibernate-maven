package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PedidoDao;
import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Moto;
import ar.edu.unlam.tallerweb1.tpoModels.Pedido;

@Service("servicioPedido")
@Transactional
public class ServicioPedidoImpl implements ServicioPedido{
	@Inject
	private PedidoDao servicioPedidoDao;

	@Override
	public void agregarPedido(Pedido pedido,Map<Item,Integer> productos) {
		servicioPedidoDao.agregarPedido(pedido,productos);
	}

	@Override
	public void cargarMotos() {
		servicioPedidoDao.cargarMotos();
	}

	@Override
	public void asignarMotoApedido(Pedido pedido) {
		servicioPedidoDao.asignarMotoApedido(pedido);
	}

	@Override
	public List<Pedido> obtenerPedidos() {
		return servicioPedidoDao.obtenerPedidos();
	}

	@Override
	public List<Moto> obtenerMotos() {
		return servicioPedidoDao.obtenerMotos();
	}

	@Override
	public Boolean hayPedidoEnEspera() {
		return servicioPedidoDao.hayPedidoEnEspera();
	}

	@Override
	public void entregarPedidoYliberarMoto(Long id) {
		servicioPedidoDao.entregarPedidoYliberarMoto(id);
	}

	@Override
	public void tomarPedidoEnEspera(Long moto_id) {
		servicioPedidoDao.tomarPedidoEnEspera(moto_id);
	}

	@Override
	public List<Pedido> obtenerPedidosFinalizados() {
		return servicioPedidoDao.obtenerPedidosFinalizados();
	}

	@Override
	public String obtenerDireccionDeUnPedidoEnProceso(Long id_moto) {
		return servicioPedidoDao.obtenerDireccionDeUnPedidoEnProceso(id_moto);
	}
	
	
}
