package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Usuario;

public interface IUsuarioService {
	List<Usuario> getAll();
	Optional<Usuario> update(Usuario usuaurio, Long id);
	Usuario save(Usuario usuario);
}
