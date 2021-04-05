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
import com.educati.EducaTI.repository.TemasRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemasController {
	
	@Autowired
	private TemasRepository repository;
	
	
	
	@GetMapping
	public ResponseEntity<List<Temas>>getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Temas>getById(@PathVariable Long id){
		return repository.findById(id).map(tema -> ResponseEntity.ok(tema))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Temas>>getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Temas>>getByTexto(@PathVariable String texto){
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	}
	@GetMapping("/nivel/{nivel}")
	public ResponseEntity<List<Temas>>getByNivel(@PathVariable String nivel){
		return ResponseEntity.ok(repository.findAllByNivelContainingIgnoreCase(nivel));
	}
	
	
	
	@PostMapping
	public ResponseEntity<Temas> post (@RequestBody Temas temas){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(temas));
	}
	@PutMapping
	public ResponseEntity<Temas> put (@RequestBody Temas temas){
		return  ResponseEntity.ok(repository.save(temas));
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
