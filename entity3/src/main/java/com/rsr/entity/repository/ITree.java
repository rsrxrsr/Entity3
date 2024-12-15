package com.rsr.entity.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rsr.entity.model.Tree;

@RepositoryRestResource
public interface ITree extends JpaRepository<Tree, Long> {
	Optional<Tree> findByIdOrderByOrden(Long id);
	
}
