package ar.edu.unlam.tallerweb1.tpoModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Localidad {
	@Id
	@GeneratedValue
	private Long id;
	private String descripcion;
	
	public Localidad(){}
	
	public Localidad(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
