package com.rsr.entity.model;

import java.util.List;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class AreaJefatura {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdAreaJefatura;
	private String Nombre;
	private int Orden;
	
	@Transient
	private int Nivel;
	@Transient
	private int Hijos;
	@Transient
	private String Path;
	@Transient
	private String inOrden;

	@JsonIgnore
    @JoinColumn(name="idJefatura")
    @ManyToOne (fetch = FetchType.LAZY)
	private AreaJefatura jefatura;
    
	//@JsonIgnore
    @OneToMany(mappedBy="jefatura")
    private List<AreaJefatura> areas; 
	
}