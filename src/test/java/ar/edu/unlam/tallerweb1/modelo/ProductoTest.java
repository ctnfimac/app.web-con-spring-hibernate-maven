package ar.edu.unlam.tallerweb1.modelo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.tpoModels.Categoria;
import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Stock;
import ar.edu.unlam.tallerweb1.tpoModels.TipoDePiel;


public class ProductoTest extends SpringTest{
	private Categoria categoria1, categoria2, categoria3;
	private TipoDePiel tipoDePielSeca, tipoDePielGrasa, tipoDePielMixta;
	private Item producto1, producto2, producto3, producto4, 
				 producto5, producto6, producto7, producto8;
	private Session session;
	@Before
	public void inicializacionAntesDeCadaTest(){
		//usuario1	=		new Usuario();
		categoria1 = new Categoria("Humectante");
		categoria2 = new Categoria("Limpieza");
		categoria3 = new Categoria("Protector");
		
		tipoDePielSeca = new TipoDePiel("seca");
		tipoDePielGrasa = new TipoDePiel("grasa");
		tipoDePielMixta = new TipoDePiel("mixta");
		
		producto1 = new Item();
		producto2 = new Item();
		producto3 = new Item();
		producto4 = new Item();
		producto5 = new Item();
		producto6 = new Item();
		producto7 = new Item();
		producto8 = new Item();
		
		session = getSession();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueFiltraLosProductosPorPielSeca(){
		producto1.setTipoDePiel(tipoDePielSeca);
		producto2.setTipoDePiel(tipoDePielSeca);
		producto3.setTipoDePiel(tipoDePielGrasa);
		producto4.setTipoDePiel(tipoDePielMixta);
		producto5.setTipoDePiel(tipoDePielSeca);
		producto6.setTipoDePiel(tipoDePielGrasa);
		producto7.setTipoDePiel(tipoDePielSeca);
		producto8.setTipoDePiel(tipoDePielMixta);
		
		session.save(producto1);
		session.save(producto2);
		session.save(producto3);
		session.save(producto4);
		session.save(producto5);
		session.save(producto6);
		session.save(producto7);
		session.save(producto8);
//		
		List<Item> listaItems = session.createCriteria(Item.class)
				.createAlias("tipoDePiel", "p")
				.add(Restrictions.eq("p.descripcion", "seca"))
				.list();
		assertThat(listaItems).hasSize(4);
	}
	
	@Test 
	@Transactional
	@Rollback(true) 
	public void testQueObtieneProductoPorId(){
		session.save(producto1);
		session.save(producto2);
		session.save(producto3);
		session.save(producto4);
		session.save(producto5);
		session.save(producto6);
		session.save(producto7);
		session.save(producto8);
		
		Long id = producto3.getId();
		
		Item item = (Item) session.createCriteria(Item.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		
		assertThat(producto3).isEqualTo(item);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueObtieneProductosSeleccionados(){
		session.save(producto1);
		session.save(producto2);
		session.save(producto3);
		session.save(producto4);
		session.save(producto5);
		session.save(producto6);
		session.save(producto7);
		session.save(producto8);
		
		List<Item> itemsEsperados = new ArrayList<Item>();
		itemsEsperados.add(producto2);
		itemsEsperados.add(producto5);
		itemsEsperados.add(producto7);
		
		List<Long> ids = new ArrayList<Long>();
		ids.add(new Long(2));
		ids.add(new Long(5));
		ids.add(new Long(7));
		
		List<Item> itemsBuscados = session.createCriteria(Item.class)
				.add(Restrictions.in("id", ids))
				.list();
		
		assertThat(itemsEsperados).isEqualTo(itemsBuscados);
	}
}
