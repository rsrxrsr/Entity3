package com.rsr.entity.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rsr.entity.model.Area;
import com.rsr.entity.model.AreaDto;
import com.rsr.entity.model.AreaJefaturaDto;
import com.rsr.entity.model.Nivel;
import com.rsr.entity.model.PaginaArea;

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
	
	@GetMapping(path="/pagina")
	PaginaArea getPaginaArea(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort) {
		return areaService.getPaginaArea(page, size, sort);
	}

	@GetMapping(path="/name/{area}")
	Area findByArea(@PathVariable("area") String area) {
		return areaRepository.findByArea(area) .orElse(new Area());
	}

	@GetMapping(path="/{id}")
	AreaDto findById(@PathVariable("id") Long id) {
		return new AreaDto(areaRepository.findById(id) .orElse(new Area()));	
	}
	
	@GetMapping(path="/{id}/jefatura")
	AreaJefaturaDto findByJefatura(@PathVariable("id") Long id) {
		return new AreaJefaturaDto(areaRepository.findById(id).orElse(new Area()));
	}
	
	@GetMapping(path="/tree")
	List<AreaDto> findTreeArea() {
		return areaRepository.findByJefaturaIsNull().stream()
							 .map(AreaDto::new).collect(Collectors.toList());
	}
	
	@GetMapping(path="/nivel")
	Nivel[] nivel() {
		return Nivel.values();
	}

	@PostMapping()
    Area save(@RequestBody Area area) {
    	return areaRepository.save(area);
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
