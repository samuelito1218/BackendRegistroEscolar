package com.example.registroEscolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.registroEscolar.model.Administrativo;

public interface AdministrativoRepository extends JpaRepository<Administrativo, Integer> {

    @Query("SELECT a FROM Administrativo a WHERE " +
           "(:cargo IS NULL OR LOWER(a.cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))) AND " +
           "(:departamento IS NULL OR LOWER(a.departamento) LIKE LOWER(CONCAT('%', :departamento, '%'))) AND " +
           "(:nombre IS NULL OR LOWER(a.persona.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:apellido IS NULL OR LOWER(a.persona.apellido) LIKE LOWER(CONCAT('%', :apellido, '%')))")

    List<Administrativo> filtrarAdministrativos( 
        @Param("cargo") String cargo,
        @Param("departamento") String departamento,
        @Param("nombre") String nombre,
        @Param("apellido") String apellido
    );

}
