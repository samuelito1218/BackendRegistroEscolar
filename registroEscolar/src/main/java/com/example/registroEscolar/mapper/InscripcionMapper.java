package com.example.registroEscolar.mapper;

import com.example.registroEscolar.dto.InscripcionDTO;
import com.example.registroEscolar.model.Curso;
import com.example.registroEscolar.model.Estudiante;
import com.example.registroEscolar.model.Inscripcion;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper {

    public Inscripcion toEntity(InscripcionDTO dto, Estudiante estudiante, Curso curso) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setFechaInscripcion(dto.getFechaInscripcion());
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        return inscripcion;
    }

    public InscripcionDTO toDTO(Inscripcion inscripcion) {
        InscripcionDTO dto = new InscripcionDTO();
        dto.setIdInscripcion(inscripcion.getIdInscripcion());
        dto.setIdEstudiante(inscripcion.getEstudiante().getIdPersona());
        dto.setIdCurso(inscripcion.getCurso().getIdCurso());
        dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
        return dto;
    }
}
