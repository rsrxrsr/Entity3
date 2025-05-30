package com.rsr.entity.model;

import java.util.List;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Area {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String area;
	private int orden;
	private int nivel;
	private String puesto;
	
	@JsonIgnore
    //@JoinColumn(name="jefatura_id")
    @ManyToOne (cascade=CascadeType.ALL) //fetch = FetchType.LAZY)
	private Area jefatura;

	@JsonIgnore
    //@JoinColumn(name="jefe_id")
    @OneToOne (cascade=CascadeType.ALL)
	private Usuario jefe;
	
	@JsonIgnore
    @OneToMany(mappedBy="jefatura") //, cascade=CascadeType.ALL)
    private List<Area> areas; 

	@JsonIgnore
	@OneToMany (mappedBy="area") //(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Usuario> usuarios;
	
}