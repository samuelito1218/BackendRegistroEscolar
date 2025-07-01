package com.example.registroEscolar.service;

import com.example.registroEscolar.dto.InscripcionDTO;

import java.time.LocalDate;
import java.util.List;

public interface InscripcionService {
    InscripcionDTO registrar(InscripcionDTO dto);
    List<InscripcionDTO> listar();
    InscripcionDTO obtenerPorId(Integer id);
    void eliminar(Integer id);
    InscripcionDTO actualizarFechaInscripcion(Integer id, LocalDate nuevaFecha); 

    //Filtros
    List<InscripcionDTO> filtrarInscripciones(String nombreEstudiante, String apellidoEstudiante, String nombreCurso, Integer anoInscripcion);
}