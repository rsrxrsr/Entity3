package com.rsr.entity.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rol {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String rol;

	@ManyToMany
	private List<Rol> roles;

	@ManyToMany(cascade=CascadeType.ALL)
	private List<Funcion> funciones;
	
	@JsonIgnore
	@ManyToMany (mappedBy="roles", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Usuario> usuarios;
		
}
