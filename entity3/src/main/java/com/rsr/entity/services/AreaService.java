package com.rsr.entity.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsr.entity.model.Area;
import com.rsr.entity.model.AreaDto;
import com.rsr.entity.repository.IArea;

@Service
public class AreaService {

	@Autowired
	IArea areaRepository;

	public List<Area> updateArea() {
		/*List<AreaDto> areasDto = new ArrayList<AreaDto>();	    
	      areaRepository.findAll().forEach(area -> areasDto.add(new AreaDto(area)));
		  return areasDto; */
		return areaRepository.findAll()
			  .stream().map( x -> {x.setOrden(x.getOrden()*10);return x;})
			  .collect(Collectors.toList());
	}
	
	public AreaDto updateArbol(Long id) {
        Area area = updateArbol(areaRepository.findById(id) .orElse(new Area()));
		areaRepository.save(area);
		AreaDto areaDto = new AreaDto(area);
        return areaDto;       
	}
	
	public Area updateArbol(Area area) {
		area.setOrden(area.getOrden() * 10);
    	area.setAreas(updateAreas(area.getAreas()));
		return area;
	}
	
	public List<Area> updateAreas(List<Area> areas) {
		return areas.stream().map(x -> updateArbol(x))
				    .collect(Collectors.toList());
	}
		
}
