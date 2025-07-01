package com.example.registroEscolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.registroEscolar.model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {

    @Query("SELECT p FROM Profesor p WHERE " +
           "(:especialidad IS NULL OR LOWER(p.especialidad) LIKE LOWER(CONCAT('%', :especialidad, '%'))) AND " +
           "(:nombre IS NULL OR LOWER(p.persona.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:apellido IS NULL OR LOWER(p.persona.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))) AND " +
           "(:email IS NULL OR LOWER(p.persona.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:anoContratacion IS NULL OR FUNCTION('YEAR', p.fechaContratacion) = :anoContratacion)")
    
    //Filtros con querys para buscar por especialidad, nombre, apellido, email y año de contratación
    List<Profesor> filtrarProfesores(
        @Param ("especialidad") String especialidad,
        @Param ("nombre") String nombre,
        @Param ("apellido") String apellido,
        @Param ("email") String email,
        @Param ("anoContratacion") Integer anoContratacion

    );
    
}
