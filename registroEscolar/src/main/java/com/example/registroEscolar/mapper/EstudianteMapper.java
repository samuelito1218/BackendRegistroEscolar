package com.example.registroEscolar.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.registroEscolar.dto.EstudianteDTO;
import com.example.registroEscolar.model.Estudiante;
import com.example.registroEscolar.model.Persona;

@Component
public class EstudianteMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public EstudianteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public EstudianteDTO toDTO(Estudiante estudiante) {
        EstudianteDTO dto = modelMapper.map(estudiante.getPersona(), EstudianteDTO.class);
        dto.setNumeroMatricula(estudiante.getNumeroMatricula().toString());
        dto.setGrado(estudiante.getGrado());
        return dto;
    }
    public Estudiante toEntity(EstudianteDTO dto, Persona persona, Integer numeroMatricula) {
        Estudiante estudiante = new Estudiante();
        estudiante.setPersona(persona);
        //estudiante.setIdPersona(persona.getIdPersona());
        estudiante.setNumeroMatricula(numeroMatricula);
        estudiante.setGrado(dto.getGrado());
        return estudiante;    
    }
    
}
