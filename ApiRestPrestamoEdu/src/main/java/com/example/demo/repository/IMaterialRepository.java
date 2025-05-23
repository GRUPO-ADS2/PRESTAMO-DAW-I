package com.example.demo.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Material;

import jakarta.validation.constraints.Null;
@Repository
//public interface IMaterialRepository extends CrudRepository<Material, Integer>{
public interface IMaterialRepository extends JpaRepository<Material, Integer>{
	
	@Cacheable(value = "material")
	@Procedure(name = "filtrarMateriales")
	List<Material> filtrarMateriales(String p_categoria, String p_buscar );
	
	
	@CacheEvict(value = "material", allEntries = true)
	Material save(Material material);
}
