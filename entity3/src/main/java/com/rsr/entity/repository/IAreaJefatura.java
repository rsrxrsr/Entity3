package com.rsr.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rsr.entity.model.AreaJefatura;

@RepositoryRestResource
public interface IAreaJefatura extends JpaRepository<AreaJefatura, Long> {
	
	@Query (
			value="SELECT * FROM myTree",
			nativeQuery = true
	)
	List<AreaJefatura> sqlFindAll();

}
