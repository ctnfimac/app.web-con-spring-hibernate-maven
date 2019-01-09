package ar.edu.unlam.tallerweb1.tpoModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Moto {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private Integer estado; // 1: disponible
							// 2: no disponible
	
	public Moto(){}
	
	public Moto(String nombre, Integer estado) {
		this.nombre = nombre;
		this.estado = estado;
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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	public String getEstadoStr() {
		String resultado = null;
		if(this.estado == 1) resultado = "Disponible";
		if(this.estado == 2) resultado = "No disponible";
		return resultado;
	}
}
