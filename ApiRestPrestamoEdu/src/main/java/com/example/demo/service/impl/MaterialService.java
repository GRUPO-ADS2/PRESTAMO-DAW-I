package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Material;
import com.example.demo.repository.IMaterialRepository;
import com.example.demo.service.IMaterialService;

@Service
public class MaterialService implements IMaterialService {
    @Autowired
    private IMaterialRepository _IMaterialRepository;
    private final static Logger log = LoggerFactory.getLogger(MaterialService.class);

    @Transactional
    @Override
    public Material save(Material material) {
	try {

	    return _IMaterialRepository.save(material);
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return null;
    }

    @Transactional
    @Override
    public Material update(Material material) {
	Optional<Material> optional = _IMaterialRepository.findById(material.getCodMaterial());
	optional.ifPresentOrElse((mat) -> {
	    mat.setDescripcion(mat.getDescripcion());
	    mat.setNombre(mat.getNombre());
	    mat.setStock(mat.getStock());
	    mat.setTipo(mat.getTipo());
	}, () -> {
	    log.error("Error al hacer la operacion");
	});
	return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Material> findAll() {
	return (List<Material>) _IMaterialRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Material getById(Integer id) {
	Optional<Material> optional = _IMaterialRepository.findById(id);
	if (optional.isPresent())
	    return optional.get();
	return new Material();
    }

}
