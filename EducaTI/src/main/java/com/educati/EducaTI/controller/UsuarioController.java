package com.educati.EducaTI.controller;

import java.util.List;
import java.util.Optional;

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

import com.educati.EducaTI.model.Posts;
import com.educati.EducaTI.model.Temas;
import com.educati.EducaTI.model.Usuario;
import com.educati.EducaTI.model.UsuarioLogin;
import com.educati.EducaTI.repository.TemasRepository;
import com.educati.EducaTI.repository.UsuarioRepository;
import com.educati.EducaTI.services.UsuarioServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuario")

public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private TemasRepository temasRepository;
	
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
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastro(@RequestBody Usuario usuario){
		Optional<Usuario> novoUsuario = service.cadastroUsuario(usuario);
			if (novoUsuario.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: O usuário que está tentando criar já existe.");
			}	
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> logar(@RequestBody Optional<UsuarioLogin> usuarioConnect){
		Optional<UsuarioLogin> novoLogin = service.loginUsuario(usuarioConnect);
			if (novoLogin.isPresent()) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(novoLogin);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: Não é possivel efetuar o login, verifique e-mail e senha.");
			}
	}
	
		
	@PostMapping("/cadastrar/{id}/post")
	public ResponseEntity<?> criarPost(@RequestBody Posts posts, @PathVariable Long id){
		Optional<Usuario> postNovo = service.criarPost(posts, id);
			if (postNovo.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(postNovo);
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: Conta não existe.");
			}	
	}
	
	@PutMapping("/inscricao/{idTema}/{idUsuario}")
	public ResponseEntity<?> inscreverTema(@PathVariable Long idTema, @PathVariable Long idUsuario){
		
		Optional<Usuario> usuarioValido = repository.findById(idUsuario);
		Optional<Temas> temaValido = temasRepository.findById(idTema);
		
		if(usuarioValido.isPresent() && temaValido.isPresent()){
		
			return ResponseEntity.ok(service.inscreverTema(idTema, idUsuario));
		
		}else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Usuario e/ou tema não cadastrado!");
			
		}
		
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario){
		return  ResponseEntity.ok(repository.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@DeleteMapping("/post/{idUsuario}/{idPost}")
	public void delete(@PathVariable Long idUsuario, @PathVariable Long idPost) {
		service.deletarPost(idPost, idUsuario);
	}
	
	
	
}
