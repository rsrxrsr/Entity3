package com.rsr.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rsr.entity.model.Rol;
import com.rsr.entity.repository.IRol;
import com.rsr.entity.services.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {

	@Autowired
	IRol rolRepository;

	@Autowired
	RolService rolService;

	@GetMapping()
	List<Rol> findAll() {
		return rolRepository.findAll();	
	}
	
	@GetMapping(path="/{id}")
	Rol findById(@PathVariable("id") Long id) {
		return rolRepository.findById(id) .orElse(new Rol());	
	}
	
	@GetMapping(path="/tree")
	List<Rol> getTree() {
		return rolRepository.findAll();	
	}
	
	@PostMapping(path="/tree")
	List<Rol> save(@RequestBody List<Rol> roles) {
		System.out.print(roles);
		return rolService.saveTree(roles);
	}
			
}
