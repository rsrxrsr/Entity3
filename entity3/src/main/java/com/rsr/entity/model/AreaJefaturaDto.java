package com.rsr.entity.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaJefaturaDto extends Area {

	@JsonProperty(value="jefatura")
	private Area jefatura;
	@JsonProperty(value="areas")
	private List<Area> areas;
	@JsonProperty(value="usuarios")
	private List<Usuario> usuarios;

	public AreaJefaturaDto (Area area) {
    	this.setId(area.getId());
    	this.setArea(area.getArea());
    	this.setJefatura(area.getJefatura());    	
    	this.setAreas(area.getAreas());    	
    	this.setUsuarios(area.getUsuarios());    	
	}

}
