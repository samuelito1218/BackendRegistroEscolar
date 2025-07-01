package com.example.registroEscolar.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.registroEscolar.dto.ProfesorDTO;
import com.example.registroEscolar.model.Persona;
import com.example.registroEscolar.model.Profesor;

@Component
public class ProfesorMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ProfesorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProfesorDTO toDTO(Profesor profesor){
        ProfesorDTO dto = modelMapper.map(profesor.getPersona(), ProfesorDTO.class);
        dto.setFechaContratacion(profesor.getFechaContratacion());
        dto.setEspecialidad(profesor.getEspecialidad());
        return dto;

    }

    public Profesor toEntity(ProfesorDTO dto, Persona persona) {
        Profesor profesor = new Profesor();
        profesor.setPersona(persona);
        profesor.setFechaContratacion(dto.getFechaContratacion());
        profesor.setEspecialidad(dto.getEspecialidad());
        return profesor;
    }
    
}
