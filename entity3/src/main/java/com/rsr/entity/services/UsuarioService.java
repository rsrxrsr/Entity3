package com.rsr.entity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

import com.rsr.entity.model.Rol;
import com.rsr.entity.model.Usuario;
import com.rsr.entity.repository.IArea;
import com.rsr.entity.repository.IPersona;
import com.rsr.entity.repository.IRol;
import com.rsr.entity.repository.IUsuario;

import jakarta.transaction.Transactional;

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
		return usuarioRepository.findByUsuarioAndPasswordAndEstatus(usuario.getUsuario(), usuario.getPassword(), Usuario.Estatus.ACTIVO)
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
	
	/*
	@Transactional
	public List<Usuario> saveTree(List<Usuario> usuarios) {
		rolRepository.deleteRol_usuarios();
    	usuarios.forEach(usuario -> {
    		if (usuario.getRoles()!=null) {
    			usuario.getRoles().forEach(rol -> {
    				System.out.println("*** ID *** " + rol.getId());
    				rol = rolRepository.findByRol(rol.getRol());
    				rolRepository.saveRol_usuarios(rol.getId(),usuario.getId()); 	    		
    			});
    		}			
    	});
		//List<Usuario> usuariosBd = new ArrayList<>();
    	usuarios.forEach(usuario -> {
			Usuario usuarioBd = usuarioRepository.findById(usuario.getId()).orElseThrow();
    		usuarioBd.getRoles().clear();
    		if (usuario.getRoles()!=null) {
        		List<Rol> roles = new ArrayList<>();
    			usuario.getRoles().forEach(rol -> {
    				rol = rolRepository.findByRol(rol.getRol());
    				roles.add(rol);
    			});
        		usuario.setRoles(roles);
    		}	
        	usuarioRepository.save(usuario);
    	});
    	//
    	usuarioRepository.saveAll(usuarios);
    	return usuarios;
	}
*/
	
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
