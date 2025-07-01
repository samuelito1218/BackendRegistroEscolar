package com.example.registroEscolar.service;

import java.util.List;

import com.example.registroEscolar.dto.PersonaDTO;

public interface PersonaService {

    List<PersonaDTO> listarTodos();
    PersonaDTO guardar(PersonaDTO persona);
    PersonaDTO obtenerPorId(Integer id);
    PersonaDTO actualizar(Integer id, PersonaDTO persona);
    void eliminar(Integer id);

    //Filtros
    List<PersonaDTO> filtrar(String nombre, String apellido, String email);
}

