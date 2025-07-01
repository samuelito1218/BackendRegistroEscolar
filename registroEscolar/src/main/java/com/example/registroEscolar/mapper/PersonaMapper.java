package com.example.registroEscolar.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.registroEscolar.dto.PersonaDTO;
import com.example.registroEscolar.model.Persona;

@Component
public class PersonaMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public PersonaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonaDTO toDTO(Persona persona) {
        return modelMapper.map(persona, PersonaDTO.class);
    }

    public Persona toEntity(PersonaDTO dto) {
        return modelMapper.map(dto, Persona.class);
    }
}
