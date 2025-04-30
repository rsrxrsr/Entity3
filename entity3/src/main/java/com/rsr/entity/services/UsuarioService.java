package com.rsr.entity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rsr.entity.model.Usuario;
import com.rsr.entity.repository.IArea;
import com.rsr.entity.repository.IPersona;
import com.rsr.entity.repository.IRol;
import com.rsr.entity.repository.IUsuario;

@Service
public class UsuarioService {

	@Autowired
	IArea areaRepository;
	@Autowired
	IPersona personaRepository;
	@Autowired
	IRol rolRepository;
	@Autowired
	IUsuario usuarioRepository;
	
	public Usuario login(Usuario usuario) {
		return usuarioRepository.findByUsuarioAndPasswordAndEstatus(usuario.getUsuario(), usuario.getPassword(), 1)
								.orElse(new Usuario());
	}

	public Usuario save(Usuario usuario) {
		/*
		Long idArea = areaRepository.findByArea(usuario.getArea().getArea()).getId();
		if (idArea!=null) {
			usuario.getArea().setId(idArea);
		}
		*/ 
		usuario.setArea(areaRepository.save(usuario.getArea()));								
		usuario.setPersona(personaRepository.save(usuario.getPersona()));
		usuario.setRoles(rolRepository.saveAll(usuario.getRoles()));
		return usuarioRepository.save(usuario);
	}
	
	public boolean deleteById(Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;		
	}
	
	public ResponseEntity<?> deleteUser(Long id) {
		try {
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException ex) {
			String error = "Usuario no encontrado";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);		
		}
	}
	
}
