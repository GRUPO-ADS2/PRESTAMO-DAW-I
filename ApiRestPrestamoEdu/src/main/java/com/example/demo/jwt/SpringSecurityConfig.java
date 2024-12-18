package com.example.demo.jwt;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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

@Configuration
public class SpringSecurityConfig  {	
  
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Value("${ORIGIN_ANGULAR}")
    private String origenAngular;
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	return http.authorizeHttpRequests((authz) -> authz
    			.requestMatchers("/", "/login", "/**").permitAll()
    			//.requestMatchers(HttpMethod.GET,"/materiales").permitAll()
    			//.requestMatchers(HttpMethod.POST,"/solicitud").hasAnyRole("ADMIN","USER")
    			//.requestMatchers("/solicitudes").hasAnyRole("ADMIN")
    			.anyRequest().authenticated()
    			)
    			.cors(cors -> cors.configurationSource(configurationSource()))
    			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
    			.addFilter(new JwtValidadtionFilter(authenticationManager()))
    			.csrf(csrf -> csrf.disable())
    			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    			.build();
    }
	
    @Bean
    CorsConfigurationSource configurationSource() {
    	CorsConfiguration config = new CorsConfiguration();
    	config.setAllowedOriginPatterns(Arrays.asList("*"));
    	config.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:8081",origenAngular));
    	config.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
    	config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
    	config.setAllowCredentials(true);
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", config);
    	return source;
    }
    
    @Bean
    FilterRegistrationBean<CorsFilter> corsfilter(){
    	FilterRegistrationBean<CorsFilter> corsbean = new FilterRegistrationBean<CorsFilter>(
    			new CorsFilter(this.configurationSource())
    			);
    	corsbean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    	return corsbean;
    }
}
