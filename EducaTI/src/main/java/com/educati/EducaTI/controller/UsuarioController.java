package com.educati.EducaTI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educati.EducaTI.model.Temas;
import com.educati.EducaTI.model.Usuario;
import com.educati.EducaTI.repository.TemasRepository;
import com.educati.EducaTI.repository.UsuarioRepository;
import com.educati.EducaTI.services.UsuarioServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/Usuario")

public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	UsuarioServices service;
		
	
	@GetMapping
	public ResponseEntity<List<Usuario>>getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario>getById(@PathVariable Long id){
		return repository.findById(id).map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>>getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	@GetMapping("/CPF/{CPF}")
	public ResponseEntity<List<Usuario>>getByCPF(@PathVariable String CPF){
		return ResponseEntity.ok(repository.findAllByCPFContaining(CPF));
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> post (@RequestBody Usuario usuario){
		return service.cadastroUsuario(usuario);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put (@RequestBody Usuario usuario){
		return  ResponseEntity.ok(repository.save(usuario));
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	

}
