package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.tpoModels.Moto;
import ar.edu.unlam.tallerweb1.tpoModels.Pedido;

import static org.assertj.core.api.Assertions.assertThat;

public class PedidoTest extends SpringTest{
	private Pedido pedido1, pedido2, pedido3, pedido4, pedido5;
	private Moto moto1, moto2, moto3;
	private Session session;
	
	@Before
	public void inicializacionAntesDeCadaTest(){
		session = getSession();
		
		pedido1 = new Pedido();
		pedido2 = new Pedido();
		pedido3 = new Pedido();
		pedido4 = new Pedido();
		pedido5 = new Pedido();
		
		moto1 = new Moto("Mario",1);
		moto2 = new Moto("Hugo",1);
		moto3 = new Moto("Luis",1);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQuePruebaQueSeAsignaMotoApedido(){
		moto1.setEstado(2);
		moto2.setEstado(2);
		session.save(moto1);
		session.save(moto2);
		session.save(moto3);
		
		session.save(pedido1);
		
		List<Moto> motosDisponibles = session.createCriteria(Moto.class)
				.add(Restrictions.eq("estado", 1))
				.list();

		if(motosDisponibles != null){
			for(Moto moto : motosDisponibles){
				pedido1.setMoto(moto);
				moto.setEstado(2);
				pedido1.setEstado(2);
				session.update(moto);
				session.update(pedido1);
				break;
			}
		}
		
		List<Moto> motosDespues = session.createCriteria(Moto.class)
				.add(Restrictions.eq("estado", 1))
				.list();
		
		assertThat(motosDespues).isEmpty();	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQuePruebaSiHayPedidoEnEspera(){
		pedido1.setEstado(1);
		pedido2.setEstado(1);
		pedido3.setEstado(2);
		pedido4.setEstado(2);
		pedido5.setEstado(3);
		
		session.save(pedido1);
		session.save(pedido2);
		session.save(pedido3);
		session.save(pedido4);
		session.save(pedido5);
		
		List<Pedido> pedidosEnEspera =  session.createCriteria(Pedido.class)
				.add(Restrictions.eq("estado", 1))
				.list();
		
		assertThat(pedidosEnEspera).hasSize(2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQuePruebaEntregarPedidoYliberarMoto(){
		pedido1.setMoto(moto1);
		pedido1.setEstado(2);
		moto1.setEstado(2);
		
		session.save(pedido1);
		session.save(pedido2);
		session.save(pedido3);
		
		Long id_moto = moto1.getId();
		
		Pedido pedido = (Pedido) session.createCriteria(Pedido.class)
				.createAlias("moto", "m")
				.add(Restrictions.eq("m.id", id_moto))
				.add(Restrictions.eq("estado",2))
				.uniqueResult();
		
		pedido.setEstado(3);
		
		Moto moto = (Moto) session.createCriteria(Moto.class)
				.add(Restrictions.eq("id", id_moto))
				.uniqueResult();
		moto.setEstado(1);
		
		session.update(pedido1);
		session.update(moto1);
		
		Integer estado_pedido = pedido1.getEstado();
		Integer estado_moto = moto1.getEstado();
		
		assertThat(estado_pedido).isEqualTo(3);
		assertThat(estado_moto).isEqualTo(1);
	}
}
