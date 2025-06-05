package com.rsr.entity.model;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

public class UsuarioDto extends Usuario {
	
    private Map<String, Boolean> rutas = new HashMap<>();
	private String permisos;
	
	public UsuarioDto (String usuario, String password) {
		setUsuario(usuario);
		setPassword(password);
	}

	public UsuarioDto (Usuario usuario) {
		setUsuario(usuario.getUsuario());
		setEstatus(usuario.getEstatus());
		setArea(usuario.getArea());
		setRoles(usuario.getRoles());
		setRutas(usuario.getRoles());
		setPermisos(usuario.getRoles());
	}
		
	public Map<String, Boolean> getRutas() {
		return rutas;
	}

	public void setRutas(List<Rol> roles) {
        roles.stream().forEach(rol -> {
        	rol.getFunciones().forEach(funcion ->{rutas.put(funcion.getUrl(),true);});
        	setRutas(rol.getRoles());
        });
	}

	public String getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Rol> roles) {
		/*		
		permisos=roles.get(0).getRol();
		for (int i=1;i<roles.size();i++) permisos += ", " + roles.get(i).getRol();
		*/
		permisos =  (roles==null) ? "Forbiden"
				 : roles.stream().map(Rol::getRol).collect(Collectors.joining(","));
	}
	
}
