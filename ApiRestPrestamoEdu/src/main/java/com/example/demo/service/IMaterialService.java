package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Material;

public interface IMaterialService {
    Material save(Material material);
    Material update(Material material);
    List<Material> findAll();
	Material getById(Integer id);
    
    
}
