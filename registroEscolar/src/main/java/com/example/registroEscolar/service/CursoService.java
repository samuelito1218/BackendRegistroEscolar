package com.example.registroEscolar.service;

import java.util.List;

import com.example.registroEscolar.dto.CursoDTO;

public interface CursoService {

    CursoDTO registrarCurso(CursoDTO cursoDTO);
    List<CursoDTO> listarCursos();
    CursoDTO buscarCursoPorId(Integer idCurso);
    CursoDTO actualizarCurso(CursoDTO cursoDTO, Integer idCurso);
    void eliminarCurso(Integer idCurso);

    //Filtros
    List<CursoDTO> filtrarCursos(String nombre, Integer creditos, Integer idProfesor);

    
    
}
