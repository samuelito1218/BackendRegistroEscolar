package com.example.registroEscolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.registroEscolar.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    boolean existsByNombreIgnoreCaseAndProfesor_IdPersona(String nombre, Integer idProfesor);

    //Filtros para buscar por nombre, número de creditos y el id del profesor
    @Query("SELECT c FROM Curso c WHERE " +
       "(:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
       "(:creditos IS NULL OR c.creditos = :creditos) AND " +
       "(:idProfesor IS NULL OR c.profesor.idPersona = :idProfesor)")
       
       List<Curso> filtrarCursos(
        @Param("nombre") String nombre,
        @Param("creditos") Integer creditos,
        @Param("idProfesor") Integer idProfesor
        );


}
