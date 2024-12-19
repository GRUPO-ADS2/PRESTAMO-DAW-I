package com.example.demo.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodUsuario")
    private Integer codUsuario;
    
    @NotBlank
    @Size(min = 4, max = 12)
    @Column(nullable = true, unique = true)
    private String username;
    
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "Contrasenia", nullable = false, length = 200)
    private String contrasenia;
    
    @NotNull
    private Boolean enabled;
    
    @Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;
    
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @Column(name = "Role", nullable = false, length = 20)
    private Set<Role> roles;
    
    
}
