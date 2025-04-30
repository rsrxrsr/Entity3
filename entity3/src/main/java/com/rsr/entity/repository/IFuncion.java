package com.rsr.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rsr.entity.model.Funcion;

@CrossOrigin(origins = "*")
public interface IFuncion extends JpaRepository<Funcion, Long> {}
