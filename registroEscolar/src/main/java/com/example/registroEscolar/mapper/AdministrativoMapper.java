package com.example.registroEscolar.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.registroEscolar.dto.AdministrativoDTO;
import com.example.registroEscolar.model.Administrativo;
import com.example.registroEscolar.model.Persona;

@Component
public class AdministrativoMapper {

    private final ModelMapper modelMapper;

    public AdministrativoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AdministrativoDTO toDTO(Administrativo administrativo) {
        AdministrativoDTO dto = modelMapper.map(administrativo.getPersona(), AdministrativoDTO.class);
        dto.setCargo(administrativo.getCargo());
        dto.setDepartamento(administrativo.getDepartamento());
        return dto;
    }

    public Administrativo toEntity(AdministrativoDTO dto, Persona persona) {
        Administrativo administrativo = new Administrativo();
        administrativo.setPersona(persona);
        administrativo.setCargo(dto.getCargo());
        administrativo.setDepartamento(dto.getDepartamento());
        return administrativo;
    }
}
