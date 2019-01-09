package ar.edu.unlam.tallerweb1.tpoModels;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PedidoContenido {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private Double precio_unitario;
	//private Item item;
	private Integer cantidad;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	private Pedido pedido;
	
	public PedidoContenido(){}

	public PedidoContenido(String nombre, Double precio_unitario, Integer cantidad, Pedido pedido) {
		this.nombre = nombre;
		this.precio_unitario = precio_unitario;
		this.cantidad = cantidad;
		this.pedido = pedido;
	}



//	public Item getItem() {
//		return item;
//	}
//
//	public void setItem(Item item) {
//		this.item = item;
//	}

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

	public Double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(Double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
