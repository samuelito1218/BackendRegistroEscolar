package com.example.registroEscolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.registroEscolar.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    //Filtros con querys para buscar por grado, nombre, apellido, email y número de mátricula
    @Query("SELECT e FROM Estudiante e WHERE " +
           "(:grado IS NULL OR e.grado = :grado) AND " +
           "(:nombre IS NULL OR LOWER(e.persona.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:apellido IS NULL OR LOWER(e.persona.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))) AND " +
           "(:email IS NULL OR LOWER(e.persona.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:numeroMatricula IS NULL OR e.numeroMatricula = :numeroMatricula)")
    List<Estudiante> filtrarEstudiantes(
        @Param("grado") Integer grado,
        @Param("nombre") String nombre,
        @Param("apellido") String apellido,
        @Param("email") String email,
        @Param("numeroMatricula") Integer numeroMatricula
    );
        
    
    
}
