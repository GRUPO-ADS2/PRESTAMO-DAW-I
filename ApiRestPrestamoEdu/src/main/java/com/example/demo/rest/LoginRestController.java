package com.example.demo.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestLogin;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginRestController {
    

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody RequestLogin loginRequest, HttpServletRequest request) {
	try {
	    Authentication authentication = authenticationManager.authenticate(
		    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    // Guardar en sesión si se requiere
	    request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
		    SecurityContextHolder.getContext());

	    User user = (User) authentication.getPrincipal();

	    return ResponseEntity.ok(
		    Map.of("mensaje", "Login correcto", "usuario", user.getUsername(), "roles", user.getAuthorities()));
	} catch (BadCredentialsException e) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
	}
    }

}
