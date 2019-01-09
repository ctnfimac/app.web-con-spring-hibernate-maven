package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Moto;
import ar.edu.unlam.tallerweb1.tpoModels.Pedido;

public interface PedidoDao {
	void agregarPedido(Pedido pedido,Map<Item,Integer> productos);
	void cargarMotos();
	void asignarMotoApedido(Pedido pedido);
	List<Pedido> obtenerPedidos();
	List<Moto> obtenerMotos();
	Boolean hayPedidoEnEspera();
	void entregarPedidoYliberarMoto(Long id);
	void tomarPedidoEnEspera(Long moto_id);
	List<Pedido> obtenerPedidosFinalizados();
	String obtenerDireccionDeUnPedidoEnProceso(Long id_moto);
}
