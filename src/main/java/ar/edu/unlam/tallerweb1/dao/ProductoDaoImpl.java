package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.tpoModels.Categoria;
import ar.edu.unlam.tallerweb1.tpoModels.Item;
import ar.edu.unlam.tallerweb1.tpoModels.Stock;
import ar.edu.unlam.tallerweb1.tpoModels.TipoDePiel;


@Repository("productoDao")
public class ProductoDaoImpl implements ProductoDao{
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void CargarProductos() {
		final Session session = sessionFactory.getCurrentSession();
		
		Item item = (Item) session.createCriteria(Item.class)
				.uniqueResult();
		
		if(item == null){
			Categoria categoria1 = new Categoria("Humectante");
			Categoria categoria2 = new Categoria("Limpieza");
			Categoria categoria3 = new Categoria("Protector");
			
			TipoDePiel tipoDePielSeca = new TipoDePiel("seca");
			TipoDePiel tipoDePielGrasa = new TipoDePiel("grasa");
			TipoDePiel tipoDePielMixta = new TipoDePiel("mixta");
			
			Stock stock1 = new Stock(5);
			Stock stock2 = new Stock(2);
			Stock stock3 = new Stock(10);
			Stock stock4 = new Stock(4);
			
			Item producto1 = new Item("Cetaphil", "Crema de limpieza","img/PIELGRASA/cetaphil-limpieza.jpg" , 100.00 , categoria2 , stock1 , tipoDePielGrasa);
			Item producto2 = new Item("Lidherma", "Crema hidratante","img/PIELGRASA/lidherma-hidratante.jpg" , 50.00  , categoria1 , stock2 , tipoDePielGrasa);
			Item producto3 = new Item("Neutrogena", "Crema protectora","img/PIELGRASA/neutrogena-protector.jpg" , 150.00 , categoria3 , stock3 , tipoDePielGrasa);
			Item producto4 = new Item("Lactibon", "Crema de limpieza","img/PIELGRASA/lactibon-limpieza.jpg" , 200.00 , categoria2 , stock4 , tipoDePielGrasa);
			
			Item producto5 = new Item("Hidra-total 5", "Crema hidratante","img/PIELMIXTA/hidratante1.jpg" , 300.00 , categoria2 , stock1 , tipoDePielMixta);
			Item producto6 = new Item("Eucerin", "Protector solar","img/PIELMIXTA/protector1.jpg" , 250.00  , categoria3 , stock2 , tipoDePielMixta);
			Item producto7 = new Item("Vichy", "Crema protectora","img/PIELMIXTA/protector2.jpg" , 350.00 , categoria3 , stock3 , tipoDePielMixta);
			
			Item producto8 = new Item("dermaglos", "Crema de limpieza","img/PIELSECA/dermaglos-limpieza.jpg" , 75.00 , categoria2 , stock1 , tipoDePielSeca);
			Item producto9 = new Item("Neutrogena", "Crema hidratante","img/PIELSECA/neutrogena-hidratante.jpg" , 400.00  , categoria1 , stock2 , tipoDePielSeca);
			Item producto10 = new Item("Nivea", "Crema de limpieza","img/PIELSECA/nivea-limpieza.jpg" , 550.00 , categoria2 , stock3 , tipoDePielSeca);
			Item producto11 = new Item("Avene", "Crema protectora","img/PIELSECA/avene-protector.jpg" , 25.00 , categoria3 , stock4 , tipoDePielSeca);
			
			
			session.save(producto1);
			session.save(producto2);
			session.save(producto3);
			session.save(producto4);
			session.save(producto5);
			session.save(producto6);
			session.save(producto7);
			session.save(producto8);
			session.save(producto9);
			session.save(producto10);
			session.save(producto11);
		}
		
		
	}

	@Override
	public List<Item> obtenerProductos() {
		final Session session = sessionFactory.getCurrentSession();
		List<Item> lista = session.createCriteria(Item.class)
				.list();
		return lista;
	}

	@Override
	public List<Item> obtenerProductosSeleccionados(List<Long> ids) {
		final Session session = sessionFactory.getCurrentSession();
		List<Item> lista = session.createCriteria(Item.class)
				.add(Restrictions.in("id", ids))
				.list();
		return lista;
	}

	@Override
	public Item obtenerProductoPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Item item = (Item) session.createCriteria(Item.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
				
		return item;
	}

	@Override
	public List<Item> filtrarPorPiel(String tipo) {
		final Session session = sessionFactory.getCurrentSession();
		List<Item> lista = session.createCriteria(Item.class,"item")
				.createAlias("tipoDePiel", "p")
				.add(Restrictions.eq("p.descripcion", tipo))
				.list();
		return lista;
	}
}
