package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.jwt.filter.JwtAuthenticationFilter;
import com.example.demo.jwt.filter.JwtValidadtionFilter;
import com.example.demo.service.IUsuarioService;
import com.example.demo.service.impl.UsuarioService;

@Configuration
public class SpringSecurityConfig {

//    @Value("${ORIGIN_ANGULAR}")
//    private String origenAngular;

    @Bean
    PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
    
   

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
	AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	return authBuilder.build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	System.err.println(passwordEncoder().encode("admin"));
	return http.authorizeHttpRequests((authz) -> authz
		// TODOS LOS USUARIOS
		.requestMatchers("/**", "/login", "/chat-websocket/**").permitAll()
//		.requestMatchers(HttpMethod.GET, "/solicitud/**").hasAnyRole("ADMIN", "USER")
//		.requestMatchers(HttpMethod.POST, "/solicitud/**").hasAnyRole("ADMIN", "USER")
//		.requestMatchers(HttpMethod.GET, "/materiales/**").hasAnyRole("ADMIN", "USER")
//		// ADMIN
//
//		.requestMatchers(HttpMethod.GET, "/mantenimiento/**").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.POST, "/mantenimiento/**").hasAnyRole("ADMIN")
//
//		.requestMatchers(HttpMethod.POST, "/prestamo/**").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.GET, "/prestamos/**").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.PUT, "/prestamo/**").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.PUT, "/devolucion/**").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.GET, "/penalizacion/**").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.POST, "/penalizacion").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.PUT, "/penalizacion/**").hasAnyRole("ADMIN")
//		.requestMatchers(HttpMethod.GET, "/penalizaciones").hasAnyRole("ADMIN")

		.requestMatchers("/solicitudes").hasAnyRole("ADMIN").anyRequest().authenticated())
//		.cors(cors -> cors.configurationSource(configurationSource()))
//		.addFilter(new JwtAuthenticationFilter(authenticationManager(http)))
//		.addFilter(new JwtValidadtionFilter(authenticationManager(http)))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))		
		.csrf(csrf -> csrf.disable())
		.formLogin(
			(form) -> form.loginPage("/login").defaultSuccessUrl("/mantenimiento/materiales").permitAll())
		.logout((logout) -> logout.permitAll()).build();
    }

    @Bean
    CorsConfigurationSource configurationSource() {
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowedOriginPatterns(Arrays.asList("*"));
	config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
	config.setAllowedHeaders(
		Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "X-Socket-Id", "X-Frame-Options"));
	config.setAllowCredentials(true);

	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", config);
	return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsfilter() {
	FilterRegistrationBean<CorsFilter> corsbean = new FilterRegistrationBean<>(
		new CorsFilter(this.configurationSource()));
	corsbean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	return corsbean;
    }
}
