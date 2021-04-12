package com.educati.EducaTI.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.educati.EducaTI.model.Posts;
import com.educati.EducaTI.model.Usuario;
import com.educati.EducaTI.model.UsuarioLogin;
import com.educati.EducaTI.repository.PostsRepository;
import com.educati.EducaTI.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostsRepository postsRepository;
	
	
	public Optional<Usuario> cadastroUsuario(Usuario novoUsuario) {
	
		if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaEncoder);
			return Optional.ofNullable(usuarioRepository.save(novoUsuario));
		}
		else {
			return null;
		}
	}
	
	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.get().getEmail());
		
		if(usuarioExistente.isEmpty()) {
			return null;
		}
		else {
			if (encoder.matches(usuario.get().getSenha(), usuarioExistente.get().getSenha())) {

				String auth = usuario.get().getEmail() + ":" + usuario.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuario.get().setToken(authHeader);
				usuario.get().setNome(usuarioExistente.get().getNome());
				usuario.get().setEmail(usuarioExistente.get().getEmail());
				usuario.get().setSenha(usuarioExistente.get().getSenha());
				
				return usuario;
			}
			else {
				
				return null;
			}
			
		}
		
	}
	
	public Optional<Usuario> criarPost(Posts postNovo, Long idUsuario ){
		return null;
	}
	
	

}
