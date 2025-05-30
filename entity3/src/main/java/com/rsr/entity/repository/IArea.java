package com.rsr.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rsr.entity.model.Area;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface IArea extends JpaRepository<Area, Long> {
	// http://localhost:8080/entity/restapi/areas/search/findByArea?Area=DDI
	public abstract Optional<Area> findByArea(String Area);

	public List<Area> findByJefaturaIsNull();

}
