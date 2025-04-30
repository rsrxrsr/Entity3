package com.rsr.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rsr.entity.model.Tree;
import com.rsr.entity.repository.ITree;
import com.rsr.entity.repository.Repositorio;

@RestController
@RequestMapping("/tree")
public class TreeController {

	@Autowired
	ITree treeRepository;
	@Autowired
	Repositorio repositorio;
			
	@GetMapping(path="/{id}")
	Tree getTree(@PathVariable("id") Long id) {
		return new Tree(treeRepository.findById(id) .orElse(new Tree()));
//		return new Tree(treeRepository.findByIdOrderByOrden(id) .orElse(new Tree()));
	}

	@GetMapping(path="/nodos")
	int getNodos() {
		return  repositorio.nodos();
	}
	
	@GetMapping(path="/orden")
	List<Tree> ordenMayor() {
		return  repositorio.ordenMayor();
	}

	@GetMapping(path="/insert")
	int insertArea() {
		return  repositorio.insertArea();
	}
	
}
