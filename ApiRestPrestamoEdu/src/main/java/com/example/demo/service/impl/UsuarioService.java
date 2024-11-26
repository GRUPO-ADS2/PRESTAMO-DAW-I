package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Usuario;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioRepository _IUsuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> getAll(){
		return _IUsuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> update(Usuario usuario, Long id) {
		return _IUsuarioRepository.findById(id)
				.map(usu -> {
					usu.setEnabled(usuario.getEnabled());
					usu.setRoles(usuario.getRoles());
					usu.setUsername(usuario.getUsername());
					return _IUsuarioRepository.save(usu);
				});
	}

	@Override
	public Usuario save(Usuario usuario) {
		usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
		return _IUsuarioRepository.save(usuario);
	}
	
	
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> existusuario = _IUsuarioRepository.findByUsername(username);
		
		if (existusuario.isEmpty()) {
			logger.error("Error en el login: no existe el usuario"+ username);
			throw new UsernameNotFoundException("Error en el login: no existe el usuario"+ username);
		}
		Usuario usuario = existusuario.orElseThrow();		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority-> logger.info("Role" + authority.getAuthority()))
				.collect(Collectors.toList())
				;
		return new User(
				usuario.getUsername(),
				usuario.getContrasenia(),
				usuario.getEnabled(),
				true, true, true, 
				authorities);
	}
	

}
