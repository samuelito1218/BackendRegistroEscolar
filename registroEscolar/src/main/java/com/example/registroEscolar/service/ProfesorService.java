package com.example.registroEscolar.service;

import java.util.List;

import com.example.registroEscolar.dto.ProfesorDTO;

public interface ProfesorService {

    ProfesorDTO registrarProfesor(ProfesorDTO profesorDTO);
    List<ProfesorDTO> listarProfesores();
    ProfesorDTO obtenerProfesorPorId(Integer idPersona);
    ProfesorDTO actualizarEspecialidadProfesor(Integer idPersona, String nuevaEspecialidad);
    void eliminarProfesor(Integer idPersona);

    //Filtros
    List<ProfesorDTO> filtrar(String especialidad, String nombre, String apellido, String email, Integer anoContratacion);
    
}
