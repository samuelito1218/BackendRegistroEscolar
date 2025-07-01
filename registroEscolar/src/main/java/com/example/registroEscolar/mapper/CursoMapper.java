package com.example.registroEscolar.mapper;

import org.springframework.stereotype.Component;

import com.example.registroEscolar.dto.CursoDTO;
import com.example.registroEscolar.model.Curso;
import com.example.registroEscolar.model.Profesor;

@Component
public class CursoMapper {
    public Curso toEntity(CursoDTO cursoDTO, Profesor profesor){
        Curso curso = new Curso();
        curso.setNombre(cursoDTO.getNombre());
        curso.setDescripcion(cursoDTO.getDescripcion());
        curso.setCreditos(cursoDTO.getCreditos());
        curso.setProfesor(profesor);
        return curso;
    }

    public CursoDTO toDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setIdCurso(curso.getIdCurso());
        cursoDTO.setNombre(curso.getNombre());
        cursoDTO.setDescripcion(curso.getDescripcion());
        cursoDTO.setCreditos(curso.getCreditos());
        cursoDTO.setIdProfesor(curso.getProfesor().getIdPersona());
        return cursoDTO;
    }
    
}
