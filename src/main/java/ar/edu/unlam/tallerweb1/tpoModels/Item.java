package ar.edu.unlam.tallerweb1.tpoModels;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String descripcion;
	private Double precio;
	private String imagen;
	
	@ManyToOne (cascade= {CascadeType.ALL})
	private Categoria categoria;
	
	@OneToOne (cascade={CascadeType.ALL})
	private Stock stock;
	
	@ManyToOne (cascade= {CascadeType.ALL})
	private TipoDePiel tipoDePiel;

	public Item(){}
	
	public Item(String nombre, String descripcion, String imagen, Double precio, Categoria categoria, Stock stock, TipoDePiel tipoDePiel) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
		this.categoria = categoria;
		this.stock = stock;
		this.tipoDePiel = tipoDePiel;
	}
	
	public Item(String nombre, String descripcion, String imagen, Double precio, Categoria categoria, Stock stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
		this.categoria = categoria;
		this.stock = stock;
	}
	public Item(String nombre, String descripcion, Double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	public TipoDePiel getTipoDePiel() {
		return tipoDePiel;
	}

	public void setTipoDePiel(TipoDePiel tipoDePiel) {
		this.tipoDePiel = tipoDePiel;
	}
}
