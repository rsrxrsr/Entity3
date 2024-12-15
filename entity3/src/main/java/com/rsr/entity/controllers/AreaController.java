package com.rsr.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rsr.entity.model.Area;
import com.rsr.entity.model.AreaDto;
import com.rsr.entity.model.AreaJefaturaDto;
import com.rsr.entity.model.Nivel;
import com.rsr.entity.repository.IArea;
import com.rsr.entity.services.AreaService;

@RestController
@RequestMapping("/area")
public class AreaController {


	@Autowired
	IArea areaRepository;

	@Autowired
	AreaService areaService;

	@GetMapping()
	List<Area> findAll() {
		return areaRepository.findAll();
	}

	@GetMapping(path="/name/{area}")
	Area findByArea(@PathVariable("area") String area) {
		return areaRepository.findByArea(area) .orElse(new Area());
	}

	@PostMapping()
    Area save(@RequestBody Area area) {
    	return areaRepository.save(area);
    }

	@GetMapping(path="/{id}")
	AreaDto findById(@PathVariable("id") Long id) {
		return new AreaDto(areaRepository.findById(id) .orElse(new Area()));	
	}
	
	@GetMapping(path="/{id}/jefatura")
	AreaJefaturaDto findByJefatura(@PathVariable("id") Long id) {
		return new AreaJefaturaDto(areaRepository.findById(id).orElse(new Area()));
	}
	
	@GetMapping(path="/nivel")
	Nivel[] nivel() {
		return Nivel.values();
	}
	
	@GetMapping(path="/updateArea")
	List<Area> update() {
		return areaService.updateArea();
	}

	@GetMapping(path="/updateArbol/{id}")
	AreaDto updateArbol(@PathVariable("id") Long id) {
		return areaService.updateArbol(id);
	}
		
}
