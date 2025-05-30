package com.rsr.entity.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsr.entity.model.Rol;
import com.rsr.entity.repository.IRol;
import com.rsr.entity.repository.IFuncion;

@Service
public class RolService {

	@Autowired
	IRol rolRepository;
	@Autowired
	IFuncion funcionRepository;

	Map<String, Rol> mapaBase = new HashMap<>();
	Map<String, Rol> mapaRol = new HashMap<>();
	
	@Transactional
	public List<Rol> saveTree(List<Rol> roles) {
		mapaBase = rolRepository.findAll().stream()
		        .collect(Collectors.toMap(Rol::getRol, rol -> rol, (rol, dup) -> rol));
        reMapea(roles);
        List<Rol> saveRoles = new ArrayList<>(mapaRol.values()); 
        rolRepository.deleteRol_roles();
        addRoles(saveRoles);
		rolRepository.deleteRol_funciones();
		addFunciones(roles);
	    //List<Rol> saveRoles = rolRepository.saveAll(new ArrayList<>(mapaRol.values()));
        List<Long> ids = saveRoles.stream().map(Rol::getId).toList();
		Integer deletes = rolRepository.deleteTree(ids);
		System.out.println("Registros borrados=" + deletes);
		System.out.println("****** IDS" + ids);		
		return saveRoles;				
	}
		
	void reMapea(List<Rol> roles) {		
		roles.forEach(rol -> {
			Rol rolBase = mapaBase.get(rol.getRol());
			Rol mpRol = mapaRol.get(rol.getRol());

			if (mpRol==null && rolBase==null) {
	    		rol = rolRepository.save(rol);
			} else if (mpRol==null) {
			    rol.setId(rolBase.getId());
			} else {
		    	rol.setId(mpRol.getId());
		    }
	    	mapaRol.put(rol.getRol(), rol);
	    	reMapea(rol.getRoles());						
		});
	}
	
	void addRoles(List<Rol> roles) {
		roles.forEach(rol -> { 
			if (rol.getRoles()!=null) {
				rol.getRoles().forEach(child -> {
					rolRepository.saveRol_roles(rol.getId(),child.getId()); 	    		
					addRoles(child.getRoles());		
				});
			}
		});
	}
	
	void addFunciones(List<Rol> roles) {
		roles.forEach(rol -> {
			if (rol.getFunciones()!=null) {
				rol.getFunciones().forEach(funcion -> {
	            	funcion = funcionRepository.findByFuncion(funcion.getFuncion());
					rolRepository.saveRol_funciones(rol.getId(),funcion.getId()); 	    		
				});
			}			
		});
	}

} //End Class
