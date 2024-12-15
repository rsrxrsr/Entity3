package com.rsr.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rsr.entity.model.Area;

@RepositoryRestResource
public interface IArea extends JpaRepository<Area, Long> {
	// http://localhost:8080/entity/restapi/areas/search/findByArea?Area=DDI
	public abstract Optional<Area> findByArea(String Area);
	
	@Query (
			value="SELECT a FROM Area a "
			     +"LEFT JOIN Area j ON (a.id = j.id)"
	)
	List<Area> hqlFindAll();

}
