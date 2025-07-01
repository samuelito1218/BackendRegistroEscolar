package com.example.registroEscolar.repository;

import com.example.registroEscolar.model.Inscripcion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    boolean existsByEstudiante_IdPersonaAndCurso_IdCurso(Integer idEstudiante, Integer idCurso);

    //Filtros con querys para buscar por nombre-apellido del estudiante, nombre del curso y año de inscripción
    @Query("SELECT i FROM Inscripcion i " +
       "JOIN i.estudiante e " +
       "JOIN i.curso c " +
       "WHERE (:nombreEstudiante IS NULL OR LOWER(e.persona.nombre) LIKE LOWER(CONCAT('%', :nombreEstudiante, '%'))) " +
       "AND (:apellidoEstudiante IS NULL OR LOWER(e.persona.apellido) LIKE LOWER(CONCAT('%', :apellidoEstudiante, '%'))) " +
       "AND (:nombreCurso IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombreCurso, '%'))) " +
       "AND (:anoInscripcion IS NULL OR FUNCTION('YEAR', i.fechaInscripcion) = :anoInscripcion)")
       List<Inscripcion> filtrarInscripciones(
        @Param("nombreEstudiante") String nombreEstudiante,
        @Param("apellidoEstudiante") String apellidoEstudiante,
        @Param("nombreCurso") String nombreCurso,
        @Param("anoInscripcion") Integer anoInscripcion
    );

}
