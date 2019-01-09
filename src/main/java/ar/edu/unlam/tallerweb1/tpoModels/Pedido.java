package ar.edu.unlam.tallerweb1.tpoModels;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue
	private Long Id;
	private Integer estado; // 1: pedido sin tomar 
							// 2: pedido tomado
							// 3: pedido finalizado
	private Double precio;
	
	@OneToOne (cascade = {CascadeType.ALL})
	private Moto moto;
	
	@ManyToOne  (cascade = {CascadeType.ALL})
	private Cliente cliente;
	
	private String Direccion;
	
	public Pedido(){}

	public Pedido(Integer estado, Double precio, Cliente cliente) {
		this.estado = estado;
		this.precio = precio;
		this.cliente = cliente;
	}
	
	public Pedido(Integer estado, Double precio, Cliente cliente, String direccion) {
		this.estado = estado;
		this.precio = precio;
		this.cliente = cliente;
		this.Direccion = direccion;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getEstadoStr(){
		String resultado = "error";
		if(this.estado == 1 ) resultado = "En espera";
		if(this.estado == 2 ) resultado = "En proceso";
		if(this.estado == 3 ) resultado = "Finalizado";
		return resultado;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
}
