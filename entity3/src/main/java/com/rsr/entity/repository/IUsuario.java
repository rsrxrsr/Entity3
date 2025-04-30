package com.rsr.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rsr.entity.model.Usuario;

@CrossOrigin(origins = "*")
public interface IUsuario extends JpaRepository<Usuario, Long> {
	
	//http://localhost:8080/entity/restapi/usuarios/search/findByUsuario?usuario=Luis
	abstract Usuario findByUsuario(String usuario);
	//http://localhost:8080/entity/restapi/usuarios/search/findByUsuarioAndPasswordAndEstatus?usuario=Luis&password=psw&estatus=1
	abstract Optional<Usuario> findByUsuarioAndPasswordAndEstatus(String usuario, String password, Integer estatus);
	//http://localhost:8080/entity/restapi/usuarios/search/findByUsuarioOrPassword?usuario=Luis&password=nose
	abstract List<Usuario> findByUsuarioOrPassword(String usuario, String password);

	// http://localhost:8080/entity/restapi/usuarios/search/queryByEstatus?estatus=1
	@Query (
			value="SELECT u FROM Usuario u WHERE u.estatus = :estatus"
	)
	List<Usuario> queryByEstatus(@Param("estatus") Integer estatus);

	// http://localhost:8080/entity/restapi/usuarios/search/sqlByEstatus?estatus=1
	@Query (
			value="SELECT u.* FROM Usuario u WHERE u.estatus = :estatus",
			nativeQuery = true
	)
	List<Usuario> sqlByEstatus(@Param("estatus") Integer estatus);
	
	//@Transactional(readOnly = true)
	@Procedure
	List<Usuario> FindByEstatus(Integer estatus);
	
	/*
	 * POST:
	 *   http://localhost:8080/entity/restapi/usuario
	{
	"id": "nulo o id para create o modify"
    "usuario": "Create",
    "password": "psw",
    "estatus": 3,
    "persona": null,
    "area": null
	}
	 * Delete: Return Objeto o 404 Not Found
	 *   http://localhost:8080/entity/restapi/usuario/id	  
    */
}
