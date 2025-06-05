package com.rsr.entity.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AreaDto extends Area {

	@JsonProperty(value="areas")
	private List<AreaDto> areas;

	@JsonProperty(value="jefe", access=JsonProperty.Access.READ_WRITE)
	private Usuario jefe;

	@Override
	@JsonProperty(value="jefatura", access=JsonProperty.Access.READ_WRITE)
	public Area getJefatura() {
        return super.getJefatura();
    }

	//@SuppressWarnings("unused")
	//@JsonProperty(value="usuarios")
	//private List<Usuario> usuarios;
	
	public AreaDto (Area area) {
    	this.setId(area.getId());
    	this.setArea(area.getArea());
    	this.setOrden(area.getOrden());
    	this.setNivel(area.getNivel());
    	this.setEstatus(area.getEstatus());
    	this.setPuesto(area.getPuesto());
    	this.setJefe(area.getJefe());
    	this.setJefatura(area.getJefatura());	
    	this.setAreas(sortAreas(area.getAreas()));    	
    	//this.setUsuarios(area.getUsuarios());    	
	}

	public List<Area> sortAreas(List<Area> areas) {
		return areas.stream().sorted(Comparator.comparing(Area::getOrden))
	   			    . map(AreaDto::new).collect(Collectors.toList());
	}
	
} // End Class

/*
if (area.getJefatura()!=null) {
	Area jefatura = new Area();
	jefatura.setId(area.getJefatura().getId());
	jefatura.setArea(area.getJefatura().getArea());
	this.setJefatura(jefatura);
}
if (area.getJefe()!=null) {
	this.setJefe(area.getJefe());
}
*/	



