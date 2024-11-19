package com.example.demo.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Material;
@Repository
public interface IMaterialRepository extends JpaRepository<Material, Integer>{
	@Procedure(name = "filtrarMateriales")
	List<Material> filtrarMateriales(String categoria, String buscar);
	
	Page<Material> findAll(Pageable pageable);
}
