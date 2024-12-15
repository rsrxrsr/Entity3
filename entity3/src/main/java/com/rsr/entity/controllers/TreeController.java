package com.rsr.entity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rsr.entity.model.Tree;
import com.rsr.entity.repository.ITree;

@RestController
@RequestMapping("/tree")
public class TreeController {

	@Autowired
	ITree treeRepository;
			
	@GetMapping(path="/{id}")
	Tree getTree(@PathVariable("id") Long id) {
		return new Tree(treeRepository.findById(id) .orElse(new Tree()));
//		return new Tree(treeRepository.findByIdOrderByOrden(id) .orElse(new Tree()));
	}
		
}
