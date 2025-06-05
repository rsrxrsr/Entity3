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
	private Nivel nivel;
	private String puesto;
	private Usuario.Estatus estatus;
	
	//@JsonIgnore
    //@JoinColumn(name="jefatura_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne (cascade=CascadeType.ALL) //fetch = FetchType.LAZY)
	private Area jefatura;

	//@JsonIgnore
    //@JoinColumn(name="jefe_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne (cascade=CascadeType.ALL)
	private Usuario jefe;
	
	@JsonIgnore
    @OneToMany(mappedBy="jefatura")
    private List<Area> areas; 

	@JsonIgnore
	@OneToMany (mappedBy="area") //(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Usuario> usuarios;
	
}




