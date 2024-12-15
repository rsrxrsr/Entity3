package com.rsr.entity.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Immutable
@Table(name="AreaJefatura")
@Entity
@Data
public class Tree {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="area")	
	private String nombre;
	private int orden;
	
	private int nivel;
	private int hijos;
	private String path;
	private String inOrden;
	
    @JsonIgnore
    @JoinColumn(name="jefatura_id")
    @ManyToOne //(fetch = FetchType.LAZY)
	private Tree jefatura;
	
    @OneToMany(mappedBy="jefatura")
    private List<Tree> areas;
    
    public Tree() {super();};
    
    public Tree (Tree tree) {
    	this.setId(tree.getId());
    	this.setNombre(tree.getNombre());
    	this.setOrden(tree.getOrden());
    	this.setNivel(tree.getNivel());
    	this.setHijos(tree.getHijos());
    	this.setPath(tree.getPath());
    	this.setInOrden(tree.getInOrden());
        this.setAreas(tree.getAreas());  
    }
    
	void setAreas(List<Tree> areas) {
		this.areas = 
		     areas.stream().sorted(Comparator.comparing(Tree::getOrden)) 
			      . map(Tree::new).collect(Collectors.toList());
	}
    	
}