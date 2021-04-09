package com.educati.EducaTI.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Usuario", uniqueConstraints= @UniqueConstraint(columnNames={"nome", "email", "CPF"}))


public class Usuario {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 256)
	private String nome;
	
	@NotNull
	@Size(min = 10, max = 500)
	private String email;
	
	@NotNull
	@Size (min = 6, max = 1000)
	private String senha;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Inscrição", 
				joinColumns = 
					@JoinColumn(name = "usuarioId"),
				inverseJoinColumns = 	
					@JoinColumn(name = "temaId"))
	@JsonIgnoreProperties({"usuariosInscritos"})
	private List<Temas> temasInscritos;
	
	
	@OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuarioCriador")
	private List<Posts> postsUsuario;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public List<Temas> getTemasInscritos() {
		return temasInscritos;
	}

	public void setTemasInscritos(List<Temas> temasInscritos) {
		this.temasInscritos = temasInscritos;
	}

	public List<Posts> getPostsUsuario() {
		return postsUsuario;
	}

	public void setPostsUsuario(List<Posts> postsUsuario) {
		this.postsUsuario = postsUsuario;
	}
}
