package com.example.registroEscolar.service;

import java.util.List;

import com.example.registroEscolar.dto.EstudianteDTO;

public interface EstudianteService {

    EstudianteDTO registrarEstudiante(EstudianteDTO estudianteDTO);
    List<EstudianteDTO> listarEstudiantes();
    EstudianteDTO obtenerEstudiantePorId(Integer idPersona);
    EstudianteDTO actualizarGradoEstudiante(Integer idPersona, Integer nuevoGrado);
    void eliminarEstudiante(Integer idPersona);

    //Filtros
    List<EstudianteDTO> filtrar(Integer grado, String nombre, String apellido, String email, Integer numeroMatricula);
    
}
