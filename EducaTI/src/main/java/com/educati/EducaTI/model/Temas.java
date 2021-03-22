package com.educati.EducaTI.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "Temas", uniqueConstraints = @UniqueConstraint(columnNames={"id","nomeTemas"}) )
public class Temas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@ManyToMany(mappedBy = "idUsuario" , cascade = CascadeType.ALL)
	//@JsonIgnoreProperties("idUsuario")
	private Long id;
	
	//@OneToMany(mappedBy = "nomeTemas")
	private String nomeTemas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTemas() {
		return nomeTemas;
	}

	public void setNomeTemas(String nomeTemas) {
		this.nomeTemas = nomeTemas;
	}
	
	
}
