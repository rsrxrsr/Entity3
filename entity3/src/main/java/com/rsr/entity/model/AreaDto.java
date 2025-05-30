package com.rsr.entity.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embedded;

public class AreaDto extends Area {

	@JsonProperty(value="areas")
	private List<AreaDto> areas;

	@JsonProperty(value="jefe")
	private Usuario jefe;

	@JsonProperty(value="jefatura")
	private Area jefatura;

	//@SuppressWarnings("unused")
	//@JsonProperty(value="usuarios")
	//private List<Usuario> usuarios;
	
	public AreaDto (Area area) {
    	this.setId(area.getId());
    	this.setArea(area.getArea());
    	this.setOrden(area.getOrden());
    	this.setNivel(area.getNivel());
    	this.setAreas(sortAreas(area.getAreas()));    	
    	//
    	if (area.getJefatura()!=null) {
	    	Area jefatura = new Area();
	    	jefatura.setId(area.getJefatura().getId());
	    	jefatura.setArea(area.getJefatura().getArea());
	    	this.setJefatura(jefatura);
    	}
    	if (area.getJefe()!=null) {
	    	this.setJefe(area.getJefe());
    	}
    	//	
    	this.setUsuarios(area.getUsuarios());    	
	}
	
	public List<Area> sortAreas(List<Area> areas) {
		return areas.stream().sorted(Comparator.comparing(Area::getOrden))
	   			    . map(AreaDto::new).collect(Collectors.toList());
	}
	
}
