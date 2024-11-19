package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {	
	Optional<Usuario> findByUsername(String username);
}
