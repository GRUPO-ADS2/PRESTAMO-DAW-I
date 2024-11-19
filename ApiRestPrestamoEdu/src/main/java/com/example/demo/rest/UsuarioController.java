package com.example.demo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Usuario;
import com.example.demo.service.impl.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> getMethodName() {
		return usuarioService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> postMethodName(@Valid @RequestBody Usuario usuario ,BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), "El campo" + error.getField() + " " + error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	
}
