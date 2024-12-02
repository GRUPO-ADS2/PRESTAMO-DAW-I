package com.example.demo.jwt.filter;

import static com.example.demo.jwt.filter.JwtValuesConfig.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.models.Usuario;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//se encarga de recibir los datos de inciaio de session
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {		
		this.authenticationManager = authenticationManager;		
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = null;
		String password = null;
		Usuario user = null;
		
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			username = user.getUsername();
			password = user.getContrasenia();
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return this.authenticationManager.authenticate(authenticationToken);
	}


	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		String username = user.getUsername();
		
		//pasamos los roles resultado de autorizacion
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		boolean isAdmin = roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
		Claims claims = Jwts.claims()
				.add("authorities",new ObjectMapper().writeValueAsString(roles))				
				.add("username",username)
				.add("isAdmin",isAdmin)
				.build();
				
		String jwt = Jwts.builder()
					.claims(claims)
					.subject(username)
					.signWith(SECRET_KEY)
					.issuedAt(new Date())
					.expiration(new Date(System.currentTimeMillis() + 3600000))
				.compact();
		//armando la respuesta		
		response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + jwt);
		Map<String,String> body = new HashMap<>();
		body.put("token", jwt);
		body.put("username", username);
		body.put("message", String.format("hola %s", username));
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType(CONTENT_TYPE);
		response.setStatus(200);
	}


	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, String> body = new HashMap<>();
		body.put("message", "error en la authenticacion con username o password incorrento!!");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType(CONTENT_TYPE);
		response.setStatus(401);
	}	
}
