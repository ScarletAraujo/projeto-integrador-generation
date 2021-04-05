package com.educati.EducaTI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educati.EducaTI.model.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{
	
	public List<Posts> findAllByTextoPostsContainingIgnoreCase (String textoPosts);
	
	public List<Posts> findAllByTituloPostsContainingIgnoreCase (String tituloPosts);
	
}
