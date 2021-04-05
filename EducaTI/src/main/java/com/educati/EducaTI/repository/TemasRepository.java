package com.educati.EducaTI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educati.EducaTI.model.Temas;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Long>{
	/*
	 * Repositório criado para o sistema encontrar diferentes temas de acordo
	 * com diferentes atributos
	 * 	
	 */
	
	public List<Temas> findAllByTituloContainingIgnoreCase(String titulo);
	
	public List<Temas> findAllByTextoContainingIgnoreCase(String texto);
	
	public List<Temas> findAllByNivelContainingIgnoreCase(String nivel);
	
}
