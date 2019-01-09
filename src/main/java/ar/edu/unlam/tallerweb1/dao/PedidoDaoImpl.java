package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Moto;
import ar.edu.unlam.tallerweb1.tpoModels.Pedido;
import ar.edu.unlam.tallerweb1.tpoModels.PedidoContenido;


@Repository("pedidoDao")
public class PedidoDaoImpl implements PedidoDao{

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void agregarPedido(Pedido pedido,Map<Item,Integer> productos) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(pedido);
		
		for (Map.Entry<Item, Integer> row : productos.entrySet()) {
			PedidoContenido contenido = new PedidoContenido(row.getKey().getNombre(),row.getKey().getPrecio(), 
					row.getValue(), pedido);
			session.save(contenido);
		}
	}

	@Override
	public void cargarMotos() {
		Moto moto1 = new Moto("Mario",1);
		Moto moto2 = new Moto("Hugo",1);
		Moto moto3 = new Moto("Luis",1);
		
		final Session session = sessionFactory.getCurrentSession();
		session.save(moto1);
		session.save(moto2);
		session.save(moto3);
	}

	@Override
	public void asignarMotoApedido(Pedido pedido) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		List<Moto> motos = session.createCriteria(Moto.class)
				.add(Restrictions.eq("estado", 1))
				.list();
		
		if(motos != null){
			for(Moto moto : motos){
				pedido.setMoto(moto);
				moto.setEstado(2);
				pedido.setEstado(2);
				session.update(moto);
				session.update(pedido);
				break;
			}
		}else{
			System.out.println("No hay moto disponible");
		}
	}

	@Override
	public List<Pedido> obtenerPedidos() {
		final Session session =  sessionFactory.getCurrentSession();
		
		List<Pedido> pedidos =  session.createCriteria(Pedido.class)
				.list();
		
		return pedidos;
	}

	@Override
	public List<Moto> obtenerMotos() {
		final Session session =  sessionFactory.getCurrentSession();	
		List<Moto> motos =  session.createCriteria(Moto.class)
				.list();
				return motos;
	}

	@Override
	public Boolean hayPedidoEnEspera() {
		Boolean resultado = false;
		final Session session =  sessionFactory.getCurrentSession();
		
		List<Pedido> pedidos =  session.createCriteria(Pedido.class)
				.add(Restrictions.eq("estado", 1))
				.list();
		//System.out.println("cantidad de pedidos en espera: " + pedidos.size() );
		if(pedidos.size() >= 1) resultado = true;
		
		return resultado;
	}

	@Override
	public void entregarPedidoYliberarMoto(Long id) {
		final Session session =  sessionFactory.getCurrentSession();
		Pedido pedido = (Pedido) session.createCriteria(Pedido.class)
				.createAlias("moto", "m")
				.add(Restrictions.eq("m.id", id))
				.add(Restrictions.eq("estado",2))
				.uniqueResult();
		pedido.setEstado(3);// lo finaliza
		
		//System.out.println("id pedido" + pedido.getId());
		Moto moto = (Moto) session.createCriteria(Moto.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		moto.setEstado(1);
		
		session.save(pedido);
		session.save(moto);
		
	}

	@Override
	public void tomarPedidoEnEspera(Long moto_id) {
		final Session session = sessionFactory.getCurrentSession();
		//busco pedidos en espera
		List<Pedido> pedidosEnEspera =  session.createCriteria(Pedido.class)
				.add(Restrictions.eq("estado", 1))
				.list();
		Pedido pedido = new Pedido();
		for(Pedido pedidoaux: pedidosEnEspera){
			pedido = pedidoaux;
			break;
		}
		System.out.println("pedido id : " + pedido.getId());
		//le asigno moto
		Moto moto = (Moto) session.createCriteria(Moto.class)
				.add(Restrictions.eq("id", moto_id))
				.uniqueResult();
	//	System.out.println("moto nombre : " +moto.getNombre());
//		
		pedido.setMoto(moto);
//		//actualizo el estado del pedido y de la moto
		pedido.setEstado(2);
		moto.setEstado(2);
		
		session.save(moto);
		session.save(pedido);
	}

	@Override
	public List<Pedido> obtenerPedidosFinalizados() {
		final Session session =  sessionFactory.getCurrentSession();
		List<Pedido> pedidos =  session.createCriteria(Pedido.class)
				.add(Restrictions.eq("estado", 3))
				.list();
		return pedidos;
	}

	@Override
	public String obtenerDireccionDeUnPedidoEnProceso(Long id_moto) {
		String direccion = "";
		final Session session =  sessionFactory.getCurrentSession();
		Pedido pedido =   (Pedido) session.createCriteria(Pedido.class)
				.createAlias("moto", "m")
				.add(Restrictions.eq("estado", 2))
				.add(Restrictions.eq("m.id", id_moto))
				.uniqueResult();
		direccion = pedido.getDireccion();
		return direccion;
	}
	
}
