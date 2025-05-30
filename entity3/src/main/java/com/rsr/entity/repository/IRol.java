package com.rsr.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import com.rsr.entity.model.Rol;

@CrossOrigin(origins = "*")
public interface IRol extends JpaRepository<Rol, Long> {
	
	Rol findByRol(String rol);

    @Query(value =
    	"SELECT r.* FROM Rol r"
    	//+ " LEFT JOIN Rol_roles rr ON r.id = rr.roles_id"
    	//+ " WHERE rr.roles_id IS NULL " //OR rr.Rol_id = rr.roles_id"
    	,nativeQuery = true
    )
    List<Rol> findTree();

    @Modifying
    @Query(value =
    	    " DELETE rol FROM rol r"
    	    + " WHERE r.id NOT IN :ids"
        	, nativeQuery = true
        )
    Integer deleteTree(@Param("ids") List<Long> ids);
    
    @Modifying
    @Query(value =
    		"MERGE Rol_roles AS target "
    		+ "USING (VALUES (:idRol, :idChild)) AS source (Rol_id, roles_id) "
    		+ "ON (target.Rol_id = source.Rol_id AND target.roles_id = source.roles_id) "
    		+ "WHEN NOT MATCHED THEN "
    		+ "   INSERT (Rol_id, roles_id) "
    		+ "   VALUES (source.Rol_id, source.roles_id);"
       	, nativeQuery = true
    )
    void saveRol_roles(@Param("idRol") Long idRol, Long idChild);
        
    @Modifying
    @Query(value =
    	"DELETE FROM Rol_roles "
    	, nativeQuery = true
    )
    Integer deleteRol_roles();

    @Modifying
    @Query(value =
    		"MERGE Rol_funciones AS target "
    		+ "USING (VALUES (:idRol, :idChild)) AS source (roles_id, funciones_id) "
    		+ "ON (target.roles_id = source.roles_id AND target.funciones_id = source.funciones_id) "
    		+ "WHEN NOT MATCHED THEN "
    		+ "   INSERT (roles_id, funciones_id) "
    		+ "   VALUES (source.roles_id, source.funciones_id);"
       	, nativeQuery = true
    )
    void saveRol_funciones(@Param("idRol") Long idRol, Long idChild);
    
    @Modifying
    @Query(value =
    	"DELETE FROM Rol_funciones "
    	, nativeQuery = true
    )
    Integer deleteRol_funciones();

    @Modifying
    @Query(value =
    		"MERGE Usuario_roles AS target "
    		+ "USING (VALUES (:idRol, :idChild)) AS source (roles_id, usuarios_id) "
    		+ "ON (target.roles_id = source.roles_id AND target.usuarios_id = source.usuarios_id) "
    		+ "WHEN NOT MATCHED THEN "
    		+ "   INSERT (roles_id, usuarios_id) "
    		+ "   VALUES (source.roles_id, source.usuarios_id);"
       	, nativeQuery = true
    )
    void saveRol_usuarios(@Param("idRol") Long idRol, Long idChild);
    
    @Modifying
    @Query(value =
    	"DELETE FROM Usuario_roles "
    	, nativeQuery = true
    )
    Integer deleteRol_usuarios();

}
