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

	@JsonIgnore
    //@JoinColumn(name="jefatura_id")
    @ManyToOne //(fetch = FetchType.LAZY)
	private Area jefatura;
    
	@JsonIgnore
    @OneToMany(mappedBy="jefatura") //, cascade=CascadeType.ALL)
    private List<Area> areas; 

	@JsonIgnore
	@OneToMany (mappedBy="area") //(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Usuario> usuarios;
	
}